package cz.los.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@NoArgsConstructor
@WebServlet(name = "ListUserServlet", value = "/users/list")
public class ListUserServlet extends UserServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> users = userService.getAllUsers();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"users\":[");
        String combinedUsers = String.join(",", users);
        writer.write(combinedUsers);
        writer.write("]}");
    }
}
