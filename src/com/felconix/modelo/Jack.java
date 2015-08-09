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

public class Jack extends Figura  {
    
	public Jack(float x, float y, float w, float h , Color color, Stroke stroke, double angulo, boolean fill) {
            super(stroke, color, new Point((int)(x + w *0.5),(int) (y + h * 0.5)), fill);
            
		GeneralPath path=new GeneralPath();
                path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
		
		path.moveTo(x+0.70*w,y+0.20*h);
		path.curveTo(x+1.30*w,y+0.20*h,x+1.30*w,y+1.30*h,x+0.70*w,y+1.30*h);
	   
		path.curveTo(x+0.10*w,y+1.30*h,x+0.10*w, y+0.20*h,x+0.70*w, y+0.20*h);
		
		path.moveTo(x+0.45*w,y+0.50*h);
		path.curveTo(x+0.56*w,y+0.52*h,x+0.49*w,y+0.85*h,x+0.40*w,y+0.85*h);
		path.curveTo(x+0.30*w,y+0.78*h,x+0.39*w,y+0.48*h,x+0.45*w,y+0.50*h);
		path.moveTo(x+1.19*w,y+0.82*h);
		path.curveTo(x+1.25*w,y+0.95*h, x+1.04*w,y+1.10*h, x+0.80*w,y+1.09*h);
        path.curveTo(x+0.70*w,y+ 1.12*h,x+0.55*w,y+1.15*h,x+0.30*w,y+1.10*h);
        
        path.moveTo(x+0.63*w,y+0.72*h);
        path.curveTo(x+0.67*w,y+0.73*h,x+0.74*w,y+0.80*h,x+0.73*w,y+0.83*h);
        path.curveTo(x+0.71*w,y+0.85*h,x+0.63*w,y+0.72*h,x+0.63*w,y+0.72*h);
        
        path.moveTo(x+0.57*w,y+0.75*h);
		path.curveTo(x+0.60*w, y+0.76*h,x+0.60*w,y+0.86*h,x+0.57*w,y+0.84*h);
		path.curveTo(x+0.55*w,y+0.83*h,x+0.54*w,y+0.76*h,x+0.57*w,y+0.75*h);
		
		path.moveTo(x+1.21*w,y+.91*h);
		path.lineTo(x+1.16*w,y+0.87*h);
		path.moveTo(x+1.13*w,y+0.92*h);
		path.lineTo(x+1.16*w,y+0.97*h);
		path.moveTo(x+1.07*w,y+0.98*h);
		path.lineTo(x+1.09*w,y+1.04*h);
		path.moveTo(x+1.00*w,y+1.00*h);
		path.lineTo(x+1.00*w,y+1.09*h);
		path.moveTo(x+.91*w, y+1.01*h);
		path.lineTo(x+.92*w, y+1.13*h);
		path.moveTo(x+0.83*w,y+1.02*h);
		path.moveTo(x+0.81*w,y+1.12*h);
		path.moveTo(x+0.71*w,y+1.02*h);
		path.lineTo(x+0.74*w,y+1.15*h);
		path.moveTo(x+0.61*w,y+1.07*h);
		path.lineTo(x+0.63*w,y+1.16*h);
		path.moveTo(x+0.53*w,y+1.08*h);
		path.lineTo(x+0.54*w,y+1.17*h);
		path.moveTo(x+0.47*w,y+1.09*h);
		path.lineTo(x+0.47*w,y+1.16*h);
		path.moveTo(x+0.40*w,y+1.07*h);
		path.lineTo(x+0.40*w,y+1.13*h);
		
		
		
		path.moveTo(x+0.73*w,y+0.42*h);
		path.curveTo(x+0.82*w,y+0.33*h,x+1.10*w,y+0.59*h,x+1.00*w,y+0.68*h);
		path.curveTo(x+0.90*w,y+0.79*h,x+0.65*w,y+0.52*h,x+0.73*w,y+0.42*h);
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
