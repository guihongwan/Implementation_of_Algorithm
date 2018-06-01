package com.ghwan.graph;

import java.awt.Color;

import com.ghwan.graph.datastructure.MyLinkedList;

public class GraphAlgo {
	
	private static int time = 0;
	
    /*
     * DFS
     */
	public static void DFS(Vertex[] G) {
		//init
		for(Vertex u: G) {
			u.color = Color.WHITE;
			u.pi = null;	
		}
		time = 0;
		//revoke
		for(Vertex u: G) {
			if(u.color == Color.WHITE) {
			    DFS_VISIT(G, u);
			}
		}
	}
	
	private static void DFS_VISIT(Vertex[] G, Vertex u) {
		u.color = Color.GRAY;
		time++;
		u.d = time;
		
		MyLinkedList<Vertex> Adj = u.getAdj();
		for(Vertex v: Adj) {
			if(v.color == Color.WHITE) {
			    v.pi = u;
			    DFS_VISIT(G, v);
			}
		}
		u.color = Color.BLACK;
		time++;
		u.f = time;
	}
	
	/*
     * topological sort
     */
	public static MyLinkedList<Vertex> TOPOLOGICAL_SORT(Vertex[] G) {
		//init
		for(Vertex u: G) {
			u.color = Color.WHITE;
			u.pi = null;	
		}
		time = 0;
		MyLinkedList<Vertex> list = new MyLinkedList<Vertex>();
		
		//revoke
		for(Vertex u: G) {
			if(u.color == Color.WHITE) {
			    DFS_VISIT(G, u, list);
			}
		}
		
		return list;
	}
	
	private static void DFS_VISIT(Vertex[] G, Vertex u, MyLinkedList<Vertex> list) {
		u.color = Color.GRAY;
		time++;
		u.d = time;
		
		MyLinkedList<Vertex> Adj = u.getAdj();
		for(Vertex v: Adj) {
			if(v.color == Color.WHITE) {
			    v.pi = u;
			    DFS_VISIT(G, v, list);
			}
		}
		u.color = Color.BLACK;
		time++;
		u.f = time;
		
		list.add(0, u);
	}
	
	public static Vertex[] getGT(Vertex[] G) {
		//init GT
		Vertex[] GT = new Vertex[G.length];
		int i = 0;
		for(Vertex u: G) {
			GT[i] = new Vertex(u.getName(), i, new MyLinkedList<Vertex>());
			i++;
		}
		
		System.out.println();
		for(Vertex u: G) {
			System.out.print(u.name+ " " + u.id +": ");
		    for(Vertex v: u.getAdj()) {
		      	System.out.print(v.name+ " " + v.id +",");
			    GT[v.id].getAdj().add(GT[u.id]);
		    }
		    System.out.println();
		}
		return GT;
	}
	
}
