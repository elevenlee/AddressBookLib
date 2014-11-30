package edu.nyu.cs.addressbook;

/**
 * @author shenli
 * <p>
 * The {@code Tag} enum represents tag name information. 
 * <p>
 * {@code Tag} are constant; their values could not be changed after they are created. Because {@code Tag} 
 * objects are immutable they could be shared.
 */
enum Tag {
    ROOT("contact"),
    ID("id"), ENTRY("entry"),
    FIRSTNAME("first_name"), LASTNAME("last_name"), 
    PHONE("phone"), AREACODE("area_code"), PREFIX("prefix"), LINENUMBER("line_number"),
    EMAIL("email"), USERNAME("user_name"), DOMAIN("domain"),
    ADDRESS("address"), ZIPCODE("zipcode"), STATE("state"), CITY("city"),STREET("street"),
    NOTE("note");

    private final String name;
    
    /**
     * Initializes a newly created {@code Tag} object so that it records tag name information.
     * <p>
     * @param name the tag name
     */
    private Tag(String name) {
        assert name != null;
        
        this.name = name;
    }
    
    /**
     * Return string representation of this {@code Tag} object. The string representation consists of the tag name.
     * <p>
     * @return string representation of this {@code Tag} object
     */
    @Override
    public String toString() {
        return name;
    }
    
}
