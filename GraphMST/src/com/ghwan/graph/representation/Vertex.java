package com.ghwan.graph.representation;

import java.awt.Color;

import com.ghwan.graph.datastructure.MyLinkedList;

public class Vertex {
	public String name;
	public int id;
	private MyLinkedList<Edge> adj;
	
	public Vertex pi;
    public int distance = 0;
    
    public Color color;
    public int d;//discover timestamp
    public int f;//finish timestamp
    
    public int x;//x_cooridnate
    public int y;//y_cooridnate

	public Vertex(String _name, int _id, MyLinkedList<Edge> _adj) {
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



	public MyLinkedList<Edge> getAdj() {
		return adj;
	}



	public void setAdj(MyLinkedList<Edge> adj) {
		this.adj = adj;
	}
	
	public String toString() {
		String parent = null;
		if(pi != null) {
			parent = pi.getName();
		}
		return id + " " + name + " " + parent + " d: " +d + " f: "+ f;
	}
}
