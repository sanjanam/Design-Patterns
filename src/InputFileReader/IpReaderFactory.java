package InputFileReader;

import Client.AbstractFactory;
import OutputFileWriter.IWriter;

public class IpReaderFactory extends AbstractFactory {
public IReader GradeIpFactoryMethod(String type) {            
	IReader reader = null;
	//Depending on the user input selects the input file
        switch (type) {
            case "XML":
            	//if user enters xml then inputxml is opened
                reader = new IpXml();
                break;
            case "JSON":
            	//if user enters json then inputjson is opened
                reader = new IpJSON();
                break;
            default:
                break;
        }
        return reader;
    }

@Override
public IWriter GradeOpFactoryMethod(String type) {
	// TODO Auto-generated method stub
	return null;
}
}
