/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.GUI.toolBar;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 *
 * @author Jorge
 */
public class HerramientaBar extends JPanel {
    
    public JToggleButton seleccionar;  
    public JToggleButton dibujar; 
    public JToggleButton borrar;
    public JToggleButton drag;
    public JButton archivar;
    public JButton guardar;
    public JButton abrir;
    
    private ButtonGroup tool;
    
    public HerramientaBar()
    {
        super(new BorderLayout());
        init();
    }
    
    private void init()
    {
        JToolBar tools = new JToolBar("Tools");
        tools.setLayout(new GridLayout(1, 2));
        
        tool = new ButtonGroup();
        
        int width = 30;
        int height = 30;
        
        ImageIcon image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/Seleccionar.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        seleccionar = new JToggleButton(image);  
        seleccionar.setBorderPainted(false); 
        tool.add(seleccionar);
        tools.add(seleccionar);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/dibujar.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        dibujar = new JToggleButton(image);                
        dibujar.setBorderPainted(false);   
        dibujar.setSelected(true);
        tool.add(dibujar);
        tools.add(dibujar);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/Borrar.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        borrar = new JToggleButton(image);                
        borrar.setBorderPainted(false);   
        borrar.setSelected(false);
        tool.add(borrar);
        tools.add(borrar);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/Drag.png"));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, 100));
        drag = new JToggleButton(image);                
        drag.setBorderPainted(false);   
        drag.setSelected(false);
        tool.add(drag);
        tools.add(drag);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/iconoGuardar.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width,height,100));
        archivar = new JButton(image);
        archivar.setBorderPainted(false);
        archivar.setSelected(false);
        tools.add(archivar);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/Guardar.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width,height,100));
        guardar = new JButton(image);
        guardar.setBorderPainted(false);
        guardar.setSelected(false);
        tools.add(guardar);
        
        image = new ImageIcon(HerramientaBar.class.getResource("/com/felconix/image/Abrir.jpg"));
        image = new ImageIcon(image.getImage().getScaledInstance(width,height,100));
        abrir = new JButton(image);
        abrir.setBorderPainted(false);
        abrir.setSelected(false);
        tools.add(abrir);
        
        
        
        
        add(tools,BorderLayout.WEST);
    }
    
    
}
