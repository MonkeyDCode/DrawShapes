/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.GUI.toolBar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;


/**
 *
 * @author Jorge
 */
public class ColorBar extends JPanel{
    
    public JSlider red;
    public JSlider blue;
    public JSlider green;
    
    public JCheckBox fill;
    
    public ColorBar()
    {
        init();
    }
    
    private void init()
    {
        JToolBar color = new JToolBar("Color");
        color.setOrientation(JToolBar.VERTICAL);
        color.setLayout(new GridLayout(4, 1));
        
                            
        red = new JSlider(0, 255, 0);
        blue = new JSlider(0, 255, 0);
        green = new JSlider(0, 255, 0);
        
        red.setPreferredSize(new Dimension(100, 20));
        blue.setPreferredSize(new Dimension(100, 20));
        green.setPreferredSize(new Dimension(100, 20));
        
        red.setName("red");
        green.setName("green");
        blue.setName("blue");
        
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("Rojo   "));
        panel.add(red);
        color.add(panel);
        
        panel = new JPanel();
        panel.add(new JLabel("Azul   "));
        panel.add(blue);
        color.add(panel);
        
        panel = new JPanel();
        panel.add(new JLabel("Verde "));
        panel.add(green);
        color.add(panel);
        
        panel = new JPanel();
        fill = new JCheckBox("fill");
        fill.setFont(new Font(Font.DIALOG,Font.PLAIN , 15));
        panel.add(fill);
        color.add(panel);
        
        add(color);
    }
    
}
