package InputFileReader;

import java.util.List;
import java.util.Map;

public class GradeBook {
	//creating list for category
	List<String> category;
	//creating map for grades
	Map<String, String> gradeItem;
	//creating setters and getters
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public Map<String, String> getGradeItem() {
		return gradeItem;
	}
	public void setGradeItem(Map<String, String> gradeItem) {
		this.gradeItem = gradeItem;
	}

	
	
}
