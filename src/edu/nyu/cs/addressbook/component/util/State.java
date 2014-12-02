package edu.nyu.cs.addressbook.component.util;

/**
 * @author shenli
 * <p>
 * The {@code State} enum represents state information.
 * <p>
 * {@code State} are constant; their values could not be changed after they are created. Because {@code State} 
 * objects are immutable they could be shared.
 */
public enum State {
    /**
     * Select State, Default state option
     */
    NONE("Select State"), 
    /**
     * Washington, D.C.
     */
    DC("Washington, D.C."),
    /**
     * Alabama
     */
    AL("Alabama"), 
    /**
     * Alaska
     */
    AK("Alaska"), 
    /**
     * Arizona
     */
    AZ("Arizona"), 
    /**
     * Arkansas
     */
    AR("Arkansas"),
    /**
     * California
     */
    CA("California"), 
    /**
     * Colorado
     */
    CO("Colorado"), 
    /**
     * Connecticut
     */
    CT("Connecticut"),
    /**
     * Delaware
     */
    DE("Delaware"), 
    /**
     * Florida
     */
    FL("Florida"), 
    /**
     * Georgia
     */
    GA("Georgia"), 
    /**
     * Hawaii
     */
    HI("Hawaii"),
    /**
     * Idaho
     */
    ID("Idaho"), 
    /**
     * Illinois
     */
    IL("Illinois"), 
    /**
     * Indiana
     */
    IN("Indiana"), 
    /**
     * Iowa
     */
    IA("Iowa"),
    /**
     * Kansas
     */
    KS("Kansas"), 
    /**
     * Kentucky
     */
    KY("Kentucky"), 
    /**
     * Louisiana
     */
    LA("Louisiana"),
    /**
     * Maine
     */
    ME("Maine"), 
    /**
     * Maryland
     */
    MD("Maryland"), 
    /**
     * Massachusetts
     */
    MA("Massachusetts"), 
    /**
     * Michigan
     */
    MI("Michigan"),
    /**
     * Minnesota
     */
    MN("Minnesota"), 
    /**
     * Mississippi
     */
    MS("Mississippi"), 
    /**
     * Missouri
     */
    MO("Missouri"), 
    /**
     * Montana
     */
    MT("Montana"),
    /**
     * Nebraska
     */
    NE("Nebraska"), 
    /**
     * Nevada
     */
    NV("Nevada"), 
    /**
     * New Hampshire
     */
    NH("New Hampshire"), 
    /**
     * New Jersey
     */
    NJ("New Jersey"),
    /**
     * New Mexico
     */
    NM("New Mexico"), 
    /**
     * New York
     */
    NY("New York"), 
    /**
     * North Carolina
     */
    NC("North Carolina"), 
    /**
     * North Dakota
     */
    ND("North Dakota"),
    /**
     * Ohio
     */
    OH("Ohio"), 
    /**
     * Oklahoma
     */
    OK("Oklahoma"), 
    /**
     * Oregon
     */
    OR("Oregon"), 
    /**
     * Pennsylvania
     */
    PA("Pennsylvania"),
    /**
     * Rhode Island
     */
    RI("Rhode Island"), 
    /**
     * South Carolina
     */
    SC("South Carolina"), 
    /**
     * South Dakota
     */
    SD("South Dakota"),
    /**
     * Tennessee
     */
    TN("Tennessee"), 
    /**
     * Texas
     */
    TX("Texas"), 
    /**
     * Utah
     */
    UT("Utah"),
    /**
     * Vermont
     */
    VT("Vermont"), 
    /**
     * Virginia
     */
    VA("Virginia"),
    /**
     * Washington
     */
    WA("Washington"), 
    /**
     * West Virginia
     */
    WV("West Virginia"), 
    /**
     * Wisconsin
     */
    WI("Wisconsin"), 
    /**
     * Wyoming
     */
    WY("Wyoming");
    
    private final String description;
    
    /**
     * Initializes a newly created {@code State} object so that it records state information.
     * <p>
     * @param description the state description
     */
    private State(String description) {
        assert description != null;
        
        this.description = description;
    }
    
    /**
     * Return string representation of this {@code State} object. The string representation consists of the 
     * description.
     * <p>
     * @return string representation of this {@code State} object
     */
    @Override
    public String toString() {
        return description;
    }
    
}
