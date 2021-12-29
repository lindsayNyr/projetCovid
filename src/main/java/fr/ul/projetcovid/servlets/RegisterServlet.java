package fr.ul.projetcovid.servlets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

@WebServlet(value = "/register")
public final class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Request takes:
        // - lastname
        // - firstname
        // - email
        // - password
        // - password-confirm

        final String lastname = Objects.nonNullOrElse(request.getParameter("lastname"), "");
        final String firstname = Objects.nonNullOrElse(request.getParameter("firstname"), "");
        final String email = Objects.nonNullOrElse(request.getParameter("email"), "");
        final String password = Objects.nonNullOrElse(request.getParameter("password"), "");
        final String passwordConfirm = Objects.nonNullOrElse(request.getParameter("password-confirm"), "");

        final UserAccount account = new UserAccount();
        account.setLogin(email);
        account.setNom(lastname);
        account.setPrenom(firstname);
        account.setPassword(password);
        account.setNaissance(new Date());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<UserAccount>> violations = validator.validate(account);
        if (!violations.isEmpty()) {
            final JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("error", "validation_failed");
            final JsonObjectBuilder builder2 = Json.createObjectBuilder();
            for (final ConstraintViolation<UserAccount> violation : violations)
                builder2.add(violation.getPropertyPath().toString(), violation.getMessage());
            builder.add("messages", builder2.build());
            response.getWriter().println(builder.build().toString());
            return;
        }

        if (!password.equals(passwordConfirm)) {
            final JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("error", "password_confirmation_failed");
            builder.add("messages", Json.createObjectBuilder().add("password-confirm", "La confirmation du mot de passe ne correspond pas au mot de passe"));
            response.getWriter().println(builder.build().toString());
            return;
        }

        Argon2 argon2id = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        try {
            String hash = argon2id.hash(10, 65536, 1, password.toCharArray());
            account.setPassword(hash);
        } finally {
            argon2id.wipeArray(password.toCharArray());
        }

        UserAccountDAO dao = new UserAccountDAO();
        if (dao.get(account.getLogin()).isPresent()) {
            final JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("error", "already_exists");
            builder.add("messages", Json.createObjectBuilder().add("email", "Un utilisateur existe déjà avec cet email"));
            response.getWriter().println(builder.build().toString());
            return;
        }

        dao.save(account);

        response.getWriter().println(Json.createObjectBuilder().build().toString());
    }
}
