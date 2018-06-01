package com.ghw.convexhull;

import java.util.HashSet;

public class ConvexHull {

	//vector01 vector 02
	//vector01 is the base
	//cosa = ab/|a||b|
	public double angleVector(Point[] points) {
		Point vector01 = new Point(points[1].x-points[0].x, points[1].y-points[0].y);
		Point vector02 = new Point(points[2].x-points[0].x, points[2].y-points[0].y);
		
		double abs_vector01 = Math.sqrt( Math.pow(vector01.x,2) + Math.pow(vector01.y,2) );
		double abs_vector02 = Math.sqrt( Math.pow(vector02.x,2) + Math.pow(vector02.y,2) );
		
		double vector01vector02 = vector01.x * vector02.x + vector01.y * vector02.y;
		
		double cosa = vector01vector02/(abs_vector01*abs_vector02);
		double angle = Math.toDegrees(Math.acos(cosa));

		//crossproduct = ax * by - ay * bx
		//pay attention the x-y coordinate of java graph!!!
		double cross_vetor01vector02 = vector01.x*vector02.y - vector01.y*vector02.x;
		if(cross_vetor01vector02 > 0) {
			angle = 360 - angle;
		}
		//System.out.println("The angle is " + angle);
		return angle;
	}
	//p0 p1 p2
	//vector01 
	//vector02 is base.
	// 0 is the base point.
	public int crossproduct(Point[] points) {
		if(points.length == 3) {
			int product = (points[1].x - points[0].x)*(points[2].y - points[0].y) - 
					  (points[2].x - points[0].x) * (points[1].y - points[0].y);
			return product;
		}
		return Integer.MAX_VALUE;
		
	}
	private int find_max_y(Point[] points) {
		int ret = 0;
		for(int i = 1; i< points.length; i++) {
			if(points[ret].y < points[i].y) ret = i;
		}
		return ret;	
	}
	private int find_min_y(Point[] points) {
		int ret = 0;
		for(int i = 1; i< points.length; i++) {
			if(points[ret].y > points[i].y) ret = i;
		}
		return ret;	
	}
	public Point[] convexHull(Point[] points) {
		Point[] hull = new Point[points.length];
		int num = 0 ;
		
		int highest = find_max_y(points);//O(n)
		int lowest = find_min_y(points);//O(n)
		
		hull[num] = points[lowest];
		num++;
		int current = lowest;
		System.out.println("lowest " + lowest + " highest "+highest);
		this.printPoints(points);
		HashSet<Integer> set = new HashSet<Integer>();
		

		boolean changedirection = false;
		do {
			System.out.println("current " + current + " "+points.length);
			
			//find the smallest angle
			//vector01 vector 02
			Point p0 = points[current];
			Point p1 = null;
			
			p1 = new Point(0,p0.y);//x
			if(current == highest) {
				changedirection = true;
			}
			if(changedirection) {
			    p1 = new Point(p0.x+10,p0.y);//-x
			}

			int smallest_angle_index = Integer.MAX_VALUE;
			double smallest_angle = Integer.MAX_VALUE;
			Point[] pendingpoints = new Point[3];
			pendingpoints[0] = p0;
			pendingpoints[1] = p1;
            for(int i = 0; i < points.length; i++) {
            	    if(!set.contains(i)) {
                    pendingpoints[2] = points[i];

        				double angle = angleVector(pendingpoints);
        				if(angle < smallest_angle) {
        					smallest_angle = angle;
        					smallest_angle_index = i;
        				}	
				}
			}
			
			
			System.out.println("smallest_angle_index " +smallest_angle_index);
			hull[num] = points[smallest_angle_index];
			num++;
			set.add(smallest_angle_index);
			
			current = smallest_angle_index;
			
		}while(current != lowest && num < points.length);
			
		System.out.println("=======num======"+num);
		
		Point[] ret = new Point[num];
		for(int i = 0; i< ret.length; i++) {
			ret[i] = new Point(hull[i].x, hull[i].y);
		}
		printPoints(ret);
		return ret;
	}
	public void printPoints(Point[] points) {
		System.out.println("=======printPoints======"+points.length);
		System.out.println("");
		for(int i = 0; i< points.length; i++) {
			if(points[i] != null)
			System.out.print("("+points[i].x +","+ points[i].y+") ");
		}
		System.out.println("");
	}
}
