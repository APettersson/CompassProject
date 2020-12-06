import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Arrow {
	private double x;
	private double y;

	private ArrayList<double[]> compass;

	private Line head;
	private Line tail;

	private Circle cir1;
	private Circle cir2;


	private double rotation = 0;

	public Arrow(double x, double y) {
		this.x = x;
		this.y = y;

		this.head = new Line();
		this.head.setStartX(x);
		this.head.setStartY(y);
		this.head.setEndX(x);
		this.head.setEndY(y - 25);
		this.head.setStrokeWidth(2);

		this.tail = new Line();
		this.tail.setStartX(x);
		this.tail.setStartY(y);
		this.tail.setEndX(x);
		this.tail.setEndY(y + 25);
		this.tail.setStrokeWidth(2);
		this.tail.setStroke(Color.RED);

		this.cir1 = new Circle(25);
		this.cir1.setStrokeWidth(2);
		this.cir1.setStroke(Color.SILVER);

		this.cir1.setCenterX(x);
		this.cir1.setCenterY(y);
		this.cir1.setFill(null);

		this.cir2 = new Circle(27);
		this.cir2.setStrokeWidth(1);
		this.cir2.setStroke(Color.BLACK);

		this.cir2.setCenterX(x);
		this.cir2.setCenterY(y);
		this.cir2.setFill(null);


		//createCompass(x, y);
	}

	public Line getHead() {
		return head;
	}

	public Line getTail() {
		return tail;
	}

	private double checkHead(double x, double y) {
		double h = Math.hypot(this.x - x, this.y - y);
		if (h < 25) {
			return h + 1;
		}
		return 25;
	}

	private double checkTail(double x, double y) {
		double h = Math.hypot(this.x - x, this.y - y);
		if (h < 25) {
			this.tail.setStrokeWidth(3);
			return -25 - (30 - h);
		}
		this.tail.setStrokeWidth(2);
		return -25;
	}

	public void update(double x, double y) {
		/** Førte Løsning:
		 int shortest = 0;
		 for(int i = 0; i < compass.size(); i++){
		 if(getDistance(compass.get(i), x, y) < getDistance(compass.get(shortest), x, y)){
		 shortest = i;
		 }
		 }
		 this.head.setEndX(compass.get(shortest)[0]);
		 this.head.setEndY(compass.get(shortest)[1]);
		 */

		// Skikkelig løsning:
		double deg = Math.atan2(y - this.y, x - this.x);

		this.rotation = deg;

		double h = checkHead(x, y);
		//double t = checkTail(x, y);
		System.out.println(deg);
		this.head.setEndX(this.x + (h * Math.cos(deg)));
		this.head.setEndY(this.y + (h * Math.sin(deg)));
		this.tail.setEndX(this.x + (-25 * Math.cos(deg)));
		this.tail.setEndY(this.y + (-25 * Math.sin(deg)));

	}

	private void createCompass(double x, double y) {
		compass = new ArrayList<>();
		for (int i = 0; i < 360; i++) {
			double new_x = 25 * Math.cos(i);
			double new_y = 25 * Math.sin(i);
			compass.add(new double[]{x + new_x, y + new_y});
		}
	}

	public double getDistance(double[] compass, double x, double y) {
		double k1 = compass[0] - x;
		double k2 = compass[1] - y;
		return Math.hypot(k1, k2);
	}


	public void spin() {
		if(rotation >= Math.PI){
			this.rotation = 0 - Math.PI;
		} else {
			rotation += Math.PI / 360;
		}
		this.head.setEndX(this.x + (25 * Math.cos(rotation)));
		this.head.setEndY(this.y + (25 * Math.sin(rotation)));
		this.tail.setEndX(this.x + (-25 * Math.cos(rotation)));
		this.tail.setEndY(this.y + (-25 * Math.sin(rotation)));
	}


	public Circle getCir1() {
		return cir1;
	}

	public Circle getCir2() {
		return cir2;
	}
}