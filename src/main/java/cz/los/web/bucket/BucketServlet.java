package cz.los.web.bucket;

import cz.los.service.BucketService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import static cz.los.service.BucketService.BUCKET_SERVICE;

public abstract class BucketServlet extends HttpServlet {

    protected BucketService bucketService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        bucketService = (BucketService) servletContext.getAttribute(BUCKET_SERVICE);
    }

}
