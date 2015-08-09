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


/**
 *
 * @author Vaio
 */
public class Hoja extends Figura {

  public Hoja(float x, float y, float w, float h, Color color, Stroke stroke, double angulo, boolean fill) {
    super(stroke, color, new Point((int)(x + w * 0.5),(int) (y + h *0.5)), fill);
    GeneralPath path = new GeneralPath();
    path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
    float x0 = x + 0.9f*w;
    float y0 = y + 0f*h;
    float x1 = x + 0.75f*w;
    float y1 = y + 0.13f * h;
    float x2 = x + 0.72f * w;
    float y2 = y + 0.26f * h;
    float x3 = x + 0.4f * w;
    float y3 = y + .11f * h;
    float x4 = x + 0f * w;
    float y4 = y + .33f * h;
    float x5 = x + 0.18f * w;
    float y5 = y + 0.43f * h;
    float x6 = x + .37f *w;
    float y6 = y + .47f*h;
    float x7 = x + .26f *w;
    float y7 = y + .66f*h;
    float x8 = x + .3f *w;
    float y8 = y + .9f*h;
    float x9 = x + .55f *w;
    float y9 = y + .83f*h;
    float x10 = x + .80f *w;
    float y10 = y + .65f*h;
    float x11 = x + .90f *w;
    float y11 = y + .73f*h;
    float x12 = x + 1.f *w;
    float y12 = y + .75f*h;
    float x13 = x + .85f *w;
    float y13 = y + .25f*h;
    float x14 = x + 1.f *w;
    float y14 = y + .32f*h;
    float x15 = x + .7f *w;
    float y15 = y + .3f*h;
    float x16 = x + .55f *w;
    float y16 = y + .31f*h;
    float x17 = x + .38f *w;
    float y17 = y + .29f*h;
    float x18 = x + .6f *w;
    float y18 = y + .57f*h;
    float x19 = x + .3f *w;
    float y19 = y + .9f*h;
    float x20 = x + .8f *w;
    float y20 = y + .4f*h;
    float x21 = x + 1.f *w;
    float y21 = y + .51f*h;
    
    
    
    
           
    path.moveTo(x0, y0);
    path.curveTo(x0, y0, x1, y1, x2, y2);
    path.moveTo(x2,y2);
    path.curveTo(x2, y2, x3, y3, x4, y4);
    path.moveTo(x4, y4);
    path.curveTo(x4, y4, x5, y5, x6, y6);
    path.moveTo(x6, y6);
    path.curveTo(x6, y6, x7, y7, x8,y8);
    path.moveTo(x8, y8);
    path.curveTo(x8, y8, x9, y9, x10,y10);
    path.moveTo(x10, y10);
    path.curveTo(x10, y10, x11, y11, x12,y12);
    path.moveTo(x2, y2);
    path.curveTo(x2, y2, x13, y13, x14,y14);
    path.moveTo(x14, y14);
    path.lineTo(x12, y12);
    path.moveTo(x15, y15);
    path.curveTo(x15,y15, x16, y16, x17,y17);
    path.moveTo(x15, y15);
    path.curveTo(x15,y15, x18, y18, x19,y19);
    path.moveTo(x15, y15);
    path.curveTo(x15,y15, x20, y20, x21,y21); 
    
    this.path = path;
    paux = path;
    
    setAngulo(angulo);
    
    //path.curveTo(x4, y4, x5, y5, x0, y0);
  }
  
    
}
