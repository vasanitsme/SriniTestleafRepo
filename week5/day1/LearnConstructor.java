package week5.day1;

public class LearnConstructor {

		// Global Variable
		int age;
		String name;

		// Default Constructor - Cannot duplicate
		public LearnConstructor() {
			System.out.println("Iam a Default Constructor");
		}

		// Parameterized Constructor - Can duplicate by changing the parameters
		public LearnConstructor(int num, String name) {
			System.out.println("Iam a Default Constructor");
		}

		public LearnConstructor(int num) {
			System.out.println("Iam a Default Constructor");
		}

		public LearnConstructor(String name, int num) {
			System.out.println("Iam a Default Constructor");
		}

		public static void main(String[] args) {

			LearnConstructor obj = new LearnConstructor(20);
			System.out.println(obj.age + " " + obj.name);
			obj.age = 10;
			obj.name = "DILIP KUMAR";
			System.out.println(obj.age + " " + obj.name);

		}

	}
