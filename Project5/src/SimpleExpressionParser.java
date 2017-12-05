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
		int idxOfOp = str.indexOf(operator);
		while (idxOfOp >= 0) {
			Expression resultFn1 = parseFn1.apply(str.substring(0, idxOfOp));
			Expression resultFn2 = parseFn2.apply(str.substring(idxOfOp+1));
			if (resultFn1 != null && resultFn2 != null) {

				//Create a node of type operator
				//Add children from resultFn1 and 2
			}

			idxOfOp = str.indexOf('+', idxOfOp+1);
		}

		//Alternate case
		Expression altResult = parseFn2.apply(str);
		if (altResult != null) {
			return altResult;
		}

		return null;
	}

	private static Expression parseE(String str) {
		return parserHelper(str, '+', SimpleExpressionParser::parseE, SimpleExpressionParser::parseM);
	}

	private static Expression parseM(String str) {
		return parserHelper(str, '*', SimpleExpressionParser::parseM, SimpleExpressionParser::parseX);
	}

	private static Expression parseX(String str) {
		return null;
	}

	private static Expression parseL(String str) {
		return null;
	}
}
