package next.util;

import javax.servlet.http.Cookie;

public class HttpUtil {

    private HttpUtil() {}

    public static boolean checkIsLoginCookie(Cookie cookie) {
        String cookieName = cookie.getName();

        if(!cookieName.equals("isLogin")) {
            return false;
        }

        return cookie.getValue().equals("true");
    }
}
