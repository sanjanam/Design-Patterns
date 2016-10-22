package Caluclator;

public class GradeCaluclator {
	public String calGrade(float total){
		String grade = null;
			if(total >= 99){
				grade = "A+";
			} else if(total >= 95){
				grade = "A";
			} else if(total >= 90){
				grade = "A-";
			} else if(total >= 87){
				grade = "B+";
			} else if(total >= 84){
				grade = "B";
			} else if(total >= 80){
				grade = "B-";
			} else if(total >= 75){
				grade = "C+";
			} else if(total >= 70){
				grade = "C";
			} else if(total >= 60){
				grade = "D";
			} else if(total < 60){
				grade = "E";
			}
			
			return grade;
		}
	
	public float isLetterGrade(String grade){
		float val = 0;
		if(grade.equalsIgnoreCase("A+")){
			val = (99 + 100)/2;
		} else if(grade.equalsIgnoreCase("A")){
			val = (float) ((95 + 98.99)/2);
		} else if(grade.equalsIgnoreCase("A-")){
			val = (float) ((90 + 94.99)/2);
		} else if(grade.equalsIgnoreCase("B+")){
			val = (float) ((87 + 89.99)/2);
		} else if(grade.equalsIgnoreCase("B")){
			val = (float) ((84 + 86.99)/2);
		} else if(grade.equalsIgnoreCase("B-")){
			val = (float) ((80 + 83.99)/2);
		} else if(grade.equalsIgnoreCase("C+")){
			val = (float) ((75 + 79.99)/2);
		} else if(grade.equalsIgnoreCase("C")){
			val = (float) ((70 + 74.99)/2);
		} else if(grade.equalsIgnoreCase("D")){
			val = (float) ((60 + 69.99)/2);
		} else if(grade.equalsIgnoreCase("E")){
			val = (float) ((0 + 59.99)/2);
		} else{
			val = Float.parseFloat(grade);
		}
		
		return val;
	}

	
}
