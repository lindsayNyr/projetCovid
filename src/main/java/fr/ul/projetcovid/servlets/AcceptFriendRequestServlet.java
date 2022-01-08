package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.BasicNotification;
import fr.ul.projetcovid.persistence.FriendRequestNotification;
import fr.ul.projetcovid.persistence.Notification;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.FriendsDAO;
import fr.ul.projetcovid.persistence.dao.NotificationDAO;
import javaf.util.Objects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AcceptFriendRequestPOST", value = "/acceptfr")
public class AcceptFriendRequestServlet extends HttpServlet {
    private final NotificationDAO dao = new NotificationDAO();
    private final FriendsDAO friendsDAO = new FriendsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // id : author id
        // status : "accept" | "deny"

        final String id = Objects.nonNullOrElse(request.getParameter("id"), "");
        final String status = Objects.nonNullOrElse(request.getParameter("status"), "");

        Long notificationId;
        try {
            notificationId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            response.sendError(403);
            return;
        }

        final Optional<Notification> maybeNotification = dao.getNotificationById(notificationId);
        if (!maybeNotification.isPresent()) {
            response.sendError(400);
            return;
        }
        final Notification notification = maybeNotification.get();
        if (notification.getType() != Notification.NotificationType.FRIEND_REQUEST) {
            response.getWriter().println("Invalid notification [NOT FRIEND REQUEST]");
            response.sendError(400);
            return;
        }

        final FriendRequestNotification notificationFR = (FriendRequestNotification) notification;

        dao.markAccepted(notificationFR);
        dao.markRead(notificationFR);

        if ("accept".equals(status)) {
            final BasicNotification msg = new BasicNotification();
            msg.setAuthor(notificationFR.getRecipient());
            msg.setMessage("%s est maintenant votre ami(e).");
            msg.setRecipient(notificationFR.getAuthor());
            dao.sendNotification(msg);

            final BasicNotification msg1 = new BasicNotification();
            msg1.setAuthor(notificationFR.getAuthor());
            msg1.setMessage("%s est maintenant votre ami(e).");
            msg1.setRecipient(notificationFR.getRecipient());
            dao.sendNotification(msg1);

            friendsDAO.addFriendTo(notificationFR.getAuthor(), notificationFR.getRecipient());
        }

        response.sendRedirect(this.getServletContext().getContextPath() + "/notif.jsp");
    }
}
