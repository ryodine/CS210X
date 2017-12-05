import java.util.LinkedList;

public class AbstractCompoundExpression implements CompoundExpression {
	protected String name;
	private LinkedList<Expression> children;
	private CompoundExpression parent;
	
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
	
	@Override
	public Expression deepCopy() {
		AbstractCompoundExpression copy = new AbstractCompoundExpression();
		copy.setName(this.name);
		copy.setParent(null); //might change this later

		for(Expression e: this.children) {
			Expression ecopy = e.deepCopy();
			ecopy.setParent(copy);
			copy.children.add(ecopy);
		}

		return copy;
	}

	// TODO delete this
	public LinkedList<Expression> getChildren() {
		return children;
	}

	@Override
	public void flatten() {
		// TODO Auto-generated method stub
	}
	
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
