package lab.model;

public class Passes {
	
	private int diseasePasses;
	private int otherReasonPasses;
	private int withoutReasonPasses;
	private int allPasses;
	
	public Passes(int diseasePasses, int otherReasonPasses, int withoutReasonPasses) {
		this.diseasePasses = diseasePasses;
		this.otherReasonPasses = otherReasonPasses;
		this.withoutReasonPasses = withoutReasonPasses;
		this.allPasses = diseasePasses + otherReasonPasses + withoutReasonPasses;
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
