package OutputFileWriter;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import InputFileReader.GradeBook;
import InputFileReader.Student;

public interface IWriter {
	
	public void write_File(List<String> header, GradeBook gradeBook, List<Student> studentList) 
			throws IOException, ParseException, ParserConfigurationException, SAXException;
}
