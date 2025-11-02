package week5.day1;

// when we call a static method only that method gets executed - ln. no 25
// When calling a static with via  class (ln no 23) - firt executes static blocks if it have then static method 

public class LearnStaticKeyWord {

	// static - Variable
	static int number = 05;

	// Static - Mehtod
	public static void staticMethod() {
		System.out.println("Iam a static Method");
	}

	// Static - block
	static {
		System.out.println("Iam a static Block");
	}

	public static void main(String[] args) {

		staticMethod();
		LearnStaticKeyWord.staticMethod();

		System.out.println(LearnStaticKeyWord.number);

	}
}
