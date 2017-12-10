import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ParentheticalExpression extends AbstractCompoundExpression{
	public ParentheticalExpression() {
		this.setName("()");
	}

	@Override
	public void flatten() {
		getChildren().get(0).flatten();
	}

	@Override
	public Node getNode() {
		Pane p = new HBox();
		Text lparen = new Text();
		lparen.setFont(Expression.font);
		lparen.setText("(");

		Text rparen = new Text();
		rparen.setFont(Expression.font);
		rparen.setText(")");

		p.getChildren().add(lparen);
		p.getChildren().add(getChildren().get(0).getNode());
		p.getChildren().add(rparen);
		return p;
	}
}
