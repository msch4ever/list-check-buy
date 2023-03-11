package cz.los.web.user;

import cz.los.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import static cz.los.service.UserService.USER_SERVICE;

public abstract class UserServlet extends HttpServlet {

    protected UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute(USER_SERVICE);
    }

}
