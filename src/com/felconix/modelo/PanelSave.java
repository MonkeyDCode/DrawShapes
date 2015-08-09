/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 *
 * @author Vaio
 */
public class PanelSave {
    
    private final Stroke stroke;
    private final Color color;
    private final Point p;
    private final Double angulo;
    private final Boolean fill;
    private final ArrayList<PuntosEditable> figuras;

    public PanelSave(Stroke stroke, Color color, Point p, Double angulo, Boolean fill, ArrayList<PuntosEditable> figuras) {
        this.stroke = stroke;
        this.color = color;
        this.p = p;
        this.angulo = angulo;
        this.fill = fill;
        this.figuras = figuras;
    }

    
    
    /**
     * @return the stroke
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the p
     */
    public Point getP() {
        return p;
    }

    /**
     * @return the angulo
     */
    public Double getAngulo() {
        return angulo;
    }

    /**
     * @return the fill
     */
    public Boolean getFill() {
        return fill;
    }

    /**
     * @return the figuras
     */
    public ArrayList<PuntosEditable> getFiguras() {
        return figuras;
    }
    
    

  
    
}
