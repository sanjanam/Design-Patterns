package InputFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public interface IReader {

	public GradeBook readIpGradeBook() throws IOException, ParseException, ParserConfigurationException, SAXException;
	public List<Student> readIpStudent() throws IOException, ParseException, ParserConfigurationException, SAXException;
	public List<String> CreateSchema() throws FileNotFoundException, IOException, ParseException, ParserConfigurationException, SAXException;

}
