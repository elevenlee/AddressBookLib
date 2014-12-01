package edu.nyu.cs.addressbook.search.impl;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.builder.Builder;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code ContactField} class represents criteria information.
 * <p>
 * The specified criteria is the element should match the specified contact entry fields. {@code ContactField} 
 * are constant; their value could not be changed after they are created. Because {@code ContactField} objects 
 * are immutable they could be shared.
 */
public class ContactField implements SearchCriteria {
    private final String firstName;
    private final String lastName;
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    private final String username;
    private final String domain;
    private final int zipCode;
    private final State state;
    private final String city;
    private final String street;
    private final String note;
    private volatile int hashCode;
    
    /**
     * @author shenli
     * <p>
     * The {@code ContactFieldBuilder} class represents building contact matcher criteria information.
     * <p>
     * {@code ContactFieldBuilder} objects are not constant; their values could be changed after they are 
     * created. The {@code ContactFieldBuilder} object is not thread-safe. To use it concurrently, user must
     * surround each method invocation with external synchronization of the users' choosing.
     */
    public static class ContactFieldBuilder implements Builder<ContactField> {
        private String firstName = "";
        private String lastName = "";
        private short areaCode = 0;
        private short prefix = 0;
        private short lineNumber = 0;
        private String username = "";
        private String domain = "";
        private int zipCode = 0;
        private State state = State.NONE;
        private String city = "";
        private String street = "";
        private String note = "";
        
        /**
         * Initializes a newly created {@code ContactFieldBuilder} object so that it records contact matcher
         * criteria information.
         */
        public ContactFieldBuilder() {
            
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified first name.
         * <p>
         * @param str the first name
         * @return this {@code {@code ContactFieldBuilder}} with specified first name
         */
        public ContactFieldBuilder firstName(String str) {
            ParameterChecker.nullCheck(str, "first name");
            
            firstName = str.trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified last name.
         * <p>
         * @param str the last name
         * @return this {@code ContactFieldBuilder} with specified last name
         */
        public ContactFieldBuilder lastName(String str) {
            ParameterChecker.nullCheck(str, "last name");
            
            lastName = str.trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified area code.
         * <p>
         * @param val the area code
         * @return this {@code ContactFieldBuilder} with specified area code
         */
        public ContactFieldBuilder areaCode(int val) {
            areaCode = (short) val;
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified prefix.
         * <p>
         * @param val the prefix
         * @return this {@code ContactFieldBuilder} with specified prefix
         */
        public ContactFieldBuilder prefix(int val) {
            prefix = (short) val;
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified line number.
         * <p>
         * @param val the line number
         * @return this {@code ContactFieldBuilder} with specified line number
         */
        public ContactFieldBuilder lineNumber(int val) {
            lineNumber = (short) val;
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified user name.
         * <p>
         * @param str the user name
         * @return this {@code ContactFieldBuilder} with specified user name
         */
        public ContactFieldBuilder username(String str) {
            ParameterChecker.nullCheck(str, "user name");
            
            username = str.toLowerCase().trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified domain.
         * <p>
         * @param str the domain
         * @return this {@code ContactFieldBuilder} with specified domain
         */
        public ContactFieldBuilder domain(String str) {
            ParameterChecker.nullCheck(str, "domain");
            
            domain = str.toLowerCase().trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified zip code.
         * <p>
         * @param val the zip code
         * @return this {@code ContactFieldBuilder} with specified zip code
         */
        public ContactFieldBuilder zipCode(int val) {
            zipCode = val;
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified state.
         * <p>
         * @param val the state
         * @return this {@code ContactFieldBuilder} with specified state
         */
        public ContactFieldBuilder state(State val) {
            ParameterChecker.nullCheck(val, "state");
            
            state = val;
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified city.
         * <p>
         * @param str the city
         * @return this {@code ContactFieldBuilder} with specified city
         */
        public ContactFieldBuilder city(String str) {
            ParameterChecker.nullCheck(str, "city");
            
            city = str.trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified street.
         * <p>
         * @param str the street
         * @return this {@code ContactFieldBuilder} with specified street
         */
        public ContactFieldBuilder street(String str) {
            ParameterChecker.nullCheck(str, "street");
            
            street = str.trim();
            return this;
        }
        
        /**
         * Returns this {@code ContactFieldBuilder} with specified note.
         * <p>
         * @param str the note
         * @return this {@code ContactFieldBuilder} with specified note
         */
        public ContactFieldBuilder note(String str) {
            ParameterChecker.nullCheck(str, "note");
            
            note = str;
            return this;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public ContactField build() {
            return new ContactField(this);
        }
        
    }
    
    /**
     * Initializes a newly created {@code ContactField} object so that it records contact matcher criteria 
     * information.
     * <p>
     * @param builder the builder pattern object
     */
    private ContactField(ContactFieldBuilder builder) {
        assert builder != null;
        
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.areaCode = builder.areaCode;
        this.prefix = builder.prefix;
        this.lineNumber = builder.lineNumber;
        this.username = builder.username;
        this.domain = builder.domain;
        this.zipCode = builder.zipCode;
        this.state = builder.state;
        this.city = builder.city;
        this.street = builder.street;
        this.note = builder.note;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchCondition(ContactEntry element) {
        if (element == null) {
            return false;
        }
        boolean result = true;
        if (!firstName.equals("")) {
            result = result && firstName.equals(element.getFirstName());
        }
        if (!lastName.equals("")) {
            result = result && lastName.equals(element.getLastName());
        }
        if (areaCode != 0) {
            result = result && element.containsAreaCode(areaCode);
        }
        if (prefix != 0) {
            result = result && element.containsPrefix(prefix);
        }
        if (lineNumber != 0) {
            result = result && element.containsLineNumber(lineNumber);
        }
        if (!username.equals("")) {
            result = result && element.containsUsername(username);
        }
        if (!domain.equals("")) {
            result = result && element.containsDomain(domain);
        }
        if (zipCode != 0) {
            result = result && element.containsZipcode(zipCode);
        }
        if (state != State.NONE) {
            result = result && element.containsState(state);
        }
        if (!city.equals("")) {
            result = result && element.containsCity(city);
        }
        if (!street.equals("")) {
            result = result && element.containsStreet(street);
        }
        if (!note.equals("")) {
            result = result && element.getNote().contains(note);
        }
        return result;
    }
    
    /**
     * Compares the specified object with this {@code ContactField} for equality. Returns true if and only if 
     * the specified object is also a {@code ContactField} object, both objects have the same first name, last 
     * name, area code, prefix, line number, user name, domain, zipcode, state, city, street, and note.
     * <p>
     * This implementation first checks if the specified object is this {@code ContactField}. If so, it returns 
     * true; if not, it checks if the specified object is a {@code ContactField} object. If not, it returns 
     * false; if so, it iterates over both {@code ContactField} objects, comparing corresponding fields. If 
     * any comparison returns false, this method returns false. Otherwise it returns true when the iterations 
     * complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code ContactField} object
     * @return true if the specified object is equal to this {@code ContactField} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof ContactField)) {
            return false;
        }
        ContactField cf = (ContactField) o;
        return firstName.equals(cf.firstName)
                && lastName.equals(cf.lastName)
                && areaCode == cf.areaCode
                && prefix == cf.prefix
                && lineNumber == cf.lineNumber
                && username.equals(cf.username)
                && domain.equals(cf.domain)
                && zipCode == cf.zipCode
                && state == cf.state
                && city.equals(cf.city)
                && street.equals(cf.street)
                && note.equals(cf.note);
    }
    
    /**
     * Returns the hash code value for this {@code ContactField} object.
     * <p>
     * @return the hash code value for this {@code ContactField} object
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            final int prime = 31;
            result = 17;
            result = result * prime + firstName.hashCode();
            result = result * prime + lastName.hashCode();
            result = result * prime + areaCode;
            result = result * prime + prefix;
            result = result * prime + lineNumber;
            result = result * prime + username.hashCode();
            result = result * prime + domain.hashCode();
            result = result * prime + zipCode;
            result = result * prime + state.ordinal();
            result = result * prime + city.hashCode();
            result = result * prime + street.hashCode();
            result = result * prime + note.hashCode();
            hashCode = result;
        }
        return result;
    }
    
    /**
     * Return string representation of this {@code ContactField} object.
     * <p>
     * @return a string representation of this {@code ContactField} object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!firstName.equals("")) {
            sb.append("first name: " + firstName + "\n");
        }
        if (!lastName.equals("")) {
            sb.append("last name: " + lastName + "\n");
        }
        if (areaCode != 0) {
            sb.append("area code: " + areaCode + "\n");
        }
        if (prefix != 0) {
            sb.append("prefix: " + prefix + "\n");
        }
        if (lineNumber != 0) {
            sb.append("line number: " + lineNumber + "\n");
        }
        if (!username.equals("")) {
            sb.append("user name: " + username + "\n");
        }
        if (!domain.equals("")) {
            sb.append("domain: " + domain + "\n");
        }
        if (zipCode != 0) {
            sb.append(String.format("zipcode: %05d\n", zipCode));
        }
        if (state != State.NONE) {
            sb.append("state: " + state.name() + "\n");
        }
        if (!city.equals("")) {
            sb.append("city: " + city + "\n");
        }
        if (!street.equals("")) {
            sb.append("street: " + street + "\n");
        }
        if (!note.equals("")) {
            sb.append("note: " + note + "\n");
        }
        return sb.toString();
    }

}
