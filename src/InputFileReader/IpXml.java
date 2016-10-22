package InputFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class IpXml implements IReader{

	Student std;
	GradeBook grdBook = new GradeBook();
	List<Student> stdlist = new ArrayList<>();
	
	public GradeBook readIpGradeBook() throws ParserConfigurationException, SAXException, IOException{
		Map<String, String> grdMap = new HashMap<>();
		List<String> category = new ArrayList<>();
	    NodeList grdList = readXMLFile("GradingSchema");		
		for (int m = 0; m < grdList.getLength(); m++) {
			Node gradeSchemaNode = grdList.item(m);							
			if (gradeSchemaNode.getNodeType() == Node.ELEMENT_NODE) {
				Element gradeSchemaElement = (Element) gradeSchemaNode;
			    NodeList gradeItemList = gradeSchemaElement.getElementsByTagName("GradeItem");
				grdMap = new HashMap<>();
				for (int n = 0; n < gradeItemList.getLength(); n++) {
					Node gradeItemNode = gradeItemList.item(n);
					if (gradeItemNode.getNodeType() == Node.ELEMENT_NODE) {
						Element gradeItemElement = (Element) gradeItemNode;
						category.add(gradeItemElement.getElementsByTagName("Category").item(0).getTextContent());					    
						grdMap.put(gradeItemElement.getElementsByTagName("Category").item(0).getTextContent(),
						gradeItemElement.getElementsByTagName("Percentage").item(0).getTextContent());
					}
				}
			}
		}
		grdBook.setCategory(category);
		grdBook.setGradeItem(grdMap);
		return grdBook;
	}

	public List<Student> readIpStudent() throws ParserConfigurationException, SAXException, IOException{
		
		Map<String, List<String>> assignedWorkMap;
		List<String> grdWorkList;
		String category;
		//Reading the input xml file
		File fXmlFile = new File("src/inputandOutput/input.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		//parsing the xml file
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();	
		Node gradeBookNode = doc.getElementsByTagName("GradeBook").item(0);
		String course = null;
		if (gradeBookNode.getNodeType() == Node.ELEMENT_NODE) {
			Element gradeBookElement = (Element) gradeBookNode;
			course = gradeBookElement.getAttribute("class");
		}
		NodeList studentList = doc.getElementsByTagName("Student");	
		for (int m = 0; m < studentList.getLength(); m++) {
			std = new Student();
			Node studentNode = studentList.item(m);							
			if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element studentElement = (Element) studentNode;
				std.setCourse(course);
				std.setName(studentElement.getElementsByTagName("Name").item(0).getTextContent());
				std.setId(studentElement.getElementsByTagName("ID").item(0).getTextContent());
				NodeList AssignedWorkList = studentElement.getElementsByTagName("AssignedWork");
				assignedWorkMap = new HashMap<>();
				for (int n = 0; n < AssignedWorkList.getLength(); n++) {
					Node assignedWorkNode = AssignedWorkList.item(n);
					if (assignedWorkNode.getNodeType() == Node.ELEMENT_NODE) {
						Element assignedWorkElement = (Element) assignedWorkNode;
						category = assignedWorkElement.getAttribute("category");					    
						NodeList gradedWorkNodeList = assignedWorkElement.getElementsByTagName("GradedWork");
						grdWorkList = new ArrayList<>();
						for (int x = 0; x < gradedWorkNodeList.getLength(); x++) {
							Node gradedWorkNode = gradedWorkNodeList.item(x);
							
							if (gradedWorkNode.getNodeType() == Node.ELEMENT_NODE) {
								Element gradedWorkElement = (Element) gradedWorkNode;
								grdWorkList.add(gradedWorkElement.getElementsByTagName("Grade").item(0).getTextContent());
							}
						}
						assignedWorkMap.put(category, grdWorkList);    

					}
				}
				std.setAssignedwork(assignedWorkMap);
			}
			stdlist.add(std);
		}
		
		return stdlist;
	}
	public List<String> CreateSchema() throws ParserConfigurationException, SAXException, IOException {

		List<String> schema = new ArrayList<>();
			//Reading the student node values
		NodeList studentList = readXMLFile("Student");
		Node studentNode = studentList.item(0);							
		if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
			Element studentElement = (Element) studentNode;
			schema.add("Name");
			schema.add("ID");
			NodeList AssignedWorkList = studentElement.getElementsByTagName("AssignedWork");
			for (int m = 0; m < AssignedWorkList.getLength(); m++) {
				Node assignedWorkNode = AssignedWorkList.item(m);
				if (assignedWorkNode.getNodeType() == Node.ELEMENT_NODE) {
					Element assignedWorkElement = (Element) assignedWorkNode;
					NodeList grdWorkNodeList = assignedWorkElement.getElementsByTagName("GradedWork");
					for (int n = 0; n < grdWorkNodeList.getLength(); n++) {
						Node gradedWorkNode = grdWorkNodeList.item(n);
						if (gradedWorkNode.getNodeType() == Node.ELEMENT_NODE) {
							Element gradedWorkElement = (Element) gradedWorkNode;
							schema.add(gradedWorkElement.getElementsByTagName("Name").item(0).getTextContent());
						}
					}
				}
			}
		}		         
	    schema.add("Total Percentage");
	    schema.add("Letter Grade");
		return schema;
	}

	
	private NodeList readXMLFile(String elementName) throws ParserConfigurationException, SAXException, IOException{
		//reading the input file
		File fXmlFile = new File("src/inputandOutput/input.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();				
		
		NodeList nodeList = doc.getElementsByTagName(elementName);	
		return nodeList;
	}
	
	
}
