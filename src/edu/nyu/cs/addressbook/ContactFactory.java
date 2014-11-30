package edu.nyu.cs.addressbook;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * Factory object that can vend {@link edu.nyu.cs.addressbook.Contact} object.
 * <p>
 * NOTE: The factory object is thread-safe.
 */
public class ContactFactory {
    private static Set<String> idSet = new CopyOnWriteArraySet<>();
    private static List<Contact> contactList = new CopyOnWriteArrayList<>();
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private ContactFactory() {
        throw new AssertionError();
    }
    
    /**
     * Create a {@link edu.nyu.cs.addressbook.Contact} object based on the specific id.
     * <p>
     * @param id the identifier of the {@link edu.nyu.cs.addressbook.Contact} object
     * @return a suitable {@link edu.nyu.cs.addressbook.Contact}
     */
    public static Contact getContact(String id) {
        ParameterChecker.nullCheck(id, "contact id");
        ParameterChecker.emptyCheck(id.trim(), "contact id");
        if (!idSet.add(id.trim())) {
            throw new IllegalArgumentException(
                    "contact id: " + id.trim() + " already taken!");
        }
        
        Contact contact = new Contact(id.trim());
        contactList.add(contact);
        return contact;
    }
    
    /**
     * Returns an unmodifiable view of the {@link edu.nyu.cs.addressbook.Contact} list. This method allows 
     * modules to provide users with "read-only" access to internal lists. Query operations on the returned 
     * list "read through" to the specified list, and attempts to modify the returned list, whether direct or 
     * via its iterator, result in an {@link java.lang.UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no {@link edu.nyu.cs.addressbook.Contact} have 
     * been created, returns an empty list
     */
    public static List<Contact> getContacts() {
        return Collections.unmodifiableList(contactList);
    }
    
}
