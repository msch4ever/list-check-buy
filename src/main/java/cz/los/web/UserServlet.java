package cz.los.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.los.model.User;
import cz.los.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static cz.los.service.UserService.USER_SERVICE;

public class UserServlet extends HttpServlet {

    @NoArgsConstructor
    @WebServlet(name = "GetUserServlet", value = "/users/*")
    public static class GetUserServlet extends HttpServlet {
        private UserService userService;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            ServletContext servletContext = config.getServletContext();
            userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String pathInfo = request.getPathInfo();
            boolean listPattern = pathInfo == null || pathInfo.equals("/");
            if (listPattern) {
                listUsers(response);
                return;
            }
            boolean getByIdPattern = pathInfo.matches("/\\d+");
            if (getByIdPattern) {
                getUser(response, pathInfo);
                return;
            }
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong get operation on user");
        }

        private void listUsers(HttpServletResponse response) throws IOException {
            List<String> users = userService.getAllUsers();
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write("{\"users\":[");
            String combinedUsers = String.join(",", users);
            writer.write(combinedUsers);
            writer.write("]}");
        }

        private void getUser(HttpServletResponse response, String pathInfo) throws IOException {
            long id = Long.parseLong(pathInfo.substring(1));
            Optional<String> user = userService.getUserById(id);
            if (user.isPresent()) {
                response.setContentType("application/json");
                response.getWriter().write(user.get());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @NoArgsConstructor
    @WebServlet(name = "PostUserServlet", value = "/createUser")
    public static class PostUserServlet extends HttpServlet {

        private UserService userService;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            ServletContext servletContext = config.getServletContext();
            userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        }

        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(request.getReader());
            userService.createUser(jsonNode.get("name").textValue());
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
    }
}
