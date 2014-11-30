package edu.nyu.cs.addressbook.component;

import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code EmailAddress} class represents email address information.
 * <p>
 * {@code EmailAddress} are constant; their value could not be changed after they are created. Because 
 * {@code EmailAddress} objects are immutable they could be shared.
 */
public final class EmailAddress implements Cloneable, Comparable<EmailAddress> {
    private final String username;
    private final String domain;
    private volatile int hashCode;
    
    /**
     * Initializes a newly created {@code EmailAddress} object so that it records email address information. 
     * <p>
     * @param username the user name
     * @param domain the domain
     */
    public EmailAddress(String username, String domain) {
        ParameterChecker.nullCheck(username, "username");
        ParameterChecker.nullCheck(domain, "domain");
        ParameterChecker.emptyCheck(username.trim(), "username");
        ParameterChecker.emptyCheck(domain.trim(), "domain");
        
        this.username = username.toLowerCase().trim();
        this.domain = domain.toLowerCase().trim();
    }
    
    /**
     * Returns user name.
     * <p>
     * @return user name
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Returns domain.
     * <p>
     * @return domain
     */
    public String getDomain() {
        return domain;
    }
    
    /**
     * Compares the specified object with this {@code EmailAddress} for equality. Returns true if and only if 
     * the specified object is also an {@code EmailAddress} object, both objects have the same user name and 
     * domain.
     * <p>
     * This implementation first checks if the specified object is this {@code EmailAddress}. If so, it returns 
     * true; if not, it checks if the specified object is an {@code EmailAddress} object. If not, it returns 
     * false; if so, it iterates over both {@code EmailAddress} objects, comparing corresponding fields. If 
     * any comparison returns false, this method returns false. Otherwise it returns true when the iterations 
     * complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code EmailAddress} object
     * @return true if the specified object is equal to this {@code EmailAddress} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof EmailAddress)) {
            return false;
        }
        EmailAddress ea = (EmailAddress) o;
        return domain.equals(ea.domain)
                && username.equals(ea.username);
    }
    
    /**
     * Returns the hash code value for this {@code EmailAddress} object.
     * <p>
     * @return the hash code value for this {@code EmailAddress} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            final int prime = 31;
            result = 17;
            result = result * prime + username.hashCode();
            result = result * prime + domain.hashCode();
            hashCode = result;
        }
        return result;
    }
    
    /**
     * Returns the string representation of this {@code EmailAddress}. The string format is "username@domain".
     * <p>
     * @return string comprising the user name and domain
     */
    @Override
    public String toString() {
        return username + "@" + domain;
    }
    
    /**
     * Creates and returns a copy of this object.
     * <p>
     * The method clone for class {@code EmailAddress} performs a specific cloning operation. First, if the 
     * class of this {@code EmailAddress} object does not implement the interface {@link java.lang.Cloneable}, 
     * then a {@link java.lang.CloneNotSupportedException} is thrown. Note that all arrays are considered to 
     * implement the interface {@link java.lang.Cloneable}. Otherwise, this method creates a new instance of 
     * the class of this {@code EmailAddress} object and initializes all its fields with exactly the contents 
     * of the corresponding fields of this object, as if by assignment; the contents of the fields are not 
     * themselves cloned. Thus, this method performs a "shallow copy" of this object, not a "deep copy" 
     * operation. 
     * <p>
     * @return a clone of this instance
     * @throws CloneNotSupportedException if the object's class does not support the {@link java.lang.Cloneable}
     * interface. Subclasses that override the clone method can also throw this exception to indicate that an 
     * instance cannot be cloned.
     */
    @Override
    public EmailAddress clone() {
        try {
            return (EmailAddress) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    /**
     * Compares the email addresses represented by two {@code EmailAddress} objects. Returns a negative integer, 
     * zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * <p> 
     * @param ea the {@code EmailAddress} to be compared.
     * @return the value 0 if the email address represented by the argument is equal to the email address 
     * represented by this {@code EmailAddress}; a value less than 0 if the email address of this {@code EmailAddress} 
     * is lexicographically less than the email address represented by the argument; and a value greater than
     * 0 if the email address of this {@code EmailAddress} is lexicographically greater than the email address 
     * represented by the argument.
     */
    @Override
    public int compareTo(EmailAddress ea) {
        ParameterChecker.nullCheck(ea, "compared edu.nyu.cs.addressbook.component.EmailAddress");
        
        int usernameDiff = username.compareTo(ea.username);
        if (usernameDiff != 0) {
            return usernameDiff;
        }
        return domain.compareTo(ea.domain);
    }
    
}
