package fr.ul.projetcovid.servlets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;
import org.apache.commons.text.StringEscapeUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

@WebServlet(name = "EditUserPOST", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    private final UserAccountDAO dao = new UserAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Request takes:
        // - lastname
        // - firstname
        // - email
        // - password
        // - password-confirm
        // - birthdate

        final String lastname = Objects.nonNullOrElse(request.getParameter("lastname"), "");
        final String firstname = Objects.nonNullOrElse(request.getParameter("firstname"), "");
        final String email = Objects.nonNullOrElse(request.getParameter("email"), "");
        final String password = Objects.nonNullOrElse(request.getParameter("password"), "");
        final String passwordConfirm = Objects.nonNullOrElse(request.getParameter("password-confirm"), "");
        final String birthdate = Objects.nonNullOrElse(request.getParameter("birthdate"), "");
        final String userId   = Objects.nonNullOrElse(request.getParameter("idUser"), "");



        if (userId.equals("")) {
            response.sendError(403);
            return;
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        UserAccount account = dao.getById(userId).get();


        final String oldLogin = account.getLogin();

        account.setNom(lastname);
        account.setPrenom(firstname);
        account.setLogin(email);
        try {
            account.setNaissance(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
        } catch (ParseException e) {
            request.setAttribute("error", "Date invalide???");
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            return;
        }

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<UserAccount>> violations = validator.validate(account);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<UserAccount> violation : violations)
                sb.append(violation.getMessage()).append("\n");
            request.setAttribute("error", sb.toString());
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            return;
        }

        if (!password.isEmpty() && !passwordConfirm.isEmpty()) {
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("error", "La confirmation du mot de passe ne correspond pas au mot de passe");
                getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                return;
            }

            Argon2 argon2id = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            try {
                String hash = argon2id.hash(10, 65536, 1, password.toCharArray());
                account.setPassword(hash);
            } finally {
                argon2id.wipeArray(password.toCharArray());
            }
        }

        if (!oldLogin.equals(email) && dao.getByLogin(account.getLogin()).isPresent()) {
            request.setAttribute("error",  "Un utilisateur existe déjà avec cet email");
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            return;
        }

        dao.update(account);
      /*  if (!oldLogin.equals(email)) {
            session.removeAttribute("id");
        }*/

        response.sendRedirect(getServletContext().getContextPath() + "/user.jsp");
    }
}