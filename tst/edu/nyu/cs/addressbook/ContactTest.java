package edu.nyu.cs.addressbook;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;

public class ContactTest {
    private Contact contact;
    private Contact contactWithNothing;
    private List<ContactEntry> contactEntryList;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contact = new Contact("Eleven");
        contactWithNothing = new Contact("Sheldon");
        contactEntryList = new LinkedList<ContactEntry>();
        contactEntryList.add(
                new ContactEntry.ContactEntryBuilder("Jennifer", "Darlington").build());
        contactEntryList.add(
                new ContactEntry.ContactEntryBuilder("Shen", "Li")
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
                    .note("I am an internation student in NYU.").build());
        contactEntryList.add(
                new ContactEntry.ContactEntryBuilder("Howard", "Wolowitz")
                    .phoneNumber(
                            new PhoneNumber(123, 456, 7890),
                            new PhoneNumber(987, 654, 3210))
                    .emailAddress(new EmailAddress("howardw", "cs.nyu.edu"))
                    .note("I am a engineering in CIT.").build());
        for (ContactEntry ce : contactEntryList) {
            contact.add(ce);
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#Contact(java.lang.String)}.
     */
    @Test
    public void testContactWithLegalArgument() {
        @SuppressWarnings("unused")
        Contact c = new Contact("Leonard");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#Contact(java.lang.String)}.
     */
    @Test
    public void testContactWithNullObject() {
        @SuppressWarnings("unused")
        Contact c = new Contact(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#Contact(java.lang.String)}.
     */
    @Test
    public void testContactWithEmptyObject() {
        @SuppressWarnings("unused")
        Contact c = new Contact("    ");
    }

    /**
     * Test method for {@link edu.nyu.cs.pqs.ps1.impl.Contact#getID()}.
     */
    @Test
    public void testGetID() {
        assertEquals("Eleven", contact.getID());
        assertEquals("Sheldon", contactWithNothing.getID());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#getContacts()}.
     */
    @Test
    public void testGetContacts() {
        assertEquals(contactEntryList, contact.getContacts());
        assertEquals(Collections.emptyList(), contactWithNothing.getContacts());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#add(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddWithNullObject() {
        contact.add(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#add(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testAddWithFirstPositionObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Smith", "Bob")
                                .phoneNumber(new PhoneNumber(123, 456,7890))
                                .note("Test name.").build();
        contact.add(ce);
        contactEntryList.add(0, ce);
        assertEquals(contactEntryList, contact.getContacts());
        contactWithNothing.add(ce);
        List<ContactEntry> ceList = Arrays.asList(ce);
        assertEquals(ceList, contactWithNothing.getContacts());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#add(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testAddWithMiddlePositionObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Hanks", "Paul")
                                .emailAddress(new EmailAddress("hanksp", "gmail.com"))
                                .note("Test name.").build();
        contact.add(ce);
        contactEntryList.add(2, ce);
        assertEquals(contactEntryList, contact.getContacts());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#add(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testAddWithLastPositionObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("David", "Zac")
                                .postalAddress(
                                        new PostalAddress.PostalAddressBuilder()
                                            .state(State.PA).city("Pittsburger").build())
                                .note("Test name.").build();
        contact.add(ce);
        contactEntryList.add(3, ce);
        assertEquals(contactEntryList, contact.getContacts());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#add(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testAddWithEqualObject() {
        ContactEntry ce = contactEntryList.get(0);
        contact.add(ce);
        contactEntryList.add(1, ce);
        assertEquals(contactEntryList, contact.getContacts());
        ce = contactEntryList.get(2);
        contact.add(ce);
        contactEntryList.add(3, ce);
        assertEquals(contactEntryList, contact.getContacts());
        ce = contactEntryList.get(4);
        contact.add(ce);
        contactEntryList.add(5, ce);
        assertEquals(contactEntryList, contact.getContacts());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#clear()}.
     */
    @Test
    public void testClear() {
        contact.clear();
        assertEquals(Collections.emptyList(), contact.getContacts());
        contactWithNothing.clear();
        assertEquals(Collections.emptyList(), contactWithNothing.getContacts());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#remove(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullObject() {
        contact.remove(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#remove(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testRemoveWithExistObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Shen", "Li")
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
        assertTrue(contact.remove(ce));
        assertFalse(contact.getContacts().contains(ce));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#remove(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testRemoveWithNotExistObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper").build();
        assertFalse(contact.remove(ce));
        assertEquals(contactEntryList, contact.getContacts());
        assertFalse(contactWithNothing.remove(ce));
        assertEquals(Collections.emptyList(), contactWithNothing.getContacts());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#remove(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testRemoveWithDuplicateObject() {
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Shen", "Li")
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
        contact.add(ce);
        assertTrue(contact.remove(ce));
        assertTrue(contact.getContacts().contains(ce));
        assertTrue(contact.getContacts().get(1).equals(ce));
        assertFalse(contact.getContacts().get(2).equals(ce));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#iterator()}.
     */
    @Test
    public void testIteratorWithLegalElement() {
        Iterator<ContactEntry> iterWithContact = contact.iterator();
        Iterator<ContactEntry> iterWithContactEntry = contactEntryList.iterator();
        assertEquals(iterWithContactEntry.next(), iterWithContact.next());
        assertEquals(iterWithContactEntry.next(), iterWithContact.next());
        assertEquals(iterWithContactEntry.next(), iterWithContact.next());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#iterator()}.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorWithOutOfRangeElement() {
        Iterator<ContactEntry> iterWithContact = contact.iterator();
        iterWithContact.next();
        iterWithContact.next();
        iterWithContact.next();
        iterWithContact.next();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(contact.equals(contact));
        assertTrue(contactWithNothing.equals(contactWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(contact.equals(null));
        assertFalse(contactWithNothing.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotContactObject() {
        Object o = new Object();
        assertFalse(contact.equals(o));
        assertFalse(o.equals(contact));
        assertFalse(contactWithNothing.equals(o));
        assertFalse(o.equals(contactWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualContactObject() {
        Contact c1 = new Contact("Eleven");
        Contact c2 = new Contact("Eleven");
        c2.add(new ContactEntry.ContactEntryBuilder("Leonard", "Hofstadter").build());
        assertTrue(contact.equals(c1));
        assertTrue(c1.equals(contact));
        assertTrue(c1.equals(c2));
        assertTrue(contact.equals(c2));
        
        c1 = new Contact("Sheldon");
        c2 = new Contact("Sheldon");
        c2.add(new ContactEntry.ContactEntryBuilder("Leonard", "Hofstadter").build());
        assertTrue(contactWithNothing.equals(c1));
        assertTrue(c1.equals(contactWithNothing));
        assertTrue(c1.equals(c2));
        assertTrue(contactWithNothing.equals(c2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferContactObject() {
        List<Contact> contactList = Arrays.asList(
                new Contact("Amy"),
                new Contact("Howard"),
                new Contact("Zac"));
        for (Contact c : contactList) {
            assertFalse(contact.equals(c));
            assertFalse(c.equals(contact));
            assertFalse(contactWithNothing.equals(c));
            assertFalse(c.equals(contactWithNothing));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(2078277744, contact.hashCode());
        assertEquals(-572580874, contactWithNothing.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("id: Eleven\n" +
                "Jennifer Darlington\n\n" +
                "Shen Li\n(646) 620-5666\n" +
                "sl3268@nyu.edu\nyunfeiyang4ever@gmail.com\nlishenapply@yahoo.com\n" +
                "465 46th Street APT 5\nBrooklyn, NY 11220\nThe United States\n" +
                "112 Avenue T 2F\nLos Angles, CA 98765\nThe United States\n" +
                "I am an internation student in NYU.\n" +
                "Howard Wolowitz\n(123) 456-7890\n(987) 654-3210\n" +
                "howardw@cs.nyu.edu\n" +
                "I am a engineering in CIT.\n", contact.toString());
        assertEquals("id: Sheldon\n", contactWithNothing.toString());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#compareTo(edu.nyu.cs.addressbook.Contact)}.
     */
    @Test(expected = NullPointerException.class)
    public void testCompareToWithNullObject() {
        contact.compareTo(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.Contact#compareTo(edu.nyu.cs.addressbook.Contact)}.
     */
    @Test
    public void testCompareToWithNotNullObject() {
        Contact contactLess = new Contact("Amy");
        Contact contactGreater = new Contact("Howard");
        Contact contactEqual = new Contact("Eleven");
        assertTrue(contact.compareTo(contactLess) > 0);
        assertTrue(contactLess.compareTo(contact) < 0);
        assertTrue(contact.compareTo(contactGreater) < 0);
        assertTrue(contactGreater.compareTo(contact) > 0);
        assertTrue(contact.compareTo(contactEqual) == 0);
        assertTrue(contactEqual.compareTo(contact) == 0);
    }

}
