package edu.nyu.cs.addressbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import edu.nyu.cs.addressbook.component.EmailAddress;
import edu.nyu.cs.addressbook.component.PhoneNumber;
import edu.nyu.cs.addressbook.component.PostalAddress;
import edu.nyu.cs.addressbook.component.util.State;
import edu.nyu.cs.addressbook.utils.ParameterChecker;

public class ContactUtil {
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private ContactUtil() {
        throw new AssertionError();
    }
    
    /**
     * Reads from the specified input stream. The general contract for {@code load} is XML format. Returns 
     * the {@link edu.nyu.cs.addressbook.Contact} object specified by input stream.
     * <p>
     * @param in the input stream
     * @return the {@link edu.nyu.cs.addressbook.Contact} object specified by input stream
     * @throws ParserConfigurationException a serious configuration error
     * @throws SAXException a general SAX error or warning
     * @throws IOException failed or interrupted I/O operations
     * @throws IllegalArgumentException if the XML file is ill format
     */
    public static Contact load(InputStream in) throws SAXException, IOException, ParserConfigurationException {
        ParameterChecker.nullCheck(in, "input stream");
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(in);
        Element rootNode = document.getDocumentElement();
        Contact contact =
                ContactFactory.getContact(parseNode(rootNode, Tag.ID));
        
        NodeList entryNodes = getNodeList(rootNode, Tag.ENTRY);
        for (int i = 0; i < entryNodes.getLength(); i++) {
            Element entryNode = (Element) entryNodes.item(i);
            ContactEntry ce =
                    new ContactEntry.ContactEntryBuilder(
                            parseNode(entryNode, Tag.FIRST_NAME),
                            parseNode(entryNode, Tag.LAST_NAME)).build();
            
            NodeList phones = getNodeList(entryNode, Tag.PHONE);
            for (int j = 0; j < phones.getLength(); j++) {
                Element phoneNode = (Element) phones.item(j);
                PhoneNumber pn =
                        new PhoneNumber(
                                Integer.parseInt(parseNode(phoneNode, Tag.AREA_CODE)),
                                Integer.parseInt(parseNode(phoneNode, Tag.PREFIX)),
                                Integer.parseInt(parseNode(phoneNode, Tag.LINE_NUMBER)));
                ce.addPhoneNumber(pn);
            }
            
            NodeList emails = getNodeList(entryNode, Tag.EMAIL);
            for (int j = 0; j < emails.getLength(); j++) {
                Element emailNode = (Element) emails.item(j);
                EmailAddress ea =
                        new EmailAddress(
                                parseNode(emailNode, Tag.USER_NAME),
                                parseNode(emailNode, Tag.DOMAIN));
                ce.addEmailAddress(ea);
            }
            
            NodeList addresses = getNodeList(entryNode, Tag.ADDRESS);
            for (int j = 0; j < addresses.getLength(); j++) {
                PostalAddress.PostalAddressBuilder paBuilder = new PostalAddress.PostalAddressBuilder();
                
                Element addressNode = (Element) addresses.item(j);
                paBuilder.zipCode(Integer.parseInt(parseNode(addressNode, Tag.ZIPCODE)));
                paBuilder.state(
                        Enum.valueOf(
                                State.class, parseNode(addressNode, Tag.STATE)));
                String city = parseNode(addressNode, Tag.CITY);
                if (city != null) {
                    paBuilder.city(city);
                }
                String street = parseNode(addressNode, Tag.STREET);
                if (street != null) {
                    paBuilder.street(street);
                }
                
                ce.addPostalAddress(paBuilder.build());
            }
            
            String note = parseNode(entryNode, Tag.NOTE, false);
            if (note != null) {
                ce.setNote(note);
            }
            
            contact.add(ce);
        }
        return contact;
    }
    
    /**
     * Writes the specified {@link edu.nyu.cs.addressbook.Contact} to the specified output stream. The general
     * contract for {@code save} is XML format.
     * <p>
     * @param out the output stream
     * @param c the {@link edu.nyu.cs.addressbook.Contact} to be written
     * @return true if the specified {@link edu.nyu.cs.addressbook.Contact} writes to the specified output 
     * stream in XML format as a result of the call
     * @throws ParserConfigurationException a serious configuration error
     * @throws TransformerConfigurationException a serious configuration error
     * @throws TransformerException an exceptional condition that occurred during the transformation process
     */
    public static boolean save(OutputStream out, Contact c) throws ParserConfigurationException {
        ParameterChecker.nullCheck(out, "output stream");
        ParameterChecker.nullCheck(c, "contact");
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element rootNode = addNode(document, Tag.CONTACT);
        addNode(document, rootNode, Tag.ID, c.getID());
        for (ContactEntry ce : c) {
            Element entryNode = addNode(document, rootNode, Tag.ENTRY);
            addNode(document, entryNode, Tag.FIRST_NAME, ce.getFirstName());
            addNode(document, entryNode, Tag.LAST_NAME, ce.getLastName());
            for (PhoneNumber pn : ce.getPhoneNumbers()) {
                Element phoneNode = addNode(document, entryNode, Tag.PHONE);
                addNode(document, phoneNode, Tag.AREA_CODE, String.valueOf(pn.getAreaCode()));
                addNode(document, phoneNode, Tag.PREFIX, String.valueOf(pn.getPrefix()));
                addNode(document, phoneNode, Tag.LINE_NUMBER, String.valueOf(pn.getLineNumber()));
            }
            for (EmailAddress ea : ce.getEmailAddresses()) {
                Element emailNode = addNode(document, entryNode, Tag.EMAIL);
                addNode(document, emailNode, Tag.USER_NAME, ea.getUsername());
                addNode(document, emailNode, Tag.DOMAIN, ea.getDomain());
            }
            for (PostalAddress pa : ce.getPostalAddresses()) {
                Element addressNode = addNode(document, entryNode, Tag.ADDRESS);
                addNode(document, addressNode, Tag.ZIPCODE, String.valueOf(pa.getZipCode()));
                addNode(document, addressNode, Tag.STATE, pa.getState().name());
                addNode(document, addressNode, Tag.CITY, pa.getCity());
                addNode(document, addressNode, Tag.STREET, pa.getStreet());
            }
            addNode(document, entryNode, Tag.NOTE, ce.getNote());
        }
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.transform(
                    new DOMSource(document), new StreamResult(out));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    /**
     * Returns node element by the specified {@link edu.nyu.cs.addressbook.Tag}. The node element will be added
     * to the {@code document} after this call returns.
     * <p>
     * @param document the document
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @return node element by the specified {@link edu.nyu.cs.addressbook.Tag}. The node element will be added
     * to the {@code document} after this call returns
     */
    private static Element addNode(Document document, Tag tag) {
        Element element = document.createElement(tag.tagName());
        document.appendChild(element);
        return element;
    }
    
    /**
     * Returns node element by the specified parent {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}. 
     * The node element will be added to the {@code document} after this call returns.
     * <p>
     * @param document the document
     * @param parentElement the parent element
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @return node element by the specified parent {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}
     */
    private static Element addNode(
            Document document, Element parentElement, Tag tag) {
        Element element = document.createElement(tag.tagName());
        parentElement.appendChild(element);
        return element;
    }
    
    /**
     * Returns node element by the specified parent {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}. 
     * The node element will be added to the {@code document} after this call returns.
     * <p>
     * @param document the document
     * @param parentElement the parent element
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @param nodeValue the node value
     * @return node element by the specified parent {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}
     */
    private static void addNode(
            Document document, Element parentElement, Tag tag, String nodeValue) {
        Element element = document.createElement(tag.tagName());
        parentElement.appendChild(element);
        Text text = document.createTextNode(nodeValue);
        element.appendChild(text);
    }
    
    /**
     * Returns node list by the specified {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}.
     * <p>
     * @param parentElement the parent element
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @return node list by the specified {@link org.w3c.dom.Element} and {@link edu.nyu.cs.addressbook.Tag}
     */
    private static NodeList getNodeList(Element parentElement, Tag tag) {
        return parentElement.getElementsByTagName(tag.tagName());
    }
    
    /**
     * Returns string representation of node value by the specified {@link org.w3c.dom.Element} and 
     * {@link edu.nyu.cs.addressbook.Tag}.
     * <p>
     * @param parentElement the parent element
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @return string representation of node value by the specified {@link org.w3c.dom.Element} and 
     * {@link edu.nyu.cs.addressbook.Tag}
     * @throws IllegalArgumentException if the number of node is not one
     */
    private static String parseNode(Element parentElement, Tag tag) {
        NodeList nodes =
                parentElement.getElementsByTagName(tag.tagName());
        if (nodes.getLength() != 1) {
            throw new IllegalArgumentException(
                    "XML Parsing Error: illegal '" + tag + "' tag number");
        }
        Node node = nodes.item(0).getFirstChild();
        return node == null ? null : node.getNodeValue().trim();
    }
    
    /**
     * Returns string representation of node value by the specified {@link org.w3c.dom.Element} and 
     * {@link edu.nyu.cs.addressbook.Tag}. If the third argument is true, then leading and trailing whitespace
     * omitted.
     * <p>
     * @param parentElement the parent element
     * @param tag the {@link edu.nyu.cs.addressbook.Tag} object
     * @param trim if true, then leading and trailing whitespace omitted
     * @return string representation of node value by the specified {@link org.w3c.dom.Element} and 
     * {@link edu.nyu.cs.addressbook.Tag}.
     * @throws IllegalArgumentException if the number of node is not one
     */
    private static String parseNode(Element parentElement, Tag tag, boolean trim) {
        NodeList nodes =
                parentElement.getElementsByTagName(tag.tagName());
        if (nodes.getLength() != 1) {
            throw new IllegalArgumentException(
                    "XML Parsing Error: illegal '" + tag + "' tag number");
        }
        Node node = nodes.item(0).getFirstChild();
        return node == null ? null :
                trim ? node.getNodeValue().trim() : node.getNodeValue();
    }
    
}
