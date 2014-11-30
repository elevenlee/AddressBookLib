package edu.nyu.cs.addressbook.component;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.component.util.State;

public class PostalAddressTest {
    private PostalAddress postalAddress;
    private PostalAddress postalAddressWithNothing;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        postalAddress = new PostalAddress.PostalAddressBuilder()
                            .zipCode(11220).state(State.NY).city("Brooklyn")
                            .street("465 46th Street APT 5").build();
        postalAddressWithNothing = new PostalAddress.PostalAddressBuilder().build();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#PostalAddress(edu.nyu.cs.addressbook.builder.Builder)}.
     */
    @Test
    public void testPostalAddressWithLegalArgument() {
        @SuppressWarnings("unused")
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(11223).state(State.NY).city("San Francisco")
                                .street("789 Union Street").build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#PostalAddress(edu.nyu.cs.addressbook.builder.Builder)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostalAddressWithIllegalZipCode() {
        @SuppressWarnings("unused")
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .zipCode(123456).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#PostalAddress(edu.nyu.cs.addressbook.builder.Builder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testPostalAddressWithIllegalState() {
        @SuppressWarnings("unused")
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .state(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#PostalAddress(edu.nyu.cs.addressbook.builder.Builder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testPostalAddressWithNullCity() {
        @SuppressWarnings("unused")
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .city(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#PostalAddress(edu.nyu.cs.addressbook.builder.Builder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testPostalAddressWithNullStreet() {
        @SuppressWarnings("unused")
        PostalAddress pa = new PostalAddress.PostalAddressBuilder()
                                .street(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#getZipCode()}.
     */
    @Test
    public void testGetZipCode() {
        assertEquals(11220, postalAddress.getZipCode());
        assertEquals(0, postalAddressWithNothing.getZipCode());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#getState()}.
     */
    @Test
    public void testGetState() {
        assertEquals(State.NY, postalAddress.getState());
        assertEquals(State.NONE, postalAddressWithNothing.getState());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#getCity()}.
     */
    @Test
    public void testGetCity() {
        assertEquals("Brooklyn", postalAddress.getCity());
        assertEquals("", postalAddressWithNothing.getCity());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#getStreet()}.
     */
    @Test
    public void testGetStreet() {
        assertEquals("465 46th Street APT 5", postalAddress.getStreet());
        assertEquals("", postalAddressWithNothing.getStreet());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        assertTrue(postalAddress.equals(postalAddress));
        assertTrue(postalAddressWithNothing.equals(postalAddressWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        assertFalse(postalAddress.equals(null));
        assertFalse(postalAddressWithNothing.equals(null));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotPostalAddressObject() {
        Object o = new Object();
        assertFalse(postalAddress.equals(o));
        assertFalse(o.equals(postalAddress));
        assertFalse(postalAddressWithNothing.equals(o));
        assertFalse(o.equals(postalAddressWithNothing));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualPostalAddressObject() {
        PostalAddress pa1 = new PostalAddress.PostalAddressBuilder()
                                .zipCode(11220).state(State.NY).city("Brooklyn")
                                .street("465 46th Street APT 5").build();
        PostalAddress pa2 = new PostalAddress.PostalAddressBuilder()
                                .zipCode(11220).state(State.NY).city("Brooklyn")
                                .street("465 46th Street APT 5").build();
        assertTrue(postalAddress.equals(pa1));
        assertTrue(pa1.equals(postalAddress));
        assertTrue(pa1.equals(pa2));
        assertTrue(postalAddress.equals(pa2));
        
        pa1 = new PostalAddress.PostalAddressBuilder().build();
        pa2 = new PostalAddress.PostalAddressBuilder().build();
        assertTrue(postalAddressWithNothing.equals(pa1));
        assertTrue(pa1.equals(postalAddressWithNothing));
        assertTrue(pa1.equals(pa2));
        assertTrue(postalAddressWithNothing.equals(pa2));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithDifferPostalAddressObject() {
        List<PostalAddress> postalAddressList = Arrays.asList(
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(12345).state(State.NY).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.CA).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("New York")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Brooklyn")
                    .street("762 41th Street 301A").build());
        for (PostalAddress pa : postalAddressList) {
            assertFalse(postalAddress.equals(pa));
            assertFalse(pa.equals(postalAddress));
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals(-2090094990, postalAddress.hashCode());
        assertEquals(15699857, postalAddressWithNothing.hashCode());
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("465 46th Street APT 5\nBrooklyn, NY 11220\nThe United States",
                postalAddress.toString());
        assertEquals("\nThe United States", postalAddressWithNothing.toString());
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#clone()}.
     */
    @Test
    public void testClone() {
        PostalAddress pa = postalAddress.clone();
        assertTrue(postalAddress.equals(pa));
        assertTrue(pa.equals(postalAddress));
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#compareTo(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test(expected = NullPointerException.class)
    public void testCompareToWithNullObject() {
        postalAddress.compareTo(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.component.PostalAddress#compareTo(edu.nyu.cs.addressbook.component.PostalAddress)}.
     */
    @Test
    public void testCompareToWithNotNullObject() {
        List<PostalAddress> postalAddressLessList = Arrays.asList(
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(10000).state(State.NY).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.CA).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Albany")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Brooklyn")
                    .street("112 Avenue T").build());
        List<PostalAddress> postalAddressGreaterList = Arrays.asList(
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(30000).state(State.NY).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.PA).city("Brooklyn")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Queens")
                    .street("465 46th Street APT 5").build(),
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Brooklyn")
                    .street("762 41th Street 301A").build());
        PostalAddress postalAddressEqual = 
                new PostalAddress.PostalAddressBuilder()
                    .zipCode(11220).state(State.NY).city("Brooklyn")
                    .street("465 46th Street APT 5").build();
        for (PostalAddress pa : postalAddressLessList) {
            assertTrue(postalAddress.compareTo(pa) > 0);
            assertTrue(pa.compareTo(postalAddress) < 0);
        }
        for (PostalAddress pa : postalAddressGreaterList) {
            assertTrue(postalAddress.compareTo(pa) < 0);
            assertTrue(pa.compareTo(postalAddress) > 0);
        }
        assertTrue(postalAddress.compareTo(postalAddressEqual) == 0);
        assertTrue(postalAddressEqual.compareTo(postalAddress) == 0);
    }

}
