package com.ghw.convexhull;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		System.out.println("Test");
		ConvexHull ch = new ConvexHull();
		
		System.out.println("angleVector "+ch.angleVector(Dataset.points));
		
		Dataset.initData();
		
		
		
		System.out.println("crossproduct "+ch.crossproduct(Dataset.points));
		Dataset.convexhull = ch.convexHull(Dataset.points);
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                MyFrame frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
		
	}
}
