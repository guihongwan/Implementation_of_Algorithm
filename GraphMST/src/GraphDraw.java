

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import com.ghwan.graph.datastructure.MyLinkedList;
import com.ghwan.graph.representation.Edge;
import com.ghwan.graph.representation.Vertex;

public class GraphDraw {
    private int interval = 120;
	private int number_row = 3; //how many vertices each row
	private int vertex_size = 20;
	
	private Graphics2D g2d;
	
	public GraphDraw(Graphics2D g) {
		g2d = g;
	}
    
	public void drawTitle(String title, int x_start, int y_start) {
		g2d.setColor(Color.BLUE);
		g2d.drawString(title, x_start, y_start);
	}
	
	public void drawGraph(Vertex[] _G, int x_start, int y_start, boolean directed) {//directed or undirected graph
		Vertex[] G = new Vertex[_G.length];
		System.arraycopy(_G, 0, G, 0, _G.length);
		
		drawVertices(G,x_start, y_start);
		drawEdges(G, Color.BLACK, directed);
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
           	if(row%2 == 0) {
           		x += 30;
           	}
           	g2d.drawOval(x, y, vertex_size, vertex_size);
           	v.x = x+vertex_size/2;
           	v.y = y+vertex_size/2;
           	String string = v.getName();
           	g2d.drawString(string, x+vertex_size/4, y-5);
            
            count++;
        }
    }
    
    private void drawEdges(Vertex[] G, Color c, boolean directed) {
    	    g2d.setColor(c);
        for(Vertex u: G) {
          	MyLinkedList<Edge> adj = u.getAdj();
          	for(Edge e: adj) {
          		Vertex v = e.v;
          		if(directed) {
          		    drawAL(u.x, u.y, v.x, v.y);
          		} else {
          			g2d.drawLine(u.x, u.y, v.x, v.y);
          		}
          		g2d.drawString(""+e.w, (u.x+v.x)/2, (u.y+v.y)/2);
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
