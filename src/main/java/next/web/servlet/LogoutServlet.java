package next.web.servlet;

import next.model.User;
import next.web.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies())
                .filter(it -> it.getName().equals("isLogin"))
                .findFirst();

        if(cookieOptional.isPresent()) {
            Cookie cookie = cookieOptional.get();
            cookie.setValue(null);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        req.getSession().removeAttribute(Attribute.USER.getValue());

        log.debug("logout success");
        resp.sendRedirect("../index.jsp");
    }
}
