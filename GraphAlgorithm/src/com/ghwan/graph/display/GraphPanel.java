package com.ghwan.graph.display;

import java.awt.Graphics;
import javax.swing.JPanel;

import com.ghwan.graph.Vertex;

public class GraphPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Vertex[] G;
	private Vertex[] GT;

    public GraphPanel(Vertex[] G, Vertex[] GT) {
        super();
        this.G = G;
        this.GT = GT;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphDraw gd = new GraphDraw(g);
        gd.drawTitle("G = (V,E)", 50, 30);
        gd.drawGraph(G, 50, 50);
        
        gd.drawTitle("GT = (V,ET)", 450, 30);
        gd.drawGraph(GT, 450, 50);
        
        gd.drawTitle("BFS(G)", 50, 230);
        gd.drawBFS(G, 50, 250, G[0]);
        
        gd.drawTitle("DFS(G)", 450, 230);
        gd.drawDFS(G, 450, 250);
        
        gd.drawTitle("SCC(G):", 50, 430);
        gd.drawSCC(G, 50, 450);
        
    }
}
