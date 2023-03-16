package cz.los.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@NoArgsConstructor
@WebServlet(name = "GetUserServlet", value = "/users")
public class GetUserServlet extends UserServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && idParam.chars().allMatch(Character::isDigit)) {
            long userId = Long.parseLong(idParam);
            Optional<String> user = userService.getUserById(userId);
            if (user.isPresent()) {
                response.setContentType("application/json");
                response.getWriter().write(user.get());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong get operation on user");
    }
}
