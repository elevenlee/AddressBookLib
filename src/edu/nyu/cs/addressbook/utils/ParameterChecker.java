package edu.nyu.cs.addressbook.utils;

/**
 * @author shenli
 * <p>
 * A non-instantiability {@code ParameterChecker} object used to check parameter illegally.
 */
public class ParameterChecker {

    /**
     * Suppress default constructor for non-instantiable
     */
    private ParameterChecker() {
        throw new AssertionError();
    }
    
    /**
     * Check integer whether out of range based on the {@code max} argument.
     * <p>
     * @param arg the specified integer to be checked
     * @param max the maximum range number
     * @param name the name
     * @throws IllegalArgumentException if arg parameter less than zero or exceed the maximum range
     */
    public static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }
    
    /**
     * Check object whether is {@code null}.
     * <p>
     * @param arg the specified object to be checked
     * @param name the name
     * @throws NullPointerException if arg parameter is null
     */
    public static <T> void nullCheck(T arg, String name) {
        if (arg == null) {
            throw new NullPointerException(name + ": " + arg);
        }
    }
    
    /**
     * Check string whether is empty.
     * <p>
     * @param arg the specified object to be checked
     * @param name the name
     * @throws IllegalArgumentException if arg parameter is empty string
     */
    public static void emptyCheck(String arg, String name) {
        if ("".equals(arg)) {
            throw new IllegalArgumentException(name + ": " + arg + " empty!");
        }
    }
    
}
