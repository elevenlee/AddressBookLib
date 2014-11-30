package edu.nyu.cs.addressbook.builder;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code Builder} interface when it wants to impose a building on the objects.
 */
public interface Builder<T> {

    /**
     * The method is called whenever the object is built. Returns an instance with specified parameter(s).
     * <p>
     * @return an instance with specified parameter(s)
     */
    public T build();
    
}
