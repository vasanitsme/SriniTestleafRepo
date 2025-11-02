package week5.day4;

import java.util.ArrayList;
import java.util.List;

public class CodingChallengeFour {

	public static void main(String[] args) {
		int[] nums = { 4, 1, 2, 1, 2 };
		List<Integer> output = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {

			for (int j = 1; j < nums.length; j++) {

				if (nums[i] == nums[j]) {
					output.add(nums[j]);
					break;
				}

			}

		}

		System.out.println(("Duplicate numbers are : " + output));

	}

}
