package com.ghwan.graph.representation;

public class Edge implements Comparable<Edge> {
	public Vertex u = null;
	public Vertex v;
	public int w;//weight
	public Edge(Vertex u, Vertex v, int w) {
		this.u = u;
	    this.v = v;
	    this.w = w;
    }
	
	/*
	 * 0:equal
     * -1:<
     * 1:>
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Edge o) {
		if(this.w < o.w)
		    return -1;
		else if(this.w > o.w)
			return 1;
		else
		    return 0;
	}
}
