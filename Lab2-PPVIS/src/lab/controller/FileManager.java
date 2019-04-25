package lab.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import lab.model.Passes;
import lab.model.Student;

public class FileManager {
	
	private final String INDENT_SPACE_SIZE = "4";
	
	private final String RECORDS_ELEMENT = "records";
	private final String RECORD_ELEMENT = "record";
	private final String STUDENT_ELEMENT = "student";
	private final String FIRST_NAME_ELEMENT = "firstName";
	private final String MIDDLE_NAME_ELEMENT = "middleName";
	private final String LAST_NAME_ELEMENT = "lastName";
	private final String GROUP_ELEMENT = "group";
	private final String PASSES_ELEMENT = "passes";
	private final String DISEASE_PASSES_ELEMENT = "diseasePasses";
	private final String OTHER_REASON_PASSES_ELEMENT = "otherReasonPasses";
	private final String WITHOUT_REASON_PASSES_ELEMENT = "withoutReasonPasses";
	
	public void write(File file, Map<Student, Passes> records) throws ParserConfigurationException, TransformerException {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();;
		
		Element recordsElement = document.createElement(RECORDS_ELEMENT);
		document.appendChild(recordsElement);
		
		for (Map.Entry<Student, Passes> record: records.entrySet()) {
			
			Element recordElement = document.createElement(RECORD_ELEMENT);
			recordsElement.appendChild(recordElement);

			Element studentElement = document.createElement(STUDENT_ELEMENT);
			recordElement.appendChild(studentElement);
			
			Element firstNameElement = document.createElement(FIRST_NAME_ELEMENT);
			firstNameElement.setTextContent(record.getKey().getFirstName());
			studentElement.appendChild(firstNameElement);
			Element middleNameElement = document.createElement(MIDDLE_NAME_ELEMENT);
			middleNameElement.setTextContent(record.getKey().getMiddleName());
			studentElement.appendChild(middleNameElement);
			Element lastNameElement = document.createElement(LAST_NAME_ELEMENT);
			lastNameElement.setTextContent(record.getKey().getLastName());
			studentElement.appendChild(lastNameElement);
			Element groupElement = document.createElement(GROUP_ELEMENT);
			groupElement.setTextContent(record.getKey().getGroup());
			studentElement.appendChild(groupElement);
			
			Element passesElement = document.createElement(PASSES_ELEMENT);
			recordElement.appendChild(passesElement);
			
			Element diseasePassesElement = document.createElement(DISEASE_PASSES_ELEMENT);
			diseasePassesElement.setTextContent(String.valueOf(record.getValue().getDiseasePasses()));
			passesElement.appendChild(diseasePassesElement);
			Element otherReasonPassesElement = document.createElement(OTHER_REASON_PASSES_ELEMENT);
			otherReasonPassesElement.setTextContent(String.valueOf(record.getValue().getOtherReasonPasses()));
			passesElement.appendChild(otherReasonPassesElement);
			Element withoutReasonPassesElement = document.createElement(WITHOUT_REASON_PASSES_ELEMENT);
			withoutReasonPassesElement.setTextContent(String.valueOf(record.getValue().getWithoutReasonPasses()));
			passesElement.appendChild(withoutReasonPassesElement);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", INDENT_SPACE_SIZE);
		DOMSource source = new DOMSource(document);

		StreamResult result =  new StreamResult(file);
		transformer.transform(source, result);
	}
	
	public Map<Student, Passes> read(File file) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        RecordsHandler handler = new RecordsHandler();
        parser.parse(file, handler);
		return handler.getRecords();
	}
	
	private class RecordsHandler extends DefaultHandler {

		private Map<Student, Passes> records = new HashMap<Student, Passes>();;
		private StringBuilder data = null;

		private String firstName;
		private String middleName;
		private String lastName;
		private String group;
		private int diseasePasses;
		private int otherReasonPasses;
		private int withoutReasonPasses;

		private boolean findFirstName = false;
		private boolean findMiddleName = false;
		private boolean findLastName = false;
		private boolean findGroup = false;
		private boolean findDiseasePasses = false;
		private boolean findOtherReasonPasses = false;
		private boolean findWithoutReasonPasses = false;

		public Map<Student, Passes> getRecords() {
			return records;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (qName.equalsIgnoreCase(FIRST_NAME_ELEMENT)) {
				findFirstName = true;
			} else if (qName.equalsIgnoreCase(MIDDLE_NAME_ELEMENT)) {
				findMiddleName = true;
			} else if (qName.equalsIgnoreCase(LAST_NAME_ELEMENT)) {
				findLastName = true;
			} else if (qName.equalsIgnoreCase(GROUP_ELEMENT)) {
				findGroup = true;
			} else if (qName.equalsIgnoreCase(DISEASE_PASSES_ELEMENT)) {
				findDiseasePasses = true;
			} else if (qName.equalsIgnoreCase(OTHER_REASON_PASSES_ELEMENT)) {
				findOtherReasonPasses = true;
			} else if (qName.equalsIgnoreCase(WITHOUT_REASON_PASSES_ELEMENT)) {
				findWithoutReasonPasses = true;
			}
			data = new StringBuilder();
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (findFirstName) {
				firstName = data.toString();
				findFirstName = false;
			} else if (findMiddleName) {
				middleName = data.toString();
				findMiddleName = false;
			} else if (findLastName) {
				lastName = data.toString();
				findLastName = false;
			} else if (findGroup) {
				group = data.toString();
				findGroup = false;
			} else if (findDiseasePasses) {
				diseasePasses = Integer.parseInt(data.toString());
				findDiseasePasses = false;
			} else if (findOtherReasonPasses) {
				otherReasonPasses = Integer.parseInt(data.toString());
				findOtherReasonPasses = false;
			} else if (findWithoutReasonPasses) {
				withoutReasonPasses = Integer.parseInt(data.toString());
				findWithoutReasonPasses = false;
			}

			if (qName.equalsIgnoreCase(RECORD_ELEMENT)) {
				records.put(new Student(firstName, middleName, lastName, group),
						    new Passes(diseasePasses, otherReasonPasses, withoutReasonPasses));
			}
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			data.append(new String(ch, start, length));
		}
	}
}
