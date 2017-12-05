import java.lang.reflect.Parameter;
import java.util.function.Function;

/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar: (updated)
 * E := E+M | m
 * M := M*X | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */
public class SimpleExpressionParser implements ExpressionParser {
	/*
	 * Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
         * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @param withJavaFXControls you can just ignore this variable for R1
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str, boolean withJavaFXControls) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}
	
	protected Expression parseExpression (String str) {
		Expression expression;
		
		// TODO implement me
		return null;
	}


	private static Expression parserHelper (String str, char operator, Function<String, Expression> parseFn1, Function<String, Expression> parseFn2) {
		AbstractCompoundExpression expr;
		switch (operator) {
			case '*':
				expr = new MultiplicativeExpression();
				break;
			case '+':
				expr = new AdditiveExpression();
				break;
			default:
				return null;
		}

		int idxOfOp = str.indexOf(operator);

		while (idxOfOp >= 0) {
			Expression resultFn1 = parseFn1.apply(str.substring(0, idxOfOp));
			Expression resultFn2 = parseFn2.apply(str.substring(idxOfOp+1));
			if (resultFn1 != null && resultFn2 != null) {
				expr.addSubexpression(resultFn1);
				expr.addSubexpression(resultFn2);

				return expr;
			}

			idxOfOp = str.indexOf('+', idxOfOp+1);
		}

		//Alternate case
		Expression altResult = parseFn2.apply(str);
		return altResult;
	}

	//Addition
	private static Expression parseE(String str) {
		return parserHelper(str, '+', SimpleExpressionParser::parseE, SimpleExpressionParser::parseM);
	}

	//Multiplicative
	private static Expression parseM(String str) {
		return parserHelper(str, '*', SimpleExpressionParser::parseM, SimpleExpressionParser::parseX);
	}

	//Parens
	private static Expression parseX(String str) {
		if (str.substring(0,1).equals("(") && str.substring(str.length()-1, str.length()).equals(")")) {

			Expression sub = parseE(str.substring(1, str.length()-1));

			if (sub != null) {
				// return a paren expression
				ParentheticalExpression expr = new ParentheticalExpression();
				expr.addSubexpression(sub);
				return expr;
			}
		}

		Expression altsub = parseL(str);

		return altsub;
	}

	//Literal
	private static Expression parseL(String str) {
		if (isLetter(str) || isNumber(str)) {
			return new LiteralExpression(str);
		}
		else {
			return null;
		}
	}
	
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
}
