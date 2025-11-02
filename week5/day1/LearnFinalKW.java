package week5.day1;

//Final Applies to Variable, Method and Class
//Final means final - wont allow to change
//Final can be used along with static
//Restrict classes from getting inherited like extends
//Restrict  methods from getting overridden like polymorphism

public final class  LearnFinalKW {

	public final String name = "Jithin";
	public static final String empname = "Jithin";

	public static final void display() {

		System.out.println("I am a public static final void method");

	}

	public final void empdisplay() {
		
		System.out.println("I am a public non static final void method");

	}
	
	public static void main(String[] args) {
		
		LearnFinalKW kw = new LearnFinalKW();
		//kw.name = "Ravi";
		
		
	}

}
