package edu.nyu.cs.addressbook;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.search.SearchCriteria;
import edu.nyu.cs.addressbook.search.impl.ContactField;
import edu.nyu.cs.addressbook.search.impl.NonNull;
import edu.nyu.cs.addressbook.search.impl.Regex;

public class CriteriaIteratorTest {
	private List<ContactEntry> contactEntryList;
	private SearchIterator<SearchCriteria> nonNullCriteriaIterator;
	private SearchIterator<SearchCriteria> regexCriteriaIterator;
	private SearchIterator<SearchCriteria> contactMatcherCriteriaIterator;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		contactEntryList = Arrays.asList(
				new ContactEntry.ContactEntryBuilder("Jennifer", "Darlington").build(),
				new ContactEntry.ContactEntryBuilder("Shen", "Li")
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
						.note("I am an internation student in NYU.").build(),
				new ContactEntry.ContactEntryBuilder("Howard", "Wolowitz")
						.phoneNumber(
								new PhoneNumber(123, 456, 7890),
								new PhoneNumber(987, 654, 3210))
						.emailAddress(new EmailAddress("howardw", "cs.nyu.edu"))
						.note("I am a engineering in CIT.").build());
		nonNullCriteriaIterator =
				new SearchIterator<SearchCriteria>(
						contactEntryList.iterator(), NonNull.INSTANCE);
		regexCriteriaIterator =
				new SearchIterator<SearchCriteria>(
						contactEntryList.iterator(),
						new Regex("[a-zA-Z0-9]+@[a-zA-Z]+\\.com"));
		contactMatcherCriteriaIterator =
				new SearchIterator<SearchCriteria>(
						contactEntryList.iterator(),
						new ContactField.ContactFieldBuilder()
								.areaCode(123).street("Avenue").build());
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#SearchIterator(java.util.Iterator, edu.nyu.cs.addressbook.search.SearchCriteria)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testCriteriaIteratorWithNullIteratorObject() {
		@SuppressWarnings("unused")
		SearchIterator<SearchCriteria> iter =
				new SearchIterator<SearchCriteria>(null, NonNull.INSTANCE);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#SearchIterator(java.util.Iterator, edu.nyu.cs.addressbook.search.SearchCriteria)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testCriteriaIteratorWithNullCriteriaObject() {
		@SuppressWarnings("unused")
		SearchIterator<SearchCriteria> iter =
				new SearchIterator<>(contactEntryList.iterator(), null);
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#hasNext()}.
	 */
	@Test
	public void testHasNextWithNonNullCriteria() {
		assertTrue(nonNullCriteriaIterator.hasNext());
		assertTrue(nonNullCriteriaIterator.hasNext());
		nonNullCriteriaIterator.next();
		assertTrue(nonNullCriteriaIterator.hasNext());
		assertTrue(nonNullCriteriaIterator.hasNext());
		nonNullCriteriaIterator.next();
		assertTrue(nonNullCriteriaIterator.hasNext());
		assertTrue(nonNullCriteriaIterator.hasNext());
		nonNullCriteriaIterator.next();
		assertFalse(nonNullCriteriaIterator.hasNext());
		assertFalse(nonNullCriteriaIterator.hasNext());
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test
	public void testNextWithNonNullCriteria() {
		for (ContactEntry ce : contactEntryList) {
			assertEquals(ce, nonNullCriteriaIterator.next());
		}
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNextWithNonNullCriteriaNoSuchElement() {
		for (ContactEntry ce : contactEntryList) {
			assertEquals(ce, nonNullCriteriaIterator.next());
		}
		nonNullCriteriaIterator.next();
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#hasNext()}.
	 */
	@Test
	public void testHasNextWithRegexCriteria() {
		assertTrue(regexCriteriaIterator.hasNext());
		assertTrue(regexCriteriaIterator.hasNext());
		regexCriteriaIterator.next();
		assertFalse(regexCriteriaIterator.hasNext());
		assertFalse(regexCriteriaIterator.hasNext());
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test
	public void testNextWithRegexCriteria() {
		assertEquals(contactEntryList.get(1), regexCriteriaIterator.next());
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNextWithRegexCriteriaNoSuchElement() {
		regexCriteriaIterator.next();
		regexCriteriaIterator.next();
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#hasNext()}.
	 */
	@Test
	public void testHasNextWithContactMatcherCriteria() {
		assertFalse(contactMatcherCriteriaIterator.hasNext());
		assertFalse(contactMatcherCriteriaIterator.hasNext());
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test
	public void testNextWithContactMatcherCriteria() {
		
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#next()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNextWithContactMatcherCriteriaNoSuchElement() {
		contactMatcherCriteriaIterator.next();
	}

	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#remove()}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveWithNonNullCriteria() {
		nonNullCriteriaIterator.remove();
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#remove()}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveWithRegexCriteria() {
		regexCriteriaIterator.remove();
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.addressbook.SearchIterator#remove()}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveWithContactMatcherCriteriaCriteria() {
		contactMatcherCriteriaIterator.remove();
	}

}
