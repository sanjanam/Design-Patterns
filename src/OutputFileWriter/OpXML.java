package OutputFileWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import Caluclator.GradeCaluclator;
import InputFileReader.GradeBook;
import InputFileReader.Student;

public class OpXML implements IWriter {
	GradeCaluclator calculateGrade = new GradeCaluclator();
	@SuppressWarnings("deprecation")
	public void write_File(List<String> header, GradeBook gradeBook, List<Student> stdList) 
			throws IOException, ParseException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder = icFactory.newDocumentBuilder();
        Document doc = icBuilder.newDocument();
		File xmlFile = new File("src/inputandOutput/outXML");
		List<String> cList = gradeBook.getCategory();
		List<String> gradeWork;
        Element gradeBookElement = doc.createElement("GradeBook");
        doc.appendChild(gradeBookElement);
        Element gradeElement = doc.createElement("Grade");
        Student std;
        int x=0;
        Map<String, List<String>> assignedWork;
        for(int m=0; m< stdList.size(); m++){
        	std = stdList.get(m);
			
            Element studentElement = doc.createElement("Student");
        	gradeElement.appendChild(studentElement);

            Element nameElement = doc.createElement(header.get(0));
            Element idElement = doc.createElement(header.get(1));


        	Text nameText = doc.createTextNode(std.getName());
        	nameElement.appendChild(nameText);

        	Text idText = doc.createTextNode(std.getId());
        	idElement.appendChild(idText);
        	
        	studentElement.appendChild(nameElement);
        	studentElement.appendChild(idElement);

        	
        	Element assignedWorkElement = doc.createElement("AssignedWork");
        	assignedWork = std.getAssignedwork();
        	Float total = (float) 0;
        	for(int n=0; n<cList.size(); n++){
        		
            	Element gradedWorkElement = doc.createElement("GradedWork");
				gradedWorkElement.setAttribute("category", cList.get(n));

				gradeWork = assignedWork.get(cList.get(n));
				float gradeScore = Float.parseFloat(gradeBook.getGradeItem().get(cList.get(n)));
				float marks = 0;
				int value=1;
				for(x=0 ; x<gradeWork.size(); x++){

					Element gradedWorkNameElement = doc.createElement("Name");
	            	Element gradedWorkGradeElement = doc.createElement("Grade");

	            	Text gradedWorkNameText = doc.createTextNode(header.get(value++));
	            	gradedWorkNameElement.appendChild(gradedWorkNameText);
	    	        Text gradedWorkGradeText = doc.createTextNode(gradeWork.get(x));
	            	gradedWorkGradeElement.appendChild(gradedWorkGradeText);
		            float temp = calculateGrade.isLetterGrade(gradeWork.get(x));
					marks = marks + (temp/100) ;
					gradedWorkElement.appendChild(gradedWorkNameElement);
					gradedWorkElement.appendChild(gradedWorkGradeElement);
		        	assignedWorkElement.appendChild(gradedWorkElement);
				}
				total=total+((marks/x)*gradeScore);
			}
        	
        	studentElement.appendChild(assignedWorkElement);
        	Element totalElement = doc.createElement("Total");
        	Text totalText = doc.createTextNode(total.toString());
        	totalElement.appendChild(totalText);
        	studentElement.appendChild(totalElement);

        	
        	Element letterGradeElement = doc.createElement("Grade");
        	Text letterGradeText = doc.createTextNode(calculateGrade.calGrade(total));
        	letterGradeElement.appendChild(letterGradeText);
        	studentElement.appendChild(letterGradeElement);
        	
        	gradeBookElement.setAttribute("class", std.getCourse());

        }

    	gradeBookElement.appendChild(gradeElement);

        FileOutputStream outStream = new FileOutputStream(xmlFile);
		OutputFormat outFormat = new OutputFormat(doc);
		outFormat.setIndenting(true);
		XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
		serializer.serialize(doc);
	}
}
