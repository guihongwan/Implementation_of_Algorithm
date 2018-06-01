package com.ghwan.graph;

import com.ghwan.graph.datastructure.MyLinkedList;

public class Env {
    
    static Vertex[] createGraph() {
		int total = 6;
	    Vertex[] G = new Vertex[total];

	    //Vertices
	    G[0] = new Vertex("u", 0, new MyLinkedList<Vertex>());
	    G[1] = new Vertex("v", 1, new MyLinkedList<Vertex>());
	    G[2] = new Vertex("w", 2, new MyLinkedList<Vertex>());
	    G[3] = new Vertex("x", 3, new MyLinkedList<Vertex>());
	    G[4] = new Vertex("y", 4, new MyLinkedList<Vertex>());
	    G[5] = new Vertex("z", 5, new MyLinkedList<Vertex>());
	    
	    //edges
	    MyLinkedList<Vertex> list;
	    
	    list= G[0].getAdj();//u:v,x
	    list.add(G[1]);
	    list.add(G[3]);
	    
	    list = G[1].getAdj();//v:y
	    list.add(G[4]);
	    
	    list = G[2].getAdj();//w:y,z
	    list.add(G[4]);
	    list.add(G[5]);
	    
	    list = G[3].getAdj();//x:v
	    list.add(G[1]);
	    
	    list = G[4].getAdj();//y:x
	    list.add(G[3]);
	    
	    list = G[5].getAdj();//z:z
	    list.add(G[5]);
	    
		return G;
	}
}
