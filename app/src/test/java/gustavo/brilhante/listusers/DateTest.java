package gustavo.brilhante.listusers;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import gustavo.brilhante.listusers.utils.DateUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Gustavo on 22/05/17.
 */

public class DateTest {

    @Test
    public void ageCalculatorTest() throws Exception {

        String dateStr = "1991-08-20 13:20:49";

        String result = DateUtils.getAgeFromDate(dateStr);

        assertTrue(result.equals("25"));

    }

    @Test
    public void dateFormat() throws Exception {

        String dateStr = "1991-08-20 13:20:49";

        String result = DateUtils.getFormattedDateStr(dateStr);

        assertEquals(result, "20/08/1991");

    }

    @Test
    public void cacheTest() throws Exception {

        Date savedTime1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Thread.sleep(200);
        boolean timeNotExpired = DateUtils.checkDifferenceTimeInMinutes(dateFormat.format(savedTime1), 1);
        assertFalse(timeNotExpired);

        Date savedTime2 = new Date();
        Thread.sleep(60000);
        boolean timeExpired = DateUtils.checkDifferenceTimeInMinutes(dateFormat.format(savedTime2), 1);
        assertTrue(timeExpired);


        Thread.sleep(200);
        timeExpired = DateUtils.checkDifferenceTimeInMinutes(dateFormat.format(savedTime2), 1);
        assertTrue(timeExpired);

    }
}
