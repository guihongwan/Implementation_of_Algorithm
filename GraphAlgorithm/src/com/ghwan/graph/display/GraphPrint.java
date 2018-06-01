package com.ghwan.graph.display;

import com.ghwan.graph.Vertex;
import com.ghwan.graph.datastructure.MyLinkedList;

public class GraphPrint {
	public static void printDFS(Vertex[] G) {
		for(Vertex u: G) {
			System.out.println(u);
		}
	}
	public static void printG(Vertex[] G) {
		for(Vertex u: G) {
			System.out.print(u.getName() + " " + u.id + ": ");
			MyLinkedList<Vertex> adj = u.getAdj();
			for(Vertex v: adj) {
				System.out.print(v.getName());
			}
			System.out.println();
		}
	}
}
