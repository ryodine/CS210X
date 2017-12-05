import java.util.ArrayList;

public class tester {
	private static boolean isLetter(String str) {
		String name = str.toLowerCase();
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isNumber(String str){
		String check = "0123456789";
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (check.contains(str.substring(i, i + 1))) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public static void main (String[] agrs) {
		// return true
		
		// Assume that 0000001 is valid
		ArrayList<String> getRight = new ArrayList<String>();
		getRight.add("a");
		getRight.add("b");
		getRight.add("z");
		getRight.add("f");
		getRight.add("g");
		getRight.add("1");
		getRight.add("123");
		getRight.add("5347657658");
		getRight.add("145238745");
		
		System.out.println("This is all true: ");
		
		
		System.out.println();
		System.out.println("This is all false: ");
		// return false
		ArrayList<String> getWrong = new ArrayList<String>();
		getWrong.add("adf");
		getWrong.add("hn.");
		getWrong.add("(h");
		getWrong.add("/y");
		getWrong.add("*7");
		getWrong.add("+7");
		getWrong.add("7+");
		getWrong.add("7*");
		getWrong.add("7)");
		getWrong.add("8953228reg.,");
		
		
	}
}
