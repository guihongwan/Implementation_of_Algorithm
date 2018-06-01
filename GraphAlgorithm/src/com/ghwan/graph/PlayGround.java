package com.ghwan.graph;


import javax.swing.JFrame;

import com.ghwan.graph.datastructure.MyLinkedList;
import com.ghwan.graph.display.GraphPanel;
import com.ghwan.graph.display.GraphPrint;

public class PlayGround {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Vertex[] G = Env.createGraph();
		
		//print
		GraphPrint.printG(G);
		System.out.println();
		GraphAlgo.DFS(G);
		GraphPrint.printDFS(G);
		
		
		
		//but here G is cyclic. here only for test
		MyLinkedList<Vertex> list = GraphAlgo.TOPOLOGICAL_SORT(G);
		for(Vertex v: list) {
			System.out.print( v.getName() +",");
		}
		System.out.println();
		
		Vertex[] GT = GraphAlgo.getGT(G);
		System.out.println();
		GraphPrint.printG(GT);
		
		//Paint
	    showGraph(G,GT);

	}
    private static void showGraph(Vertex[] G, Vertex[] GT) {
    	    JFrame frame = new JFrame();
		frame.setBounds(100, 100, 750, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		GraphPanel graphPanel = new GraphPanel(G, GT);
		frame.add(graphPanel);

		frame.setVisible(true);
	
    }

}
