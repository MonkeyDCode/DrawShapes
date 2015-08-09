/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.GUI;

import com.felconix.modelo.Arco;
import com.felconix.modelo.Elipse;
import com.felconix.modelo.Figura;
import com.felconix.modelo.FiguraGenerica;
import com.felconix.modelo.FiguraPersonalizada;
import com.felconix.modelo.Hoja;
import com.felconix.modelo.Jack;
import com.felconix.modelo.Linea2;
import com.felconix.modelo.Pacman;
import com.felconix.modelo.PanelSave;
import com.felconix.modelo.PuntosEditable;
import com.felconix.modelo.Rectangulo;
import com.felconix.modelo.RectanguloRedondeado;
import com.felconix.modelo.Star;
import com.felconix.modelo.TriForce;
import static com.felconix.util.FigurasConstantes.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class Dibujo extends JPanel implements MouseListener, MouseMotionListener {

  private final  ArrayList<Figura> shapes = new ArrayList();
  
  private ArrayList<FiguraGenerica> shapeEdit = new ArrayList();
  public ArrayList<PuntosEditable> pathIter = new ArrayList();
  public ArrayList<PuntosEditable> pathIter2 = new ArrayList();
  private ArrayList<double[]> pointIter = new ArrayList();
  
  private double[] editPoint;
  private int editShape = 2147483647;
  

  private int shapeType = RECTANGLE;
  // vector of input points
  public ArrayList<Point> points = new ArrayList();
  private int pointIndex = 0;
  public Shape partialShape = null;
  
  private Stroke stroke;
  private Point p = null;
  private Color color;
  private boolean fill;
  private boolean pantalla;
  private int scalex;
  private int scaley;
  private double angulo;
  private double anguloPantalla;
  private final Point translate = new Point(0,0);
  private final Point translateAnt = new Point(0,0);
  
  private int optionTool = DIBUJAR;
  
  private Figura selectedFigure;
  
  private Point dragPoint;
  

  public Dibujo() {
    super();
    
    color = new Color(0, 0, 0);
    stroke =  new BasicStroke();
    fill = false;
    
    setBackground(Color.white);
    setPreferredSize(new Dimension(640, 480));
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    Dimension size = getSize();
    double rad = Math.toRadians(anguloPantalla);
    g2.rotate(rad, size.width/2, size.height/2);
    
    g2.translate(translate.x, translate.y);
    
    
    for (Figura shape : shapes) {
        g2.setStroke(shape.getStroke());
        g2.setColor(shape.getColor());
        Shape s = (Shape) shape;
        if(shape.isFill())
        {
            g2.fill(shape);
        }
        else
        {
            g2.draw(s);
        }  
    }
      g2.setColor(Color.GRAY);
      g2.setStroke(new BasicStroke());
      shapeEdit.stream().forEach((shape) -> {
          g2.fill(shape);
      });
  
  }
 
@Override
public void mouseEntered(MouseEvent ev) {
    Toolkit toolkit;
    Image image;
    Point hotSpot = new Point(0,0);
    switch(optionTool)
    {
    case SELECCIONAR:
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        break;
    case DIBUJAR:
        toolkit = Toolkit.getDefaultToolkit(); 
        image =  new ImageIcon(Dibujo.class.getResource("/com/felconix/image/pencil.gif")).getImage();
        setCursor(toolkit.createCustomCursor(image, hotSpot, "Pencil"));
        break;
    case BORRAR:
        toolkit = Toolkit.getDefaultToolkit(); 
        image =  new ImageIcon(Dibujo.class.getResource("/com/felconix/image/Borrar_mouse.png")).getImage();
        setCursor(toolkit.createCustomCursor(image, hotSpot, "Eraser"));
        break;
    case DRAG:
        toolkit = Toolkit.getDefaultToolkit(); 
        image =  new ImageIcon(Dibujo.class.getResource("/com/felconix/image/Drag.png")).getImage();
        setCursor(toolkit.createCustomCursor(image, hotSpot, "Drag"));
        break;    
    default:    
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        break;
    }
  }

  @Override
  public void mouseExited(MouseEvent ev) {
  }

  @Override
  public void mouseClicked(MouseEvent ev) {
      if(getOptionTool() == SELECCIONAR || optionTool == BORRAR)
      {
          selectedFigure = null;
          shapeEdit = new ArrayList();
          for (Figura shape : shapes) {
              if(shape.contains(ev.getX(), ev.getY()))
              {
                  setSelectedFigure(shape);
                  if(optionTool == BORRAR)
                  {
                      shapes.remove(shape);
                      selectedFigure = null;
                  }
                  else
                  {
                      drawPathIterator(shape);
                  }
                  break;
              }
          }
          repaint();
      }
           
  }
  
  private void drawPathIterator(Shape selectedFigure)
  {
        shapeEdit = new ArrayList();
        pathIter = new ArrayList();
        pointIter = new ArrayList();
        double[] aux;
        double[] aux2 = null;
        FiguraGenerica fAux = null;
        PuntosEditable pAux;
        int opt,dis = 5,Wrule;
        for(PathIterator pIter = selectedFigure.getPathIterator(null);!pIter.isDone();pIter.next())
        {
            aux = new double[6];
            opt = pIter.currentSegment(aux);
            Wrule = pIter.getWindingRule();
            switch (opt)
            {
                case PathIterator.SEG_MOVETO:
                    shapeEdit.add(fAux = new FiguraGenerica(new Ellipse2D.Double(aux[0]-dis, aux[1]-dis, 10, 10)));
                    pathIter.add(new PuntosEditable(opt, Wrule, selectedFigure.getBounds().height,
                             selectedFigure.getBounds().width ,Arrays.copyOfRange(aux, 0, 2), 
                            Arrays.copyOfRange(aux, 2, 4), Arrays.copyOfRange(aux, 4, 6)));
                    pointIter.add(aux2=pathIter.get(pathIter.size()-1).p1);
                    //System.out.println("Move to:" + Arrays.toString(aux));
                    break;
                case PathIterator.SEG_LINETO:
                    pathIter.add(pAux = new PuntosEditable(opt, Wrule, selectedFigure.getBounds().height,
                            selectedFigure.getBounds().width, Arrays.copyOfRange(aux, 0, 2), 
                            Arrays.copyOfRange(aux, 2, 4), Arrays.copyOfRange(aux, 4, 6)));
                    guardarCloseto(pAux, aux2, pAux.p1, fAux, 1);
                    //shapeEdit.add(new FiguraGenerica((new Ellipse2D.Double(aux[0]-dis, aux[1]-dis, 10, 10))));
                    //pointIter.add(pathIter.get(pathIter.size()-1).p1);
                    
                    //System.out.println("Line to:" + Arrays.toString(aux));
                    break;
                case PathIterator.SEG_CUBICTO:
                    pathIter.add(pAux = new PuntosEditable(opt, Wrule, selectedFigure.getBounds().height,
                            selectedFigure.getBounds().width, Arrays.copyOfRange(aux, 0, 2), 
                            Arrays.copyOfRange(aux, 2, 4), Arrays.copyOfRange(aux, 4, 6)));
                    shapeEdit.add(new FiguraGenerica(new Ellipse2D.Double(aux[0]-dis, aux[1]-dis, 10, 10)));
                    shapeEdit.add(new FiguraGenerica(new Ellipse2D.Double(aux[2]-dis, aux[3]-dis, 10, 10)));
                    guardarCloseto(pAux, aux2, pAux.p3, fAux, 3);
                    //shapeEdit.add(new FiguraGenerica(new Ellipse2D.Double(aux[4]-dis, aux[5]-dis, 10, 10)));
                    
                    pointIter.add(pathIter.get(pathIter.size()-1).p1);
                    pointIter.add(pathIter.get(pathIter.size()-1).p2);
                    //pointIter.add(pathIter.get(pathIter.size()-1).p3);
                    //System.out.println("Cubic to:" + Arrays.toString(aux));
                    break;
                case PathIterator.SEG_QUADTO:
                    pathIter.add(pAux = new PuntosEditable(opt, Wrule, selectedFigure.getBounds().height,
                            selectedFigure.getBounds().width, Arrays.copyOfRange(aux, 0, 2), 
                            Arrays.copyOfRange(aux, 2, 4), Arrays.copyOfRange(aux, 4, 6)));
                    shapeEdit.add(new FiguraGenerica(new Ellipse2D.Double(aux[0]-dis, aux[1]-dis, 10, 10)));
                    //shapeEdit.add(new FiguraGenerica(new Ellipse2D.Double(aux[2]-dis, aux[3]-dis, 10, 10)));
                    
                    guardarCloseto(pAux, aux2, pAux.p2, fAux, 2);
                    
                    pointIter.add(pathIter.get(pathIter.size()-1).p1);
                    //pointIter.add(pathIter.get(pathIter.size()-1).p2);
                    //System.out.println("Quad to:" + Arrays.toString(aux));
                    break;
                case PathIterator.SEG_CLOSE:
                    pathIter.add(new PuntosEditable(opt, Wrule, selectedFigure.getBounds().height,
                            selectedFigure.getBounds().width, Arrays.copyOfRange(aux, 0, 2), 
                            Arrays.copyOfRange(aux, 2, 4), Arrays.copyOfRange(aux, 4, 6)));
                    //System.out.println("Close to:" + Arrays.toString(aux));
                    break;
            }
        } 
  }
  
  private void guardarCloseto(PuntosEditable pe, double[] d, double[] d2, FiguraGenerica f, int op)
  {
    int dis = 5;
    if(d != null && d[0] == d2[0] && d[1] == d2[1] )
    {
        shapeEdit.add(f);
        pointIter.add(d);
        switch(op)
        {
            case 1:
                pe.p1 = d;
                break;
            case 2:
                pe.p2 = d;
                break;
            case 3:
                pe.p3 = d;
                break;
        }
    }
    else
    {
        shapeEdit.add(new FiguraGenerica((new Ellipse2D.Double(d2[0]-dis, d2[1]-dis, 10, 10))));
        pointIter.add(d2);
    }                   
  }

public ArrayList<PanelSave> guardarPanel()
{
    ArrayList<PanelSave> save = new ArrayList<>();
    for (Figura figura : shapes) {
        drawPathIterator(figura);
        save.add(new PanelSave(figura.getStroke(), figura.getColor(), 
                figura.getP(),figura.getAngulo(), figura.isFill(), pathIter));
    }
    shapeEdit = new ArrayList();
    pathIter = new ArrayList();
    pointIter = new ArrayList();
    return save;
}

  @Override
  public void mousePressed(MouseEvent ev) {
    switch(optionTool)
    {
        case DIBUJAR:
            points.add(ev.getPoint());
            setPointIndex(getPointIndex() + 1);
            setP(null);
            break;
            
        case SELECCIONAR:
            int i = 0;
            editShape = 2147483647;
            for (Shape shapeEdit1 : shapeEdit) {
                if(shapeEdit1.contains(ev.getPoint()))
                {
                    editPoint = pointIter.get(i);
                    editShape = i;
                    break;
                }
                i++;
            }
            break;
        case DRAG:
            dragPoint = ev.getPoint();
            break;
                
    }
    
    
    

  }

  @Override
  public void mouseReleased(MouseEvent ev) {
    Graphics g = getGraphics();
    switch(getOptionTool())
    {
        case DIBUJAR:
            Point p1 = (Point)(points.get(getPointIndex()-1));
                setP(ev.getPoint());
            Figura s = null;
            switch (getShapeType()) {
              case RECTANGLE:
                s = new Rectangulo(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case ROUNDRECTANGLE2D:
                s = new RectanguloRedondeado(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case ELLIPSE2D:
                s = new Elipse(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case ARC2D:
                s = new Arco(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case LINE2D:
                s = new Linea2(p1.x, p1.y, getP().x, getP().y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case QUADCURVE2D:
                if (getPointIndex() > 1) {
                  Point p2 = (Point)points.get(0);
                   s= new FiguraGenerica(new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, getP().x, getP().y), p2, getColor(), getStroke(), getAngulo(), isFill());
                }
                break;
              case CUBICCURVE2D:
                if (getPointIndex() > 2) {
                  Point p2 = (Point)points.get(getPointIndex()-2);
                  Point p3 = (Point)points.get(getPointIndex()-3);
                  s= new FiguraGenerica(new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y, p1.x, p1.y, getP().x, getP().y), p3, getColor(), getStroke(), getAngulo(), isFill());
                }
                break;
              case POLYGON:
                if (ev.isShiftDown()) {
                  s= new FiguraGenerica(new Polygon(), p1, getColor(), getStroke(), getAngulo(), isFill());
                  for (int i = 0; i < getPointIndex(); i++) ((Polygon)(((FiguraGenerica)s).getPath())).addPoint(((Point)points.get(i)).x, ((Point)points.get(i)).y);
                  ((Polygon)(((FiguraGenerica)s).getPath())).addPoint(getP().x, getP().y);
                }
              break;
              case TRIFORCE:
                s = new TriForce(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case PACMAN:
                s = new Pacman(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case HOJA:
                s = new Hoja(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                  break;
              case JACK:
                s = new Jack(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                  break;
              case STAR:
                s = new Star(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill());
                break;
              case CUSTOM:
                s = new FiguraPersonalizada(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill(),pathIter2);
                break;
            }
            if (s != null) {
              shapes.add(s);
              points.clear();
                    setPointIndex(0);
                    setP(null);
              repaint();
            }
            break;
        case SELECCIONAR:
            if(pathIter != null && selectedFigure != null)
            {
                selectedFigure.setPathIterator(pathIter);
                repaint();
            }
            break;
        case DRAG:
            dragPoint = null;
            translateAnt.x = translate.x;
            translateAnt.y = translate.y;
            break;
    }
  }

  @Override
  public void mouseMoved(MouseEvent ev) {
  }

  @Override
  public void mouseDragged(MouseEvent ev) {
    Graphics2D g = (Graphics2D)getGraphics();
    g.setXORMode(Color.white);
    
    g.setColor(getColor());
    g.setStroke(getStroke());
    switch(getOptionTool())
    {
    
        case DIBUJAR:
            Point p1 = (Point)points.get(getPointIndex()-1);
            switch (getShapeType()) {
              case RECTANGLE:
                if (getP() != null) g.draw(new Rectangulo(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                setP(ev.getPoint());
                g.draw(new Rectangulo(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
              case ROUNDRECTANGLE2D:
                if (getP() != null) g.draw(new RectanguloRedondeado(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                setP(ev.getPoint());
                g.draw(new RectanguloRedondeado(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
              case ELLIPSE2D:
                if (getP() != null) g.draw(new Elipse(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                setP(ev.getPoint());
                g.draw(new Elipse(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
              case ARC2D:
                if (getP() != null) g.draw(new Arco(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                setP(ev.getPoint());
                g.draw(new Arco(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
              case LINE2D:
                  if (getP() != null) g.draw(new Linea2(p1.x, p1.y, getP().x, getP().y, getColor(), getStroke(), getAngulo(), isFill()));
                setP(ev.getPoint());
                g.draw(new Linea2(p1.x, p1.y, getP().x, getP().y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
              case POLYGON:
                if (getP() != null) g.drawLine(p1.x, p1.y, getP().x, getP().y);
                setP(ev.getPoint());
                g.drawLine(p1.x, p1.y, getP().x, getP().y);
                break;
              case QUADCURVE2D:
                if (getPointIndex() == 1) {
                  if (getP() != null) g.drawLine(p1.x, p1.y, getP().x, getP().y);
                    setP(ev.getPoint());
                  g.drawLine(p1.x, p1.y, getP().x, getP().y);
                } else {
                  Point p2 = (Point)points.get(getPointIndex()-2);
                  if (getP() != null) g.draw(partialShape);
                    setP(ev.getPoint());
                  partialShape = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, getP().x, getP().y);
                  g.draw(partialShape);
                }
                break;
              case CUBICCURVE2D:
                if (getPointIndex() == 1) {
                  if (getP() != null) g.drawLine(p1.x, p1.y, getP().x, getP().y);
                    setP(ev.getPoint());
                  g.drawLine(p1.x, p1.y, getP().x, getP().y);
                } else if (getPointIndex() == 2) {
                  Point p2 = (Point)points.get(getPointIndex()-2);
                  if (getP() != null) g.draw(partialShape);
                    setP(ev.getPoint());
                  partialShape = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, getP().x, getP().y);
                  g.draw(partialShape);
                } else {
                  Point p2 = (Point)points.get(getPointIndex()-2);
                  Point p3 = (Point)points.get(getPointIndex()-3);
                  if (getP() != null) g.draw(partialShape);
                    setP(ev.getPoint());
                  partialShape = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y, p1.x, p1.y, getP().x, getP().y);
                  g.draw(partialShape);
                }
                break;
                case PACMAN:
                    if (getP() != null) g.draw(new Pacman(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    setP(ev.getPoint());
                    g.draw(new Pacman(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                break;
                case TRIFORCE:
                    if (getP() != null) g.draw(new TriForce(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    setP(ev.getPoint());
                    g.draw(new TriForce(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    break;
                case HOJA:
                    if (getP() != null) g.draw(new Hoja(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    setP(ev.getPoint());
                    g.draw(new Hoja(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    break;
                case JACK:
                    if (getP() != null) g.draw(new Jack(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    setP(ev.getPoint());
                    g.draw(new Jack(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    break;
                case STAR:
                    if (getP() != null) g.draw(new Star(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    setP(ev.getPoint());
                    g.draw(new Star(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill()));
                    break;
                case CUSTOM:
                    if (getP() != null) g.draw(new FiguraPersonalizada(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill(),pathIter2));
                    setP(ev.getPoint());
                    g.draw(new FiguraPersonalizada(p1.x, p1.y, getP().x-p1.x, getP().y-p1.y, getColor(), getStroke(), getAngulo(), isFill(),pathIter2));
                    break;
            }
        break;
        case SELECCIONAR:
            if(editShape < shapeEdit.size())
            {
                shapeEdit.get(editShape).setPath(new Ellipse2D.Double(ev.getX() - 5, ev.getY() - 5, 10, 10));
                editPoint[0] = ev.getX();
                editPoint[1] = ev.getY();
                repaint();
            }
            break;
        case DRAG:
            translate.x = ev.getX() - dragPoint.x + translateAnt.x;
            translate.y = ev.getY() - dragPoint.y + translateAnt.y;
            repaint();
            break;
    }
  }

    /**
     * @return the stroke
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * @param stroke the stroke to set
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
        if(optionTool == SELECCIONAR && selectedFigure != null)
        {
            selectedFigure.setStroke(stroke);
            repaint();
        }
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        if(optionTool == SELECCIONAR && selectedFigure != null)
        {
            selectedFigure.setColor(this.color);
            repaint();
        }
    }

    /**
     * @return the fill
     */
    public boolean isFill() {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(boolean fill) {
        this.fill = fill;
        if(optionTool == SELECCIONAR && selectedFigure != null)
        {
            selectedFigure.setFill(fill);
            repaint();
        }
    }

    /**
     * @return the optionTool
     */
    public int getOptionTool() {
        return optionTool;
    }

    /**
     * @param optionTool the optionTool to set
     */
    public void setOptionTool(int optionTool) {
        this.optionTool = optionTool;
        if(optionTool == DIBUJAR)
        {
            shapeEdit = new ArrayList();
            repaint();
        }
    }

    /**
     * @return the shapeType
     */
    public int getShapeType() {
        return shapeType;
    }

    /**
     * @param shapeType the shapeType to set
     */
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * @return the pointIndex
     */
    public int getPointIndex() {
        return pointIndex;
    }

    /**
     * @param pointIndex the pointIndex to set
     */
    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    /**
     * @return the p
     */
    public Point getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Point p) {
        this.p = p;
    }

    /**
     * @return the selectedFigure
     */
    public Figura getSelectedFigure() {
        return selectedFigure;
    }

    /**
     * @param selectedFigure the selectedFigure to set
     */
    public void setSelectedFigure(Figura selectedFigure) {
        this.selectedFigure = selectedFigure;
        if(selectedFigure != null)
        {
            this.selectedFigure.setColor(color);
            this.selectedFigure.setStroke(stroke);
            this.selectedFigure.setFill(fill);
            this.selectedFigure.setAngulo(angulo);
            repaint(); 
        }
        
        
    }

    /**
     * @return the pantalla
     */
    public boolean isPantalla() {
        return pantalla;
    }

    /**
     * @param pantalla the pantalla to set
     */
    public void setPantalla(boolean pantalla) {
        this.pantalla = pantalla;
    }

    /**
     * @return the angulo
     */
    public double getAngulo() {
        return angulo;
    }
    
    

    /**
     * @param angulo the angulo to set
     */
    public void setAngulo(double angulo) {
        if( !pantalla && optionTool == SELECCIONAR && selectedFigure != null)
        {
            this.angulo = angulo;
            selectedFigure.setAngulo(angulo);
            drawPathIterator(selectedFigure);
            repaint();
        }
        else if(pantalla)
        {
            this.anguloPantalla = angulo;
            repaint();
        }
    }

    /**
     * @return the pathIter
     */
    public ArrayList<PuntosEditable> getPathIter() {
        return pathIter;
    }

    /**
     * @return the pathIter2
     */
    public ArrayList<PuntosEditable> getPathIter2() {
        return pathIter2;
    }

    /**
     * @param pathIter2 the pathIter2 to set
     */
    public void setPathIter2(ArrayList<PuntosEditable> pathIter2) {
        this.pathIter2 = pathIter2;
    }

    /**
     * @param shape
     * 
     */
    public void setShapes(ArrayList<PanelSave> shape) {
        shapes.clear();
        for (PanelSave panelSave : shape) {
            shapes.add(new Figura(panelSave.getFiguras(), panelSave.getStroke(), 
                    panelSave.getColor(), panelSave.getP(), panelSave.getFill(), panelSave.getAngulo()) {
            });
        }
        repaint();
    }
    
    
}

