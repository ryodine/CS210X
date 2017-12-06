import java.util.ArrayList;

import java.util.*;

public class tester {
	static Scanner user = new Scanner(System.in);
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
		return (str.length() == 1);
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
		return (str.length() > 0);
	}
	
	public static void main (String[] agrs) {
		
		// return true
		
		
		// Is pre-zeroes-number considered valid? 
		// 
		
		// Assume that 0000001 is valid
		/*
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
		for (String a : getRight) {
			System.out.println(isLetter(a) || isNumber(a));
		}
		
		
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
		getWrong.add("");
		for (String a : getWrong) {
			System.out.println(isLetter(a) || isNumber(a));
		}
		
		System.out.println(isLetter("adf") || isNumber("adf"));
		*/
		
		
		SimpleExpressionParser parser = new SimpleExpressionParser();
		/*
		for (int i = 0; i < 20; i ++) {
			try {
				System.out.println("Enter new expression: ");
				String input = user.nextLine();
				input = input.replaceAll(" ", "");
				Expression current = parser.parse(input, false);
				System.out.println("---------------------");
				System.out.println(current.convertToString(0));
				System.out.println("---------------------");
			}
			
			catch (Exception e) {
				System.out.println("Not valid Expression. Enter again:");
				e.printStackTrace();
				continue;
			}
			
		}
		*/
		
		
		/*
		System.out.println("Enter new expression: ");
		String input = user.nextLine();
		input = input.replaceAll(" ", "");
		Expression current = parser.parseExpression(input);
		System.out.println(current.convertToString(0));
		*/
		
		
		//2*x + 3*y + (7+6*z)
		
		Expression two = new LiteralExpression("2");
		Expression x = new LiteralExpression("x");
		Expression three = new LiteralExpression("3");
		Expression y = new LiteralExpression("y");
		Expression seven = new LiteralExpression("7");
		Expression six = new LiteralExpression("6");
		Expression z = new LiteralExpression("z");
		
		MultiplicativeExpression multi2x = new MultiplicativeExpression();
		multi2x.addSubexpression(two);
		multi2x.addSubexpression(x);
		
		MultiplicativeExpression multi3y = new MultiplicativeExpression();
		multi3y.addSubexpression(three);
		multi3y.addSubexpression(y);
		
		MultiplicativeExpression multi6z = new MultiplicativeExpression();
		multi6z.addSubexpression(six);
		multi6z.addSubexpression(z);
		
		AdditiveExpression plus7Sub6z = new AdditiveExpression();
		plus7Sub6z.addSubexpression(seven);
		plus7Sub6z.addSubexpression(multi6z);
		
		AdditiveExpression plus2xAnd3y = new AdditiveExpression();
		plus2xAnd3y.addSubexpression(multi2x);
		plus2xAnd3y.addSubexpression(multi3y);
		
		ParentheticalExpression paren = new ParentheticalExpression();
		paren.addSubexpression(plus7Sub6z);
		
		AdditiveExpression top = new AdditiveExpression();
		top.addSubexpression(plus2xAnd3y);
		top.addSubexpression(paren);
		
		System.out.println(top.convertToString(0));
		
		top.flatten();
		System.out.println(top.convertToString(0));
		
		
		
		
		
		System.out.println("---------------------");
		System.out.println("Enter new expression: ");
		String input = user.nextLine();
		input = input.replaceAll(" ", "");
		//Expression current = parser.parseExpression(input);
		//System.out.println(current.convertToString(0));
		//current.flatten();
		//System.out.println(current.convertToString(0));
		
		
		/*
		ParentheticalExpression top1 = new ParentheticalExpression();
		
		ParentheticalExpression top2 = new ParentheticalExpression();
		top1.addSubexpression(top2);
		ParentheticalExpression top3 = new ParentheticalExpression();
		top2.addSubexpression(top3);
		ParentheticalExpression top4 = new ParentheticalExpression();
		top3.addSubexpression(top4);
		top4.addSubexpression(six);
		
		System.out.println(top1.convertToString(0));
		
		*/
		
		
		
	}
	
	
}
