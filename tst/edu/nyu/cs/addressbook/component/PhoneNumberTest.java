package edu.nyu.cs.addressbook.component;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PhoneNumberTest {
    private PhoneNumber phoneNumber;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        phoneNumber = new PhoneNumber(646, 620, 5666);
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#PhoneNumber(int, int, int)}.
     */
    @Test
    public void testPhoneNumberWithLegalArgument() {
        new PhoneNumber(123, 456, 7890);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#PhoneNumber(int, int, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPhoneNumberWithIllegalAreaCode() {
        new PhoneNumber(1234, 456, 7890);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#PhoneNumber(int, int, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPhoneNumberWithIllegalPrefix() {
        new PhoneNumber(123, 4567, 7890);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#PhoneNumber(int, int, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPhoneNumberWithIllegalLineNumber() {
        new PhoneNumber(123, 456, 78900);
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#getAreaCode()}.
     */
    @Test
    public void testGetAreaCode() {
        assertEquals(646, phoneNumber.getAreaCode());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#getPrefix()}.
     */
    @Test
    public void testGetPrefix() {
        assertEquals(620, phoneNumber.getPrefix());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#getLineNumber()}.
     */
    @Test
    public void testGetLineNumber() {
        assertEquals(5666, phoneNumber.getLineNumber());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(phoneNumber.equals(phoneNumber));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(phoneNumber.equals(null));
    }
    
    /**
     * Test method for {@link edu.edu.nyu.cs.addressbook.component.PhoneNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotPhoneNumberObject() {
        Object o = new Object();
        assertFalse(phoneNumber.equals(o));
        assertFalse(o.equals(phoneNumber));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualPhoneNumberObject() {
        PhoneNumber pn1 = new PhoneNumber(646, 620, 5666);
        PhoneNumber pn2 = new PhoneNumber(646, 620, 5666);
        assertTrue(phoneNumber.equals(pn1));
        assertTrue(pn1.equals(phoneNumber));
        assertTrue(pn1.equals(pn2));
        assertTrue(phoneNumber.equals(pn2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferPhoneNumberObject() {
        List<PhoneNumber> phoneNumberList = Arrays.asList(
                new PhoneNumber(347, 620, 5666),
                new PhoneNumber(646, 123, 5666),
                new PhoneNumber(646, 620, 1111));
        for (PhoneNumber pn : phoneNumberList) {
            assertFalse(phoneNumber.equals(pn));
            assertFalse(pn.equals(phoneNumber));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(1152139, phoneNumber.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("(646) 620-5666", phoneNumber.toString());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#clone()}.
     */
    @Test
    public void testClone() {
        PhoneNumber pn = phoneNumber.clone();
        assertTrue(phoneNumber.equals(pn));
        assertTrue(pn.equals(phoneNumber));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#compareTo(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test(expected = NullPointerException.class)
    public void testCompareToWithNullObject() {
        phoneNumber.compareTo(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PhoneNumber#compareTo(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test
    public void testCompareToWithNotNullObject() {
        List<PhoneNumber> phoneNumberLessList = Arrays.asList(
                new PhoneNumber(123, 620, 5666),
                new PhoneNumber(646, 123, 5666),
                new PhoneNumber(646, 620, 1234));
        List<PhoneNumber> phoneNumberGreaterList = Arrays.asList(
                new PhoneNumber(789, 620, 5666),
                new PhoneNumber(646, 678, 5666),
                new PhoneNumber(646, 620, 7890));
        PhoneNumber phoneNumberEqual = new PhoneNumber(646, 620, 5666);
        for (PhoneNumber pn : phoneNumberLessList) {
            assertTrue(phoneNumber.compareTo(pn) > 0);
            assertTrue(pn.compareTo(phoneNumber) < 0);
        }
        for (PhoneNumber pn : phoneNumberGreaterList) {
            assertTrue(phoneNumber.compareTo(pn) < 0);
            assertTrue(pn.compareTo(phoneNumber) > 0);
        }
        assertTrue(phoneNumber.compareTo(phoneNumberEqual) == 0);
        assertTrue(phoneNumberEqual.compareTo(phoneNumber) == 0);
    }

}
