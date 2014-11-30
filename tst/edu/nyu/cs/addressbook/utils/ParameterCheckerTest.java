package edu.nyu.cs.addressbook.utils;

import org.junit.Test;

public class ParameterCheckerTest {

    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#rangeCheck(int, int, java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRangeCheckWithNegative() {
        ParameterChecker.rangeCheck(-5, 100, "Nagative value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#rangeCheck(int, int, java.lang.String)}.
     */
    @Test
    public void testRangeCheckWithZero() {
        ParameterChecker.rangeCheck(0, 100, "Zero value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#rangeCheck(int, int, java.lang.String)}.
     */
    @Test
    public void testRangeCheckWithPositiveInRange() {
        ParameterChecker.rangeCheck(5, 100, "Positive in range value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#rangeCheck(int, int, java.lang.String)}.
     */
    @Test
    public void testRangeCheckWithPositiveOnRange() {
        ParameterChecker.rangeCheck(100, 100, "Positive on range value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#rangeCheck(int, int, java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRangeCheckWithPositiveOutOfRange() {
        ParameterChecker.rangeCheck(200, 100, "Positive out of range value");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#nullCheck(java.lang.Object, java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testNullCheckWithNull() {
        ParameterChecker.nullCheck(null, "Null value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#nullCheck(java.lang.Object, java.lang.String)}.
     */
    @Test
    public void testNullCheckWithNotNull() {
        ParameterChecker.nullCheck(new Object(), "Not null value");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCheckWithEmptyString() {
        ParameterChecker.emptyCheck("", "Empty value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEmptyCheckWithNullString() {
        ParameterChecker.emptyCheck(null, "Null value");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEmptyCheckWithNotEmptyString() {
        ParameterChecker.emptyCheck("Test", "Not empty value");
    }

}
