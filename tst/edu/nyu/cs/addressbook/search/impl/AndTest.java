package edu.nyu.cs.addressbook.search.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.search.impl.And;

public class AndTest {
    private And andCriteria;
    private And noCriteria;
    private ContactEntry contactEntry;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        andCriteria = new And(
                NonNull.INSTANCE,
                new Regex("^[a-zA-Z]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(620).build());
        noCriteria = new And();
        contactEntry = new ContactEntry.ContactEntryBuilder("Shen", "Li")
                            .phoneNumber(new PhoneNumber(646, 620, 5666))
                            .emailAddress(
                                    new EmailAddress("sl3268", "nyu.edu"),
                                    new EmailAddress("yunfeiyang4ever", "gmail.com"),
                                    new EmailAddress("lishenapply", "yahoo.com"))
                            .postalAddress(
                                    new PostalAddress.PostalAddressBuilder()
                                            .zipCode(11220).state(State.NY).city("Brooklyn")
                                            .street("465 46th Street APT 5").build(),
                                    new PostalAddress.PostalAddressBuilder()
                                            .zipCode(98765).state(State.CA).city("Los Angles")
                                            .street("112 Avenue T 2F").build())
                            .note("I am an internation student in NYU.").build();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#And(edu.nyu.cs.addressbook.search.SearchCriteria[])}.
     */
    @Test
    public void testAndCriteriaWithLegalArgument() {
        @SuppressWarnings("unused")
        And ac = new And(
                NonNull.INSTANCE,
                new Regex("^[0-9]+ [a-zA-Z]+\\n.*$"),
                new ContactField.ContactFieldBuilder().zipCode(620).build());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#And(edu.nyu.cs.addressbook.search.SearchCriteria[])}.
     */
    @Test(expected = NullPointerException.class)
    public void testAndCriteriaWithNullObject() {
        @SuppressWarnings("unused")
        And ac = new And(
                NonNull.INSTANCE,
                null,
                new ContactField.ContactFieldBuilder().zipCode(620).build());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNullObject() {
        assertFalse(andCriteria.matchCondition(null));
        assertFalse(noCriteria.matchCondition(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNotNullObject() {
        assertTrue(andCriteria.matchCondition(contactEntry));
        assertTrue(noCriteria.matchCondition(contactEntry));
        And notMatchCriteria = new And(
                NonNull.INSTANCE,
                new Regex("^[a-zA-Z]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(123).zipCode(98765).build());
        assertFalse(notMatchCriteria.matchCondition(contactEntry));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(andCriteria.equals(andCriteria));
        assertTrue(noCriteria.equals(noCriteria));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(andCriteria.equals(null));
        assertFalse(noCriteria.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotAndCriteriaObject() {
        Object o = new Object();
        assertFalse(andCriteria.equals(o));
        assertFalse(o.equals(andCriteria));
        assertFalse(noCriteria.equals(o));
        assertFalse(o.equals(noCriteria));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualAndCriteriaObject() {
        And ac1 = new And(
                NonNull.INSTANCE,
                new Regex("^[a-zA-Z]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(620).build());
        And ac2 = new And(
                NonNull.INSTANCE,
                new Regex("^[a-zA-Z]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(620).build());
        assertTrue(andCriteria.equals(ac1));
        assertTrue(ac1.equals(andCriteria));
        assertTrue(ac1.equals(ac2));
        assertTrue(andCriteria.equals(ac2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferAndCriteriaObject() {
        And ac = new And(
                NonNull.INSTANCE,
                new Regex("^[0-9]+ [a-zA-Z]+\\n.*$"),
                new ContactField.ContactFieldBuilder().lineNumber(123).prefix(620).build());
        assertFalse(andCriteria.equals(ac));
        assertFalse(ac.equals(andCriteria));
        assertFalse(noCriteria.equals(ac));
        assertFalse(ac.equals(noCriteria));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.And#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Non Null\n"
                + "^[a-zA-Z]+ [a-zA-Z]+\n"
                + "prefix: 620\n\n", andCriteria.toString());
        assertEquals("", noCriteria.toString());
    }

}
