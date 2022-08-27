package next.web.servlet;

import core.db.DataBase;
import next.model.User;
import next.util.HttpUtil;
import next.web.attribute.Attribute;
import next.web.parameter.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;


@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private static final String LOGIN_FAILED_PATH = "login_failed.jsp";
    private static final String LOGIN_SUCCESS_PATH = "../index.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter(Parameter.ID.getValue());
        String password = req.getParameter(Parameter.PASSWORD.getValue());

        if (userId == null || password == null) {
            log.debug("login failed - ID or PW is null");
            req.setAttribute(Attribute.MESSAGE.getValue(), "아이디나 패스워드를 입력해주세요.");
            forward(req, resp);
            return;
        }

        User user = DataBase.findUserById(userId);

        if (user == null) {
            log.debug("login failed - USER IS NULL");
            req.setAttribute(Attribute.MESSAGE.getValue(), "없는 사용자입니다. 회원가입을 진행해주세요.");
            forward(req, resp);
            return;
        }

        if (!user.checkPassword(password)) {
            log.debug("login failed - PASSWORD NOT MATCH");
            forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        UUID uuid = UUID.randomUUID();

        session.setAttribute(Attribute.USER.getValue(), user);

        HttpUtil.addSession(uuid.toString(), session);
        Cookie cookie = new Cookie("session", uuid.toString());
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect(LOGIN_SUCCESS_PATH);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(LoginServlet.LOGIN_FAILED_PATH);
        rd.forward(req, resp);
    }
}
