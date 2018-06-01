package com.ghwan.graph;

import java.awt.Color;

import com.ghwan.graph.datastructure.MyLinkedList;

public class Vertex {
	public String name;
	public int id;
	private MyLinkedList<Vertex> adj;
	
	public Vertex pi;
    public int distance = 0;
    
    public Color color;
    public int d;//discover timestamp
    public int f;//finish timestamp
    
    public int x;//x_cooridnate
    public int y;//y_cooridnate
	
	public Vertex(String _name, MyLinkedList<Vertex> _adj) {
		this.setName(_name);
		setAdj(_adj);
	}

	public Vertex(String _name, int _id, MyLinkedList<Vertex> _adj) {
		this.setName(_name);
		id = _id;
		setAdj(_adj);
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public MyLinkedList<Vertex> getAdj() {
		return adj;
	}



	public void setAdj(MyLinkedList<Vertex> adj) {
		this.adj = adj;
	}
	
	public String toString() {
		String parent = null;
		if(pi != null) {
			parent = pi.getName();
		}
		return name + " " + parent + " d: " +d + " f: "+ f;
	}
}
