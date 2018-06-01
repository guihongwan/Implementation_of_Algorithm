package com.ghw.convexhull;

import java.util.Random;

public class Dataset {
	
	protected static int  length = 3;
	protected static Point[] points = new Point[length];
	static {
	    points[0] = new Point(10,10);
	    //points[1] = new Point(10,20);
	    points[1] = new Point(10,0);
	    points[2] = new Point(20,20);
	}
	
	protected static void initData() {
		length = 250;
		points = new Point[length];
		///*
	    Random rm = new Random();
		for(int i = 0; i < points.length; i++) {
			int x = 50+rm.nextInt(400);
			int y = 50+rm.nextInt(400);
			points[i] = new Point(x,y);
		}
		//*/
		
		
		/*
		//(45,141) (204,65) (181,390) (321,242) (138,44)
		//(343,116) (173,147) (79,77) (53,128) (131,398)
		points[0] = new Point(45,141);
	    points[1] = new Point(204,65);
	    points[2] = new Point(181,390);
	    points[3] = new Point(321,242);
	    points[4] = new Point(138,44);
	    points[5] = new Point(343,116);
	    points[6] = new Point(173,147);
	    points[7] = new Point(79,77);
	    points[8] = new Point(53,128);
	    points[9] = new Point(131,398);
	    */
	    
	}
	protected static Point[] convexhull;
}
