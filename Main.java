import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class Main extends Application {
	private ArrayList<Arrow> arrows = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 900, 900);

		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(1),
				e -> arrows.forEach(arrow -> arrow.spin())
		));

		scene.setOnMouseClicked(event -> {
				Arrow arrow = new Arrow(event.getX(), event.getY());
				root.getChildren().addAll(arrow.getHead(), arrow.getTail(), arrow.getCir1(), arrow.getCir2());
				arrows.add(arrow);
		});


		scene.setOnMouseMoved(event -> {
			System.out.println(arrows.size());
			for(Arrow arrow : arrows){
				arrow.update(event.getX(), event.getY());
			}

		});

		scene.setOnMouseDragged(event -> {
			Arrow arrow = new Arrow(event.getX(), event.getY());
			root.getChildren().addAll(arrow.getHead(), arrow.getTail(), arrow.getCir1(), arrow.getCir2());
			arrows.add(arrow);
		});
		scene.setOnMouseExited(event -> t.play());
		scene.setOnMouseEntered(event -> t.stop());

		scene.setOnKeyPressed(event -> {
				if (toString().valueOf(event.getCode()).equals("DIGIT1")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					for (int i = 0; i <= scene.getWidth(); i += 56) {
						for (int k = 0; k <= scene.getHeight(); k += 56) {
							Arrow arrow = new Arrow(i, k);
							arrows.add(arrow);
							root.getChildren().addAll(arrow.getHead(), arrow.getTail(), arrow.getCir1(), arrow.getCir2());
						}
					}
				} else if (toString().valueOf(event.getCode()).equals("DIGIT2")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					for (int i = 0; i <= scene.getWidth(); i += 25) {
						for (int k = 0; k <= scene.getHeight(); k += 25) {
							Arrow arrow = new Arrow(i, k);
							arrows.add(arrow);
							root.getChildren().addAll(arrow.getHead(), arrow.getTail());
						}
					}
				} else if (toString().valueOf(event.getCode()).equals("DIGIT3")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					for (int i = 0; i <= scene.getWidth(); i += 15) {
						for (int k = 0; k <= scene.getHeight(); k += 15) {
							Arrow arrow = new Arrow(i, k);
							arrows.add(arrow);
							root.getChildren().addAll(arrow.getHead(), arrow.getTail());
						}
					}
				} else if (toString().valueOf(event.getCode()).equals("DIGIT4")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					for (int i = 0; i <= scene.getWidth(); i += 5) {
						for (int k = 0; k <= scene.getHeight(); k += 50) {
							Arrow arrow = new Arrow(i, k);
							arrows.add(arrow);
							root.getChildren().addAll(arrow.getHead(), arrow.getTail());
						}
					}

				} else if (toString().valueOf(event.getCode()).equals("DIGIT5")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					Boolean odd = false;
					for (int i = 0; i <= scene.getWidth(); i += 47) {
						int p = 0;
						if(odd){
							p = 25;
						}
						odd = !odd;
						for (int k = p; k <= scene.getHeight(); k += 57) {
							Arrow arrow = new Arrow(k, i);
							arrows.add(arrow);
							root.getChildren().addAll(arrow.getHead(), arrow.getTail());
						}
					}


				} else if (toString().valueOf(event.getCode()).equals("DIGIT6")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
					for (int i = 0; i <= scene.getWidth(); i += 100) {
						for (int k = 0; k <= scene.getHeight(); k += 100) {
							Arrow a1 = new Arrow(i, k-25);
							arrows.add(a1);
							root.getChildren().addAll(a1.getHead(), a1.getTail());

							Arrow a2 = new Arrow(i+25, k);
							arrows.add(a2);
							root.getChildren().addAll(a2.getHead(), a2.getTail());

							Arrow a3 = new Arrow(i, k+25);
							arrows.add(a3);
							root.getChildren().addAll(a3.getHead(), a3.getTail());

							Arrow a4 = new Arrow(i-25, k);
							arrows.add(a4);
							root.getChildren().addAll(a4.getHead(), a4.getTail());
						}
					}

				} else if (toString().valueOf(event.getCode()).equals("DIGIT7")) {
					root.getChildren().clear();
					arrows = new ArrayList<>();
				}
			});

			primaryStage.setTitle("ARROWS");
			primaryStage.setScene(scene);
			primaryStage.show();
		}

	}