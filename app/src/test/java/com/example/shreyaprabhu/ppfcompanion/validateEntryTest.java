package com.example.shreyaprabhu.ppfcompanion;

import com.example.shreyaprabhu.ppfcompanion.Utils.ValidateEntry;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by HP on 24-01-2017.
 */

public class validateEntryTest {

    private ValidateEntry validateEntry;

    @Before
    public void init() {
        validateEntry = new ValidateEntry();
    }

    @Test
    public void testDateIsNull() {
        assertFalse(validateEntry.isDateValid(0,0,0));
    }

    @Test
    public void testDayIsInvalid() {
        assertFalse(validateEntry.isDateValid(32,2,2012));
    }

    @Test
    public void testMonthIsInvalid() {
        assertFalse(validateEntry.isDateValid(31,20,2012));
    }

    @Test
    public void testYearIsInvalid() {
        assertFalse(validateEntry.isDateValid(31,20,19991));
    }

    @Test
    public void testDateFormatIsInvalid() {
        assertFalse(validateEntry.isDateValid(2012,2,20));
    }

    @Test
    public void testDateFeb29_2012() {
        assertTrue(validateEntry.isDateValid(29,2,2012));
    }

    @Test
    public void testDateFeb29_2011() {
        assertFalse(validateEntry.isDateValid(29,2,2011));
    }

    @Test
    public void testDateFeb28() {
        assertTrue(validateEntry.isDateValid(28,2,2011));
    }

    @Test
    public void testDateIsValid_1() {
        assertTrue(validateEntry.isDateValid(31,1,2012));
    }

    @Test
    public void testDateIsValid_2() {
        assertTrue(validateEntry.isDateValid(30,4,2012));
    }

    @Test
    public void testDateIsValid_3() {
        assertTrue(validateEntry.isDateValid(31,5,2012));
    }

}
