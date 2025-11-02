package week5.day1;

public class LearnSuperKW   extends LearnConstructor{
	
public LearnSuperKW() {
		
		super("ANBU", 11);
		
		super.age=11;
		super.name="JAYABHARATHI";
		System.out.println("LEARNSUPERKW");
		
	}
	
	public static void main(String[] args) {
		LearnSuperKW obj2 = new LearnSuperKW();
		System.out.println(obj2.age);
		
	}
}
