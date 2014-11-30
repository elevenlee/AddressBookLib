package edu.nyu.cs.addressbook;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import edu.nyu.cs.addressbook.builder.Builder;
import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code ContactEntry} class represents contact entry information.
 * <p>
 * {@code ContactEntry} objects are not constant; their values could be changed after they are created. The 
 * {@code ContactEntry} object is not thread-safe. To use it concurrently, user must surround each method 
 * invocation with external synchronization of the users' choosing.
 */
public class ContactEntry implements Comparable<ContactEntry> {
    private final String firstName;
    private final String lastName;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;
    private final List<PostalAddress> postalAddresses;
    private String note;
    
    /**
     * @author shenli
     * <p>
     * The {@code ContactEntryBuilder} class represents building contact entry information.
     * <p>
     * {@code ContactEntryBuilder} objects are not constant; their values could be changed after they are 
     * created. The {@code ContactEntryBuilder} object is not thread-safe. To use it concurrently, user must
     * surround each method invocation with external synchronization of the users' choosing.
     */
    public static class ContactEntryBuilder implements Builder<ContactEntry> {
        private final String firstName;
        private final String lastName;
        private List<PhoneNumber> phoneNumbers = new ArrayList<>();
        private List<EmailAddress> emailAddresses = new ArrayList<>();
        private List<PostalAddress> postalAddresses = new ArrayList<>();
        private String note = "";
        
        /**
         * Initializes a newly created {@code ContactEntryBuilder} object so that it records contact entry
         * information.
         * <p>
         * @param firstName the first name
         * @param lastName the last name
         */
        public ContactEntryBuilder(String firstName, String lastName) {
            ParameterChecker.nullCheck(firstName, "first name");
            ParameterChecker.nullCheck(lastName, "last name");
            
            this.firstName = firstName.trim();
            this.lastName = lastName.trim();
        }
        
        /**
         * Returns this {@code ContactEntryBuilder} with specified phone number(s).
         * <p>
         * @param numbers an array of specified phone number(s)
         * @return this {@code ContactEntryBuilder} with specified phone number(s)
         */
        public ContactEntryBuilder phoneNumber(PhoneNumber...numbers) {
            for (PhoneNumber pn : numbers) {
                ParameterChecker.nullCheck(pn, "phone number");
            }
            
            for (PhoneNumber pn : numbers) {
                phoneNumbers.add(pn);
            }
            return this;
        }
        
        /**
         * Returns this {@code ContactEntryBuilder} with specified email address(es).
         * <p>
         * @param addresses an array of specified email address(es)
         * @return this {@code ContactEntryBuilder} with specified email address(es)
         */
        public ContactEntryBuilder emailAddress(EmailAddress...addresses) {
            for (EmailAddress ea : addresses) {
                ParameterChecker.nullCheck(ea, "email address");
            }
            
            for (EmailAddress ea : addresses) {
                emailAddresses.add(ea);
            }
            return this;
        }
        
        /**
         * Returns this {@code ContactEntryBuilder} with specified postal address(es).
         * <p>
         * @param addresses an array of specified postal address(es)
         * @return this {@code ContactEntryBuilder} with specified postal address(es)
         */
        public ContactEntryBuilder postalAddress(PostalAddress...addresses) {
            for (PostalAddress pa : addresses) {
                ParameterChecker.nullCheck(pa, "postal address");
            }
            
            for (PostalAddress pa : addresses) {
                postalAddresses.add(pa);
            }
            return this;
        }
        
        /**
         * Returns this {@code ContactEntryBuilder} with specified note.
         * <p>
         * @param str the note
         * @return this {@code ContactEntryBuilder} with specified note
         */
        public ContactEntryBuilder note(String str) {
            ParameterChecker.nullCheck(str, "note");
            
            note = str;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public ContactEntry build() {
            return new ContactEntry(this);
        }
        
    }
    
    /**
     * Initializes a newly created {@code ContactEntry} object so that it records contact entry information.
     * <p>
     * @param builder the builder pattern object
     */
    private ContactEntry(ContactEntryBuilder builder) {
        assert builder != null;
        
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.phoneNumbers = builder.phoneNumbers;
        this.emailAddresses = builder.emailAddresses;
        this.postalAddresses = builder.postalAddresses;
        this.note = builder.note;
    }
    
    /**
     * Returns first name.
     * <p>
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Returns last name.
     * <p>
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Returns an unmodifiable view of the phone number list. This method allows modules to provide users with 
     * "read-only" access to internal lists. Query operations on the returned list "read through" to the 
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result 
     * in an {@link java.lang.UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no phone number have been created, returns an 
     * empty list
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return Collections.unmodifiableList(phoneNumbers);
    }
    
    /**
     * Returns an unmodifiable view of the email address list. This method allows modules to provide users 
     * with "read-only" access to internal lists. Query operations on the returned list "read through" to the 
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result 
     * in an {@link java.lang.UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no email address have been created, returns an 
     * empty list
     */
    public List<EmailAddress> getEmailAddresses() {
        return Collections.unmodifiableList(emailAddresses);
    }
    
    /**
     * Returns an unmodifiable view of the postal address list. This method allows modules to provide users 
     * with "read-only" access to internal lists. Query operations on the returned list "read through" to the 
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result 
     * in an {@link java.lang.UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no postal address have been created, returns an 
     * empty list
     */
    public List<PostalAddress> getPostalAddresses() {
        return Collections.unmodifiableList(postalAddresses);
    }
    
    /**
     * Returns note.
     * <p>
     * @return note
     */
    public String getNote() {
        return note;
    }
    
    /**
     * Sets the note.
     * <p>
     * @param note the note
     */
    public void setNote(String note) {
        ParameterChecker.nullCheck(note, "note");
        
        this.note = note;
    }
    
    /**
     * Appends the specified element to the end of phone number list (optional operation).
     * <p>
     * Returns true if this phone number list changed as a result of the call. (Returns false if this phone 
     * number list does not permit duplicates and already contains the specified element.)
     * <p>
     * The phone number list will refuse to add null elements. Also, it will refuse to add elements that has
     * existed.
     * <p>
     * @param pn element to be appended to phone number list
     * @return true if this phone number list changed as a result of the call.
     */
    public boolean addPhoneNumber(PhoneNumber pn) {
        ParameterChecker.nullCheck(pn, "phone number");
        
        return phoneNumbers.contains(pn) ? false : phoneNumbers.add(pn);
    }
    
    /**
     * Appends the specified element to the end of email address list (optional operation).
     * <p>
     * Returns true if this email address list changed as a result of the call. (Returns false if this email 
     * address list does not permit duplicates and already contains the specified element.)
     * <p>
     * The email address list will refuse to add null elements. Also, it will refuse to add elements that has
     * existed.
     * <p>
     * @param ea element to be appended to email address list
     * @return true if this email address list changed as a result of the call.
     */
    public boolean addEmailAddress(EmailAddress ea) {
        ParameterChecker.nullCheck(ea, "email address");
        
        return emailAddresses.contains(ea) ? false : emailAddresses.add(ea);
    }
    
    /**
     * Appends the specified element to the end of postal address list (optional operation).
     * <p>
     * Returns true if this postal address list changed as a result of the call. (Returns false if this postal 
     * address list does not permit duplicates and already contains the specified element.)
     * <p>
     * The postal address list will refuse to add null elements. Also, it will refuse to add elements that has
     * existed.
     * <p>
     * @param pa element to be appended to postal address list
     * @return true if this postal address list changed as a result of the call.
     */
    public boolean addPostalAddress(PostalAddress pa) {
        ParameterChecker.nullCheck(pa, "postal address");
        
        return postalAddresses.contains(pa) ? false : postalAddresses.add(pa);
    }
    
    /**
     * Removes all of the elements from this phone number list (optional operation). 
     * <p>
     * The list will be empty after this call returns.
     */
    public void clearPhoneNumbers() {
        phoneNumbers.clear();
    }
    
    /**
     * Removes all of the elements from this email address list (optional operation).
     * <p>
     * The list will be empty after this call returns.
     */
    public void clearEmailAddresses() {
        emailAddresses.clear();
    }
    
    /**
     * Removes all of the elements from this postal address list (optional operation).
     * <p>
     * The list will be empty after this call returns.
     */
    public void clearPostalAddresses() {
        postalAddresses.clear();
    }
    
    /**
     * Returns true if this phone number list contains the specified area code.
     * <p>
     * @param areaCode area code whose presence in this phone number list is to be tested 
     * @return true if this phone number list contains the specified area code
     */
    public boolean containsAreaCode(int areaCode) {
        for (PhoneNumber pn : phoneNumbers) {
            if (pn.getAreaCode() == (short) areaCode) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this phone number list contains the specified prefix.
     * <p>
     * @param prefix prefix whose presence in this phone number list is to be tested
     * @return true if this phone number list contains the specified prefix
     */
    public boolean containsPrefix(int prefix) {
        for (PhoneNumber pn : phoneNumbers) {
            if (pn.getPrefix() == (short) prefix) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this phone number list contains the specified line number.
     * <p>
     * @param lineNumber line number whose presence in this phone number list is to be tested
     * @return true if this phone number list contains the specified line number
     */
    public boolean containsLineNumber(int lineNumber) {
        for (PhoneNumber pn : phoneNumbers) {
            if (pn.getLineNumber() == (short) lineNumber) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this email address list contains the specified user name.
     * <p>
     * @param username user name whose presence in this email address list is to be tested
     * @return true if this email address list contains the specified user name
     */
    public boolean containsUsername(String username) {
        ParameterChecker.nullCheck(username, "user name");
        ParameterChecker.emptyCheck(username, "user name");
        
        for (EmailAddress ea : emailAddresses) {
            if (ea.getUsername().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this email address list contains the specified domain.
     * <p>
     * @param domain domain whose presence in this email address list is to be tested
     * @return true if this email address list contains the specified domain
     */
    public boolean containsDomain(String domain) {
        ParameterChecker.nullCheck(domain, "domain");
        ParameterChecker.emptyCheck(domain, "domain");
        
        for (EmailAddress ea : emailAddresses) {
            if (ea.getDomain().equals(domain.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this postal address list contains the specified zipcode.
     * <p>
     * @param zipcode zipcode whose presence in this postal address list is to be tested
     * @return true if this postal address list contains the specified zipcode
     */
    public boolean containsZipcode(int zipcode) {
        for (PostalAddress pa : postalAddresses) {
            if (pa.getZipCode() == zipcode) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this postal address list contains the specified state. 
     * <p>
     * @param state state whose presence in this postal address list is to be tested
     * @return true if this postal address list contains the specified state
     */
    public boolean containsState(State state) {
        ParameterChecker.nullCheck(state, "state");
        
        for (PostalAddress pa : postalAddresses) {
            if (pa.getState() == state) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this postal address list contains the specified city.
     * <p>
     * @param city city whose presence in this postal address list is to be tested
     * @return true if this postal address list contains the specified city
     */
    public boolean containsCity(String city) {
        ParameterChecker.nullCheck(city, "city");
        
        for (PostalAddress pa : postalAddresses) {
            if (pa.getCity().equals(city)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this postal address list contains the specified street.
     * <p>
     * @param street street whose presence in this postal address list is to be tested
     * @return true if this postal address list contains the specified street
     */
    public boolean containsStreet(String street) {
        ParameterChecker.nullCheck(street, "street");
        
        for (PostalAddress pa : postalAddresses) {
            if (pa.getStreet().contains(street)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Removes the first occurrence of the specified element from this phone number list, if it is present 
     * (optional operation).
     * <p>
     * If this phone number list does not contain the element, it is unchanged. Returns true if this phone 
     * number list contained the specified element (or equivalently, if this phone number list changed as a 
     * result of the call).
     * <p>
     * @param pn element to be removed from this phone number list, if present 
     * @return true if this phone number list contained the specified element
     */
    public boolean removePhoneNumber(PhoneNumber pn) {
        ParameterChecker.nullCheck(pn, "phone number");
        
        return phoneNumbers.remove(pn);
    }
    
    /**
     * Removes the first occurrence of the specified element from this email address list, if it is present 
     * (optional operation).
     * <p>
     * If this email address list does not contain the element, it is unchanged. Returns true if this email 
     * address list contained the specified element (or equivalently, if this email address list changed as a 
     * result of the call).
     * <p>
     * @param ea element to be removed from this email address list, if present 
     * @return true if this email address list contained the specified element
     */
    public boolean removeEmailAddress(EmailAddress ea) {
        ParameterChecker.nullCheck(ea, "email address");
        
        return emailAddresses.remove(ea);
    }
    
    /**
     * Removes the first occurrence of the specified element from this postal address list, if it is present 
     * (optional operation).
     * <p>
     * If this postal address list does not contain the element, it is unchanged. Returns true if this postal 
     * address list contained the specified element (or equivalently, if this postal address list changed as 
     * a result of the call).
     * <p>
     * @param pa element to be removed from this postal address list, if present 
     * @return true if this postal address list contained the specified element
     */
    public boolean removePostalAddress(PostalAddress pa) {
        ParameterChecker.nullCheck(pa, "postal address");
        
        return postalAddresses.remove(pa);
    }
    
    /**
     * Compares the specified object with this {@code ContactEntry} for equality. Returns true if and only if 
     * the specified object is also a {@code ContactEntry} object, both objects have the same last name and 
     * first name.
     * <p>
     * This implementation first checks if the specified object is this {@code ContactEntry}. If so, it returns 
     * true; if not, it checks if the specified object is a {@code ContactEntry} object. If not, it returns 
     * false; if so, it iterates over both {@code ContactEntry} objects, comparing corresponding fields. If 
     * any comparison returns false, this method returns false. Otherwise it returns true when the iterations 
     * complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code ContactEntry} object
     * @return true if the specified object is equal to this {@code ContactEntry} object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof ContactEntry)) {
            return false;
        }
        ContactEntry ce = (ContactEntry) o;
        return lastName.equals(ce.lastName)
                && firstName.equals(ce.firstName);
    }
    
    /**
     * Returns the hash code value for this {@code ContactEntry} object.
     * <p>
     * @return the hash code value for this {@code ContactEntry} object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = result * prime + lastName.hashCode();
        result = result * prime + firstName.hashCode();
        return result;
    }
    
    /**
     * Returns string representation of this {@code ContactEntry} object. The string format is as "firstname 
     * lastname\nphone number ...\nemail address ...\npostal address ...\nnote". Note that there is a single 
     * space separating the first name and last name.
     * <p>
     * @return a string comprising contact entry fields
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName + " " + lastName + "\n");
        for (PhoneNumber pn : phoneNumbers) {
            sb.append(pn.toString() + "\n");
        }
        for (EmailAddress ea : emailAddresses) {
            sb.append(ea.toString() + "\n");
        }
        for (PostalAddress pa : postalAddresses) {
            sb.append(pa.toString() + "\n");
        }
        sb.append(note);
        return sb.toString();
    }
    
    /**
     * Compares the contact entries represented by two {@code ContactEntry} objects. Returns a negative integer, 
     * zero, or a positive integer as this object is less than, equal to, or greater than the specified object. 
     * <p>
     * @param ce the {@code ContactEntry} to be compared.
     * @return the value 0 if the contact entry represented by the argument is equal to the contact entry 
     * represented by this {@code ContactEntry}; a value less than 0 if the contact entry of this 
     * {@code ContactEntry} is lexicographically less than the contact entry represented by the argument; and 
     * a value greater than 0 if the contact entry of this {@code ContactEntry} is lexicographically greater 
     * than the contact entry represented by the argument.
     */
    @Override
    public int compareTo(ContactEntry ce) {
        ParameterChecker.nullCheck(ce, "compared edu.nyu.cs.addressbook.ContactEntry");
        
        int lastNameDiff = lastName.compareTo(ce.lastName);
        if (lastNameDiff != 0) {
            return lastNameDiff;
        }
        return firstName.compareTo(ce.firstName);
    }
    
}