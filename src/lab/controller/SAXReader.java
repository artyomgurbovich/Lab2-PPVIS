package lab.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import lab.model.Student;

public class SAXReader {
	
	public ArrayList<Student> Read(File file) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        StudentsHandler handler = new StudentsHandler();
        parser.parse(file, handler);
		return handler.getStudents();
	}
}
