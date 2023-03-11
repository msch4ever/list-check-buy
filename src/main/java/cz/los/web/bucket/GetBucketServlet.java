package cz.los.web.bucket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@WebServlet(name = "GetBucketServlet", value = "/bucket")
public class GetBucketServlet extends BucketServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        String bucketIdParam = request.getParameter("bucketId");
        if (userIdParam != null && userIdParam.chars().allMatch(Character::isDigit)) {
            getByUserId(response, userIdParam);
            return;
        }
        if (bucketIdParam != null && bucketIdParam.chars().allMatch(Character::isDigit)) {
            getByBucketId(response, bucketIdParam);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong get operation on user");
    }

    private void getByUserId(HttpServletResponse response, String userIdParam) throws IOException {
        long userId = Long.parseLong(userIdParam);
        List<String> buckets = bucketService.getAllBucketsByUserId(userId);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"buckets\":[");
        String combinedBuckets = String.join(",", buckets);
        writer.write(combinedBuckets);
        writer.write("]}");
    }

    private void getByBucketId(HttpServletResponse response, String bucketIdParam) throws IOException {
        long bucketId = Long.parseLong(bucketIdParam);
        Optional<String> bucket = bucketService.getBucketById(bucketId);
        if (bucket.isPresent()) {
            response.setContentType("application/json");
            response.getWriter().write(bucket.get());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
