package com.example.dtd_schema;

import com.sun.xml.dtdparser.DTDEventListener;
import com.sun.xml.dtdparser.InputEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Slf4j
public class DtdListener implements DTDEventListener {

    private Schema schema = null;
    private SchemaBuilder.FieldAssembler<Schema> fieldAssembler = null;
    private Map<String, SchemaBuilder.FieldAssembler<Schema>> map = null;

    public Schema getSchema() {
        return schema;
    }

    public DtdListener() {
        schema = null;
        fieldAssembler = null;
        map = new HashMap<>();
    }

    public void setDocumentLocator(Locator loc) {}

    /**
     * Receive notification of a Processing Instruction.
     * Processing instructions contain information meaningful
     * to the application.
     *
     * @param target The target of the proceessing instruction
     *               which should have meaning to the application.
     * @param data   The instruction itself which should contain
     *               valid XML characters.
     * @throws SAXException
     */
    public void processingInstruction(String target, String data) throws SAXException {}

    /**
     * Receive notification of a Notation Declaration.
     * Notation declarations are used by elements and entities
     * for identifying embedded non-XML data.
     *
     * @param name     The notation name, referred to by entities and
     *                 elements.
     * @param publicId The public identifier
     * @param systemId The system identifier
     */
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {}

    /**
     * Receive notification of an unparsed entity declaration.
     * Unparsed entities are non-XML data.
     *
     * @param name         The name of the unparsed entity.
     * @param publicId     The public identifier
     * @param systemId     The system identifier
     * @param notationName The associated notation
     */
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {}

    /**
     * Receive notification of a internal general entity declaration event.
     *
     * @param name  The internal general entity name.
     * @param value The value of the entity, which may include unexpanded
     *              entity references.  Character references will have been
     *              expanded.
     * @throws SAXException
     * @see #externalGeneralEntityDecl(String, String, String)
     */
    public void internalGeneralEntityDecl(String name, String value) throws SAXException {
        log.info("internal general entity, name = "+name+", value = "+value);
    }

    /**
     * Receive notification of an external parsed general entity
     * declaration event.
     * <p>
     * <p>If a system identifier is present, and it is a relative URL, the
     * parser will have resolved it fully before passing it through this
     * method to a listener.</p>
     *
     * @param name     The entity name.
     * @param publicId The entity's public identifier, or null if
     *                 none was given.
     * @param systemId The entity's system identifier.
     * @throws SAXException
     * @see #unparsedEntityDecl(String, String, String, String)
     */
    public void externalGeneralEntityDecl(String name, String publicId, String systemId) throws SAXException {}

    /**
     * Receive notification of a internal parameter entity declaration
     * event.
     *
     * @param name  The internal parameter entity name.
     * @param value The value of the entity, which may include unexpanded
     *              entity references.  Character references will have been
     *              expanded.
     * @throws SAXException
     * @see #externalParameterEntityDecl(String, String, String)
     */
    public void internalParameterEntityDecl(String name, String value) throws SAXException {
        log.info("internal parameter entity delcaration name = "+name+", value = "+value);
    }

    public void externalParameterEntityDecl(String name, String publicId, String systemId) throws SAXException {}

    /**
     * Receive notification of the beginning of the DTD.
     *
     * @param in Current input entity.
     * @see #endDTD()
     */
    public void startDTD(InputEntity in) throws SAXException {
        log.info("input entity = "+in.getLineNumber()
                +","+in.getEncoding()
                +","+in.getPublicId()
                +","+in.getSystemId()
                +","+in.rememberText());


    }

    public void endDTD() throws SAXException {
        log.info("dtd end...");
    }

    public void comment(String text) throws SAXException {}

    /**
     * Receive notification of character data.
     * <p>
     * <p>The Parser will call this method to report each chunk of
     * character data.  SAX parsers may return all contiguous character
     * data in a single chunk, or they may split it into several
     * chunks; however, all of the characters in any single event
     * must come from the same external entity, so that the Locator
     * provides useful information.</p>
     * <p>
     * <p>The application must not attempt to read from the array
     * outside of the specified range.</p>
     * <p>
     * <p>Note that some parsers will report whitespace using the
     * ignorableWhitespace() method rather than this one (validating
     * parsers must do so).</p>
     *
     * @param ch     The characters from the DTD.
     * @param start  The start position in the array.
     * @param length The number of characters to read from the array.
     * @throws SAXException
     * @see #ignorableWhitespace(char[], int, int)
     */
    public void characters(char ch[], int start, int length) throws SAXException {}


    /**
     * Receive notification of ignorable whitespace in element content.
     * <p>
     * <p>Validating Parsers must use this method to report each chunk
     * of ignorable whitespace (see the W3C XML 1.0 recommendation,
     * section 2.10): non-validating parsers may also use this method
     * if they are capable of parsing and using content models.</p>
     * <p>
     * <p>SAX parsers may return all contiguous whitespace in a single
     * chunk, or they may split it into several chunks; however, all of
     * the characters in any single event must come from the same
     * external entity, so that the Locator provides useful
     * information.</p>
     * <p>
     * <p>The application must not attempt to read from the array
     * outside of the specified range.</p>
     *
     * @param ch     The characters from the DTD.
     * @param start  The start position in the array.
     * @param length The number of characters to read from the array.
     * @throws SAXException
     * @see #characters(char[], int, int)
     */
    public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {}


    public void startCDATA() throws SAXException {}

    public void endCDATA() throws SAXException {}

    public void fatalError(SAXParseException e) throws SAXException {}

    public void error(SAXParseException e) throws SAXException {}

    public void warning(SAXParseException err) throws SAXException {}

    /**
     * receives notification that parsing of content model is beginning.
     *
     * @param elementName      name of the element whose content model is going to be defined.
     * @param contentModelType {@link #CONTENT_MODEL_EMPTY}
     *                         this element has EMPTY content model. This notification
     *                         will be immediately followed by the corresponding endContentModel.
     *                         {@link #CONTENT_MODEL_ANY}
     *                         this element has ANY content model. This notification
     *                         will be immediately followed by the corresponding endContentModel.
     *                         {@link #CONTENT_MODEL_MIXED}
     *                         this element has mixed content model. #PCDATA will not be reported.
     *                         each child element will be reported by mixedElement method.
     *                         {@link #CONTENT_MODEL_CHILDREN}
     *                         this elemen has child content model. The actual content model will
     *                         be reported by childElement, startModelGroup, endModelGroup, and
     *                         connector methods. Possible call sequences are:
     *                         <p>
     *                         START := MODEL_GROUP
     *                         MODEL_GROUP := startModelGroup TOKEN (connector TOKEN)* endModelGroup
     *                         TOKEN := childElement
     *                         | MODEL_GROUP
     */
    public void startContentModel(String elementName, short contentModelType) throws SAXException {
        log.info(">> content model elementName = "+elementName
        +", contentModelType = "+contentModelType);
    }

    /**
     * receives notification that parsing of content model is finished.
     */
    public void endContentModel(String elementName, short contentModelType) throws SAXException {
        log.info("<< content model elementName = "+elementName+", contentModelType = "+contentModelType);
    }


    public void attributeDecl(String elementName, String attributeName, String attributeType,
                              String[] enumeration, short attributeUse, String defaultValue) throws SAXException {
        log.info("attribute elementName = "+elementName
                +", attributeName = "+attributeName
                +", attributeType = "+attributeType
                +", enumeration = "+ Arrays.toString(enumeration)
                +", attributeUse = "+attributeUse
                +", defaultValue = "+defaultValue);

    }

    public void childElement(String elementName, short occurence) throws SAXException {
        log.info("child element = "+elementName+", occurence = "+occurence);
    }

    /**
     * receives notification of child element of mixed content model.
     * this method is called for each child element.
     *
     * @see #startContentModel(String, short)
     */
    public void mixedElement(String elementName) throws SAXException {
        log.info("mixed element = "+elementName);
    }

    public void startModelGroup() throws SAXException {

    }

    public void endModelGroup(short occurence) throws SAXException {

    }

    /**
     * Connectors in one model group is guaranteed to be the same.
     * <p>
     * <p>
     * IOW, you'll never see an event sequence like (a|b,c)
     */
    public void connector(short connectorType) throws SAXException {
        log.info("connectorType = "+connectorType);
    }
}
