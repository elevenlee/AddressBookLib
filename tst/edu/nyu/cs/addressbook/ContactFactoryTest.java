package edu.nyu.cs.addressbook;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ContactFactoryTest {
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactFactory#getContact(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testGetContactWithNullObject() {
        ContactFactory.getContact(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactFactory#getContact(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactWithEmptyObject() {
        ContactFactory.getContact("      ");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactFactory#getContact(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactWithExistObject() {
        @SuppressWarnings("unused")
        List<Contact> contacts = Arrays.asList(
                ContactFactory.getContact("Eleven"),
                ContactFactory.getContact("Sheldon"),
                ContactFactory.getContact("Howard"),
                ContactFactory.getContact("Leonard"),
                ContactFactory.getContact("Rajesh"));
        ContactFactory.getContact("    Leonard   ");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactFactory#getContact(java.lang.String)}.
     */
    @Test
    public void testGetContactWithLegalObject() {
        assertEquals(new Contact("Penny"), ContactFactory.getContact("  Penny "));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.ContactFactory#getContacts()}.
     */
    @Test
    public void testGetContacts() {
        List<Contact> contacts = Arrays.asList(new Contact("Penny"));
        assertEquals(contacts, ContactFactory.getContacts());
    }

}
