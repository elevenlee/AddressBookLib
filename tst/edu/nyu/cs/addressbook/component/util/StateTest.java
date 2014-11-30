package edu.nyu.cs.addressbook.component.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
    private List<String> descriptionList;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        descriptionList = Arrays.asList(
                "Select State", "Washington, D.C.",
                "Alabama", "Alaska", "Arizona", "Arkansas",
                "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii",
                "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana",
                "Maine", "Maryland", "Massachusetts", "Michigan",
                "Minnesota", "Mississippi", "Missouri", "Montana",
                "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                "New Mexico", "New York", "North Carolina", "North Dakota",
                "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
                "Rhode Island", "South Carolina", "South Dakota",
                "Tennessee", "Texas", "Utah",
                "Vermont", "Virginia",
                "Washington", "West Virginia", "Wisconsin", "Wyoming");
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.util.State#toString()}.
     */
    @Test
    public void testToString() {
        State[] states = State.values();
        for (int i = 0; i < states.length; i++) {
            assertEquals(descriptionList.get(i), states[i].toString());
        }
    }

}
