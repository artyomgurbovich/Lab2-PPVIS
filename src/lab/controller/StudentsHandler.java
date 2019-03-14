package lab.controller;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lab.model.Student;

public class StudentsHandler extends DefaultHandler {
	
	private ArrayList<Student> students = new ArrayList<Student>();
	private StringBuilder data = null;

	public ArrayList<Student> getStudents() {
		return students;
	}

	boolean bGroup = false;
	boolean bDiseasePasses = false;
	boolean bOtherReasonPasses = false;
	boolean bWithoutReasonPasses = false;
	
	String fullname;
	int group;
	int diseasePasses;
	int otherReasonPasses;
	int withoutReasonPasses;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("student")) {
			fullname = attributes.getValue("fullname");
		} else if (qName.equalsIgnoreCase("group")) {
			bGroup = true;
		} else if (qName.equalsIgnoreCase("diseasePasses")) {
			bDiseasePasses = true;
		} else if (qName.equalsIgnoreCase("otherReasonPasses")) {
			bOtherReasonPasses = true;
		} else if (qName.equalsIgnoreCase("withoutReasonPasses")) {
			bWithoutReasonPasses = true;
		}
		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (bGroup) {
			group = Integer.parseInt(data.toString());
			bGroup = false;
		} else if (bDiseasePasses) {
			diseasePasses = Integer.parseInt(data.toString());
			bDiseasePasses = false;
		} else if (bOtherReasonPasses) {
			otherReasonPasses = Integer.parseInt(data.toString());
			bOtherReasonPasses = false;
		} else if (bWithoutReasonPasses) {
			withoutReasonPasses = Integer.parseInt(data.toString());
			bWithoutReasonPasses = false;
		}
		
		if (qName.equalsIgnoreCase("student")) {
			students.add(new Student(fullname, group, diseasePasses, otherReasonPasses, withoutReasonPasses));
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}