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
    NONE("Select State"), DC("Washington, D.C."),
    AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"),
    CA("California"), CO("Colorado"), CT("Connecticut"),
    DE("Delaware"), FL("Florida"), GA("Georgia"), HI("Hawaii"),
    ID("Idaho"), IL("Illinois"), IN("Indiana"), IA("Iowa"),
    KS("Kansas"), KY("Kentucky"), LA("Louisiana"),
    ME("Maine"), MD("Maryland"), MA("Massachusetts"), MI("Michigan"),
    MN("Minnesota"), MS("Mississippi"), MO("Missouri"), MT("Montana"),
    NE("Nebraska"), NV("Nevada"), NH("New Hampshire"), NJ("New Jersey"),
    NM("New Mexico"), NY("New York"), NC("North Carolina"), ND("North Dakota"),
    OH("Ohio"), OK("Oklahoma"), OR("Oregon"), PA("Pennsylvania"),
    RI("Rhode Island"), SC("South Carolina"), SD("South Dakota"),
    TN("Tennessee"), TX("Texas"), UT("Utah"),
    VT("Vermont"), VA("Virginia"),
    WA("Washington"), WV("West Virginia"), WI("Wisconsin"), WY("Wyoming");
    
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
