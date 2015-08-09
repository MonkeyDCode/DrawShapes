package com.felconix.modelo;



import java.awt.BasicStroke;
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

public class Star extends Figura implements Shape{
 
    Stroke dashStroke = new BasicStroke(6, BasicStroke.CAP_ROUND,
					      BasicStroke.JOIN_ROUND, 10f,new float[]{4f, 4f}, 0f);
					      
    int margin = 10; 
    int scale = 10;
	  // new 2D features. 
	  
	 
	  
    public Star(float x, float y, float w, float h, Color color, Stroke stroke, double angulo, boolean fill) {
        super(stroke, color, new Point((int)(x + w * 0.5),(int) (y + h *0.5)), fill);
        GeneralPath path=new GeneralPath();
	path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
	path.moveTo(x+0.40*w,y+0.50*h);
	path.quadTo(x+0.60*w,y+0.50*h,x+0.60*w,y+0.30*h);
	path.quadTo(x+0.60*w,y+0*h,x+0.80*w,y+0.05*h);
	path.quadTo(x+0.80*w,y+0.50*h,x+1.10*w,y+0.50*h);
			
	path.quadTo(x+1.45*w,y+0.55*h,x+1.40*w,y+0.80*h);
	path.quadTo(x+1.00*w,y+0.80*h,x+1.10*w,y+1.20*h);
	path.quadTo(x+1.20*w,y+1.50*h,x+.90*w,y+1.30*h);
	path.quadTo(x+.75*w,y+1.15*h,x+0.62*w,y+1.30*h);
	path.quadTo(x+.29*w,y+1.57*h,x+.39*w,y+1.12*h);
	path.quadTo(x+0.43*w,y+.86*h,x+.22*w,y+.70*h);
	path.quadTo(x+.11*w,y+.53*h,x+.40*w,y+.50*h);
	path.moveTo(x+.83*w,y+.83*h);
	path.curveTo(x+.83*w,y+1.00*h,x+.67*w,y+1.00*h,x+.67*w,y+.83*h);
	path.moveTo(x+.83*w,y+.83*h);
	path.quadTo(x+.75*w,y+.89*h,x+.67*w,y+.83*h);
	path.moveTo(x+.85*w,y+.68*h);
	
        path.curveTo(x+.91*w,y+.68*h,x+.91*w,y+.76*h,x+.85*w,y+.76*h);
        path.curveTo(x+0.81*w,y+0.76*h,x+0.81*w,y+0.68*h,x+0.85*w,y+0.68*h);
        path.moveTo(x+0.65*w,y+0.68*h);
        path.curveTo(x+0.71*w,y+0.68*h,x+0.71*w,y+0.76*h,x+0.65*w,y+0.76*h);
        path.curveTo(x+0.61*w,y+0.76*h,x+0.61*w,y+0.68*h,x+0.65*w,y+0.68*h);
        this.path = path;
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
