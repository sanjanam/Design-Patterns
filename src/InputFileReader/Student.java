package InputFileReader;

import java.util.List;
import java.util.Map;

public class Student {
 private String course;
 private String name;
 private String id;
 Map<String, List<String>> assignedwork;
 
 //creating setters and getters

 
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Map<String, List<String>> getAssignedwork() {
	return assignedwork;
}
public void setAssignedwork(Map<String, List<String>> assignedwork) {
	this.assignedwork = assignedwork;
}

}
