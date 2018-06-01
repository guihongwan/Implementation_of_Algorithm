
import javax.swing.JFrame;

import com.ghwan.graph.representation.Vertex;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Container;

/**
 * 
 * @author wanguihong
 *
 */

public class Display extends JFrame{
    private static final boolean DEBUG = true;
    
    private Graphics2D mGraphics;
    private GraphDraw mGraphDraw;
    
    public Display(String title){
        setTitle(title);
        setSize(Utils.width, Utils.height);
        setVisible(true);
        setLocationRelativeTo(null);//center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
        Container p = getContentPane();
        //p.setBackground(Utils.BACKGROUND);
        
        mGraphics =  (Graphics2D)getGraphics();
        mGraphDraw = new GraphDraw(mGraphics);
    }
    //public void 
    public void drawOrignalGraph(Vertex[] G){
       	mGraphDraw.drawTitle("G = (V, E)", 50, 50);
       	mGraphDraw.drawGraph(G, 50, 70, false);
    }
  //public void 
    public void drawMST(Vertex[] G){
       	mGraphDraw.drawTitle("MST-KRUSKAL", 450, 50);
       	mGraphDraw.drawGraph(G, 450, 70, false);
    }
}