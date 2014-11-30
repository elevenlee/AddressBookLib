package edu.nyu.cs.addressbook.component;

import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code PhoneNumber} class represents phone number information.
 * <p>
 * {@code PhoneNumber} are constant; their value could not be changed after they are created. Because 
 * {@code PhoneNumber} objects are immutable they could be shared.
 */
public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private static final short MAX_AREACODE = 999;
    private static final short MAX_PREFIX = 999;
    private static final short MAX_LINENUMBER = 9999;
    
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    private volatile int hashCode;
    
    /**
     * Initializes a newly created {@code PhoneNumber} object so that it records phone number information. 
     * <p>
     * @param areaCode the area code
     * @param prefix the prefix
     * @param lineNumber the line number
     */
    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        ParameterChecker.rangeCheck(areaCode, MAX_AREACODE, "area code");
        ParameterChecker.rangeCheck(prefix, MAX_PREFIX, "prefix");
        ParameterChecker.rangeCheck(lineNumber, MAX_LINENUMBER, "line number");
        
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    /**
     * Returns area code.
     * <p>
     * @return area code
     */
    public short getAreaCode() {
        return areaCode;
    }
    
    /**
     * Returns prefix.
     * <p>
     * @return prefix
     */
    public short getPrefix() {
        return prefix;
    }
    
    /**
     * Returns line number.
     * <p>
     * @return line number
     */
    public short getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Compares the specified object with this {@code PhoneNumber} for equality. Returns true if and only if 
     * the specified object is also a {@code PhoneNumber} object, both objects have the same area code, prefix, 
     * and line number.
     * <p>
     * This implementation first checks if the specified object is this {@code PhoneNumber}. If so, it returns 
     * true; if not, it checks if the specified object is a {@code PhoneNumber} object. If not, it returns 
     * false; if so, it iterates over both {@code PhoneNumber} objects, comparing corresponding fields. If any 
     * comparison returns false, this method returns false. Otherwise it returns true when the iterations 
     * complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code PhoneNumber}
     * @return true if the specified object is equal to this {@code PhoneNumber}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber pn = (PhoneNumber) o;
        return areaCode == pn.areaCode
                && prefix == pn.prefix
                && lineNumber == pn.lineNumber;
    }
    
    /**
     * Returns the hash code value for this {@code PhoneNumber} object.
     * <p>
     * @return the hash code value for this {@code PhoneNumber} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            final int prime = 31;
            result = 17;
            result = result * prime + areaCode;
            result = result * prime + prefix;
            result = result * prime + lineNumber;
            hashCode = result;
        }
        return result;
    }
    
    /**
     * Returns the string representation of this {@code PhoneNumber} object. The string consists of fourteen 
     * characters whose format is "(XXX) YYY-ZZZZ", where XXX is the area code, YYY is the prefix, and ZZZZ 
     * is the line number. (Each of the capital letters represents a single decimal digit.)
     * <p>
     * If any of the three parts of this phone number is too small to fill up its field, the field is padded 
     * with leading zeros. For example, if the value of the line number is 123, the last four characters of 
     * the string representation will be "0123".
     * <p>
     * Note that there is a single space separating the closing parenthesis after the area code from the first 
     * digit of the prefix.
     * <p>
     * @return string comprising the area code, prefix, and line number
     */
    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d",
                                areaCode, prefix, lineNumber);
    }
    
    /**
     * Creates and returns a copy of this {@code PhoneNumber} object.
     * <p>
     * The method clone for class {@code PhoneNumber} performs a specific cloning operation. First, if the 
     * class of this {@code PhoneNumber} object does not implement the interface {@link java.lang.Cloneable}, 
     * then a {@link java.lang.CloneNotSupportedException} is thrown. Note that all arrays are considered to 
     * implement the interface {@link java.lang.Cloneable}. Otherwise, this method creates a new instance of 
     * the class of this {@code PhoneNumber} object and initializes all its fields with exactly the contents 
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
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    /**
     * Compares the phone numbers represented by two {@code PhoneNumber} objects. Returns a negative integer, 
     * zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * <p>
     * @param pn the {@code PhoneNumber} to be compared.
     * @return the value 0 if the phone number represented by the argument is equal to the phone number 
     * represented by this {@code PhoneNumber}; a value less than 0 if the phone number of this {@code PhoneNumber} 
     * is less than the phone number represented by the argument; and a value greater than 0 if the phone number
     * of this {@code PhoneNumber} is greater than the phone number represented by the argument.
     */
    @Override
    public int compareTo(PhoneNumber pn) {
        ParameterChecker.nullCheck(pn, "compared edu.nyu.cs.addressbook.component.PhoneNumber");
        
        int areaCodeDiff = areaCode - pn.areaCode;
        if (areaCodeDiff != 0) {
            return areaCodeDiff;
        }
        int prefixDiff = prefix - pn.prefix;
        if (prefixDiff != 0) {
            return prefixDiff;
        }
        return lineNumber - pn.lineNumber;
    }

}
