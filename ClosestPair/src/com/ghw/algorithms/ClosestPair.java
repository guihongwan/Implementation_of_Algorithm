package com.ghw.algorithms;

import java.util.Random;

public class ClosestPair {
	public Result closetPari2D(Point[] X, int p, int r) {//points sorted by x-coordinate
		Result ret = null;
		Pair retPair = new Pair(Integer.MAX_VALUE, null);
		Point[] Y = null;
		
		//step0: base case
		if( p == r ) {//only one element
			Y = new Point[1];
			Y[0] = X[p];
			return new Result(retPair, Y);
		}
		if( (r-p) <= 2 ) {// 2~3 elements
			retPair = naiveCloestPair2D(X, p, r);
			Y = insertSortY(X, p, r);
			return new Result(retPair, Y);
		}
		
		//step1: divide
		//idx and median
		int idx = p + (r-p)/2;
		double median = X[idx].x;
		
		if(X.length%2 == 0) {//even	
			median = (X[idx].x + X[idx+1].x)/2.0;
		}	
		System.out.println(p + " " + idx + " " + r +" median: " + median);
		//recursively
		Result r_left = closetPari2D(X, p, idx);
		Result r_right = closetPari2D(X, idx+1, r);
		
		
		//step2: get ordered Y
		Y = merge(r_left.Y, r_right.Y);
		
		//step3: conquer
		// get YY with elements in R
		 double delta = Math.min(r_left.pair.distance, r_right.pair.distance);
		 System.out.println("delta: "+ delta);
			
		 Point[] YY = new Point[Y.length];
		 int k = 0;
		 for(int i=0; i < Y.length; i++) {
			 if(Y[i].x >= (median-delta) && Y[i].x <= (median+delta)) {
				 YY[k] = Y[i];
				 k++;
			 }
		 }
		 System.out.println("The length of YY is " + k);
		 
//		 if(true) {
//			 for(int i = 0; i < k; i++) {
//				 System.out.print(" ("+ YY[i].x + ", "+YY[i].y + ") ");
//			 }
//			 System.out.println("");
//		 }
		 
		 
		//get r_middle
		double d_middle = Integer.MAX_VALUE;
		Point[] min_pair = null;
		//iterate the points in the regain near the line, stored in YY.
		//from bottom to up
		for(int i = 0; i < k; i++) {
			Point qq = YY[i];
			
			//Calculate shortest distance to qq in R
			int j = i+1;
			int num = 1;//for check, num should be less than 8
			double min_distance_from_qq = Integer.MAX_VALUE;
			
			Point[] min_pair_qq = new Point[2];
			min_pair_qq[0] = qq;//the minimum y coordinate
			
			//iterate the points in the rectangle
			//j < k make sure j is less than YY.length
			while(j < k && (YY[j].y - qq.y) <= delta) {//num will not be greater than 7.
				Point pp = YY[j];
				double f = Math.pow((qq.x - pp.x), 2) + Math.pow((qq.y - pp.y), 2);
				double d = Math.sqrt(f) ;
				if(d < min_distance_from_qq) {
					min_distance_from_qq = d;
					min_pair_qq[1] = pp;
				}
				num++;
				j++;
			}
			//SHOULD BE 8.
			if(num >= 5) 
			    System.err.println("num == "+ num +" should be less than 8");
			
			if(d_middle > min_distance_from_qq) {
				d_middle = min_distance_from_qq;
				min_pair = min_pair_qq;
			}
		}
		Pair p_middle = new Pair(d_middle, min_pair);
		
		//r_left, p_middle, r_right
		if ( r_left.pair.distance < r_right.pair.distance) {
			retPair = r_left.pair;
		} else {
			retPair = r_right.pair;
		}
		if(retPair.distance > p_middle.distance) {
			retPair = p_middle;
		}		
		System.out.println(" "+retPair.distance);
		
		System.out.println("");
		ret = new Result(retPair, Y);
		return ret;
	}
	
	//==================================================
	
	private Point[] merge(Point[] y1, Point[] y2) {
		Point[] ret = new Point[y1.length+y2.length];
		
		int i = 0; 
		int j = 0;
		int k = 0;
		//System.out.println("length "+y1.length +" "+y2.length);
		while( i < y1.length && j < y2.length) {
			//System.out.println(""+i +" "+j);
			
			if(y1[i].y < y2[j].y) {
				ret[k] = y1[i];
				i++;
			} else {
				ret[k] = y2[j];
				j++;
			}
			k++;
		}
		
		for(int ii = i; ii < y1.length; ii++) {
			ret[k] = y1[ii];
			k++;
		}
		for(int jj = j; jj < y2.length; jj++) {
			ret[k] = y2[jj];
			k++;
		}
		
		
		return ret;
	}
	
	//4,8,2
	private Point[] insertSortY(Point[] X, int p, int r) {//O(1)
		//TODO CHECK arguments

		Point[] y = new Point[(r-p)+1];
		
		int k = 0;
		y[k] = X[p];
		
		for(int i = p+1; i <= r; i++) {
			Point tmp = X[i];
			
			int j = k;
			for(;j >= 0; j--) {
			    if(tmp.y < y[j].y) {
				    y[j+1] = y[j];
			    }else {
			        break;
			    }  
			}
			y[j+1] = tmp;
	    	    k++;
			
		}
		
		return y;
	}
	private Pair naiveCloestPair2D(Point[] X, int p, int r) {//O(1)
		Pair ret = null;
		// TODO check arguments
		
			double min_d = Integer.MAX_VALUE;
			Point point1 = null;
			Point point2 = null;
			
			//p1,p2,p3
			for(int i = p; i <= r-1; i++) {
				for(int j = i+1; j <= r; j++) {
					double f = Math.pow((X[i].x - X[j].x), 2) + Math.pow((X[i].y - X[j].y), 2);
					double d = Math.sqrt(f) ;
					
					if(min_d > d) {
						min_d = d;
						point1 = X[i];
						point2 = X[j];
					}
				}
			}
			Point[] points = new Point[2];
			points[0] = point1;
			points[1] = point2;
			ret = new Pair(min_d, points);
	
		return ret;
	}

}
