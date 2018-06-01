package com.ghw.algorithms;

import java.util.Random;

public class Main {
	//test
	public static void main(String[] args) {

		
		//for debug
//		int length = 3;
//		int length = 8;
//		Point[] X = new Point[length];
//		X[0] = new Point(-2,0);
//		X[1] = new Point(3,8);
//		X[2] = new Point(4,2);
//		X[3] = new Point(5,7);
//		X[4] = new Point(6,4);
//		X[5] = new Point(7,1);
//		X[6] = new Point(8,5);
//		X[7] = new Point(15,9);
		
		//for test
		//init randomly
		int length = 1000;
		Point[] X = new Point[length];
		double rangeMin = -1000.0;
		double rangeMax = 1000.0;
		
		Random r = new Random();

		for(int i = 0; i < X.length; i++) {
			double randomValue1 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			double randomValue2 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			X[i] = new Point(randomValue1, randomValue2);
		}
		double time0 = System.currentTimeMillis();
		ClosestPair cp = new ClosestPair();
		Result ret = cp.closetPari2D(X, 0, X.length-1);
		double time1 = System.currentTimeMillis();
		
		System.out.println("=== Test Result ====");
		if(ret != null) ret.print();
		System.out.print("Running Time is "+(time1-time0)/1000.0 +" s.");
	}
}
