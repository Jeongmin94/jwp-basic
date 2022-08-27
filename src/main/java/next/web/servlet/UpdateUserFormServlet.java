package next.web.servlet;

import core.db.DataBase;
import next.model.User;
import next.util.HttpUtil;
import next.web.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static next.util.HttpUtil.checkIsLoginCookie;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    private static final String LOGIN_FAILED_PATH = "login_failed.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session == null) {
            log.error("session is null");
            req.setAttribute(Attribute.MESSAGE.getValue(), "로그인을 먼저 해주세요.");
            RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FAILED_PATH);
            rd.forward(req, resp);
            return;
        }

        User user = (User) session.getAttribute("user");
        if(user == null) {
            log.error("user is null");
            req.setAttribute(Attribute.MESSAGE.getValue(), "로그인을 먼저 해주세요.");
            RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FAILED_PATH);
            rd.forward(req, resp);
            return;
        }

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            req.setAttribute(Attribute.MESSAGE.getValue(), "존재하지 않는 아이디입니다. 아이디를 올바르게 입력해주세요.");
            RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
            rd.forward(req, resp);
            return;
        }

        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");

        user.updateUser(userId, password, name, email);

        log.debug("update user info success");
        resp.sendRedirect("/index.jsp");
    }
}
