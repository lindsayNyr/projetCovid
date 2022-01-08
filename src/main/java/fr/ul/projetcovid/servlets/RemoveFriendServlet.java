package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.BasicNotification;
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

@WebServlet(name = "RemoveFriendGET", value = "/removefriend")
public class RemoveFriendServlet extends HttpServlet {
    private final UserAccountDAO userDAO = new UserAccountDAO();
    private final FriendsDAO friendsDAO = new FriendsDAO();
    private final NotificationDAO notifDAO = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // id - account id

        final HttpSession session = request.getSession();
        final String myId = Objects.nonNullOrElse((String) session.getAttribute("id"), "");
        final Optional<UserAccount> maybeMyself = userDAO.getById(myId);
        if (!maybeMyself.isPresent()) {
            response.sendError(403);
            return;
        }
        final UserAccount myself = maybeMyself.get();

        final String friendId = Objects.nonNullOrElse(request.getParameter("id"), "");
        final Optional<UserAccount> maybeFriend = userDAO.getById(friendId);
        if (!maybeFriend.isPresent()) {
            response.sendError(400);
            return;
        }
        final UserAccount friend = maybeFriend.get();

        friendsDAO.removeFriend(myself, friend);

        BasicNotification notif = new BasicNotification();
        notif.setAuthor(myself);
        notif.setRecipient(friend);
        notif.setMessage("Vous n'êtes plus ami avec %s.");
        notifDAO.sendNotification(notif);
        BasicNotification notif1 = new BasicNotification();
        notif1.setAuthor(friend);
        notif1.setRecipient(myself);
        notif1.setMessage("Vous n'êtes plus ami avec %s.");
        notifDAO.sendNotification(notif1);

        response.sendRedirect(this.getServletContext().getContextPath() + "/friends.jsp");
    }
}
