/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jorge
 */
public final class Pacman extends Figura {
    
    private float x;
    private float y;
    private float w;
    private float h;
    
    private Shape s;

    

  public Pacman(float x, float y, float w, float h, Color color, Stroke stroke, double angulo, boolean fill) 
  {
      super(stroke, color, new Point((int)(x + w * 0.5),(int) (y + h * 0.5)), fill);
      setX(x,w);
      setY(y,h);
      setW(w);
      setH(h);
      s = new GeneralPath();
      path = new Area();
      ((Area)path).add(new Area(new Ellipse2D.Double(this.x, this.y, this.w, this.h)));
      ((GeneralPath)s).moveTo(x+ 0.5 * w, y + 0.5 * h);
      ((GeneralPath)s).lineTo(x + w, y);
      ((GeneralPath)s).lineTo(x + w, y + h);
      ((GeneralPath)s).closePath();
      ((Area)path).subtract(new Area(s));
      
      paux = path;
      
      setAngulo(angulo);
      
  }
  

    /**
     * @param x the x to set
     * @param w
     */
    public void setX(float x,float w) {
        if(w < 0)
        {
            this.x = x + w;
        }
        else
        {
            this.x = x;
        }    
    }

    /**
     * @param y the y to set
     * @param h
     */
    public void setY(float y,float h) {
        if(h < 0)
        {
            this.y = y + h;
        }
        else
        {
            this.y = y;
        }
    }

    /**
     * @param w the w to set
     */
    public void setW(float w) {
        this.w = Math.abs(w);
    }

    /**
     * @param h the h to set
     */
    public void setH(float h) {
        this.h = Math.abs(h);
    }

    /**
     * @param s the s to set
     */
    public void setS(Shape s) {
        this.s = s;
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
