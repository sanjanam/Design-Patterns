package OutputFileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import Caluclator.GradeCaluclator;
import InputFileReader.GradeBook;
import InputFileReader.IpJSON;
import InputFileReader.IpXml;
import InputFileReader.Student;

public class OpCSV implements IWriter {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	IpXml inputXML = new IpXml();
	GradeCaluclator calGrade = new GradeCaluclator();

	
	public void write_File(List<String> header, GradeBook gradeBook, List<Student> student) throws IOException, ParseException, ParserConfigurationException, SAXException{
		//creating an output file
		FileWriter fw = new FileWriter("src/inputandOutput/outCSV.csv");
		
		for(int m=0; m<header.size(); m++){
			fw.append(header.get(m));
			fw.append(COMMA_DELIMITER);
		}
		fw.append(NEW_LINE_SEPARATOR);
		WriteData_CSV(fw, gradeBook, student);
		fw.flush();
		fw.close();
	}	
	
	public void WriteData_CSV(FileWriter fileWriter, GradeBook gradeBook, List<Student> studentList) throws IOException, ParseException, ParserConfigurationException, SAXException{
		
		Student student;
		Map<String, List<String>> assignedWork;
		List<String> gradeWork;
		int x=0;

		
		List<String> cList = gradeBook.getCategory();
		for(int m=0; m<studentList.size(); m++){
			student = studentList.get(m);
			
			fileWriter.append(student.getName());
			fileWriter.append(COMMA_DELIMITER);

			fileWriter.append(student.getId());
			fileWriter.append(COMMA_DELIMITER);

			assignedWork = student.getAssignedwork();
			Float total = (float) 0;
			
			for(int n=0; n<cList.size(); n++){
				gradeWork = assignedWork.get(cList.get(n));
				float gradeScore = Float.parseFloat(gradeBook.getGradeItem().get(cList.get(n)));
				float marks = 0;
				for(x=0 ; x<gradeWork.size(); x++){
					fileWriter.append(gradeWork.get(x));
					fileWriter.append(COMMA_DELIMITER);
					float temp = calGrade.isLetterGrade(gradeWork.get(x));
					marks = marks + (temp/ 100) ;
				}
				total = total + ((marks/ x) * gradeScore);
			}
			fileWriter.append(total.toString());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(calGrade.calGrade(total));
			fileWriter.append(NEW_LINE_SEPARATOR);
		}
	}
}
