package convex;
import java.util.Comparator;

import princeton.lib.StdDraw;

public class Point2D implements Comparable<Point2D> {

	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();

	final int x;
	final int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public static int ccw(Point2D p, Point2D q1, Point2D q2) {
		int res = (q1.x - p.x) * (q2.y - p.y) - (q1.y - p.y) * (q2.x - p.x);
		if (res > 0)
			return 1;
		if (res < 0)
			return -1;
		return 0;
	}

	public int compareTo(Point2D that) {
		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return 1;
		if (this.x < that.x)
			return 1;
		if (this.x > that.x)
			return -1;
		return 0;
	}

	private class PolarOrder implements Comparator<Point2D> {

		public int compare(Point2D p, Point2D q) {
			int py = p.y - Point2D.this.y;
			int px = p.x - Point2D.this.x;
			int qy = q.y - Point2D.this.y;
			int qx = q.x - Point2D.this.x;
			if (p.equals(Point2D.this))
				return -1;
			if (q.equals(Point2D.this))
				return 1;
			if (px == 0 && qx == 0) {
				if (py > qy)
					return 1;
				return -1;
			}
			if (py == 0 && qy == 0) {
				if (px >= 0 && qx < 0)
					return -1;
				if (qx >= 0 && px < 0)
					return 1;
				return 0;
			}
			return -ccw(Point2D.this, p, q);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() == Point2D.class)
			if (x == ((Point2D) other).x && y == ((Point2D) other).y)
				return true;
		return false;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}