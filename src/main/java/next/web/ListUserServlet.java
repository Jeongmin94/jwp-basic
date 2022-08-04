package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import next.web.attribute.Attribute;

@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        boolean isLogin = false;
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                isLogin = checkIsLoginCookie(cookie);
            }
        }

        if(!isLogin) {
            req.setAttribute(Attribute.MESSAGE.getValue(), "로그인을 하고 이용해주세요.");
            RequestDispatcher rd = req.getRequestDispatcher("login_failed.jsp");
            rd.forward(req, resp);
        }

        req.setAttribute("users", DataBase.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

    private boolean checkIsLoginCookie(Cookie cookie) {
        String cookieName = cookie.getName();

        if(!cookieName.equals("isLogin")) {
            return false;
        }

        return cookie.getValue().equals("true");
    }
}
