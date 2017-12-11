import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ParentheticalExpression extends AbstractCompoundExpression{
	private Pane javaFXView;

	public ParentheticalExpression() {
		this.setName("()");
	}

	@Override
	public void flatten() {
		getChildren().get(0).flatten();
	}

	@Override
	public Node getNode() {
		if (javaFXView == null) {
			javaFXView = new HBox();
			Text lparen = new Text();
			lparen.setFont(Expression.font);
			lparen.setText("(");

			Text rparen = new Text();
			rparen.setFont(Expression.font);
			rparen.setText(")");

			javaFXView.getChildren().add(lparen);
			javaFXView.getChildren().add(getChildren().get(0).getNode());
			javaFXView.getChildren().add(rparen);
		}
		return javaFXView;
	}
}
