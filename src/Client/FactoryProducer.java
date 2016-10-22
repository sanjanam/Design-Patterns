package Client;

import InputFileReader.IpReaderFactory;
import OutputFileWriter.OpWriterFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice){
		   
	      if(choice.equalsIgnoreCase("reader")){
	         return new IpReaderFactory();
	         
	      }else if(choice.equalsIgnoreCase("writer")){
	         return new OpWriterFactory();
	      }
	      
	      return null;
	   }
}
