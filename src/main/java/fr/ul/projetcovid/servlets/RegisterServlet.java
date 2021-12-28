package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String resultat = "" ;
        String name = request.getParameter("nameRegister");


        if(name.equals("")){
            request.setAttribute("error", resultat);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }



        response.getWriter().println("register.jsp");
    }
}
