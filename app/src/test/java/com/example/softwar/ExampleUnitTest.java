pacctly answer you question. You would be able to attain the behavior you want by copying your current layout xml file into a new folder titled "layout-land" and changing the necessary attributes of the LinearLayout. This is the folder that will be used by Android to load your layout files if the device is in landscape mode. If it cannot find a given xml file in the "layout-land" folder then it will default to the "layout" folder.kage com.example.softwar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}