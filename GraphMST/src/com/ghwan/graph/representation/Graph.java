package com.ghwan.graph.representation;


import com.ghwan.graph.datastructure.MyLinkedList;
import com.ghwan.graph.representation.Edge;
import com.ghwan.graph.representation.Vertex;

public class Graph {
	private static int total = 9;
	private static int a = 3;
	private static int b = 0;
	private static int c = 1;
	private static int d = 2;
	private static int e = 5;
	private static int f = 8;
	private static int g = 7;
	private static int h = 6;
	private static int i = 4;
	
	public static Vertex[] createUGraphWithoutEdge() {
	    Vertex[] G = new Vertex[total];

	    //Vertices
	    G[a] = new Vertex("a", a, new MyLinkedList<Edge>());
	    G[b] = new Vertex("b", b, new MyLinkedList<Edge>());
	    G[c] = new Vertex("c", c, new MyLinkedList<Edge>());
	    G[d] = new Vertex("d", d, new MyLinkedList<Edge>());
	    G[e] = new Vertex("e", e, new MyLinkedList<Edge>());
	    G[f] = new Vertex("f", f, new MyLinkedList<Edge>());
	    G[g] = new Vertex("g", g, new MyLinkedList<Edge>());
	    G[h] = new Vertex("h", h, new MyLinkedList<Edge>());
	    G[i] = new Vertex("i", i, new MyLinkedList<Edge>());
	    
	    return G;
	}
    public static Vertex[] createUGraph() {
		
	    Vertex[] G = createUGraphWithoutEdge();
	    
	    //edges
	    MyLinkedList<Edge> list;
	    
	    list= G[a].getAdj();//a:b 4,h 8
	    list.add(new Edge(G[a],G[b],4));
	    list.add(new Edge(G[a],G[h],8));
	    
	    list = G[b].getAdj();//b:a 4, c 8,h 11
	    list.add(new Edge(G[b],G[a],4));
	    list.add(new Edge(G[b],G[c],8));
	    list.add(new Edge(G[b],G[h],11));

	    
	    list = G[c].getAdj();//c: b 8, i 2, f 4, d 7
	    list.add(new Edge(G[c],G[b],8));
	    list.add(new Edge(G[c],G[i],2));
	    list.add(new Edge(G[c],G[f],4));
	    list.add(new Edge(G[c],G[d],7));
	    
	    list = G[d].getAdj();//d: c 7, f 14, e 9
	    list.add(new Edge(G[d],G[c],7));
	    list.add(new Edge(G[d],G[f],14));
	    list.add(new Edge(G[d],G[e],9));
	    
	    list = G[e].getAdj();//e: d 9, f 10
	    list.add(new Edge(G[e],G[d],9));
	    list.add(new Edge(G[e],G[f],10));
	    
	    list = G[f].getAdj();//f: e 10, d 14, c 4, g 2
	    list.add(new Edge(G[f],G[e],10));
	    list.add(new Edge(G[f],G[d],14));
	    list.add(new Edge(G[f],G[c],4));
	    list.add(new Edge(G[f],G[g],2));
	    
	    list = G[g].getAdj();//g: f 2, i 6, h 1
	    list.add(new Edge(G[g],G[f],2));
	    list.add(new Edge(G[g],G[i],6));
	    list.add(new Edge(G[g],G[h],1));
	    
	    list = G[h].getAdj();//h:g 1, i 7, b 11, a 8
	    list.add(new Edge(G[h],G[g],1));
	    list.add(new Edge(G[h],G[i],7));
	    list.add(new Edge(G[h],G[b],11));
	    list.add(new Edge(G[h],G[a],8));

	    
	    list = G[i].getAdj();//i: h 7, g 6, c 2
	    list.add(new Edge(G[i],G[h],7));
	    list.add(new Edge(G[i],G[g],6));
	    list.add(new Edge(G[i],G[c],2));
	    
		return G;
	}
}
