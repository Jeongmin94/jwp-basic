package next.web.servlet;

import next.model.User;
import next.util.HttpUtil;
import next.web.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies())
                .filter(it -> it.getName().equals("session"))
                .findFirst();

        if(cookieOptional.isPresent()) {
            Cookie cookie = cookieOptional.get();
            cookie.setValue(null);
            cookie.setMaxAge(0);

            removeSession(cookie.getValue(), req.getSession());
            resp.addCookie(cookie);
        }

        log.debug("logout success");
        resp.sendRedirect("../index.jsp");
    }

    private void removeSession(String sessionId, HttpSession session) {
        HttpUtil.removeSession(sessionId);
        session.invalidate();
    }
}
