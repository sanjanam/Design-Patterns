package Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import InputFileReader.GradeBook;
import InputFileReader.IReader;
import InputFileReader.IpJSON;
import InputFileReader.IpReaderFactory;
import InputFileReader.IpXml;
import InputFileReader.Student;
import OutputFileWriter.IWriter;
import OutputFileWriter.OpCSV;
import OutputFileWriter.OpWriterFactory;

public class Test {
	
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException, ParseException{	
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter type of input file as XML/JSON");
		String inputType = scan.nextLine();
		System.out.println("Enter type of output file as CSV/HTML/XML");
		String outputType = scan.nextLine();
		AbstractFactory readerFactory=FactoryProducer.getFactory("reader"); 
		AbstractFactory writerFactory=FactoryProducer.getFactory("writer"); 
		IReader reader=readerFactory.GradeIpFactoryMethod(inputType);
		IWriter writer=writerFactory.GradeOpFactoryMethod(outputType);
		List<String> header = reader.CreateSchema();
		GradeBook grdBook = reader.readIpGradeBook();
		List<Student> stdList = reader.readIpStudent();
		writer.write_File(header, grdBook, stdList);
		System.out.println("Open the corresponding file in the inputandOutput folder in the project to see the output");
		
	}
    
	
}
