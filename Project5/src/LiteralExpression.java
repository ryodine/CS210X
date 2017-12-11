import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * Completed class for Literal Expression
 * Literal Expression will always be a leaf node in the expression tree
 * Therefore it has no children
 */
public class LiteralExpression implements Expression {
	private String name;
	private CompoundExpression parent;
	private Text javaFXView;
	
	/**
	 * Constructor
	 * @param name
	 */
	public LiteralExpression (String name) {
		this.name = name;
		parent = null;
	}
	
	/**
	 * Constructor (overloading)
	 * @param name
	 * @param parent
	 */
	public LiteralExpression (String name, CompoundExpression parent) {
		this.name = name;
		this.parent = parent; 
	}
	
	/**
	 * This is here for testing the deepCopy() method if necessary
	 * @param name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public CompoundExpression getParent() {
		return this.parent;
	}

	@Override
	public void setParent(CompoundExpression parent) {
		this.parent = parent;
	}

	@Override
	public Expression deepCopy() {
		String newName = new String(this.name);
		Expression copy = new LiteralExpression(newName);
		return copy;
	}

	@Override
	public Node getNode() {
		if (javaFXView == null) {
			javaFXView = new Text();

			if (SimpleExpressionParser.isLetter(name)) {
				javaFXView.setFont(Expression.italicfont);
			} else {
				javaFXView.setFont(Expression.font);
			}
			javaFXView.setText(name);
		}
		return javaFXView;
	}

	@Override
	public void flatten() {
		//Literals are not to be flattened!
		return;
	}

	@Override
	public String convertToString(int indentLevel) {
		String result = "";
		for (int i = 0; i < indentLevel; i ++){
			result += "\t";
		}
		result += this.name  + "\n";
		return result;
	}
	
	
}
