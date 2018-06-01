package com.ghw.convexhull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private MyFrame frame;

    public MyPanel(MyFrame frame) {
        super();
        this.frame = frame;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容

        /* 自行打开下面注释查看各绘制效果 */

        // 1. 线段 / 折线
        drawLine(g);
        drawPoint(g);
    }
    
    private void drawPoint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        for(int i = 0; i < Dataset.points.length; i++) {
        	    if(i%3 == 0) {
        	    	    g2d.setColor(Color.BLACK);
        	    }else if(i%2 == 0) {
        	    	    g2d.setColor(Color.GREEN);
        	    }else {
        	     	g2d.setColor(Color.ORANGE);
        	    }
        	    
            g2d.drawOval(Dataset.points[i].x, Dataset.points[i].y, 2, 2);
        }
        
    }
    
    private void drawLine(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        if(Dataset.convexhull != null) {
        	    int[] xPoints = new int[Dataset.convexhull.length];
        	    int[] yPoints = new int[Dataset.convexhull.length];
        	    
        	    for(int i = 0; i< Dataset.convexhull.length; i++) {
        	    	    xPoints[i] = Dataset.convexhull[i].x;
        	    	    yPoints[i] = Dataset.convexhull[i].y;
        	    }
        	    g2d.drawPolyline(xPoints, yPoints, Dataset.convexhull.length);
        }
        
    }


}
