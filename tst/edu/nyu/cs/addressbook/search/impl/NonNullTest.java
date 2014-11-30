package edu.nyu.cs.addressbook.search.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.cs.addressbook.ContactEntry;

public class NonNullTest {

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.NonNull#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchCondition() {
        ContactEntry ce =
                new ContactEntry.ContactEntryBuilder("Eleven", "Lee").build();
        assertTrue(NonNull.INSTANCE.matchCondition(ce));
        assertFalse(NonNull.INSTANCE.matchCondition(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.NonNull#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Non Null", NonNull.INSTANCE.toString());
    }

}
