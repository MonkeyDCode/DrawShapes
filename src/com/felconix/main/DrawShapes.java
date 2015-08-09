package com.felconix.main;

import com.felconix.GUI.AbrirPanel;
import com.felconix.GUI.toolBar.ColorBar;
import com.felconix.GUI.Dibujo;
import com.felconix.GUI.toolBar.HerramientaBar;
import com.felconix.GUI.toolBar.RotarBar;
import com.felconix.GUI.toolBar.StrokeBar;
import com.felconix.modelo.Arco;
import com.felconix.modelo.FiguraGenerica;
import com.felconix.modelo.FiguraSalvar;
import com.felconix.modelo.Hoja;
import com.felconix.modelo.Jack;
import com.felconix.modelo.Linea2;
import com.felconix.modelo.Pacman;
import com.felconix.modelo.PanelSave;
import com.felconix.modelo.PanelSave2;
import com.felconix.modelo.Star;
import com.felconix.modelo.TriForce;
import com.felconix.modelo.db.CustomizedFig;
import com.felconix.modelo.db.PaneSave;
import com.felconix.modelo.strokes.ShapeStroke;
import com.felconix.modelo.strokes.WobbleStroke;
import com.felconix.modelo.strokes.ZigzagStroke;
import static com.felconix.util.FigurasConstantes.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public final class DrawShapes extends JApplet implements ActionListener, ChangeListener {
    
    private ColorBar color;
    private int red, blue, green;
    private boolean special;
    private StrokeBar stroke;
    private RotarBar rotar;
    private HerramientaBar tools;
    private CustomizedFig customFig;
    private PaneSave paneSave;
    private JFrame open; 
    
public static void main(String s[]) {    
    JFrame frame = new JFrame();
    frame.setTitle("Drawing Geometric Shapes");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JApplet applet = new DrawShapes();
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setVisible(true);
}

    public DrawShapes() throws HeadlessException {
        init();
    }
  
  

  Dibujo panel = null;

  @Override
  public void init() {
      customFig = new CustomizedFig();
      paneSave = new PaneSave();
      
      open = new JFrame("Abrir");
      open.setVisible(false);
      open.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
      color = new ColorBar();
      color.red.addChangeListener(this);
      color.blue.addChangeListener(this);
      color.green.addChangeListener(this);
      color.fill.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent ae) {
              panel.setFill(color.fill.isSelected());
          }
      });
            
      stroke = new StrokeBar();
      
      stroke.basicStroke.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setStroke(new BasicStroke());
              special = false;
          }
      });
      stroke.zigzagStroke.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setStroke(new ZigzagStroke());
              special = false;
          }
      });
      stroke.wobbleStroke.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setStroke(new WobbleStroke());
              special = false;
          }
      });
      stroke.shapeStroke.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              special = true;
              specialStrokeTypeSelected();
          }
      });
      
      tools = new HerramientaBar();
      
      tools.seleccionar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setOptionTool(SELECCIONAR);
          }
      });
      
      tools.dibujar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setOptionTool(DIBUJAR);
          }
      });
      
      tools.borrar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setOptionTool(BORRAR);
          }
      });
      
      tools.drag.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setOptionTool(DRAG);
          }
      });
      
      tools.archivar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              guardar();
          }
      });
      
      tools.guardar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              guardarPanel();
          }
      });
      
      tools.abrir.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              abrirPanel();
          }
      });
      
      rotar = new RotarBar();
      rotar.rotar.addChangeListener(this);
      rotar.pantalla.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent ae) {
              panel.setPantalla(rotar.pantalla.isSelected());
          }
      });
      
    setLayout(new BorderLayout());
      
    JMenuBar mb = new JMenuBar();
    setJMenuBar(mb);
    JMenu menu = new JMenu("Objects");
    mb.add(menu);
    JMenu miObjects = new JMenu("Objects2");
    mb.add(miObjects);
    JMenuItem mi = new JMenuItem("Rectangle");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("RoundRectangle");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Ellipse");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Arc");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Line");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("QuadCurve");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("CubicCurve");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Polygon");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Tri-force");
    mi.addActionListener(this);
    miObjects.add(mi);
    mi = new JMenuItem("Pacman");
    mi.addActionListener(this);
    miObjects.add(mi);
    mi = new JMenuItem("Hoja");
    mi.addActionListener(this);
    miObjects.add(mi);
    mi = new JMenuItem("Jack");
    mi.addActionListener(this);
    miObjects.add(mi);
    mi = new JMenuItem("Star");
    mi.addActionListener(this);
    miObjects.add(mi);
    panel = new Dibujo();
    
    JMenu personalObjects = new JMenu("Customized objects");
    mb.add(personalObjects);
    inicializedMenu(personalObjects);
    
    JPanel auxPane = new JPanel(new GridLayout(2, 1));
    auxPane.add(color);
    auxPane.add(stroke);
    
    getContentPane().add(tools,BorderLayout.NORTH);
    getContentPane().add(panel,BorderLayout.CENTER);
    getContentPane().add(auxPane,BorderLayout.WEST);
    getContentPane().add(rotar,BorderLayout.EAST);
  }
  
private void inicializedMenu(JMenu menu)
{
    ArrayList<String> nombresCustomizedFig = customFig.nombresCustomizedFig();
    JMenuItem item;
    for (String string : nombresCustomizedFig) {
        item = new JMenuItem(string);
        item.addActionListener(this);
        menu.add(item);
  }
}
  
private void guardar()
{
    JMenuItem item;
    if(panel.getSelectedFigure() == null)
    {
        return;
    }
    String nombre = JOptionPane.showInputDialog(this, "Salvar", "Nombre:", JOptionPane.QUESTION_MESSAGE);
    if(customFig.salvarFigura(new FiguraSalvar(nombre, panel.getPathIter())))
    {
        getJMenuBar().getMenu(2).add(item = new JMenuItem(nombre));
        item.addActionListener(this);
    }
}

private void guardarPanel()
{
    String nombre  = JOptionPane.showInputDialog(this, "Salvar", "Nombre:", JOptionPane.QUESTION_MESSAGE);
    paneSave.salvarFigura(new PanelSave2(panel.guardarPanel(), nombre));
}

private void abrirPanel()
{
    AbrirPanel abrir = new AbrirPanel(paneSave.nombresPaneles());
    open.setContentPane(abrir);
    open.pack();
    open.setVisible(true);
    
    abrir.getAceptar().addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<PanelSave> figuras = paneSave.getFiguras(abrir.getjList1().getSelectedValue().toString());
            panel.setShapes(figuras);
            open.setVisible(false);
        }
    });
}
  
  private void specialStrokeTypeSelected()
  {
      switch (panel.getShapeType()) {
          case RECTANGLE:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Rectangle2D.Double(0, 0,  10, 10),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case ROUNDRECTANGLE2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new RoundRectangle2D.Double(0, 0,  10, 10,5,5),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case ELLIPSE2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Ellipse2D.Float(0, 0, 4, 4),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case ARC2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Arco(0, 0,  10, 10,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case LINE2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Linea2(0, 0,  10, 10,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case QUADCURVE2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new FiguraGenerica(new QuadCurve2D.Float(0, 0, 5, 5, 10, 10),null,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case CUBICCURVE2D:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new FiguraGenerica(new CubicCurve2D.Double(0, 0, 2.5, 2.5, 5, -2.5, 10, 10),null,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case POLYGON:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Pacman(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case TRIFORCE:          
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new TriForce(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case PACMAN:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Pacman(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case HOJA:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Hoja(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case JACK:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Jack(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
          case STAR:
          default:
              panel.setStroke(new ShapeStroke(
                      new Shape[] {
                          new Star(0f, 0f,  10f, 10f,null,null,0,false),
                          new Ellipse2D.Float(0, 0, 4, 4)
                      },
                      15.0f
              ));
              break;
      }
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    String command = ev.getActionCommand();
    if (null != command) switch (command) {
          case "Rectangle":
              panel.setShapeType(RECTANGLE);
              break;
          case "RoundRectangle":
              panel.setShapeType(ROUNDRECTANGLE2D);
              break;
          case "Ellipse":
              panel.setShapeType(ELLIPSE2D);
              break;
          case "Arc":
              panel.setShapeType(ARC2D);
              break;
          case "Line":
              panel.setShapeType(LINE2D);
              break;
          case "QuadCurve":
              panel.setShapeType(QUADCURVE2D);
              break;
          case "CubicCurve":
              panel.setShapeType(CUBICCURVE2D);
              break;
          case "Polygon":
              panel.setShapeType(POLYGON);
              break;
          case "Tri-force":          
              panel.setShapeType(TRIFORCE);
              break;
          case "Pacman":
              panel.setShapeType(PACMAN);
              break;
          case "Hoja":
              panel.setShapeType(HOJA);
              break;
          case "Jack":
              panel.setShapeType(JACK);
              break;
          case "Star":
              panel.setShapeType(STAR);
              break;
          default:
              panel.setShapeType(CUSTOM);
              panel.setPathIter2(customFig.getPersonalizedFig(command)); 
              break;
      }
    if(special)
    {
        specialStrokeTypeSelected();
    }
      
  }

    @Override
    public void stateChanged(ChangeEvent ce) {
        JSlider slider = (JSlider)ce.getSource();
        switch(slider.getName())
        {
            
            case "red":
                red = slider.getValue();
                panel.setColor(new Color(red, green, blue));
                break;
            case "blue":
                blue = slider.getValue();
                panel.setColor(new Color(red, green, blue));
                break;
            case "green":
                green = slider.getValue();
                panel.setColor(new Color(red, green, blue));
                break;
            case "rotar":
                panel.setAngulo(slider.getValue());
                
                break;
        }          
    }
}