package next.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private HttpUtil() {}

    public static final Map<String, HttpSession> httpSessions = new HashMap<>();

    public static boolean checkIsLoginCookie(Cookie cookie) {
        String cookieName = cookie.getName();

        if(!cookieName.equals("isLogin")) {
            return false;
        }

        return cookie.getValue().equals("true");
    }

    public static void addSession(String sessionId, HttpSession httpSession) {
        httpSessions.put(sessionId, httpSession);
    }

    public static HttpSession getSession(String sessionId) {
        return httpSessions.get(sessionId);
    }

    public static void removeSession(String sessionId) {
        httpSessions.remove(sessionId);
    }
}
