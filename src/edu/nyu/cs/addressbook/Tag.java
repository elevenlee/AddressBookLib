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
    /**
     * contact
     */
    CONTACT,
    /**
     * contact id
     */
    ID,
    /**
     * contact entry
     */
    ENTRY,
    /**
     * contact entry first name
     */
    FIRST_NAME, 
    /**
     * contact entry last name
     */
    LAST_NAME, 
    /**
     * contact entry phone number
     */
    PHONE, 
    /**
     * phone area code
     */
    AREA_CODE, 
    /**
     * phone prefix
     */
    PREFIX, 
    /**
     * phone line number
     */
    LINE_NUMBER,
    /**
     * contact entry email address
     */
    EMAIL, 
    /**
     * email user name
     */
    USER_NAME, 
    /**
     * email domain
     */
    DOMAIN,
    /**
     * contact entry postal address
     */
    ADDRESS, 
    /**
     * postal address zipcode
     */
    ZIPCODE, 
    /**
     * postal address {@link edu.nyu.cs.addressbook.component.util.State}
     */
    STATE, 
    /**
     * postal address city
     */
    CITY,
    /**
     * postal address street
     */
    STREET,
    /**
     * contact entry note
     */
    NOTE;
    
    String tagName() {
        return this.name().toLowerCase();
    }
    
}
