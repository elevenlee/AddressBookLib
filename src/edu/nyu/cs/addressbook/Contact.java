package edu.nyu.cs.addressbook;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code Contact} class represents contact information.
 * <p>
 * {@code Contact} object might be obtains by calls on {@link edu.nyu.cs.addressbook.ContactFactory#getContact(java.lang.String)}
 * factory methods which will return the {@code Contact} object.
 * <p>
 * {@code Contact} objects are not constant; their values can be changed after they are created. Because 
 * {@code Contact} objects are implemented by synchronized list they can be shared.
 */
public class Contact implements Iterable<ContactEntry>, Comparable<Contact> {
    private final List<ContactEntry> contacts = Collections.synchronizedList(new LinkedList<ContactEntry>());;
    private final String id;
    private volatile int hashCode;
    
    /**
     * Initializes a newly created {@code Contact} object so that it records contact information.
     * <p>
     * @param id the identify of this {@code Contact} object
     */
    Contact(String id) {
        assert id != null && !id.equals("");
        
        this.id = id;
    }
    
    /**
     * Return string representation of this {@code Contact} identifier.
     * <p>
     * @return string representation of this {@code Contact} identifier
     */
    public String getID() {
        return id;
    }
    
    /**
     * Returns an unmodifiable view of the contact list. This method allows modules to provide users with 
     * "read-only" access to internal lists. Query operations on the returned list "read through" to the 
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result 
     * in an {@link java.lang.UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no contact have been created, returns an empty 
     * list
     */
    public List<ContactEntry> getContacts() {
        return Collections.unmodifiableList(contacts);
    }
    
    /**
     * Inserts the specified element at the specified position (lexicographical order) in this {@code Contact} 
     * list (optional operation). Shifts the element currently at that position (if any) and any subsequent 
     * elements to the right (adds one to their indices). The contact entry list will refuse to add null elements.
     * <p>
     * @param ce element to be inserted
     */
    public void add(ContactEntry ce) {
        ParameterChecker.nullCheck(ce, "contact entry");
        
        int index = 0;
        for (ContactEntry tmp : contacts) {
            if (tmp.compareTo(ce) > 0) {
                break;
            } else {
                index++;
            }
        }
        contacts.add(index, ce);
    }
    
    /**
     * Removes all of the elements from this {@code Contact} list (optional operation). The contact entry list 
     * will be empty after this call returns. 
     */
    public void clear() {
        contacts.clear();
    }
    
    /**
     * Removes the first occurrence of the specified element from this contact entry list, if it is present 
     * (optional operation).
     * <p>
     * If this {@code Contact} list does not contain the element, it is unchanged. Returns true if this contact 
     * entry list contained the specified element (or equivalently, if this contact entry list changed as a 
     * result of the call).
     * <p>
     * @param ce element to be removed from this contact entry list, if present
     * @return true if this contact entry list contained the specified element
     */
    public boolean remove(ContactEntry ce) {
        ParameterChecker.nullCheck(ce, "contact entry");
        
        return contacts.remove(ce);
    }
    
    /**
     * Returns the iterator over a set of elements of type {@link edu.nyu.cs.addressbook.ContactEntry}.
     * @return the iterator over a set of elements of type {@link edu.nyu.cs.addressbook.ContactEntry}
     */
    @Override
    public Iterator<ContactEntry> iterator() {
        return contacts.iterator();
    }
    
    /**
     * Compares the specified object with this {@code Contact} for equality. Returns true if and only if the 
     * specified object is also a {@code Contact} object, both objects have the same identifier.
     * <p>
     * This implementation first checks if the specified object is this {@code Contact}. If so, it returns 
     * true; if not, it checks if the specified object is a {@code Contact} object. If not, it returns false; 
     * if so, it iterates over both Contact objects, comparing corresponding fields. If any comparison returns 
     * false, this method returns false. Otherwise it returns true when the iterations complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code Contact} object
     * @return true if the specified object is equal to this {@code Contact} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof Contact)) {
            return false;
        }
        Contact c = (Contact) o;
        return id.equals(c.id);
    }
    
    /**
     * Returns the hash code value for this {@code Contact} object.
     * <p>
     * @return the hash code value for this {@code Contact} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = result * 31 + id.hashCode();
            hashCode = result;
        }
        return result;
    }
    
    /**
     * Returns the string representation of this {@code Contact} object. The string format is as "id: identifier\n
     * each contact entry string representation\nid: identifier\neach contact entry string representation\n ...".
     * <p>
     * Note that there is a single space separating the "id:" and identifier.
     * <p>
     * @return a string comprising contact fields
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id + "\n");
        for (ContactEntry ce : contacts) {
            sb.append(ce.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Compares the contacts represented by two {@code Contact} objects. Returns a negative integer, zero, or 
     * a positive integer as this object is less than, equal to, or greater than the specified object. 
     * <p>
     * @param c the {@code Contact} to be compared.
     * @return the value 0 if the contact represented by the argument is equal to the contact represented by 
     * this {@code Contact} object; a value less than 0 if the contact of this {@code Contact} is 
     * lexicographically less than the contact represented by the argument; and a value greater than 0 if the 
     * contact of this {@code Contact} is lexicographically greater than the contact represented by the argument.
     */
    @Override
    public int compareTo(Contact c) {
        ParameterChecker.nullCheck(c, "compared edu.nyu.cs.addressbook.Contact");
        
        return id.compareTo(c.id);
    }

}
