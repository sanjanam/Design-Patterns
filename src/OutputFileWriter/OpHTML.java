package OutputFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import Caluclator.GradeCaluclator;
import InputFileReader.GradeBook;
import InputFileReader.Student;

public class OpHTML implements IWriter{
	public void write_File(List<String> header, GradeBook gradeBook, List<Student> studentList) 
			throws IOException, ParseException, ParserConfigurationException, SAXException{
		 StringBuilder sb = new StringBuilder();
		    sb.append("<html>");
		    sb.append("<head>");
		    sb.append("<title>Student_Grades</title>");
		    sb.append("</head>");
		    sb.append("<body>");
		    sb.append("<table border='1'>");
		    sb.append("<tr>");
		    
		    for(int m=0; m<header.size(); m++){
			    sb.append("<th>");
		    	sb.append(header.get(m));
			    sb.append("</th>");
			}
		    sb.append("</tr>");

			WriteData_HTML(sb, gradeBook, studentList);
			sb.append("</body>");
		    sb.append("</html>");
		    FileWriter fstream = new FileWriter("src/inputandOutput/outHTML.html");
		    BufferedWriter bw = new BufferedWriter(fstream);
		    bw.write(sb.toString());
		    bw.close();
	}
	
	public void WriteData_HTML(StringBuilder sb, GradeBook gradeBook, List<Student> studentList){
		Student std;
		GradeCaluclator calGrade = new GradeCaluclator();
		Map<String, List<String>> assignedWork;
		List<String> grade;
		int x=0;

		
		List<String> cList = gradeBook.getCategory();
		for(int m=0; m<studentList.size(); m++){
		    
			std = studentList.get(m);
			sb.append("<tr>");
		    sb.append("<td>"+std.getName()+"</td>");
		    sb.append("<td>"+std.getId()+"</td>");

			assignedWork = std.getAssignedwork();
			Float total = (float) 0;
			for(int n=0; n<cList.size(); n++){
				grade = assignedWork.get(cList.get(n));
				float gradeScore = Float.parseFloat(gradeBook.getGradeItem().get(cList.get(n)));
				float marks = 0;
				for(x=0 ; x<grade.size(); x++){
					sb.append("<td>"+grade.get(x)+"</td>");
					float temp = calGrade.isLetterGrade(grade.get(x));
					marks = marks + ( temp/ 100) ;
				}
				total = total + ((marks/ x) * gradeScore);
			}
			sb.append("<td>"+total.toString()+"</td>");
			sb.append("<td>"+calGrade.calGrade(total)+"</td>");
		}
	}

}
