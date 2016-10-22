package InputFileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IpJSON implements IReader{
	Student std;
    GradeBook grdBook = new GradeBook();

	public GradeBook readIpGradeBook() throws IOException, ParseException {
		//creating a list for category items
		List<String> cList = new ArrayList<>(); 
        Map<String, String> gradeItem = new HashMap<>();
        JSONParser parser = new JSONParser();	
        Object obj = parser.parse(new FileReader("src/inputandOutput/input.json"));
         JSONArray jsonArray;
        JSONObject jsonObject = null;
        if (obj instanceof JSONArray) {
        	jsonArray = (JSONArray) obj;  
        	jsonObject=(JSONObject) jsonArray.get(0);
        } else if (obj instanceof JSONObject) {
            jsonObject = (JSONObject)obj;
        }
        JSONArray grdBookArray;
        JSONObject grdBookObject = null;
        if(jsonObject.get("GradeBook") instanceof JSONArray){
        	grdBookArray = (JSONArray) jsonObject.get("GradeBook");
            grdBookObject=(JSONObject) grdBookArray.get(0);
        } else if(jsonObject.get("GradeBook") instanceof JSONObject){
        	grdBookObject=(JSONObject) jsonObject.get("GradeBook");
        }
        

        JSONArray gradingSchemaArray;
        JSONObject gradingSchemaObject = null;
        if(grdBookObject.get("GradingSchema") instanceof JSONArray){
        	gradingSchemaArray = (JSONArray) grdBookObject.get("GradingSchema");
        	gradingSchemaObject=(JSONObject) gradingSchemaArray.get(0);
        } else if(grdBookObject.get("GradingSchema") instanceof JSONObject){
        	gradingSchemaObject=(JSONObject) grdBookObject.get("GradingSchema");
        }

        JSONArray grdItemArray;
        JSONObject grdItemObject = null;
        if(gradingSchemaObject.get("GradeItem") instanceof JSONArray){
        	grdItemArray = (JSONArray) gradingSchemaObject.get("GradeItem");
        	for(int i=0; i<grdItemArray.size();i++){
    	        grdItemObject=(JSONObject) grdItemArray.get(i);
    	        String category = (String) grdItemObject.get("Category");
    	        String percentage = (String) grdItemObject.get("Percentage");
    	        cList.add(category);
    	        gradeItem.put(category, percentage);
            }        
        } else if(gradingSchemaObject.get("GradeItem") instanceof JSONObject){
        	grdItemObject = (JSONObject) gradingSchemaObject.get("GradeItem");
        	String category = (String) grdItemObject.get("Category");
	        String percentage = (String) grdItemObject.get("Percentage");
	        cList.add(category);
	        gradeItem.put(category, percentage);
        }
                
        grdBook.setGradeItem(gradeItem);
    	grdBook.setCategory(cList);
        return grdBook;
	}
	
	public List<Student> readIpStudent() throws IOException, ParseException {
		List<Student> studentList = new ArrayList<>();
		
		JSONParser parser = new JSONParser();		
        Object obj = parser.parse(new FileReader("src/inputandOutput/input.json" ));
        
        JSONArray jsonArray;
        JSONObject jsonObject = null;
        if (obj instanceof JSONArray) {
        	jsonArray = (JSONArray) obj;  
        	jsonObject=(JSONObject) jsonArray.get(0);
        } else if (obj instanceof JSONObject) {
            jsonObject = (JSONObject)obj;
        }
        
        JSONArray grdBookArray;
        JSONObject grdBookObject = null;
        if(jsonObject.get("GradeBook") instanceof JSONArray){
        	grdBookArray = (JSONArray) jsonObject.get("GradeBook");
            grdBookObject=(JSONObject) grdBookArray.get(0);
        } else if(jsonObject.get("GradeBook") instanceof JSONObject){
        	grdBookObject=(JSONObject) jsonObject.get("GradeBook");
        }
        
	    String course = (String) grdBookObject.get("-class");

    
        JSONArray grdArray;
        JSONObject gradesObject = null;
        if(grdBookObject.get("Grades") instanceof JSONArray){
        	grdArray = (JSONArray) grdBookObject.get("Grades");
        	gradesObject = (JSONObject) grdArray.get(0);
        } else if(grdBookObject.get("Grades") instanceof JSONObject){
        	gradesObject = (JSONObject) grdBookObject.get("Grades");
        }
        

        JSONArray studentsArray;
        JSONObject studentObject;
        if(gradesObject.get("Student") instanceof JSONArray){
        	studentsArray = (JSONArray) gradesObject.get("Student");
        	 for(int i=0; i<studentsArray.size(); i++){
      	        studentObject = (JSONObject) studentsArray.get(i);
        		std = createStudentList(studentObject, course);
    	        studentList.add(std);
             } 
        } else if(gradesObject.get("Student") instanceof JSONObject){
           studentObject = (JSONObject) gradesObject.get("Student");
           std = createStudentList(studentObject, course);
	       studentList.add(std);
        }
               
       return studentList;
	}
	public List<String> createSchema(JSONObject assignedWorkObject, List<String> schema){
		
		JSONArray grdWorkArray;
		JSONObject grdWorkObject = null;
		
		if(assignedWorkObject.get("GradedWork") instanceof JSONArray){
			grdWorkArray = (JSONArray) assignedWorkObject.get("GradedWork");
        	for(int k=0; k<grdWorkArray.size(); k++){
        		grdWorkObject = (JSONObject) grdWorkArray.get(k);
     		    schema.add((String)grdWorkObject.get("Name"));
    	    }               	
        } else if(assignedWorkObject.get("GradedWork") instanceof JSONObject){
        	grdWorkObject = (JSONObject) assignedWorkObject.get("GradedWork");
 		    schema.add((String)grdWorkObject.get("Name"));
        }
		
	    return schema;
	}
	public List<String> CreateSchema() throws FileNotFoundException, IOException, ParseException{

		List<String> schema = new ArrayList<>();
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(new FileReader("src/inputandOutput/input.json"));
        
        JSONArray jsonArray;
        JSONObject jsonObject = null;
        if (obj instanceof JSONArray) {
        	jsonArray = (JSONArray) obj;  
        	jsonObject=(JSONObject) jsonArray.get(0);
        } else if (obj instanceof JSONObject) {
            jsonObject = (JSONObject)obj;
        }
        
        JSONArray grdBookArray;
        JSONObject grdBookObject = null;
        if(jsonObject.get("GradeBook") instanceof JSONArray){
        	grdBookArray = (JSONArray) jsonObject.get("GradeBook");
            grdBookObject=(JSONObject) grdBookArray.get(0);
        } else if(jsonObject.get("GradeBook") instanceof JSONObject){
        	grdBookObject=(JSONObject) jsonObject.get("GradeBook");
        }
        
	    String course = (String) grdBookObject.get("-class");
        
        JSONArray gradesArray;
        JSONObject gradesObject = null;
        if(grdBookObject.get("Grades") instanceof JSONArray){
        	gradesArray = (JSONArray) grdBookObject.get("Grades");
        	gradesObject = (JSONObject) gradesArray.get(0);
        } else if(grdBookObject.get("Grades") instanceof JSONObject){
        	gradesObject = (JSONObject) grdBookObject.get("Grades");
        }
		
        JSONArray studentsArray;
        JSONObject studentObject = null;
        if(gradesObject.get("Student") instanceof JSONArray){
        	studentsArray = (JSONArray) gradesObject.get("Student");
        	studentObject = (JSONObject) studentsArray.get(0);
        } else if(gradesObject.get("Student") instanceof JSONObject){
           studentObject = (JSONObject) gradesObject.get("Student");
        }
        
        schema.add("Name");
        schema.add("ID");
        
        JSONArray assignedWorkArray;
        JSONObject assignedWorkObject = null;
        if(studentObject.get("AssignedWork") instanceof JSONArray){
        	assignedWorkArray = (JSONArray) studentObject.get("AssignedWork");
        	for(int j=0; j<assignedWorkArray.size(); j++){
    		    assignedWorkObject = (JSONObject) assignedWorkArray.get(j);
    		    schema = createSchema(assignedWorkObject, schema);
    	    }               	
        } else if(studentObject.get("AssignedWork") instanceof JSONObject){
        	assignedWorkObject = (JSONObject) studentObject.get("AssignedWork");
        	schema = createSchema(assignedWorkObject, schema);
        }
              
	    schema.add("Total Percentage");
	    schema.add("Letter Grade");
		return schema;
	}
	

	public Map<String, List<String>> createStudentMarksList(JSONObject assignedWorkObject, Map<String, List<String>>  assignedWorkMap){
		 List<String> grdWorkList;
	     String category = (String) assignedWorkObject.get("-category");
	     JSONArray grdWorkArray;
	     JSONObject grdWorkObject;
	     if(assignedWorkObject.get("GradedWork") instanceof JSONArray){
	    	 grdWorkArray = (JSONArray) assignedWorkObject.get("GradedWork");
		     grdWorkList = new ArrayList<>();
	         for(int k=0; k<grdWorkArray.size(); k++){
	        	   grdWorkObject = (JSONObject) grdWorkArray.get(k);
			       grdWorkList.add((String)grdWorkObject.get("Grade"));
		       }  
		       assignedWorkMap.put(category, grdWorkList);
	      } else if(assignedWorkObject.get("GradedWork") instanceof JSONObject){
			  grdWorkList = new ArrayList<>();
	    	  grdWorkObject = (JSONObject) assignedWorkObject.get("GradedWork");
	    	  grdWorkList.add((String)grdWorkObject.get("Grade"));
		      assignedWorkMap.put(category, grdWorkList);
	      }	        
	      return assignedWorkMap;
	}
	
	public Student createStudentList(JSONObject studentObject, String course){
		std = new Student();
	
		Map<String, List<String>> assignedWorkMap = new HashMap<>();
		

        std.setCourse(course);
	    std.setName((String) studentObject.get("Name"));
	    std.setId((String) studentObject.get("ID"));
	       
	    JSONArray assignedWorkArray;
	    JSONObject assignedWorkObject = null;
        if(studentObject.get("AssignedWork") instanceof JSONArray){
        	assignedWorkArray = (JSONArray) studentObject.get("AssignedWork");
        	 for(int j=0; j<assignedWorkArray.size(); j++){
        		 assignedWorkObject = (JSONObject) assignedWorkArray.get(j);
        		 assignedWorkMap = createStudentMarksList(assignedWorkObject, assignedWorkMap);
        	 }       
        	 std.setAssignedwork(assignedWorkMap);
        } else if(studentObject.get("AssignedWork") instanceof JSONObject){
        	assignedWorkObject = (JSONObject) studentObject.get("AssignedWork");
        	assignedWorkMap = createStudentMarksList(assignedWorkObject, assignedWorkMap);
       	    std.setAssignedwork(assignedWorkMap);
        }
        
	    return std;
	}
	

}