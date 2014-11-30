package edu.nyu.cs.addressbook.search.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code Or} class represents criteria information.
 * <p>
 * The specified criteria is the element should match any one of criteria. {@code Or} objects are not constant; 
 * their values could be changed after they are created. Because {@code Or} objects are implemented by 
 * synchronized list they could be shared.
 */
public class Or implements SearchCriteria {
    private List<SearchCriteria> criterias = new CopyOnWriteArrayList<>();
    
    /**
     * Initializes a newly created {@code Or} object so that it records criteria information.
     * <p>
     * @param criterias an array of specified criteria(s)
     */
    public Or(SearchCriteria...criterias) {
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
            if (sc.matchCondition(element)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Compares the specified object with this {@code Or} object for equality. Returns true if and only if the 
     * specified object is also an {@code Or} object, both objects have the same criteria list.
     * <p>
     * This implementation first checks if the specified object is this {@code Or}. If so, it returns true; if 
     * not, it checks if the specified object is an {@code Or} object. If not, it returns false; if so, it 
     * iterates over both {@code Or} objects, comparing corresponding fields. If any comparison returns false, 
     * this method returns false. Otherwise it returns true when the iterations complete.
     * <p>
     * @param o the object to be compared for equality with this {@code Or} object
     * @return true if the specified object is equal to this {@code Or} object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof Or)) {
            return false;
        }
        Or or = (Or) o;
        return criterias.equals(or.criterias);
    }
    
    /**
     * Returns the hash code value for this {@code Or} object.
     * <p>
     * @return the hash code value for this {@code Or} object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + criterias.hashCode();
        return result;
    }

    /**
     * Return string representation of this {@code Or} object. The string representation consists of all 
     * criteria separated by line feed.
     * <p>
     * @return string representation of this {@code Or} object
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
