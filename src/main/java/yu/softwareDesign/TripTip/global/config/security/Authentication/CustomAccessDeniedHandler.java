package yu.softwareDesign.TripTip.global.config.security.Authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    private String errorPage;
//    public void setErrorPage(String errorPage) {
//        this.errorPage = errorPage;
//    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String accessDeniedUrl = (String) request.getSession().getAttribute("accessDeniedUrl");
        if (accessDeniedUrl != null) {
            log.info("accessDeniedUrl : {}", accessDeniedUrl);
            log.info("accessDeniedException : {}", accessDeniedException.getMessage());
            log.info("request method : {}", request.getMethod());

//            response.sendRedirect(accessDeniedUrl + "?exception=" + accessDeniedException.getMessage()); // 저장된 API 주소로 리다이렉트
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter writer = response.getWriter();
            writer.write("{\"error\": \"" + accessDeniedException.getMessage() + "\"}");
            writer.flush();

        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }

//        String deniedUrl = errorPage + "?exception=" + accessDeniedException.getMessage();
//        response.sendRedirect(deniedUrl);
    }
}
