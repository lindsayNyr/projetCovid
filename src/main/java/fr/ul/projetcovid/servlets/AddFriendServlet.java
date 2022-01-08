package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.FriendRequestNotification;
import fr.ul.projetcovid.persistence.Notification;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.FriendsDAO;
import fr.ul.projetcovid.persistence.dao.NotificationDAO;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AddFriendPOST", value = "/addfriend")
public class AddFriendServlet extends HttpServlet {
    private final UserAccountDAO accountDAO = new UserAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String email = Objects.nonNullOrElse(request.getParameter("emailFriend"), "");

        final HttpSession session = request.getSession();
        final String myId = (String) session.getAttribute("id");

        final Optional<UserAccount> maybeMyself = accountDAO.getById(myId);
        final Optional<UserAccount> maybeFriend = accountDAO.getByLogin(email);
        if (!maybeMyself.isPresent()) {
            response.sendError(403);
            return;
        }
        if (!maybeFriend.isPresent()) {
            request.setAttribute("error", "Adresse email introuvable");
            this.getServletContext().getRequestDispatcher("/friends.jsp").forward(request, response);
            return;
        }

        final UserAccount myself = maybeMyself.get();
        final UserAccount friend = maybeFriend.get();

        if (email.equals(myself.getLogin())) {
            request.setAttribute("error", "Impossible de s'ajouter en tant qu'ami...");
            this.getServletContext().getRequestDispatcher("/friends.jsp").forward(request, response);
            return;
        }

        FriendRequestNotification friendRequest = new FriendRequestNotification();
        friendRequest.setMessage("Vous avez reçu une demande d'ami de la part de %s.");
        friendRequest.setAuthor(myself);
        friendRequest.setRecipient(friend);
        new NotificationDAO().sendNotification(friendRequest);

        response.sendRedirect(this.getServletContext().getContextPath() + "/friends.jsp");
    }
}
