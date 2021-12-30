package fr.ul.projetcovid.servlets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginPOST", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserAccountDAO dao = new UserAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parameters
        // - email
        // - password

        final String email = Objects.nonNullOrElse(request.getParameter("email"), "");
        final String password = Objects.nonNullOrElse(request.getParameter("password"), "");

        Optional<UserAccount> account = dao.getByLogin(email);
        if (!account.isPresent() || password.isEmpty()) {
            request.setAttribute("error", "Email ou mot de passe invalide");
            getServletContext().getRequestDispatcher("/logged.jsp").forward(request, response);
            return;
        }

        UserAccount acc = account.get();

        Argon2 argon2id = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (!argon2id.verify(acc.getPassword(), password.toCharArray())) {
            request.setAttribute("error", "Email ou mot de passe invalide");
            getServletContext().getRequestDispatcher("/logged.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", acc.getId());

        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
    }
}
