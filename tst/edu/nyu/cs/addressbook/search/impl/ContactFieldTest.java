package edu.nyu.cs.addressbook.search.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.ContactEntry;
import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;

public class ContactFieldTest {
    private List<ContactField> criteriaList;
    private ContactEntry contactEntry;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        criteriaList = Arrays.asList(
                new ContactField.ContactFieldBuilder().firstName("Shen").build(),
                new ContactField.ContactFieldBuilder().lastName("Li").build(),
                new ContactField.ContactFieldBuilder().areaCode(646).build(),
                new ContactField.ContactFieldBuilder().prefix(620).build(),
                new ContactField.ContactFieldBuilder().lineNumber(5666).build(),
                new ContactField.ContactFieldBuilder().username("lishenapply").build(),
                new ContactField.ContactFieldBuilder().domain("nyu.edu").build(),
                new ContactField.ContactFieldBuilder().zipCode(98765).build(),
                new ContactField.ContactFieldBuilder().state(State.NY).build(),
                new ContactField.ContactFieldBuilder().city("Los Angles").build(),
                new ContactField.ContactFieldBuilder().street("APT").build(),
                new ContactField.ContactFieldBuilder().note("student").build());
        contactEntry = new ContactEntry.ContactEntryBuilder("Shen", "Li")
                            .phoneNumber(new PhoneNumber(646, 620, 5666))
                            .emailAddress(
                                    new EmailAddress("sl3268", "nyu.edu"),
                                    new EmailAddress("yunfeiyang4ever", "gmail.com"),
                                    new EmailAddress("lishenapply", "yahoo.com"))
                            .postalAddress(
                                    new PostalAddress.PostalAddressBuilder()
                                            .zipCode(11220).state(State.NY).city("Brooklyn")
                                            .street("465 46th Street APT 5").build(),
                                    new PostalAddress.PostalAddressBuilder()
                                            .zipCode(98765).state(State.CA).city("Los Angles")
                                            .street("112 Avenue T 2F").build())
                            .note("I am an internation student in NYU.").build();
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test
    public void testContactMatcherCriteriaWithLegalArgument() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queen").street("APT")
                        .note("student").build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullFirstName() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().firstName(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullLastName() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().lastName(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullUserName() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().username(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullDomain() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().domain(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullState() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().state(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullCity() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().city(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullStreet() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().street(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#ContactField(edu.nyu.cs.addressbook.search.impl.ContactField.ContactFieldBuilder)}.
     */
    @Test(expected = NullPointerException.class)
    public void testContactMatcherCriteriaWithNullNote() {
        @SuppressWarnings("unused")
        ContactField cmc =
                new ContactField.ContactFieldBuilder().note(null).build();
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNullObject() {
        ContactField cmc = new ContactField.ContactFieldBuilder().build();
        assertFalse(cmc.matchCondition(null));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#matchCondition(edu.nyu.cs.addressbook.ContactEntry)}.
     */
    @Test
    public void testMatchConditionWithNotNullObject() {
        for (ContactField cmc : criteriaList) {
            assertTrue(cmc.matchCondition(contactEntry));
        }
        List<ContactField> notMatchList = Arrays.asList(
                new ContactField.ContactFieldBuilder().firstName("David").build(),
                new ContactField.ContactFieldBuilder().lastName("Lee").build(),
                new ContactField.ContactFieldBuilder().areaCode(123).build(),
                new ContactField.ContactFieldBuilder().prefix(456).build(),
                new ContactField.ContactFieldBuilder().lineNumber(7890).build(),
                new ContactField.ContactFieldBuilder().username("shenl").build(),
                new ContactField.ContactFieldBuilder().domain("icloud.com").build(),
                new ContactField.ContactFieldBuilder().zipCode(12345).build(),
                new ContactField.ContactFieldBuilder().state(State.IL).build(),
                new ContactField.ContactFieldBuilder().city("San Francisco").build(),
                new ContactField.ContactFieldBuilder().street("Union Square").build(),
                new ContactField.ContactFieldBuilder().note("scientist").build());
        for (ContactField cmc : notMatchList) {
            assertFalse(cmc.matchCondition(contactEntry));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithThisObject() {
        for (ContactField cmc : criteriaList) {
            assertTrue(cmc.equals(cmc));
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNullObject() {
        for (ContactField cmc : criteriaList) {
            assertFalse(cmc.equals(null));
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithNotContactMatcherCriteriaObject() {
        Object o = new Object();
        for (ContactField cmc : criteriaList) {
            assertFalse(cmc.equals(o));
            assertFalse(o.equals(cmc));
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithEqualContactMatcherCriteriaObject() {
        ContactField cmc1 =
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queen").street("APT")
                        .note("student").build();
        ContactField cmc2 =
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queen").street("APT")
                        .note("student").build();
        ContactField cmc3 =
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queen").street("APT")
                        .note("student").build();
        assertTrue(cmc1.equals(cmc2));
        assertTrue(cmc2.equals(cmc1));
        assertTrue(cmc2.equals(cmc3));
        assertTrue(cmc1.equals(cmc3));
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectWithFDifferContactMatcherCriteriaObject() {
        ContactField cmc =
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queens").street("APT")
                        .note("student").build();
        List<ContactField> cmcDiffer = Arrays.asList(
                new ContactField.ContactFieldBuilder().firstName("Eleven").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Cooper").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(646).build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(620).build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(7890).build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("sl3268").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("gmail.com").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(12345).build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.CA).build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("New York").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queens").street("street").build(),
                new ContactField.ContactFieldBuilder()
                        .firstName("David").lastName("Lee")
                        .areaCode(123).prefix(456).lineNumber(5666)
                        .username("shenl").domain("nyu.edu")
                        .zipCode(98765).state(State.NY).city("Queens").street("APT")
                        .note("international").build());
        for (ContactField c : cmcDiffer) {
            assertFalse(c.equals(cmc));
            assertFalse(cmc.equals(c));
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#hashCode()}.
     */
    @Test
    public void testHashCode() {
        List<Integer> intList = Arrays.asList(
                586639859, 826136558, 1208410827, -328186627,
                -59339217, -412281884, 1013611007, 324354526,
                -691900720, -987480159, -690867924, 1722937548);
        for (int i = 0; i < criteriaList.size(); i++) {
            assertEquals(intList.get(i).intValue(), criteriaList.get(i).hashCode());
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.addressbook.search.impl.ContactField#toString()}.
     */
    @Test
    public void testToString() {
        List<String> strList = Arrays.asList(
                "first name: Shen\n",
                "last name: Li\n",
                "area code: 646\n",
                "prefix: 620\n",
                "line number: 5666\n",
                "user name: lishenapply\n",
                "domain: nyu.edu\n",
                "zipcode: 98765\n",
                "state: NY\n",
                "city: Los Angles\n",
                "street: APT\n",
                "note: student\n");
        for (int i = 0; i < criteriaList.size(); i++) {
            assertEquals(strList.get(i), criteriaList.get(i).toString());
        }
    }

}
