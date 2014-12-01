package edu.nyu.cs.addressbook.search.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;

public class OrTest {
    private Or orCriteria;
    private Or noCriteria;
    private ContactEntry contactEntry;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        orCriteria = new Or(
                new Regex("^[0-9]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(123).build(),
                new ContactField.ContactFieldBuilder()
                        .username("lishenapply").domain("gmail.com").build());
        noCriteria = new Or();
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
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#Or(edu.nyu.cs.addressbook.search.SearchCriteria[])}.
     */
    @Test
    public void testOrCriteriaWithLegalArgument() {
        @SuppressWarnings("unused")
        Or oc = new Or(
                NonNull.INSTANCE,
                new Regex("^[0-9]+ [a-zA-Z]+\\n.*$"),
                new ContactField.ContactFieldBuilder().zipCode(620).build());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#Or(edu.nyu.cs.addressbook.search.SearchCriteria[])}.
     */
    @Test(expected = NullPointerException.class)
    public void testOrCriteriaWithNullObject() {
        @SuppressWarnings("unused")
        Or oc = new Or(
                NonNull.INSTANCE,
                null,
                new ContactField.ContactFieldBuilder().zipCode(620).build());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNullObject() {
        assertFalse(orCriteria.matchCondition(null));
        assertFalse(noCriteria.matchCondition(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNotNullObject() {
        assertTrue(orCriteria.matchCondition(contactEntry));
        assertFalse(noCriteria.matchCondition(contactEntry));
        Or notMatchCriteria = new Or(
                new Regex("^.*$"),
                new ContactField.ContactFieldBuilder().firstName("Sheldon").build(),
                new ContactField.ContactFieldBuilder().street("union square").build(),
                new Regex("^[0-9]+[0-9]{4}$"));
        assertFalse(notMatchCriteria.matchCondition(contactEntry));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(orCriteria.equals(orCriteria));
        assertTrue(noCriteria.equals(noCriteria));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(orCriteria.equals(null));
        assertFalse(noCriteria.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotOrCriteriaObject() {
        Object o = new Object();
        assertFalse(orCriteria.equals(o));
        assertFalse(o.equals(orCriteria));
        assertFalse(noCriteria.equals(o));
        assertFalse(o.equals(noCriteria));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualOrCriteriaObject() {
        Or or1 = new Or(
                new Regex("^[0-9]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(123).build(),
                new ContactField.ContactFieldBuilder()
                        .username("lishenapply").domain("gmail.com").build());
        Or or2 = new Or(
                new Regex("^[0-9]+ [a-zA-Z]+"),
                new ContactField.ContactFieldBuilder().prefix(123).build(),
                new ContactField.ContactFieldBuilder()
                        .username("lishenapply").domain("gmail.com").build());
        assertTrue(orCriteria.equals(or1));
        assertTrue(or1.equals(orCriteria));
        assertTrue(or1.equals(or2));
        assertTrue(orCriteria.equals(or2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferOrCriteriaObject() {
        Or oc = new Or(
                new ContactField.ContactFieldBuilder().areaCode(123).build());
        assertFalse(orCriteria.equals(oc));
        assertFalse(oc.equals(orCriteria));
        assertFalse(noCriteria.equals(oc));
        assertFalse(oc.equals(noCriteria));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(1227684634, orCriteria.hashCode());
        assertEquals(528, noCriteria.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.Or#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("^[0-9]+ [a-zA-Z]+\n"
                + "prefix: 123\n\n"
                + "user name: lishenapply\n"
                + "domain: gmail.com\n\n", orCriteria.toString());
        assertEquals("", noCriteria.toString());
    }

}
