/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Vaio
 */
public class FiguraPersonalizada extends Figura {
    
    private final double scaleX;
    private final double scaleY;
    private final double translateX;
    private final double translateY;
    
    
    public FiguraPersonalizada(float x, float y, float w, float h, Color color, 
            Stroke stroke, double angulo, boolean fill, ArrayList<PuntosEditable> path) {
        super(stroke, color, new Point((int)(x + w * 0.5),(int) (y + h *0.5)), fill);
        scaleY = h/path.get(0).height;
        scaleX = w/path.get(0).width;
        translateX = x;
        translateY = y;
        PathIterator(path);
        setAngulo(angulo);
    }
    
    
    private void PathIterator(ArrayList<PuntosEditable> pIter)
    {
        GeneralPath path1 = super.setPathIterator(pIter);
        double scaleX1 = t.getScaleX();
        double scaleY1 = t.getScaleY();
        
        t.scale(scaleX, scaleY);
        this.path = t.createTransformedShape(path1);
        t.scale(scaleX1, scaleY1);
        
        Rectangle bounds = this.path.getBounds();
        
        t.setToTranslation(-bounds.x,-bounds.y);
        this.path = t.createTransformedShape(path);
        t.setToTranslation(translateX, translateY);
        this.path = t.createTransformedShape(this.path);
        t.setToTranslation(0, 0);
        
        this.paux = path;

        
        
        
        
    }
    
  @Override
  public boolean contains(Rectangle2D rect) {
    return path.contains(rect);
  }

    @Override
  public boolean contains(Point2D point) {
    return path.contains(point);
  }

    @Override
  public boolean contains(double x, double y) {
    return path.contains(x, y);
  }

    @Override
  public boolean contains(double x, double y, double w, double h) {
    return path.contains(x, y, w, h);
  }

    @Override
  public Rectangle getBounds() {
    return path.getBounds();
  }

    @Override
  public Rectangle2D getBounds2D() {
    return path.getBounds2D();
  }

    @Override
  public PathIterator getPathIterator(AffineTransform at) {
    return path.getPathIterator(at);
  }

    @Override
  public PathIterator getPathIterator(AffineTransform at, double flatness) {
    return path.getPathIterator(at, flatness);
  }

    @Override
  public boolean intersects(Rectangle2D rect) {
    return path.intersects(rect);
  }

    @Override
  public boolean intersects(double x, double y, double w, double h) {
    return path.intersects(x, y, w, h);
  }

    
}
