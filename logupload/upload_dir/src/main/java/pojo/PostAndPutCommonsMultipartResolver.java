package pojo;

import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class PostAndPutCommonsMultipartResolver extends CommonsMultipartResolver {
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";

    @Override
    public boolean isMultipart(HttpServletRequest request) {

        boolean isMultipartRequest = false;

        if (request != null) {
            HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());

            if (Objects.equals(HttpMethod.POST,httpMethod) || Objects.equals(HttpMethod.PUT,httpMethod)) {
                String contentType = request.getContentType();

                isMultipartRequest = (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
            }
        }
        return isMultipartRequest;
    }

}
