package next.web.session;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HttpSessionTest {
    private static final Logger log = LoggerFactory.getLogger(HttpSessionTest.class);

    private final HttpSession httpSession = new HttpSession();

    @Test
    public void invalidateTest() {
        String testId = "123456";
        String name1 = "name1";
        String name2 = "name2";
        List<String> value1 = Collections.singletonList("hello world");
        IntStream value2 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        httpSession.setAttribute(name1, value1);
        httpSession.setAttribute(name2, value2);
        httpSession.setId(testId);

        assertThat(httpSession.getId(), is(testId));
        assertThat(httpSession.getAttributesSize(), is(2));

        httpSession.invalidate();

        assertNull(httpSession.getId());
        assertThat(httpSession.getAttributesSize(), is(0));
    }

    @Test
    public void setAndGetIdTest() {
        String testId = "123456";

        httpSession.setId(testId);

        assertThat(httpSession.getId(), is(testId));
    }

    @Test
    public void attributesTest() {
        String name1 = "name1";
        String name2 = "name2";
        List<String> value1 = Collections.singletonList("hello world");
        IntStream value2 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        httpSession.setAttribute(name1, value1);
        httpSession.setAttribute(name2, value2);

        assertThat(httpSession.getAttribute(name1), is(value1));
        assertThat(httpSession.getAttribute(name2), is(value2));

        httpSession.removeAttribute(name1);
        httpSession.removeAttribute(name2);
        assertThat(httpSession.getAttributesSize(), is(0));
    }
}