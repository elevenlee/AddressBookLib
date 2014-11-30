package edu.nyu.cs.addressbook.search.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.component.PhoneNumber;

public class RegexTest {
    private Regex regex;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        regex = new Regex("^[a-zA-Z]+ [a-zA-Z]+\\n.*$");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#Regex(java.lang.String)}.
     */
    @Test
    public void testRegexCriteriaWithLegalObject() {
        @SuppressWarnings("unused")
        Regex rc = new Regex("^.* Street .*$");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#Regex(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegexCriteriaWithNullObject() {
        @SuppressWarnings("unused")
        Regex rc = new Regex(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#Regex(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegexCriteriaWithEmptyObject() {
        @SuppressWarnings("unused")
        Regex rc = new Regex("");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNullObject() {
        assertFalse(regex.matchCondition(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNotNullObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Eleven", "Lee")
                                .phoneNumber(
                                        new PhoneNumber(123, 456, 7890)).build();
        assertTrue(regex.matchCondition(ce));
        Regex rc = new Regex("^[0-9]+$");
        assertFalse(rc.matchCondition(ce));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test(expected = PatternSyntaxException.class)
    public void testMatchConditionWithIllegalRegexObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Eleven", "Lee")
                                .phoneNumber(
                                        new PhoneNumber(123, 456, 7890)).build();
        Regex rc = new Regex("+=-*");
        rc.matchCondition(ce);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(regex.equals(regex));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(regex.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotRegexCriteriaObject() {
        Object o = new Object();
        assertFalse(regex.equals(o));
        assertFalse(o.equals(regex));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualRegexCriteriaObject() {
        Regex rc1 = new Regex("^[a-zA-Z]+ [a-zA-Z]+\\n.*$");
        Regex rc2 = new Regex("^[a-zA-Z]+ [a-zA-Z]+\\n.*$");
        assertTrue(regex.equals(rc1));
        assertTrue(rc1.equals(regex));
        assertTrue(rc1.equals(rc2));
        assertTrue(regex.equals(rc2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferRegexCriteriaObject() {
        List<Regex> rcList = Arrays.asList(
                new Regex("^[t-zA-Z]+ [a-zA-Z]+\\n"),
                new Regex("^[a-tA-Z]+ [a-zA-Z]+.*$"));
        for (Regex rc : rcList) {
            assertFalse(regex.equals(rc));
            assertFalse(rc.equals(regex));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(2068100241, regex.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Regex#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("^[a-zA-Z]+ [a-zA-Z]+\\n.*$", regex.toString());
    }

}
