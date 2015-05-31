package edu.nyu.cs.addressbook.component;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmailAddressTest {
    private EmailAddress emailAddress;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        emailAddress = new EmailAddress("sl3268", "nyu.edu");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#EmailAddress(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEmailAddressWithLegalArgument() {
        new EmailAddress("yunfeiyang4ever", "gmail.com");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#EmailAddress(java.lang.String, java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testEmailAddressWithNullUsername() {
        new EmailAddress(null, "gmail.com");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#EmailAddress(java.lang.String, java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testEmailAddressWithNullDomain() {
        new EmailAddress("yunfeiyang4ever", null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#EmailAddress(java.lang.String, java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailAddressWitEmptyUsername() {
        new EmailAddress("    ", "gmail.com");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#EmailAddress(java.lang.String, java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailAddressWitEmptyDomain() {
        new EmailAddress("yunfeiyang4ever", "      ");
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#getUsername()}.
     */
    @Test
    public void testGetUsername() {
        assertEquals("sl3268", emailAddress.getUsername());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#getDomain()}.
     */
    @Test
    public void testGetDomain() {
        assertEquals("nyu.edu", emailAddress.getDomain());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(emailAddress.equals(emailAddress));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(emailAddress.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotEmailAddressObject() {
        Object o = new Object();
        assertFalse(emailAddress.equals(o));
        assertFalse(o.equals(emailAddress));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualEmailAddressObject() {
        EmailAddress ea1 = new EmailAddress("sl3268", "nyu.edu");
        EmailAddress ea2 = new EmailAddress("sl3268", "nyu.edu");
        assertTrue(emailAddress.equals(ea1));
        assertTrue(ea1.equals(emailAddress));
        assertTrue(ea1.equals(ea2));
        assertTrue(emailAddress.equals(ea2));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferEmailAddressObject() {
        List<EmailAddress> emailAddressList = Arrays.asList(
                new EmailAddress("asdf", "nyu.edu"),
                new EmailAddress("sl3268", "gmail.com"));
        for (EmailAddress ea : emailAddressList) {
            assertFalse(emailAddress.equals(ea));
            assertFalse(ea.equals(emailAddress));
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(244155305, emailAddress.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("sl3268@nyu.edu", emailAddress.toString());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#clone()}.
     */
    @Test
    public void testClone() {
        EmailAddress ea = emailAddress.clone();
        assertTrue(emailAddress.equals(ea));
        assertTrue(ea.equals(emailAddress));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#compareTo(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testCompareToWithNullObject() {
        emailAddress.compareTo(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.EmailAddress#compareTo(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test
    public void testCompareToWithNotNullObject() {
        List<EmailAddress> emailAddressLessList = Arrays.asList(
                new EmailAddress("abc", "nyu.edu"),
                new EmailAddress("sl3268", "gmail.com"));
        List<EmailAddress> emailAddressGreaterList = Arrays.asList(
                new EmailAddress("zyx", "nyu.edu"),
                new EmailAddress("sl3268", "yahoo.com"));
        EmailAddress emailAddressEqual = new EmailAddress("sl3268", "nyu.edu");
        for (EmailAddress ea : emailAddressLessList) {
            assertTrue(emailAddress.compareTo(ea) > 0);
            assertTrue(ea.compareTo(emailAddress) < 0);
        }
        for (EmailAddress ea : emailAddressGreaterList) {
            assertTrue(emailAddress.compareTo(ea) < 0);
            assertTrue(ea.compareTo(emailAddress) > 0);
        }
        assertTrue(emailAddress.compareTo(emailAddressEqual) == 0);
        assertTrue(emailAddressEqual.compareTo(emailAddress) == 0);
    }

}
