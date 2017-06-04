package ch.halcyon.onthisday.api;

import ch.halcyon.onthisday.api.model.Export;
import ch.halcyon.onthisday.api.model.Happening;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by yannick on 04/06/2017.
 */
class OnThisDayAPITest {

    private static Export testExport;

    @BeforeAll
    static void setUp() {
        testExport = OnThisDayAPI.getHappeningsExport(11, 11);
    }

    @Test
    void testBirths() {
        assertEquals(189, testExport.getBirths().size(), "births not equal");
    }

    @Test
    void testDeaths() {
        assertEquals(106, testExport.getDeaths().size(), "deaths not equal");
    }

    @Test
    void testEvents() {
        assertEquals(57, testExport.getEvents().size(), "events not equal");

    }

}