import java.util.stream.Collectors;

public class SimpleCompoundExpression extends AbstractCompoundExpression{
	public void flatten(){
		boolean reflatten = false;

		for (int i = getChildren().size()-1; i >= 0; i--) {
			Expression child = getChildren().get(i);
			if (child instanceof AbstractCompoundExpression) {
				AbstractCompoundExpression child_operator = (AbstractCompoundExpression) child;
				if (child_operator.name.equals(this.name)) {
					reflatten = true;

					this.getChildren().addAll(i, child_operator.getChildren().stream().map(c -> {
						c.flatten();
						c.setParent(this);
						return c;
					}).collect(Collectors.toList()));

					this.getChildren().remove(child_operator);
				} else {
					child.flatten();
				}
			} else {
				child.flatten();
			}
		}
		if (reflatten) flatten();
	}
}
