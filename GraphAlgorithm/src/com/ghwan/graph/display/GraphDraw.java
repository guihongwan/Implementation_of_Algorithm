package com.ghwan.graph.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
import java.util.Queue;

import com.ghwan.graph.GraphAlgo;
import com.ghwan.graph.Vertex;
import com.ghwan.graph.datastructure.MyLinkedList;

public class GraphDraw {
    private int interval = 100;
	private int number_row = 3; //how many vertices each row
	private int vertex_size = 20;
	
	private Graphics2D g2d;
	
	public GraphDraw(Graphics g) {
		g2d = (Graphics2D) g.create();
	}
    
	public void drawTitle(String title, int x_start, int y_start) {
		g2d.setColor(Color.BLUE);
		g2d.drawString(title, x_start, y_start);
	}
	
	public void drawGraph(Vertex[] _G, int x_start, int y_start) {
		Vertex[] G = new Vertex[_G.length];
		System.arraycopy(_G, 0, G, 0, _G.length);
		
		drawVertices(G,x_start, y_start);
		drawEdges(G, Color.BLACK);
	}
	public void drawDFS(Vertex[] _G, int x_start, int y_start) {
		Vertex[] G = new Vertex[_G.length];
		System.arraycopy(_G, 0, G, 0, _G.length);
		
		drawVertices(G,x_start, y_start);
		drawEdgesDFS(G);
	}
	public void drawSCC(Vertex[] _G, int x_start, int y_start) {
		Vertex[] G = new Vertex[_G.length];
		System.arraycopy(_G, 0, G, 0, _G.length);
		
		MyLinkedList<Vertex> list = GraphAlgo.TOPOLOGICAL_SORT(G);
		
		Vertex[] gt = GraphAlgo.getGT(G);
		
		drawVertices(gt, x_start, y_start);
		
		//print list
		String str = "";
		for(Vertex v: list) {
			str += v.getName()+",";
		}
		g2d.drawString(str, x_start+50, y_start-20);
		
		drawEdgesSCC(gt, list);
	}
	
	//Haven't implemented
	public void drawBFS(Vertex[] _G, int x_start, int y_start, Vertex s) {
		Vertex[] G = new Vertex[_G.length];
		System.arraycopy(_G, 0, G, 0, _G.length);
		
		drawVertices(G, x_start, y_start);
		
		//init
		Queue<Vertex> queue = new LinkedList<Vertex>();
		
		for(Vertex u: G) {
		    u.color = Color.WHITE;
			u.pi = null;	
			u.distance = Integer.MAX_VALUE;
		}
		
		s.color = Color.GRAY;
		s.pi = null;
		s.distance = 0;
		queue.add(s);
		
		while(!queue.isEmpty()) {
			Vertex u = queue.poll();
			System.out.println(u.getName());
		    MyLinkedList<Vertex> adj = u.getAdj();
			for(Vertex v:adj) {
				if(v.color == Color.WHITE) {
					v.color = Color.GRAY;
					v.pi = u;
					v.distance = u.distance + 1;//for unweighted graph
					g2d.setColor(Color.GRAY);
					drawAL(u.x, u.y, v.x, v.y);
				    queue.add(v);
				}
			}
			
			u.color = Color.BLACK;	
		}
		
	}
	//draw the vertices and set the x and y (co_oridnate of the center of the vertex)
    private void drawVertices(Vertex[] G, int x_start, int y_start) {  
      	g2d.setColor(Color.BLACK);
        int row = 0;
        int col = 0;
        int count = 0;
        for(Vertex v: G) {
        	
          	row = count/number_row;
          	col = count%number_row;
           	int x = x_start + col*interval;
           	int y = y_start + row*interval;
           	g2d.drawOval(x, y, vertex_size, vertex_size);
           	v.x = x+vertex_size/2;
           	v.y = y+vertex_size/2;
           	String string = v.getName();
           	g2d.drawString(string, x+vertex_size/4, y-5);
            
            count++;
        }
    }
    
    private void drawEdges(Vertex[] G, Color c) {
    	    g2d.setColor(c);
        for(Vertex u: G) {
          	MyLinkedList<Vertex> adj = u.getAdj();
          	for(Vertex v: adj) {
          		drawAL(u.x, u.y, v.x, v.y);
          	}
        }
    }
    private int time = 0;
    private void drawEdgesDFS(Vertex[] G) {
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
    	
    	private void DFS_VISIT(Vertex[] G, Vertex u) {
    		u.color = Color.GRAY;
    		time++;
    		u.d = time;
    		
    		MyLinkedList<Vertex> Adj = u.getAdj();
    		for(Vertex v: Adj) {
    			if(v.color == Color.WHITE) {
    			    v.pi = u;
    			    g2d.setColor(Color.lightGray);
    	    		    drawAL(u.x, u.y, v.x, v.y);
    	    		    
    			    DFS_VISIT(G, v);
    			}
    		}
    		u.color = Color.BLACK;
    		time++;
    		u.f = time;
    		g2d.setColor(u.color);
    		g2d.drawString("("+u.d+"/"+u.f+")", u.x+vertex_size/4, u.y + vertex_size);
    		
    	}
    	
    	private void drawEdgesSCC(Vertex[] gt, MyLinkedList<Vertex> list) {
    		//init
    		for(Vertex u: gt) {
    			u.color = Color.WHITE;
    			u.pi = null;	
    		}
    		time = 0;
    		
    		//revoke
    		for(Vertex vertex: list) {
    			Vertex u = gt[vertex.id];
    			if(u.color == Color.WHITE) {
                //System.out.println("[Vertex] "+u);
    			    DFS_VISIT(gt, u);
    			}
    		}
    	}
    
    private void drawAL(int sx, int sy, int ex, int ey)  
    {  
        double H = 10; // 箭头高度
        double L = 2; // 底边的一半
        int x3 = 0;  
        int y3 = 0;  
        int x4 = 0;  
        int y4 = 0;  
        double awrad = Math.atan(L / H); // 箭头角度  
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度  
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点  
        double y_3 = ey - arrXY_1[1];  
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点  
        double y_4 = ey - arrXY_2[1];  
  
        Double X3 = new Double(x_3);  
        x3 = X3.intValue();  
        Double Y3 = new Double(y_3);  
        y3 = Y3.intValue();  
        Double X4 = new Double(x_4);  
        x4 = X4.intValue();  
        Double Y4 = new Double(y_4);  
        y4 = Y4.intValue();  
        // 画线  
        g2d.drawLine(sx, sy, ex, ey);  
        //  
        GeneralPath triangle = new GeneralPath();  
        triangle.moveTo(ex, ey);  
        triangle.lineTo(x3, y3);  
        triangle.lineTo(x4, y4);  
        triangle.closePath();  
        //实心箭头
        g2d.fill(triangle);  
    }
    
    private double[] rotateVec(int px, int py, double ang,  
            boolean isChLen, double newLen) {  
  
        double mathstr[] = new double[2];  
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度  
        double vx = px * Math.cos(ang) - py * Math.sin(ang);  
        double vy = px * Math.sin(ang) + py * Math.cos(ang);  
        if (isChLen) {  
            double d = Math.sqrt(vx * vx + vy * vy);  
            vx = vx / d * newLen;  
            vy = vy / d * newLen;  
            mathstr[0] = vx;  
            mathstr[1] = vy;  
        }  
        return mathstr;  
    }  
}
