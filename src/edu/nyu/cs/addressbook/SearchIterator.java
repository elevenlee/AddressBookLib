package edu.nyu.cs.addressbook;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code SearchIterator} class represents search criteria iterator information.
 * <p>
 * The {@code SearchIterator} object wraps an {@link java.util.Iterator} object and implements criteria on
 * each iterator element of the collection.
 * <p>
 * {@code SearchIterator} objects are not constant; their values could be changed after they are created. The 
 * {@code SearchIterator} object is not thread-safe. To use it concurrently, user must surround each method 
 * invocation with external synchronization of the users' choosing.
 */
public class SearchIterator<E extends SearchCriteria> implements Iterator<ContactEntry> {
    private final Iterator<ContactEntry> iterator;
    private final E criteria;
    private ContactEntry nextElement;
    
    /**
     * Initializes a newly created {@code SearchIterator} object so that it records search criteria iterator 
     * information.
     * <p>
     * @param iterator the iterator
     * @param criteria the search criteria
     */
    public SearchIterator(Iterator<ContactEntry> iterator, E criteria) {
        ParameterChecker.nullCheck(iterator, "iterator");
        ParameterChecker.nullCheck(criteria, "criteria");
        
        this.iterator = iterator;
        this.criteria = criteria;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        if (nextElement != null) {
            return true;
        }
        while (iterator.hasNext()) {
            nextElement = iterator.next();
            if (criteria.matchCondition(nextElement)) {
                return true;
            }
        }
        nextElement = null;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContactEntry next() {
        if (nextElement == null && !hasNext()) {
            throw new NoSuchElementException("No such element exists!");
        }
        ContactEntry item = nextElement;
        nextElement = null;
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException(
                "Unsupported remove() operation in " + this.getClass() + " !");
    }

}
