import javafx.application.Application;
import java.util.*;

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

		Point2D dragStart;
		
		private static boolean isFocused = false;
		private static boolean isDragged = false;

		MouseEventHandler (Pane pane_, CompoundExpression rootExpression_) {
			pane = pane_;
			pane.setBorder(Expression.RED_BORDER);
			
			rootExpression = rootExpression_;
			((Pane) rootExpression.getNode()).setBorder(Expression.RED_BORDER);
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
			System.out.println(isDragged);
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				
				if (isFocused == true && inNode(event, focus.getNode())) {
					dragStart = new Point2D(event.getX(), event.getY());



					//System.out.println("This is good");
					// create an underlying deep copy
					changeColor(focus.getNode(), Expression.GHOST_COLOR);
					deepCopy = focus.deepCopy();
					//((Pane)rootExpression.getNode()).getChildren().add(deepCopy.getNode());
					pane.getChildren().add(deepCopy.getNode());
					System.out.println(focus.getNode().getLayoutX());
					System.out.println(focus.getNode().getLayoutY());
					//deepCopy.getNode().setLayoutX(focus.getNode().getLayoutX());
					//deepCopy.getNode().setLayoutY(focus.getNode().getLayoutY());
					//deepCopy.getNode().setTranslateX(focus.getNode().getTranslateX());
					//deepCopy.getNode().setTranslateY(focus.getNode().getTranslateY());


					Bounds focusBounds = focus.getNode().localToScene(focus.getNode().getBoundsInLocal());

					deepCopy.getNode().setLayoutX(focusBounds.getMinX());
					deepCopy.getNode().setLayoutY(focusBounds.getMinY());
					deepCopy.getNode().set

					//MARK: ryan comment
					//deepCopy.getNode().setLayoutX(focus.getNode().getLayoutX() + rootExpression.getNode().getLayoutX());
					//deepCopy.getNode().setLayoutY(focus.getNode().getLayoutY() + rootExpression.getNode().getLayoutY());
					
					System.out.println(deepCopy.getNode().getLayoutX());
					System.out.println(deepCopy.getNode().getLayoutY());
					
					//System.out.println(deepCopy);
					
				}
				
				
			} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				if (focus != null && deepCopy != null && isFocused && inNode(event, deepCopy.getNode())) {
					deepCopy.getNode().setTranslateX(deepCopy.getNode().getTranslateX() + (sceneX - _lastX));
					deepCopy.getNode().setTranslateY(deepCopy.getNode().getTranslateY() + (sceneY - _lastY));
					System.out.println("Is dragged");
					System.out.println(deepCopy.getNode().getTranslateX());
					System.out.println(deepCopy.getNode().getTranslateY());
					isDragged = true;
				}
				/*
				if (focus != null && inNode(event, focus.getNode())) {
					isDragged = true;
					Node current = focus.getNode();
					current.setTranslateX(current.getTranslateX() + (sceneX - _lastX));
					current.setTranslateY(current.getTranslateY() + (sceneY - _lastY));
				}
				*/
			} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				if (focus != null && deepCopy != null) {
					pane.getChildren().remove(deepCopy.getNode());
					deepCopy = null;
					changeColor(focus.getNode(), Color.BLACK);
				}
				if (isDragged) {
					
					System.out.println("This is executed");
					/*
					Node current = deepCopy.getNode();
					current.setLayoutX(current.getLayoutX() + current.getTranslateX());
					current.setLayoutY(current.getLayoutY() + current.getTranslateY());
					current.setTranslateX(0);
					current.setTranslateY(0);
					*/
					isDragged = false;
				}
				else {
					
					helper(event); 
					//if (focus != null) {
						//System.out.println(focus.convertToString(0));
						//System.out.println(focus.getNode());
						//System.out.println(focus.getNode());
						//System.out.println(isFocused);
						//System.out.println("--------------");
					//}
					//System.out.println(isFocused);
					
				}
			}
			_lastX = sceneX;
			_lastY = sceneY;
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
		//System.out.println("x: " + e.getSceneX() + ", y: " + e.getSceneY() + ".   Bounds: " + boundsInScene);

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

		// Add the textbox and Parser button
		final Pane queryPane = new HBox();
		final TextField textField = new TextField(EXAMPLE_EXPRESSION);
		final Button button = new Button("Parse");
		queryPane.getChildren().add(textField);

		final Pane expressionPane = new Pane();

		// Add the callback to handle when the Parse button is pressed	
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				// Try to parse the expression
				try {
					// Success! Add the expression's Node to the expressionPane
					final Expression expression = expressionParser.parse(textField.getText(), true);
					System.out.println(expression.convertToString(0));
					expressionPane.getChildren().clear();
					expressionPane.getChildren().add(expression.getNode());
					expression.getNode().setLayoutX(WINDOW_WIDTH/4);
					expression.getNode().setLayoutY(WINDOW_HEIGHT/2);

					// If the parsed expression is a CompoundExpression, then register some callbacks
					if (expression instanceof CompoundExpression) {
						((Pane) expression.getNode()).setBorder(Expression.NO_BORDER);
						final MouseEventHandler eventHandler = new MouseEventHandler(expressionPane, (CompoundExpression) expression);
						expressionPane.setOnMousePressed(eventHandler);
						expressionPane.setOnMouseDragged(eventHandler);
						expressionPane.setOnMouseReleased(eventHandler);
					}
				} catch (ExpressionParseException epe) {
					// If we can't parse the expression, then mark it in red
					textField.setStyle("-fx-text-fill: red");
				}
			}
		});
		queryPane.getChildren().add(button);

		// Reset the color to black whenever the user presses a key
		textField.setOnKeyPressed(e -> textField.setStyle("-fx-text-fill: black"));
		
		final BorderPane root = new BorderPane();
		root.setTop(queryPane);
		root.setCenter(expressionPane);

		primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		primaryStage.show();
	}
}
