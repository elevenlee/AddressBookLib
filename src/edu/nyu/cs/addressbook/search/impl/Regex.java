package edu.nyu.cs.addressbook.search.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code Regex} class represents criteria information.
 * <p>
 * The specified criteria is the element should match the specified regular expression. {@code Regex} are 
 * constant; their value could not be changed after they are created. Because {@code Regex} objects are 
 * immutable they could be shared.
 */
public class Regex implements SearchCriteria {
    private final String regex;
    private volatile int hashCode;
    
    /**
     * Initializes a newly created {@code Regex} object so that it records regular expression criteria 
     * information.
     * <p>
     * @param regex the regular expression
     */
    public Regex(String regex) {
        ParameterChecker.nullCheck(regex, "regular expression");
        ParameterChecker.emptyCheck(regex, "regular expression");
        
        this.regex = regex;
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchCondition(ContactEntry element) {
        if (element == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element.toString());
        return matcher.find();
    }
    
    /**
     * Compares the specified object with this {@code Regex} object for equality. Returns true if and only if 
     * the specified object is also a {@code Regex} object, both objects have the same regular expression.
     * <p>
     * This implementation first checks if the specified object is this {@code Regex}. If so, it returns true; 
     * if not, it checks if the specified object is a {@code Regex} object. If not, it returns false; if so, 
     * it iterates over both {@code Regex} objects, comparing corresponding fields. If any comparison returns 
     * false, this method returns false. Otherwise it returns true when the iterations complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code Regex} object
     * @return true if the specified object is equal to this {@code Regex} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof Regex)) {
            return false;
        }
        Regex r = (Regex) o;
        return regex.equals(r.regex);
    }
    
    /**
     * Returns the hash code value for this {@code Regex} object.
     * <p>
     * @return the hash code value for this {@code Regex} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = result * 31 + regex.hashCode();
            hashCode = result;
        }
        return result;
    }

    /**
     * Return string representation of this {@code Regex} object. The string representation consists of the 
     * regular expression.
     * <p>
     * @return a string representation of this {{@code Regex} object
     */
    @Override
    public String toString() {
        return regex;
    }
    
}
