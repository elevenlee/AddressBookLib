package edu.nyu.cs.addressbook.component;

import edu.nyu.cs.addressbook.builder.Builder;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code PostalAddress} class represents postal address information.
 * <p>
 * {@code PostalAddress} are constant; their value could not be changed after they are created. Because 
 * {@code PostalAddress} objects are immutable they could be shared.
 */
public final class PostalAddress implements Cloneable, Comparable<PostalAddress> {
    private static final int MAX_ZIPCODE = 99999;
    private static final String DEFAULT_COUNTRY = "The United States";
    
    private final int zipCode;
    private final State state;
    private final String city;
    private final String street;
    private volatile int hashCode;
    
    /**
     * @author shenli
     * <p>
     * The {@code PostalAddressBuilder} class represents building postal address information
     * <p>
     * {@code PostalAddressBuilder} objects are not constant; their values can be changed after they are
     * created. The {@code PostalAddressBuilder} object is not thread-safe. To use it concurrently, user 
     * must surround each method invocation with external synchronization of the users' choosing.
     */
    public static class PostalAddressBuilder implements Builder<PostalAddress> {
        private int zipCode = 0;
        private State state = State.NONE;
        private String city = "";
        private String street = "";
        
        /**
         * Initializes a newly created {@code PostalAddressBuilder} object so that it records postal address
         * information.
         */
        public PostalAddressBuilder() {
            
        }
        
        /**
         * Returns this {@code PostalAddressBuilder} with specified zipcode.
         * <p>
         * @param val the zipcode
         * @return this {@code PostalAddressBuilder} with specified zipcode
         */
        public PostalAddressBuilder zipCode(int val) {
            ParameterChecker.rangeCheck(val, MAX_ZIPCODE, "zip code");
            
            zipCode = val;
            return this;
        }
        
        
        /**
         * Returns this {@code PostalAddressBuilder} with specified state.
         * <p>
         * @param val the state
         * @return this {@code PostalAddressBuilder} with specified state
         */
        public PostalAddressBuilder state(State val) {
            ParameterChecker.nullCheck(val, "state");
            
            state = val;
            return this;
        }
        
        /**
         * Returns this {@code PostalAddressBuilder} with specified city.
         * <p>
         * @param str the city
         * @return this {@code PostalAddressBuilder} with specified city
         */
        public PostalAddressBuilder city(String str) {
            ParameterChecker.nullCheck(str, "city");
            
            city = str.trim();
            return this;
        }
        
        /**
         * Returns this {@code PostalAddressBuilder} with specified street.
         * <p>
         * @param str the street
         * @return this {@code PostalAddressBuilder} with specified street
         */
        public PostalAddressBuilder street(String str) {
            ParameterChecker.nullCheck(str, "street");
            
            street = str.trim();
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        public PostalAddress build() {
            return new PostalAddress(this);
        }
        
    }
    
    /**
     * Initializes a newly created {@code PostalAddress} object so that it records postal address information.
     * <p>
     * @param builder the builder pattern object
     */
    private PostalAddress(PostalAddressBuilder builder) {
        assert builder != null;
        
        this.zipCode = builder.zipCode;
        this.state = builder.state;
        this.city = builder.city;
        this.street = builder.street;
    }
    
    /**
     * Returns zipcode.
     * <p>
     * @return zipcode
     */
    public int getZipCode() {
        return zipCode;
    }
    
    /**
     * Returns state.
     * <p>
     * @return state
     */
    public State getState() {
        return state;
    }
    
    /**
     * Returns city.
     * <p>
     * @return city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Returns street.
     * <p>
     * @return street
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * Compares the specified object with this {@code PostalAddress} for equality. Returns true if and only 
     * if the specified object is also a {@code PostalAddress} object, both objects have the same zipcode, 
     * state, city, and street.
     * <p>
     * This implementation first checks if the specified object is this {@code PostalAddress}. If so, it returns 
     * true; if not, it checks if the specified object is a {@code PostalAddress} object. If not, it returns 
     * false; if so, it iterates over both {@code PostalAddress} objects, comparing corresponding fields. If 
     * any comparison returns false, this method returns false. Otherwise it returns true when the iterations 
     * complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code PostalAddress} object
     * @return true if the specified object is equal to this {@code PostalAddress} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof PostalAddress)) {
            return false;
        }
        PostalAddress pa = (PostalAddress) o;
        return zipCode == pa.zipCode
                && state == pa.state
                && city.equals(pa.city)
                && street.equals(pa.street);
    }
    
    /**
     * Returns the hash code value for this {@code PostalAddress} object.
     * <p>
     * @return the hash code value for this {@code PostalAddress} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            final int prime = 31;
            result = 17;
            result = result * prime + zipCode;
            result = result * prime + state.ordinal();
            result = result * prime + city.hashCode();
            result = result * prime + street.hashCode();
            hashCode = result;
        }
        return result;
    }
    
    /**
     * Returns string representation of this postal address. The string format is as "street\ncity, state XXXXX\n
     * The United States", where XXXXX is the zipcode. (Each of the capital letters represents a single decimal
     * digit.)
     * <p>
     * If the zipcode is too small to fill up its field, the field is padded with leading zeros. For example, 
     * if the value of the zipcode is 1234, the zipcode string representation will be "01234".
     * <p>
     * @return a string comprising postal address fields
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!street.equals("")) {
            sb.append(street + "\n");
        }
        if (!city.equals("")) {
            sb.append(city + ", ");
        }
        if (state != State.NONE) {
            sb.append(state.name() + " ");
        }
        if (zipCode != 0) {
            sb.append(String.format("%05d", zipCode));
        }
        sb.append("\n" + DEFAULT_COUNTRY);
        return sb.toString();
    }
    
    /**
     * Creates and returns a copy of this object.
     * <p>
     * The method clone for class {@code PostalAddress} performs a specific cloning operation. First, if the 
     * class of this {@code PostalAddress} object does not implement the interface {@link java.lang.Cloneable}, 
     * then a {@link java.lang.CloneNotSupportedException} is thrown. Note that all arrays are considered to 
     * implement the interface {@link java.lang.Cloneable}. Otherwise, this method creates a new instance of 
     * the class of this {@code PostalAddress} object and initializes all its fields with exactly the contents 
     * of the corresponding fields of this object, as if by assignment; the contents of the fields are not 
     * themselves cloned. Thus, this method performs a "shallow copy" of this object, not a "deep copy" 
     * operation. 
     * <p>
     * @return a clone of this instance
     */
    @Override
    public PostalAddress clone() {
        try {
            return (PostalAddress) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    /**
     * Compares the postal addresses represented by two {@code PostalAddress} objects. Returns a negative 
     * integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified 
     * object. 
     * <p>
     * @param pa the {@code PostalAddress} to be compared.
     * @return the value 0 if the postal address represented by the argument is equal to the postal address 
     * represented by this {@code PostalAddress}; a value less than 0 if the postal address of this 
     * {@code PostalAddress} is lexicographically less than the postal address represented by the argument; 
     * and a value greater than 0 if the postal address of this {@code PostalAddress} is lexicographically 
     * greater than the postal address represented by the argument.
     */
    @Override
    public int compareTo(PostalAddress pa) {
        ParameterChecker.nullCheck(pa, "compared edu.nyu.cs.addressbook.component.PostalAddress");
        
        int zipCodeDiff = zipCode - pa.zipCode;
        if (zipCodeDiff != 0) {
            return zipCodeDiff;
        }
        int stateDiff = state.ordinal() - pa.state.ordinal();
        if (stateDiff != 0) {
            return stateDiff;
        }
        int cityDiff = city.compareTo(pa.city);
        if (cityDiff != 0) {
            return cityDiff;
        }
        return street.compareTo(pa.street);
    }

}
