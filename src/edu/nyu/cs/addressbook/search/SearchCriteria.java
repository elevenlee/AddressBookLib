package edu.nyu.cs.addressbook.search;

import edu.nyu.cs.addressbook.ContactEntry;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code SearchCriteria} interface when it wants to impose a searching criteria on 
 * the objects.
 */
public interface SearchCriteria {
    
    /**
     * This method is called whenever checking the object is matched the specified condition. Returns true if 
     * the element matches the specified condition, otherwise false.
     * <p>
     * @param element the element
     * @return true if the element matches the specified condition, otherwise false
     */
    public boolean matchCondition(ContactEntry element);
    
}
