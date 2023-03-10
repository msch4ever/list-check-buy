package cz.los.web;

import cz.los.dal.BucketDao;
import cz.los.dal.ItemDao;
import cz.los.dal.UserDao;
import cz.los.dal.mem.BucketDaoInMem;
import cz.los.dal.mem.ItemDaoInMem;
import cz.los.dal.mem.UserDaoInMem;
import cz.los.model.Bucket;
import cz.los.model.Item;
import cz.los.persistance.DB;
import cz.los.service.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static cz.los.dal.BucketDao.BUCKET_DAO;
import static cz.los.dal.ItemDao.ITEM_DAO;
import static cz.los.dal.UserDao.USER_DAO;
import static cz.los.service.BucketService.BUCKET_SERVICE;
import static cz.los.service.ItemService.ITEM_SERVICE;
import static cz.los.service.UserService.USER_SERVICE;

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

        initDao(servletContext, db);
        initService(servletContext);

        prepopulate(servletContext);

        log.info("Application initialization finished in:{} mils.", System.currentTimeMillis() - start);
    }

    private void initDao(ServletContext servletContext, DB db) {
        log.info("initializing Dao Layer..");
        UserDao userDao = new UserDaoInMem(db);
        ItemDao itemDao = new ItemDaoInMem(db);
        BucketDao bucketDao = new BucketDaoInMem(db);
        servletContext.setAttribute(USER_DAO, userDao);
        servletContext.setAttribute(ITEM_DAO, itemDao);
        servletContext.setAttribute(BUCKET_DAO, bucketDao);
        log.info("Dao Layer initialized..");
    }

    private void initService(ServletContext servletContext) {
        log.info("initializing Service Layer..");
        UserService userService = new UserServiceImpl((UserDao) servletContext.getAttribute(USER_DAO));
        ItemService itemService = new ItemServiceImpl((ItemDao) servletContext.getAttribute(ITEM_DAO));
        BucketService bucketService = new BucketServiceImpl((BucketDao) servletContext.getAttribute(BUCKET_DAO));
        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(ITEM_SERVICE, itemService);
        servletContext.setAttribute(BUCKET_SERVICE, bucketService);
        log.info("Service Layer initialized..");
    }

    private void prepopulate(ServletContext servletContext) {
        log.info("Prepopulating app...");
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        ItemService itemService = (ItemService) servletContext.getAttribute(ITEM_SERVICE);
        BucketService bucketService = (BucketService) servletContext.getAttribute(BUCKET_SERVICE);

        long userId = userService.createUser("Root").getId();
        Bucket dm = bucketService.createBucket("DM", userId);

        Item toothPaste = itemService.createItem("Tooth paste", null, 1, dm.getId());
        Item toiletPaper = itemService.createItem("Toilet paper", null, 6, dm.getId());
        Item lipsStick = itemService.createItem("Lips stick", "Oriflame, rose", 1, dm.getId());

        dm.addItems(List.of(toothPaste, lipsStick, toiletPaper));
        bucketService.updateBucket(dm);

        Bucket albert = bucketService.createBucket("Albert", userId);

        Item tomatoes = itemService.createItem("Tomatoes", "Cherry", 2, albert.getId());
        Item paper = itemService.createItem("Paprika", "Red", 5, albert.getId());
        Item chickenBrest = itemService.createItem("Chicken Brest", null, 4, albert.getId());

        albert.addItems(List.of(tomatoes, paper, chickenBrest));
        bucketService.updateBucket(albert);
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
