package cz.los.web;

import cz.los.dal.UserDao;
import cz.los.dal.UserDaoInMem;
import cz.los.persistance.DB;
import cz.los.service.UserService;
import cz.los.service.UserServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        long start = System.currentTimeMillis();
        printLogo();
        ServletContext servletContext = sce.getServletContext();

        DB db = new DB();
        servletContext.setAttribute(DB.DB, db);
        log.info("DB initialized..");

        UserDao userDao = new UserDaoInMem(db);
        servletContext.setAttribute(UserDao.USER_DAO, userDao);
        log.info("Dao Layer initialized..");

        UserService userService = new UserServiceImpl(userDao);
        servletContext.setAttribute(UserService.USER_SERVICE, userService);
        log.info("Service Layer initialized..");

        prepopulate(servletContext);

        log.info("Application initialization finished in:{} mils.", System.currentTimeMillis() - start);
    }

    private void prepopulate(ServletContext servletContext) {
        log.info("Prepopulating app...");
        UserService userService = (UserService) servletContext.getAttribute(UserService.USER_SERVICE);

        userService.createUser("Root");
        log.info("App prepopulated...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Shutting down...");
    }

    private static void printLogo() {
        String logo =
                "    __    _      __        ________              __         ____             \n" +
                        "   / /   (_)____/ /_      / ____/ /_  ___  _____/ /__      / __ )__  ____  __\n" +
                        "  / /   / / ___/ __/_____/ /   / __ \\/ _ \\/ ___/ //_/_____/ __  / / / / / / /\n" +
                        " / /___/ (__  ) /_/_____/ /___/ / / /  __/ /__/ ,< /_____/ /_/ / /_/ / /_/ / \n" +
                        "/_____/_/____/\\__/      \\____/_/ /_/\\___/\\___/_/|_|     /_____/\\__,_/\\__, /  \n" +
                        "                                                                    /____/   ";
        log.info("Initializing app...\n{}", logo);
    }

}
