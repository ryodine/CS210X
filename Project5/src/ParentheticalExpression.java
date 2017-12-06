public class ParentheticalExpression extends AbstractCompoundExpression{
	public ParentheticalExpression() {
		this.setName("()");
	}

	@Override
	public void flatten() {
		getChildren().get(0).flatten();
	}
}
