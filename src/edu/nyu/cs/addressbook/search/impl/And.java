package edu.nyu.cs.addressbook.search.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code And} class represents criteria information.
 * <p>
 * The specified criteria is the element should match all criteria(s). {@code And} objects are not constant; 
 * their values could be changed after they are created. Because {@code And} objects are implemented by 
 * synchronized list they could be shared.
 */
public class And implements SearchCriteria {
    private final List<SearchCriteria> criterias = new CopyOnWriteArrayList<>();
    
    /**
     * Initializes a newly created {@code And} object so that it records criteria information.
     * <p>
     * @param criterias an array of specified criteria(s)
     */
    public And(SearchCriteria...criterias) {
        for (SearchCriteria sc : criterias) {
            ParameterChecker.nullCheck(sc, "criteria");
        }
        
        for (SearchCriteria sc : criterias) {
            this.criterias.add(sc);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchCondition(ContactEntry element) {
        if (element == null) {
            return false;
        }
        for (SearchCriteria sc : criterias) {
            if (!sc.matchCondition(element)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Compares the specified object with this {@code And} object for equality. Returns true if and only if 
     * the specified object is also an {@code And} object, both objects have the same criteria list.
     * <p>
     * This implementation first checks if the specified object is this {@code And}. If so, it returns true; 
     * if not, it checks if the specified object is an {@code And} object. If not, it returns false; if so, 
     * it iterates over both {@code And} objects, comparing corresponding fields. If any comparison returns 
     * false, this method returns false. Otherwise it returns true when the iterations complete. 
     * <p>
     * @param o the object to be compared for equality with this {@code And} object
     * @return true if the specified object is equal to this {@code And} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof And)) {
            return false;
        }
        And a = (And) o;
        return criterias.equals(a.criterias);
    }
    
    /**
     * Returns the hash code value for this {@code And} object.
     * <p>
     * @return the hash code value for this {@code And} object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + criterias.hashCode();
        return result;
    }

    /**
     * Return string representation of this {@code And} object. The string representation consists of all 
     * criteria separated by line feed.
     * <p>
     * @return string representation of this {@code And} object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SearchCriteria sc : criterias) {
            sb.append(sc.toString() + "\n");
        }
        return sb.toString();
    }

}
