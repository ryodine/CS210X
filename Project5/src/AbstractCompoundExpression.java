import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class AbstractCompoundExpression implements CompoundExpression {
	protected String name;
	private LinkedList<Expression> children;
	private CompoundExpression parent;
	private Pane javaFXView;

	public AbstractCompoundExpression () {
		children = new LinkedList<Expression>();
	}
	
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

	/**
	 *
	 * @return A "deep copy" of this Expression
	 */
	@Override
	public Expression deepCopy() {
		AbstractCompoundExpression copy = new AbstractCompoundExpression();
		copy.setName(this.name);
		copy.setParent(null); // might change this later in R2

		for(Expression e: this.children) {
			Expression ecopy = e.deepCopy();
			ecopy.setParent(copy);
			copy.children.add(ecopy);
		}

		return copy;
	}

	@Override
	public Node getNode() {
		if (javaFXView == null) {
			javaFXView = new HBox();

			for (int i = 0; i < children.size() - 1; i++) {
				javaFXView.getChildren().add(children.get(i).getNode());

				Text oper = new Text();
				oper.setFont(Expression.font);
				oper.setText(name);
				javaFXView.getChildren().add(oper);
			}
			javaFXView.getChildren().add(children.get(children.size() - 1).getNode());
		}
		return javaFXView;
	}

	/**
	 * Helper method for flatten() in SimpleCompoundExpression
	 * @return a list of all subExpressions
	 */
	protected LinkedList<Expression> getChildren() {
		return children;
	}

	@Override
	public void flatten() {};
	
	@Override
	public String convertToString(int indentLevel) {
		String result = "";
		for (int i = 0; i < indentLevel; i ++){
			result += "\t";
		}
		result += this.name + "\n";
		for (Expression child : children) {
			result += child.convertToString(indentLevel + 1);
		}
		return result;
	}
	
	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	
	
}
