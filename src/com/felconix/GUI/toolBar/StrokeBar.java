/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.GUI.toolBar;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 *
 * @author Jorge
 */
public class StrokeBar extends JPanel {
    
    public JToggleButton basicStroke;
    public JToggleButton zigzagStroke;
    public JToggleButton wobbleStroke;
    public JToggleButton shapeStroke;
    
    private ButtonGroup strokes;
    
    
    public StrokeBar()
    {
        init();
    }
    
    
    private void init()
    {
        strokes = new ButtonGroup();
        
        JToolBar stroke = new JToolBar("Stroke");
        stroke.setOrientation(JToolBar.VERTICAL);
        stroke.setLayout(new GridLayout(4, 1));
        
        int width = 150;
        int height = 50;
        
        ImageIcon image = new ImageIcon(StrokeBar.class.getResource("/com/felconix/image/BasicStroke.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
               
        basicStroke = new JToggleButton(image);
        basicStroke.setActionCommand("Basic stroke");
        basicStroke.setBorderPainted(false);
        basicStroke.setSelected(true);
        strokes.add(basicStroke);
        stroke.add(basicStroke);
        
        image = new ImageIcon(StrokeBar.class.getResource("/com/felconix/image/ZigzagStroke.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        zigzagStroke = new JToggleButton(image);
        zigzagStroke.setActionCommand("Zigzag stroke");
        zigzagStroke.setBorderPainted(false);
        strokes.add(zigzagStroke);
        stroke.add(zigzagStroke);
                
        image = new ImageIcon(StrokeBar.class.getResource("/com/felconix/image/WobbleStroke.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        wobbleStroke = new JToggleButton(image);
        wobbleStroke.setActionCommand("Wobble stroke");
        wobbleStroke.setBorderPainted(false);
        strokes.add(wobbleStroke);
        stroke.add(wobbleStroke);
        
        image = new ImageIcon(StrokeBar.class.getResource("/com/felconix/image/ShapeStroke.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        shapeStroke = new JToggleButton(image);
        shapeStroke.setActionCommand("Shape stroke");
        shapeStroke.setBorderPainted(false);
        strokes.add(shapeStroke);
        stroke.add(shapeStroke);
        
        
        add(stroke);
    }
    
    
}
