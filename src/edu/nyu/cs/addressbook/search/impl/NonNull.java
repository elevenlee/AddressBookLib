package edu.nyu.cs.addressbook.search.impl;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.search.SearchCriteria;

/**
 * @author shenli
 * <p>
 * The {@code NonNull} enum represents criteria information.
 * <p>
 * The specified criteria is the element should not be null. {@code NonNull} are constant; their value could
 * not be changed after they are created. Because {@code NonNull} objects are immutable they could be shared.
 */
public enum NonNull implements SearchCriteria {
    INSTANCE;
    
    /**
     * Initializes a newly created {@code NonNull} object so that it records no null criteria information.
     */
    private NonNull() {
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchCondition(ContactEntry element) {
        return element != null;
    }
    
    /**
     * Return string representation of this {@code NonNull} object. The string representation is "Non Null".
     * <p>
     * @return string representation of this {@code NonNull} object
     */
    @Override
    public String toString() {
        return "Non Null";
    }

}
