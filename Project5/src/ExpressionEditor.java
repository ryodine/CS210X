import javafx.application.Application;
import java.util.*;
import java.util.stream.Collectors;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExpressionEditor extends Application {
	public static void main (String[] args) {
		launch(args);
	}

	private static Expression focus;
	private static Expression deepCopy;

	/**
	 * Mouse event handler for the entire pane that constitutes the ExpressionEditor
	 */
	private static class MouseEventHandler implements EventHandler<MouseEvent> {
		private static Pane pane;
		private static CompoundExpression rootExpression;
		double _lastX, _lastY;

		Expression _leftDragSibling, _rightDragSibling;
		
		private static boolean isFocused = false;
		private static boolean isDragged = false;

		MouseEventHandler (Pane pane_, CompoundExpression rootExpression_) {
			pane = pane_;
			
			rootExpression = rootExpression_;
			focus = null;
			isFocused = false;
			isDragged = false;
		}
		
		/**
		 * Helper method for changing color
		 * @param n
		 * @param color
		 */
		public void changeColor(Node n, Color color) {
			if (n instanceof Text) {
				Text text = (Text) n;
				text.setFill(color);
			}
			else {
				HBox hbox = (HBox) n;
				for (Node child : hbox.getChildren()) {
					changeColor(child, color);
				}
			}
		}

		public void handle (MouseEvent event) {
			final double sceneX = event.getSceneX();
			final double sceneY = event.getSceneY();
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				
				if (isFocused == true && inNode(event, focus.getNode())) {

					changeColor(focus.getNode(), Expression.GHOST_COLOR);
					deepCopy = focus.deepCopy();
					pane.getChildren().add(deepCopy.getNode());
					Bounds focusBounds = focus.getNode().localToScene(focus.getNode().getBoundsInLocal());
					_leftDragSibling = getLeftSibling(focus);
					_rightDragSibling = getRightSibling(focus);
					deepCopy.getNode().relocate(focusBounds.getMinX() - (pane.getScene().getWidth()- pane.getWidth()),
							focusBounds.getMinY()- (pane.getScene().getHeight()- pane.getHeight()));

				}
				
				
			} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				if (focus != null && deepCopy != null && isFocused) {
					deepCopy.getNode().setTranslateX(deepCopy.getNode().getTranslateX() + (sceneX - _lastX));
					deepCopy.getNode().setTranslateY(deepCopy.getNode().getTranslateY() + (sceneY - _lastY));
					isDragged = true;
					double deepcopyx = deepCopy.getNode().getLayoutX() + deepCopy.getNode().getTranslateX() + deepCopy.getNode().getBoundsInLocal().getWidth()/2;

					if (_leftDragSibling != null) {
						double leftcenterx = _leftDragSibling.getNode().localToScene(_leftDragSibling.getNode().getBoundsInLocal()).getMinX() + _leftDragSibling.getNode().getBoundsInLocal().getWidth()/2;
						if (deepcopyx < leftcenterx) {
							swapSiblings(_leftDragSibling, focus);
							_leftDragSibling = getLeftSibling(focus);
							_rightDragSibling = getRightSibling(focus);
						}
					}

					if (_rightDragSibling != null) {
						double rightcenterx = _rightDragSibling.getNode().localToScene(_rightDragSibling.getNode().getBoundsInLocal()).getMinX() + _rightDragSibling.getNode().getBoundsInLocal().getWidth() / 2;
						if (rightcenterx < deepcopyx) {
							swapSiblings(_rightDragSibling, focus);
							_leftDragSibling = getLeftSibling(focus);
							_rightDragSibling = getRightSibling(focus);
						}
					}

				}

			} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				if (focus != null && deepCopy != null) {
					pane.getChildren().remove(deepCopy.getNode());
					deepCopy = null;
					changeColor(focus.getNode(), Color.BLACK);
				}
				if (isDragged) {
					System.out.println("New tree structure:");
					System.out.println(rootExpression.convertToString(0));
					isDragged = false;
				}
				else {
					helper(event);
				}
			}
			_lastX = sceneX;
			_lastY = sceneY;
		}

		private List<Expression> getAllSiblingsAndExpr(Expression e) {
			List<Expression> all = ((AbstractCompoundExpression)e.getParent()).getChildren();
			return all;
		}

		private Expression getLeftSibling(Expression e) {
			List<Expression> all = getAllSiblingsAndExpr(e);

			Expression last = null;
			for (Expression expr : all) {
				if (expr == e) {
					return last;
				} else {
					last = expr;
				}
			}
			return null;
		}

		private Expression getRightSibling(Expression e) {
			List<Expression> all = getAllSiblingsAndExpr(e);

			boolean returnNext = false;
			for (Expression expr : all) {
				if (returnNext) return expr;
				returnNext = (expr == e);
			}
			return null;
		}

		private void swapSiblings(Expression a, Expression b) {
			List<Expression> all = getAllSiblingsAndExpr(a);
			Collections.swap(all, all.indexOf(a), all.indexOf(b) );
			a.getParent().recalculateNode();
		}
		
		public void helper(MouseEvent event) {
			if (focus == null) {
				focus = rootExpression;
				isFocused = false;
			}
			if (inNode(event, focus.getNode())) {
				if (focus instanceof AbstractCompoundExpression) {
					AbstractCompoundExpression expr = (AbstractCompoundExpression) focus;
					boolean found = false;
					for (Expression child : expr.getChildren()) {
						if (inNode(event, child.getNode())) {
							focus = child;
							found = true;
							((Pane)focus.getNode().getParent()).setBorder(Expression.NO_BORDER);
						} else {
							((Pane)focus.getNode()).setBorder(Expression.NO_BORDER);
						}
					}
					if (!found) {
						((Pane)focus.getNode()).setBorder(Expression.NO_BORDER);
						focus = null;
					}

				} else {
					((Pane)focus.getNode()).setBorder(Expression.NO_BORDER);
					focus = null;
				}
			} else {
				((Pane)focus.getNode()).setBorder(Expression.NO_BORDER);
				focus = null;
				isFocused = false;
			}

			if (focus != null) {
				((Pane) focus.getNode()).setBorder(Expression.RED_BORDER);
				isFocused = true;
			}
			else {
				isFocused = false;
			}
		}
	}

	private static boolean inNode(MouseEvent e, Node n) {

		Bounds boundsInScene = n.localToScene(n.getBoundsInLocal());
		return boundsInScene.contains(new Point2D(e.getSceneX(),e.getSceneY()));

	}

	/**
	 * Size of the GUI
	 */
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 250;

	/**
	 * Initial expression shown in the textbox
	 */
	private static final String EXAMPLE_EXPRESSION = "2*x+3*y+4*z+(7+6*z)";

	/**
	 * Parser used for parsing expressions.
	 */
	private final ExpressionParser expressionParser = new SimpleExpressionParser();

	@Override
	public void start (Stage primaryStage) {
		primaryStage.setTitle("Expression Editor");

		final Pane queryPane = new HBox();
		final TextField textField = new TextField(EXAMPLE_EXPRESSION);
		final Button button = new Button("Parse");
		queryPane.getChildren().add(textField);

		final Pane expressionPane = new Pane();

		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				try {
					final Expression expression = expressionParser.parse(textField.getText(), true);
					System.out.println(expression.convertToString(0));
					expressionPane.getChildren().clear();
					expressionPane.getChildren().add(expression.getNode());
					expression.getNode().setLayoutX(WINDOW_WIDTH/4);
					expression.getNode().setLayoutY(WINDOW_HEIGHT/2);

					if (expression instanceof CompoundExpression) {
						((Pane) expression.getNode()).setBorder(Expression.NO_BORDER);
						final MouseEventHandler eventHandler = new MouseEventHandler(expressionPane, (CompoundExpression) expression);
						expressionPane.setOnMousePressed(eventHandler);
						expressionPane.setOnMouseDragged(eventHandler);
						expressionPane.setOnMouseReleased(eventHandler);
					}
				} catch (ExpressionParseException epe) {
					textField.setStyle("-fx-text-fill: red");
				}
			}
		});
		queryPane.getChildren().add(button);

		textField.setOnKeyPressed(e -> textField.setStyle("-fx-text-fill: black"));
		
		final BorderPane root = new BorderPane();
		root.setTop(queryPane);
		root.setCenter(expressionPane);

		primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		primaryStage.show();
	}
}
