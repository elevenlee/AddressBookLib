package edu.nyu.cs.addressbook;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;

public class ContactUtilTest {
    private static final String TEST_FILES_ROOT = "test-files/";
    private static final String INPUT_FILE_NAME = "input_test.xml";
    private static final String INPUT_FILE_WITH_NOTHING_NAME = "input_test_with_nothing.xml";
    private static final String OUTPUT_FILE_NAME = "output_test.xml";
    private static final String OUTPUT_FILE_WITH_NOTHING_NAME = "output_test_with_nothing.xml";
    
    private Contact contact;
    private Contact contactWithNothing;
    private List<ContactEntry> contactEntryList;
    private InputStream in;
    private InputStream inWithNothing;
    private OutputStream out;
    private OutputStream outWithNothing;
    
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
                new ContactEntry.ContactEntryBuilder("Leonard", "Hofstadter")
                    .postalAddress(
                            new PostalAddress.PostalAddressBuilder().build()).build());
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
                    .note("I am a engineering in CaliTech.").build());
        for (ContactEntry ce : contactEntryList) {
            contact.add(ce);
        }
        in = new BufferedInputStream(
                new FileInputStream(TEST_FILES_ROOT + INPUT_FILE_NAME));
        inWithNothing = new BufferedInputStream(
                new FileInputStream(TEST_FILES_ROOT + INPUT_FILE_WITH_NOTHING_NAME));
        out = new BufferedOutputStream(
                new FileOutputStream(TEST_FILES_ROOT + OUTPUT_FILE_NAME));
        outWithNothing = new BufferedOutputStream(
                new FileOutputStream(TEST_FILES_ROOT + OUTPUT_FILE_WITH_NOTHING_NAME));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        in.close();
        inWithNothing.close();
        out.flush();
        outWithNothing.flush();
        out.close();
        outWithNothing.close();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = NullPointerException.class)
    public void testLoadWithNullInputStreamObject() {
        ContactUtil.load(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test
    public void testLoadWithLegalObject() {
        Contact c = ContactUtil.load(in);
        Contact cWithNothing = ContactUtil.load(inWithNothing);
        assertEquals("Emily", c.getID());
        assertEquals(contactEntryList, c.getContacts());
        for (int i = 0; i < contactEntryList.size(); i++) {
            assertEquals(
                    contactEntryList.get(i).getFirstName(),
                    c.getContacts().get(i).getFirstName());
            assertEquals(
                    contactEntryList.get(i).getLastName(),
                    c.getContacts().get(i).getLastName());
            assertEquals(
                    contactEntryList.get(i).getPhoneNumbers(),
                    c.getContacts().get(i).getPhoneNumbers());
            assertEquals(
                    contactEntryList.get(i).getEmailAddresses(),
                    c.getContacts().get(i).getEmailAddresses());
            assertEquals(
                    contactEntryList.get(i).getPostalAddresses(),
                    c.getContacts().get(i).getPostalAddresses());
            assertEquals(
                    contactEntryList.get(i).getNote(),
                    c.getContacts().get(i).getNote());
        }
        
        assertEquals("Daniel", cWithNothing.getID());
        assertEquals(Collections.emptyList(), cWithNothing.getContacts());
        
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalIDObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_id.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalFirstNameObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_firstname.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalLastNameObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_lastname.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalAreaCodeObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_areacode.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalPrefixObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_prefix.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalLineNumberObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_linenumber.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalUsernameObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_username.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalDomainObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_domain.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalZipcodeObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_zipcode.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalStateObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_state.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalCityObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_city.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalStreetObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_street.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoadWithIllegalNoteObject() {
        InputStream input;
        try {
            input = new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_illegal_note.xml"));
            @SuppressWarnings("unused")
            Contact c = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#load(java.io.InputStream)}.
     */
    @Test
    public void testLoadWithLoadSaveEqual() {
        try {
            InputStream input =
                    new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "input_test_with_load_save_equal.xml"));
            Contact c = ContactUtil.load(input);
            input.close();
            ContactUtil.save(out, c);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File originFile = new File(TEST_FILES_ROOT + "input_test_with_load_save_equal.xml");
        File newFile = new File(TEST_FILES_ROOT + OUTPUT_FILE_NAME);
        byte[] originContent = new byte[(int) originFile.length()];
        byte[] newContent = new byte[(int) newFile.length()];
        try {
            InputStream originIn =
                    new BufferedInputStream(
                            new FileInputStream(originFile));
            originIn.read(originContent);
            originIn.close();
            InputStream newIn =
                    new BufferedInputStream(
                            new FileInputStream(newFile));
            newIn.read(newContent);
            newIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(new String(originContent).trim(), new String(newContent).trim());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#save(java.io.OutputStream, edu.nyu.cs.addressbook.Contact)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSaveWithNullOutputStreamObject() {
        ContactUtil.save(null, contact);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#save(java.io.OutputStream, edu.nyu.cs.addressbook.Contact)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSaveWithNullContactObject() {
        ContactUtil.save(out, null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#save(java.io.OutputStream, edu.nyu.cs.addressbook.Contact)}.
     */
    @Test
    public void testSaveWithLegalObject() {
        ContactUtil.save(out, contact);
        File originFile = new File(TEST_FILES_ROOT + "output_test_origin.xml");
        File newFile = new File(TEST_FILES_ROOT + OUTPUT_FILE_NAME);
        byte[] originContent = new byte[(int) originFile.length()];
        byte[] newContent = new byte[(int) newFile.length()];
        InputStream originIn = null;
        InputStream newIn = null;
        try {
            originIn = new BufferedInputStream(new FileInputStream(originFile));
            originIn.read(originContent);
            originIn.close();
            newIn = new BufferedInputStream(new FileInputStream(newFile));
            newIn.read(newContent);
            newIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(new String(originContent).trim(), new String(newContent).trim());
        
        ContactUtil.save(outWithNothing, contactWithNothing);
        originFile = new File(TEST_FILES_ROOT + "output_test_with_nothing_origin.xml");
        newFile = new File(TEST_FILES_ROOT + OUTPUT_FILE_WITH_NOTHING_NAME);
        originContent = new byte[(int) originFile.length()];
        newContent = new byte[(int) newFile.length()];
        try {
            originIn = new BufferedInputStream(new FileInputStream(originFile));
            originIn.read(originContent);
            originIn.close();
            newIn = new BufferedInputStream(new FileInputStream(newFile));
            newIn.read(newContent);
            newIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(new String(originContent).trim(), new String(newContent).trim());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactUtil#save(java.io.OutputStream, edu.nyu.cs.addressbook.Contact)}.
     */
    @Test
    public void testSaveWithSaveLoadEqual() {
        Contact originContact = new Contact("Kevin");
        for (ContactEntry ce : contactEntryList) {
            originContact.add(ce);
        }
        try {
            OutputStream output =
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    TEST_FILES_ROOT + "output_test_with_save_load_equal.xml"));
            ContactUtil.save(output, originContact);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Contact newContact = null;
        try {
            InputStream input =
                    new BufferedInputStream(
                            new FileInputStream(
                                    TEST_FILES_ROOT + "output_test_with_save_load_equal.xml"));
            newContact = ContactUtil.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(originContact, newContact);
        assertEquals(originContact.getContacts(), newContact.getContacts());
        for (int i = 0; i < originContact.getContacts().size(); i++) {
            assertEquals(
                    originContact.getContacts().get(i),
                    newContact.getContacts().get(i));
            assertEquals(
                    originContact.getContacts().get(i).getPhoneNumbers(),
                    newContact.getContacts().get(i).getPhoneNumbers());
            assertEquals(
                    originContact.getContacts().get(i).getEmailAddresses(),
                    newContact.getContacts().get(i).getEmailAddresses());
            assertEquals(
                    originContact.getContacts().get(i).getPostalAddresses(),
                    newContact.getContacts().get(i).getPostalAddresses());
            assertEquals(
                    originContact.getContacts().get(i).getNote(),
                    newContact.getContacts().get(i).getNote());
        }
    }

}
