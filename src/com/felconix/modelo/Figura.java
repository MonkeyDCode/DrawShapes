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
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public abstract class Figura implements Shape {
    
    protected Stroke stroke;
    protected Color color;
    protected AffineTransform t;
    protected Point p;
    protected double angulo;
    protected boolean fill;
    public Shape path;
    protected Shape paux;
    
    public Figura(Shape path)
    {
        this.path = path;
    }
    
    
    
    private Figura(Point p) {
        this.p = p;
        t = new AffineTransform();
    }

    public Figura(Stroke stroke, Color color, Point p, boolean fill) {
        this(p);
        this.stroke = stroke;
        this.color = color;
        this.fill = fill;
    }
    
    public Figura(ArrayList<PuntosEditable> piter,Stroke stroke, Color color, Point p, boolean fill,double angulo) {
        this(p);
        this.stroke = stroke;
        this.color = color;
        this.fill = fill;
        this.angulo = angulo;
        setPathIterator(piter);
    }
    
    /**
     *
     * @param pIter
     * @return
     */
    public final GeneralPath setPathIterator(ArrayList<PuntosEditable> pIter)
    {
        GeneralPath path = new GeneralPath();
        path.setWindingRule(pIter.get(0).windingRule);
        for (PuntosEditable pIter1 : pIter) {
            switch(pIter1.pathType)
            {
                case PathIterator.SEG_MOVETO:
                        
                    path.moveTo(pIter1.p1[0], pIter1.p1[1]);
                    break;
                case PathIterator.SEG_LINETO:
                    path.lineTo(pIter1.p1[0], pIter1.p1[1]);
                    break;
                case PathIterator.SEG_QUADTO:
                    path.quadTo(pIter1.p1[0], pIter1.p1[1], pIter1.p2[0], pIter1.p2[1]);
                    break;
                case PathIterator.SEG_CUBICTO:
                    path.curveTo(pIter1.p1[0], pIter1.p1[1], pIter1.p2[0], pIter1.p2[1], pIter1.p3[0], pIter1.p3[1]);
                    break;
                case PathIterator.SEG_CLOSE:
                    path.closePath();
                    break;
            }  
        }
        this.path = path;
        if(angulo > 0)
        {
            double rad = Math.toRadians(360 - angulo);
            t.setToRotation(rad,p.x,p.y); 
            paux = t.createTransformedShape(path);
        }       
        else
        {
            paux = path;
        }
        return path;
    }

  

    /**
     * @return the stroke
     */
    public final Stroke getStroke() {
        return stroke;
    }

    /**
     * @param stroke the stroke to set
     */
    public final  void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    /**
     * @return the color
     */
    public final Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public final void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the t
     */
    public final AffineTransform getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public final void setT(AffineTransform t) {
        this.t = t;
    }

    /**
     * @return the p
     */
    public final Point getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public final void setP(Point p) {
        this.p = p;
    }

    /**
     * @return the angulo
     */
    public final double getAngulo() {
        return angulo;
    }

    /**
     * @param angulo the angulo to set
     */
    public final void setAngulo(double angulo)
    {
        this.angulo = angulo;
        if(angulo > 0)
        {
            double rad = Math.toRadians(angulo);
            t.setToRotation(rad,p.x,p.y); 
            path = t.createTransformedShape(paux);
        }       
        else
        {
            path = paux;
        }
    }

    /**
     * @return the fill
     */
    public final boolean isFill() {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public final void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     *
     * @param path the path to set
     */
    public void setPath(Shape path) {
        this.path = path;
    }

    /**
     * @return the path
     */
    public Shape getPath() {
        return path;
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
