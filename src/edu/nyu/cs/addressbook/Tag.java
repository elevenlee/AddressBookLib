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
    CONTACT,
    ID, ENTRY,
    FIRST_NAME, LAST_NAME, 
    PHONE, AREA_CODE, PREFIX, LINE_NUMBER,
    EMAIL, USER_NAME, DOMAIN,
    ADDRESS, ZIPCODE, STATE, CITY,STREET,
    NOTE;
    
    String tagName() {
        return this.name().toLowerCase();
    }
    
}
