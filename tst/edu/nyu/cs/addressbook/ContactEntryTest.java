package edu.nyu.cs.addressbook;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;

public class ContactEntryTest {
    private ContactEntry contactEntry;
    private ContactEntry contactEntryWithNothing;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
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
        contactEntryWithNothing = new ContactEntry.ContactEntryBuilder("Jennifer", "Darlington").build();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test
    public void testContactEntryWithLegalArgument() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper")
                                .phoneNumber(new PhoneNumber(123, 456, 7890))
                                .emailAddress(
                                        new EmailAddress("sheldon", "cims.edu"),
                                        new EmailAddress("cooper", "gmail.com"))
                                .postalAddress(
                                        new PostalAddress.PostalAddressBuilder()
                                            .zipCode(23456).state(State.WA).city("George")
                                            .street("567 Madison Avenue").build())
                                .note("I am a scentist.").build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullFirstName() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder(null, "Cooper").build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullLastName() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullPhoneNumber() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper")
                                .phoneNumber(
                                        new PhoneNumber(123, 456, 7890),
                                        null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullEmailAddress() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper")
                                .emailAddress(
                                        new EmailAddress("asdf", "yahoo.com"),
                                        null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullPostalAddress() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper")
                                .postalAddress(
                                        new PostalAddress.PostalAddressBuilder()
                                            .zipCode(23456).state(State.WA).city("George")
                                            .street("567 Madison Avenue").build(),
                                        null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#ContactEntry(edu.nyu.cs.addressbook.ContactEntry.ContactEntryBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactEntryWithNullNote() {
        @SuppressWarnings("unused")
        ContactEntry ce = new ContactEntry.ContactEntryBuilder("Sheldon", "Cooper")
                                .note(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getFirstName()}.
     */
    @Test
    public void testGetFirstName() {
        assertEquals("Shen", contactEntry.getFirstName());
        assertEquals("Jennifer", contactEntryWithNothing.getFirstName());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getLastName()}.
     */
    @Test
    public void testGetLastName() {
        assertEquals("Li", contactEntry.getLastName());
        assertEquals("Darlington", contactEntryWithNothing.getLastName());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getPhoneNumbers()}.
     */
    @Test
    public void testGetPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = Arrays.asList(
                new PhoneNumber(646, 620, 5666));
        assertEquals(phoneNumbers, contactEntry.getPhoneNumbers());
        
        List<PhoneNumber> phoneNumberEmpty = Collections.emptyList();
        assertEquals(phoneNumberEmpty, contactEntryWithNothing.getPhoneNumbers());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getEmailAddresses()}.
     */
    @Test
    public void testGetEmailAddresses() {
        List<EmailAddress> emailAddresses = Arrays.asList(
                new EmailAddress("sl3268", "nyu.edu"),
                new EmailAddress("yunfeiyang4ever", "gmail.com"),
                new EmailAddress("lishenapply", "yahoo.com"));
        assertEquals(emailAddresses, contactEntry.getEmailAddresses());
        
        List<EmailAddress> emailAddressEmpty = Collections.emptyList();
        assertEquals(emailAddressEmpty, contactEntryWithNothing.getEmailAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getPostalAddresses()}.
     */
    @Test
    public void testGetPostalAddresses() {
        List<PostalAddress> postalAddresses = Arrays.asList(
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(98765).state(State.CA).city("Los Angles")
                    .street("112 Avenue T 2F").build());
        assertEquals(postalAddresses, contactEntry.getPostalAddresses());
        
        List<PostalAddress> postalAddressEmpty = Collections.emptyList();
        assertEquals(postalAddressEmpty, contactEntryWithNothing.getPostalAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#getNote()}.
     */
    @Test
    public void testGetNote() {
        assertEquals("I am an internation student in NYU.", contactEntry.getNote());
        assertEquals("", contactEntryWithNothing.getNote());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#setNote(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetNoteWithNullObject() {
        contactEntry.setNote(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#setNote(java.lang.String)}.
     */
    @Test
    public void testSetNoteWithNotNullObject() {
        String newNote = "I am looking for a good job!";
        contactEntry.setNote(newNote);
        assertEquals(newNote, contactEntry.getNote());
        contactEntryWithNothing.setNote(newNote);
        assertEquals(newNote, contactEntryWithNothing.getNote());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddPhoneNumberWithNullObject() {
        contactEntry.addPhoneNumber(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test
    public void testAddPhoneNumberWithExistObject() {
        PhoneNumber pn = new PhoneNumber(646, 620, 5666);
        assertFalse(contactEntry.addPhoneNumber(pn));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test
    public void testAddPhoneNumberWithNotExistObject() {
        PhoneNumber pn = new PhoneNumber(123, 456, 7890);
        assertTrue(contactEntry.addPhoneNumber(pn));
        assertTrue(contactEntry.getPhoneNumbers().contains(pn));
        assertTrue(contactEntryWithNothing.addPhoneNumber(pn));
        assertTrue(contactEntryWithNothing.getPhoneNumbers().contains(pn));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddEmailAddressWithNullObject() {
        contactEntry.addEmailAddress(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test
    public void testAddEmailAddressWithExistObject() {
        EmailAddress eaLower = new EmailAddress("sl3268", "nyu.edu");
        EmailAddress eaUpper = new EmailAddress("SL3268", "NYU.EDU");
        assertFalse(contactEntry.addEmailAddress(eaLower));
        assertFalse(contactEntry.addEmailAddress(eaUpper));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test
    public void testAddEmailAddressWithNotExistObject() {
        EmailAddress ea = new EmailAddress("asdf", "qwer.com");
        assertTrue(contactEntry.addEmailAddress(ea));
        assertTrue(contactEntry.getEmailAddresses().contains(ea));
        assertTrue(contactEntryWithNothing.addEmailAddress(ea));
        assertTrue(contactEntryWithNothing.getEmailAddresses().contains(ea));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddPostalAddressWithNullObject() {
        contactEntry.addPostalAddress(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test
    public void testAddPostalAddressWithExistObject() {
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(11220).state(State.NY).city("Brooklyn")
                                .street("465 46th Street APT 5").build();
        assertFalse(contactEntry.addPostalAddress(pa));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#addPostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test
    public void testAddPostalAddressWithNotExistObject() {
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(54321).state(State.DC).city("Washington")
                                .street("762 41th Street").build();
        assertTrue(contactEntry.addPostalAddress(pa));
        assertTrue(contactEntry.getPostalAddresses().contains(pa));
        assertTrue(contactEntryWithNothing.addPostalAddress(pa));
        assertTrue(contactEntryWithNothing.getPostalAddresses().contains(pa));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#clearPhoneNumbers()}.
     */
    @Test
    public void testClearPhoneNumbers() {
        contactEntry.clearPhoneNumbers();
        assertEquals(Collections.emptyList(), contactEntry.getPhoneNumbers());
        contactEntryWithNothing.clearPhoneNumbers();
        assertEquals(Collections.emptyList(), contactEntry.getPhoneNumbers());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#clearEmailAddresses()}.
     */
    @Test
    public void testClearEmailAddresses() {
        contactEntry.clearEmailAddresses();
        assertEquals(Collections.emptyList(), contactEntry.getEmailAddresses());
        contactEntryWithNothing.clearEmailAddresses();
        assertEquals(Collections.emptyList(), contactEntryWithNothing.getEmailAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#clearPostalAddresses()}.
     */
    @Test
    public void testClearPostalAddresses() {
        contactEntry.clearPostalAddresses();
        assertEquals(Collections.emptyList(), contactEntry.getPostalAddresses());
        contactEntryWithNothing.clearPostalAddresses();
        assertEquals(Collections.emptyList(), contactEntryWithNothing.getPostalAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsAreaCode(int)}.
     */
    @Test
    public void testContainsAreaCode() {
        assertTrue(contactEntry.containsAreaCode(646));
        assertFalse(contactEntry.containsAreaCode(123));
        assertFalse(contactEntryWithNothing.containsAreaCode(646));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsPrefix(int)}.
     */
    @Test
    public void testContainsPrefix() {
        assertTrue(contactEntry.containsPrefix(620));
        assertFalse(contactEntry.containsPrefix(987));
        assertFalse(contactEntryWithNothing.containsPrefix(987));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsLineNumber(int)}.
     */
    @Test
    public void testContainsLineNumber() {
        assertTrue(contactEntry.containsLineNumber(5666));
        assertFalse(contactEntry.containsLineNumber(7890));
        assertFalse(contactEntryWithNothing.containsLineNumber(7890));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsUsername(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsUsernameWithNullObject() {
        contactEntry.containsUsername(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsUsername(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContainsUsernameWithEmptyObject() {
        contactEntry.containsUsername("");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsUsername(java.lang.String)}.
     */
    @Test
    public void testContainsUsernameWithLegalObject() {
        assertTrue(contactEntry.containsUsername("sl3268"));
        assertTrue(contactEntry.containsUsername("SL3268"));
        assertFalse(contactEntry.containsUsername("asdf"));
        assertFalse(contactEntryWithNothing.containsUsername("asdf"));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsDomain(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsDomainWithNullObject() {
        contactEntry.containsDomain(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsDomain(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContainsDomainWithEmptyObject() {
        contactEntry.containsDomain("");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsDomain(java.lang.String)}.
     */
    @Test
    public void testContainsDomainWithLegalObject() {
        assertTrue(contactEntry.containsDomain("nyu.edu"));
        assertTrue(contactEntry.containsDomain("NYU.EDU"));
        assertFalse(contactEntry.containsDomain("icloud.com"));
        assertFalse(contactEntryWithNothing.containsDomain("icloud.com"));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsZipcode(int)}.
     */
    @Test
    public void testContainsZipcode() {
        assertTrue(contactEntry.containsZipcode(11220));
        assertFalse(contactEntry.containsZipcode(12345));
        assertFalse(contactEntryWithNothing.containsZipcode(12345));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsState(edu.nyu.cs.addressbook.component.util.State)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsStateWithNullObject() {
        contactEntry.containsState(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsState(edu.nyu.cs.addressbook.component.util.State)}.
     */
    @Test
    public void testContainsStateWithNotNullObject() {
        assertTrue(contactEntry.containsState(State.NY));
        assertFalse(contactEntry.containsState(State.ID));
        assertFalse(contactEntryWithNothing.containsState(State.AK));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsCity(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsCityWithNullObject() {
        contactEntry.containsCity(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsCity(java.lang.String)}.
     */
    @Test
    public void testContainsCityWithNotNullObject() {
        assertTrue(contactEntry.containsCity("Brooklyn"));
        assertFalse(contactEntry.containsCity("Miami"));
        assertFalse(contactEntryWithNothing.containsCity("Miami"));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsStreet(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsStreetWithNullObject() {
        contactEntry.containsStreet(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#containsStreet(java.lang.String)}.
     */
    @Test
    public void testContainsStreetWithNotNullObject() {
        assertTrue(contactEntry.containsStreet("465 46th Street APT 5"));
        assertTrue(contactEntry.containsStreet("Avenue"));
        assertFalse(contactEntry.containsStreet("762 41th Street"));
        assertFalse(contactEntryWithNothing.containsStreet("762 41th Street"));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemovePhoneNumberWithNullObject() {
        contactEntry.removePhoneNumber(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test
    public void testRemovePhoneNumberWithExistObject() {
        PhoneNumber pn = new PhoneNumber(646, 620, 5666);
        assertTrue(contactEntry.removePhoneNumber(pn));
        assertFalse(contactEntry.getPhoneNumbers().contains(pn));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePhoneNumber(edu.nyu.cs.addressbook.component.PhoneNumber)}.
     */
    @Test
    public void testRemovePhoneNumberWithNotExistObject() {
        PhoneNumber pn = new PhoneNumber(123, 456, 7890);
        List<PhoneNumber> original = contactEntry.getPhoneNumbers();
        assertFalse(contactEntry.removePhoneNumber(pn));
        assertEquals(original, contactEntry.getPhoneNumbers());
        
        assertFalse(contactEntryWithNothing.removePhoneNumber(pn));
        assertEquals(Collections.emptyList(), contactEntryWithNothing.getPhoneNumbers());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removeEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveEmailAddressWithNullObject() {
        contactEntry.removeEmailAddress(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removeEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test
    public void testRemoveEmailAddressWithExistObject() {
        EmailAddress ea = new EmailAddress("sl3268", "nyu.edu");
        assertTrue(contactEntry.removeEmailAddress(ea));
        assertFalse(contactEntry.getEmailAddresses().contains(ea));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removeEmailAddress(edu.nyu.cs.addressbook.component.EmailAddress)}.
     */
    @Test
    public void testRemoveEmailAddressWithNotExistObject() {
        EmailAddress ea = new EmailAddress("asdf", "yahoo.com");
        List<EmailAddress> original = contactEntry.getEmailAddresses();
        assertFalse(contactEntry.removeEmailAddress(ea));
        assertEquals(original, contactEntry.getEmailAddresses());
        
        assertFalse(contactEntryWithNothing.removeEmailAddress(ea));
        assertEquals(Collections.emptyList(), contactEntryWithNothing.getEmailAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemovePostalAddressWithNullObject() {
        contactEntry.removePostalAddress(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test
    public void testRemovePostalAddressWithExistObject() {
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(98765).state(State.CA).city("Los Angles")
                                .street("112 Avenue T 2F").build();
        assertTrue(contactEntry.removePostalAddress(pa));
        assertFalse(contactEntry.getPostalAddresses().contains(pa));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#removePostalAddress(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test
    public void testRemovePostalAddressWithNotExistObject() {
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(12345).state(State.CO).city("Polo")
                                .street("762 41th Street").build();
        List<PostalAddress> original = contactEntry.getPostalAddresses();
        assertFalse(contactEntry.removePostalAddress(pa));
        assertEquals(original, contactEntry.getPostalAddresses());
        
        assertFalse(contactEntryWithNothing.removePostalAddress(pa));
        assertEquals(Collections.emptyList(), contactEntryWithNothing.getPostalAddresses());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(contactEntry.equals(contactEntry));
        assertTrue(contactEntryWithNothing.equals(contactEntryWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(contactEntry.equals(null));
        assertFalse(contactEntryWithNothing.equals(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotContactEntryObject() {
        Object o = new Object();
        assertFalse(contactEntry.equals(o));
        assertFalse(o.equals(contactEntry));
        assertFalse(contactEntryWithNothing.equals(o));
        assertFalse(o.equals(contactEntryWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualContactEntryObject() {
        ContactEntry ce1 = new ContactEntry.ContactEntryBuilder("Shen", "Li")
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
        ContactEntry ce2 = new ContactEntry.ContactEntryBuilder("Shen", "Li").build();
        assertTrue(contactEntry.equals(ce1));
        assertTrue(ce1.equals(contactEntry));
        assertTrue(ce1.equals(ce2));
        assertTrue(contactEntry.equals(ce2));
        
        ce1 = new ContactEntry.ContactEntryBuilder("Jennifer", "Darlington")
                    .phoneNumber(new PhoneNumber(123, 456, 7890))
                    .emailAddress(
                            new EmailAddress("jd1417", "nyu.edu"),
                            new EmailAddress("jenniferd", "cs.nyu.edu"))
                    .postalAddress(
                            new PostalAddress.PostalAddressBuilder()
                                .zipCode(11220).state(State.NY).city("Brooklyn")
                                .street("465 46th Street APT 5").build(),
                            new PostalAddress.PostalAddressBuilder()
                                .zipCode(98765).state(State.CA).city("Los Angles")
                                .street("112 Avenue T 2F").build())
                    .note("I am the CS/IS program advisor in NYU.").build();
        ce2 = new ContactEntry.ContactEntryBuilder("Jennifer", "Darlington").build();
        assertTrue(contactEntryWithNothing.equals(ce1));
        assertTrue(ce1.equals(contactEntryWithNothing));
        assertTrue(ce1.equals(ce2));
        assertTrue(contactEntryWithNothing.equals(ce2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferContactEntryObject() {
        List<ContactEntry> contactEntryList = Arrays.asList(
                new ContactEntry.ContactEntryBuilder("Sheldon", "Li").build(),
                new ContactEntry.ContactEntryBuilder("Shen", "Cooper").build());
        for (ContactEntry ce : contactEntryList) {
            assertFalse(contactEntry.equals(ce));
            assertFalse(ce.equals(contactEntry));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(2668466, contactEntry.hashCode());
        assertEquals(301072814, contactEntryWithNothing.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Shen Li\n(646) 620-5666\n" +
                "sl3268@nyu.edu\nyunfeiyang4ever@gmail.com\nlishenapply@yahoo.com\n" +
                "465 46th Street APT 5\nBrooklyn, NY 11220\nThe United States\n" +
                "112 Avenue T 2F\nLos Angles, CA 98765\nThe United States\n" +
                "I am an internation student in NYU.", contactEntry.toString());
        assertEquals("Jennifer Darlington\n", contactEntryWithNothing.toString());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#compareTo(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test(expected = NullPointerException.class)
    public void testCompareToWithNullObject() {
        contactEntry.compareTo(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactEntry#compareTo(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testCompareToWithNotNullObject() {
        List<ContactEntry> contactEntryLessList = Arrays.asList(
                new ContactEntry.ContactEntryBuilder("Amy", "Li").build(),
                new ContactEntry.ContactEntryBuilder("Shen", "Cooper").build());
        List<ContactEntry> contactEntryGreaterList = Arrays.asList(
                new ContactEntry.ContactEntryBuilder("Zac", "Li").build(),
                new ContactEntry.ContactEntryBuilder("Shen", "Wolowitz").build());
        ContactEntry contactEntryEqual =
                new ContactEntry.ContactEntryBuilder("Shen", "Li").build();
        for (ContactEntry ce : contactEntryLessList) {
            assertTrue(contactEntry.compareTo(ce) > 0);
            assertTrue(ce.compareTo(contactEntry) < 0);
        }
        for (ContactEntry ce : contactEntryGreaterList) {
            assertTrue(contactEntry.compareTo(ce) < 0);
            assertTrue(ce.compareTo(contactEntry) > 0);
        }
        assertTrue(contactEntry.compareTo(contactEntryEqual) == 0);
        assertTrue(contactEntryEqual.compareTo(contactEntry) == 0);
    }

}
