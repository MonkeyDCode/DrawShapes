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
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jorge
 */
public class Elipse extends Figura {
    
    public Elipse(double x,double y, double w, double h, Color color, Stroke stroke, double angulo, boolean fill)
    {
        super(stroke, color, new Point((int) (x + w* 0.5),(int) (y + h * 0.5)), fill);
        path = new Ellipse2D.Double(x, y, w, h);
        paux = path;
        setAngulo(angulo);
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
