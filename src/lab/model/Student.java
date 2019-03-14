package lab.model;

public class Student {
	
	private String fullName;
	private int group;
	private int diseasePasses;
	private int otherReasonPasses;
	private int withoutReasonPasses;
	private int allPasses;
	
	public Student(String fullName, int group, int diseasePasses, int otherReasonPasses, int withoutReasonPasses) {
		this.fullName = fullName;
		this.group = group;
		this.diseasePasses = diseasePasses;
		this.otherReasonPasses = otherReasonPasses;
		this.withoutReasonPasses = withoutReasonPasses;
		this.allPasses = diseasePasses + otherReasonPasses + withoutReasonPasses;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public int getGroup() {
		return group;
	}
	
	public int getDiseasePasses() {
		return diseasePasses;
	}
	
	public int getOtherReasonPasses() {
		return otherReasonPasses;
	}
	
	public int getWithoutReasonPasses() {
		return withoutReasonPasses;
	}
	
	public int getAllPasses() {
		return allPasses;
	}
}
