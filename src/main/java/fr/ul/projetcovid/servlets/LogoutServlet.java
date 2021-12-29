package fr.ul.projetcovid.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "LogoutPOST", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cs = request.getCookies();
        Optional<Cookie> user = Arrays.stream(cs)
                .filter(c -> c.getName().equals("user"))
                .findFirst();

        if (!user.isPresent()) {
            response.sendError(403, "Unable to disconnect a non-connected user");
            return;
        }

        Cookie cookie = user.get();
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
    }
}
