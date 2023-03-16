package cz.los.web.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.los.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
@WebServlet(name = "PostUserServlet", value = "/createUser")
public class PostUserServlet extends UserServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(request.getReader());
        String name = node.get("name").textValue();
        User newUser = userService.createUser(name);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write(userService.toJson(newUser));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
