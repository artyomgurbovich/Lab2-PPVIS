package lab.controller;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import lab.model.Student;

public class DOMWriter {
	public void Write(File file, ArrayList<Student> students) throws ParserConfigurationException, TransformerException {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();;
		
		Element studentsElement = document.createElement("students");
		document.appendChild(studentsElement);
		
		for (int i = 0; i < students.size(); i++) {
			Element studentElement = document.createElement("student");
			studentsElement.appendChild(studentElement);
			studentElement.setAttribute("fullname", students.get(i).getFullName());
			
			Element groupElement = document.createElement("group");
			groupElement.setTextContent(String.valueOf(students.get(i).getGroup()));
			studentElement.appendChild(groupElement);
			
			Element diseasePassesElement = document.createElement("diseasePasses");
			diseasePassesElement.setTextContent(String.valueOf(students.get(i).getDiseasePasses()));
			studentElement.appendChild(diseasePassesElement);
			
			Element otherReasonPassesElement = document.createElement("otherReasonPasses");
			otherReasonPassesElement.setTextContent(String.valueOf(students.get(i).getOtherReasonPasses()));
			studentElement.appendChild(otherReasonPassesElement);
			
			Element withoutReasonPassesElement = document.createElement("withoutReasonPasses");
			withoutReasonPassesElement.setTextContent(String.valueOf(students.get(i).getWithoutReasonPasses()));
			studentElement.appendChild(withoutReasonPassesElement);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(document);

		StreamResult result =  new StreamResult(file);
		transformer.transform(source, result);
	}
}
