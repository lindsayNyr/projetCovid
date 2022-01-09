package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.BasicNotification;
import fr.ul.projetcovid.persistence.CovidNotification;
import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.MyActivityDAO;
import fr.ul.projetcovid.persistence.dao.NotificationDAO;
import fr.ul.projetcovid.persistence.dao.UserAccountDAO;
import javaf.util.Objects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@WebServlet(name = "IAmPositivePOST", value = "/iampositive")
public class IAmPositiveServlet extends HttpServlet {
    private final UserAccountDAO accountDAO = new UserAccountDAO();
    private final MyActivityDAO activityDAO = new MyActivityDAO();
    private final NotificationDAO notifDAO = new NotificationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String myId = Objects.nonNullOrElse((String) session.getAttribute("id"), "");
        final Optional<UserAccount> maybeMyself = accountDAO.getById(myId);
        if (!maybeMyself.isPresent()) {
            response.sendError(403);
            return;
        }
        final UserAccount myself = maybeMyself.get();

        final Date minus5Days = new Date(System.currentTimeMillis() - MyActivityDAO.FIVE_DAYS);

        final List<UserAccount> myFriends = myself.getFriends();
        final List<MyActivity> myRecentActivities = activityDAO.recentActivitiesOf(myself);
        final Set<MyActivity> othersActivities = myRecentActivities.stream()
                .flatMap(act -> activityDAO.locatedAt(act.getPlace()).stream())
                .filter(act -> act.getDate().after(minus5Days))
                .collect(Collectors.toSet());
        final Set<UserAccount> concernedUsers = Stream.concat(myFriends.stream(), othersActivities.stream().map(MyActivity::getUserAccount))
                .filter(acc -> !acc.getId().equals(myself.getId()))
                .collect(Collectors.toSet());

        for (final UserAccount user : concernedUsers) {
            CovidNotification notif = new CovidNotification();
            notif.setMessage("%s a récemment été testé positif au Covid. Vous avez des chances d'être cas contact. Il serait prudent de vous faire tester et de vous isoler pendant 5 à 7 jours.");
            notif.setSource(myself);
            notif.setRecipient(user);
            notifDAO.sendNotification(notif);
        }

        response.sendRedirect(this.getServletContext().getContextPath() + "/profile.jsp");
    }
}
