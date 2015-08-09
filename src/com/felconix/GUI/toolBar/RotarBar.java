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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

/**
 *
 * @author Jorge
 */
public class RotarBar extends JPanel {
    
    public JSlider rotar;
    public JCheckBox pantalla;
    
    public RotarBar() {
        init();
        
    }
    
    private void init()
    {
        rotar = new JSlider(0, 360, 0);
        rotar.setName("rotar");
        rotar.setPreferredSize(new Dimension(100, 20));
        
        JToolBar rotacion = new JToolBar("Rotacion");
        rotacion.setOrientation(JToolBar.VERTICAL);
        rotacion.setLayout(new GridLayout(2, 1));
        
        
        JPanel panel = new JPanel();
        JLabel etiq = new JLabel("Rotar");
        panel.add(etiq);
        panel.add(rotar);
        rotacion.add(panel);
         
        panel = new JPanel();
        pantalla = new JCheckBox("Pantalla");
        pantalla.setFont(new Font(Font.DIALOG,Font.PLAIN , 15));
        panel.add(pantalla);
        rotacion.add(panel);
        
        add(rotacion);
    }
    
}
