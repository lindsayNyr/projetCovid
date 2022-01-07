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

@WebServlet(name = "DeleteUser", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final UserAccountDAO dao = new UserAccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Request takes:
        // - lastname
        // - firstname
        // - email
        // - password
        // - password-confirm
        // - birthdate



        final String userId   = Objects.nonNullOrElse(request.getParameter("idUser"), "");
        System.out.println(userId);


        if (userId.equals("")) {
            response.sendError(403);
            return;
        }


        @SuppressWarnings("OptionalGetWithoutIsPresent")
        UserAccount account = dao.getById(userId).get();
        dao.remove(account);


        response.sendRedirect(getServletContext().getContextPath() + "/user.jsp");
    }
}
