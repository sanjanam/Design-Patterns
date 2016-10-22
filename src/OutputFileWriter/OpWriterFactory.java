package OutputFileWriter;

import Client.AbstractFactory;
import InputFileReader.IReader;

public class OpWriterFactory extends AbstractFactory{
	public IWriter GradeOpFactoryMethod(String type){            
		IWriter writer = null;       
	        switch (type) {
	        //depending on the user requirement output file will be created
	            case "CSV":
	                writer = new OpCSV();
	                break;
	            case "HTML":
	                writer = new OpHTML();
	                break;
	            case "XML":
	                writer = new OpXML();
	                break;
	            default:
	                break;
	        }
	        return writer;
	    }

	@Override
	public IReader GradeIpFactoryMethod(String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
