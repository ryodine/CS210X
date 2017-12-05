
public class LiteralExpression implements Expression {
	private String name;
	private CompoundExpression parent; 
	
	public LiteralExpression (String name) {
		this.name = name;
		parent = null;
	}
	
	public LiteralExpression (String name, CompoundExpression parent) {
		this.name = name;
		this.parent = parent; 
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
		// TODO Auto-generated method stub
		String newName = new String(this.name);
		Expression copy = new LiteralExpression(newName);
		return copy;
	}

	@Override
	public void flatten() {
		// TODO Auto-generated method stub
	}

	@Override
	public String convertToString(int indentLevel) {
		// TODO Auto-generated method stub
		String result = "";
		for (int i = 0; i < indentLevel; i ++){
			result += "\t";
		}
		result += this.name;
		return result;
	}
	
	
}
