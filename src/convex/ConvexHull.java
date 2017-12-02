package convex;
import java.awt.Color;
import java.io.*;
import java.util.*;

import princeton.lib.StdDraw;


@SuppressWarnings("unused")
public class ConvexHull {
	
	private static final Random rnd = new Random();

	private static final String in = "input400.txt";
	private static final String out = "out.txt";
	
	private static Point2D[] convexHull(Point2D[] points, Point2D minPoint){
		Point2D[] res = new Point2D[points.length];
		res[0]=minPoint;
		res[1]=points[1];
		convexHull(points, res);
		return res;
	}
	
	private static void convexHull(Point2D[] points, Point2D[] res) {
		int numOfPointsRes = 2;
		for (int i = 2; i <=points.length; i++) {
			if(i==points.length){
				i=0;
			}
			int det = Point2D.ccw(res[numOfPointsRes-1], res[numOfPointsRes-2], points[i]);
			if (det <= 0) {
				res[numOfPointsRes++]=points[i];
				//uncomment to see the algorithm's work
				
				/*StdDraw.setPenColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				res[numOfPointsRes-2].drawTo(res[numOfPointsRes-1]);/**/
				if(i==0)
					break;
			}
			if (det > 0) {
				//uncomment to see the algorithm's work
				
				/*StdDraw.setPenColor(Color.white);
				res[numOfPointsRes-2].drawTo(res[numOfPointsRes-1]);/**/
				res[--numOfPointsRes]=null;
				i--;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out;
		in = new Scanner(new BufferedReader(new FileReader(ConvexHull.in)));
		out = new PrintWriter(ConvexHull.out);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		int numOfPoints = in.nextInt();
		Point2D minPoint = null;
		Point2D[] points = new Point2D[numOfPoints];
		for(int i=0;i<numOfPoints;i++){
			points[i]=new Point2D(in.nextInt(), in.nextInt());
			points[i].draw();
			if(i==0)
				minPoint=points[i];
			else if(minPoint.compareTo(points[i])>0)
				minPoint=points[i];
		}
		Arrays.sort(points, minPoint.POLAR_ORDER);
		/*StdDraw.textLeft(points[0].x, points[0].y,"0");
		StdDraw.textRight(minPoint.x, minPoint.y,"min");/**/
		/*for(int i=0;i<numOfPoints;i++){
			StdDraw.textLeft(points[i].x, points[i].y, i+"");
		}
		for(Point2D x: points)
			points[0].drawTo(x);/**/
		Point2D[] res = convexHull(points, minPoint);
		StdDraw.setPenColor();
		for(int i=0;res[i+1]!=null;i++){
			res[i].drawTo(res[i+1]);
		}
		out.println(Arrays.toString(res));
		in.close();
		out.close();
	}
}