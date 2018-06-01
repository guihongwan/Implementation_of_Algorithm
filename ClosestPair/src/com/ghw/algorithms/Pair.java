package com.ghw.algorithms;

public class Pair {
	double distance = Integer.MAX_VALUE;
    Point[] pair = null;
    
    public Pair(double distance, Point[] pair) {
    	    this.distance = distance;
    	    if (pair != null && pair.length == 2) {	
	    	    this.pair = pair;
    	    }
    }
    
    public void print() {
    	    System.out.println("The distance is " + distance);
    	    if (pair != null && pair.length == 2) {
    	        System.out.print("(" + pair[0].x + "," + pair[0].y + ") ");
    	        System.out.println("(" + pair[1].x + "," + pair[1].y + ") ");
    	    }
    }
}
