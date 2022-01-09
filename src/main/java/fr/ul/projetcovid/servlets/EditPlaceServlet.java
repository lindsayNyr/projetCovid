package fr.ul.projetcovid.servlets;


import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.UserAccount;
import fr.ul.projetcovid.persistence.dao.PlaceDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;


@WebServlet(name = "editPlace", value = "/editPlace")
public class EditPlaceServlet extends HttpServlet {


    private final PlaceDAO placeDAO = new PlaceDAO();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        final String idPlace = Objects.nonNullOrElse(request.getParameter("idPlace"), "");
        final String city = Objects.nonNullOrElse(request.getParameter("ville"), "");
        final String codePostal = Objects.nonNullOrElse(request.getParameter("cp"), "");
        final String adresse = Objects.nonNullOrElse(request.getParameter("adresse"), "");
        final String name = Objects.nonNullOrElse(request.getParameter("namePlace"), "");

        if(idPlace.equals("")) {
            request.setAttribute("error", "Le lieux n'existe pas");
            this.getServletContext().getRequestDispatcher("/place.jsp").forward(request, response);
            return;
        }


        Place place = placeDAO.getById(idPlace);

        if(place == null) {
            request.setAttribute("error", "le lieux n'existe pas");
            getServletContext().getRequestDispatcher("/editPlace.jsp?idPlace="+ idPlace).forward(request, response);
            return;
        }

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<Place>> violations = validator.validate(place);
        place.setName(name);
        place.setCity(city);
        place.setCodePostal(codePostal);
        place.setAdresse(adresse);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<Place> violation : violations)
                sb.append(violation.getMessage()).append("\n");
            request.setAttribute("error", sb.toString());
            getServletContext().getRequestDispatcher("/editPlace.jsp?idPlace="+ idPlace).forward(request, response);
            return;
        }


        placeDAO.update(place);
        response.sendRedirect(getServletContext().getContextPath() + "/place.jsp");

    }

}
