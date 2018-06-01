package com.ghw.algorithms;

public class Result {
    Pair pair;
    Point[] Y;//sorted points by y-coordinate
    public Result(Pair p, Point[] y) {
    	    pair = p;
    	    Y = y;
    }
    public void print() {
    	    if(pair !=null) pair.print();
//    	    System.out.println("The ordered of Y is ");
//    	    if(Y != null) {
//    	      	for(int i = 0; i < Y.length; i++) {
//    	    	        System.out.print("(" + Y[i].x + " " + Y[i].y + "), ");
//    	        }
//    	    }
    	    
    }
}
