package Client;

import InputFileReader.IReader;
import OutputFileWriter.IWriter;

public abstract class AbstractFactory {
	public abstract IReader GradeIpFactoryMethod(String type);
	public abstract IWriter GradeOpFactoryMethod(String type);
  
}
