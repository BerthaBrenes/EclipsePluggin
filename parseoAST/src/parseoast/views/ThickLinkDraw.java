package parseoast.views;
import java.io.File;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.EventDispatcher;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;



public class ThickLinkDraw {
	public Image decision = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision_symbol-60x46.PNG");
	private Figure root;
	/** Application entry point */
	  public static void main(String[] args) {
	    new ThickLinkDraw().open();
	  }
	  
	  public void open(){
	    Shell shell = new Shell(new Display());
	    shell.setSize(800, 800);
	    shell.setText("My Main View");
	    shell.setLayout( new GridLayout() );
	     root = new Figure();
	    // build diagram
	    Canvas canvas = buildDiagram( shell ,root);
	   
	    canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    Display display = shell.getDisplay();
	    // open and wait until closing
	    shell.open();
	    while( !shell.isDisposed() )
	      while( !display.readAndDispatch() )
	        display.sleep();
	  }
	  
  public Canvas buildDiagram( Composite parent , Figure root ){
	     
	    // instantiate root figure
	    
	    root.setFont(parent.getFont());
	    root.setLayoutManager( new XYLayout() );
	     
	    // insantiate a canvas on which to draw
	    Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
	    canvas.setBackground(ColorConstants.white);
	    LightweightSystem lws = new LightweightSystem(canvas);
	    lws.setContents(root);
	    draw(root);
	    // 
	    return canvas;
	  }
  public void draw(Figure root) {
	  Figure first= decision("First");
	  root.add(first, 
	    new Rectangle( new Point(10, 10),  
	      first.getPreferredSize() ) );
	   
	  Figure second= process("Second");
	  root.add(second, 
	    new Rectangle( new Point(200, 100),  
	      second.getPreferredSize() ) );
	 //This in recursion 
	 Figure third = FOR("Third");
	 
	 root.add(third, new Rectangle( new Point(500, 200),  
	      first.getPreferredSize() ) );
	 root.add(connect2(second, first,"Yes"));
	 root.add(connect(third, first,"No"));
	 
	 
	}
  /**
   * Metodos de figuras
   * @param name
   * @return
   */
  public Figure FOR(String name){
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\for-symbol-cycle.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	   return a;
	}
  	
  public Figure process(String name){
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\action-process-symbol.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	   return a;
	}
  public Figure decision(String name){
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\decision_symbol-60x46.PNG"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	  return a;
	}
  public Figure While(String name){
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\filled-decision_symbol-60x46.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	  return a;
	}
  public Figure Extern (String name) {
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\filled-action-process-symbol.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	  return a;
  }
  public Figure Start_End (String name) {
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\start-end-process-symbol.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	  return a;
  }
  public Figure input_output (String name) {
	  ImageFigure a = new ImageFigure();
	  a.setImage(getImage("C:\\Users\\Brayan Muñoz\\Documents\\EclipsePluggin\\parseoAST\\Iconos de Diagrama\\input-output-symbol.png"));
	  a.setLayoutManager( new ToolbarLayout() );
	  a.add( new Label( "\n"+name ) );
	  new MyListener(a);
	  return a;
  }
  /**
   * Obtener imagen a partir del path
   * @param path
   * @return
   */
  public static Image getImage(String path) {
	  File f = new File( path);
	  return new Image( Display.getCurrent(), f.getPath() );
	}

  
  /**
   * Conecciones de figuras
   * @param figure1
   * @param figure2
   * @param text
   * @return
   */
  public Connection connect(IFigure figure1, IFigure figure2,String text) {
	   PolylineConnection connection = new PolylineConnection();
	   connection.setSourceAnchor(new ChopboxAnchor(figure1));
	   connection.setTargetAnchor(new ChopboxAnchor(figure2));
	   connection.setSourceDecoration(myPolygonDecoration());
	   connection.setConnectionRouter(new ManhattanConnectionRouter());
	   addTexttoline(connection,text);
	   return connection;
	}
  public Connection connect2(IFigure figure1, IFigure figure2,String text) {
	  FixedAnchor out = new FixedAnchor(figure1);
	  out.place = new Point(0,1);
	  FixedAnchor right = new FixedAnchor(figure2);
	  right.place = new Point(1,2);
	   PolylineConnection connection = new PolylineConnection();
	   connection.setSourceAnchor(out);
	   connection.setTargetAnchor(right);
	   connection.setSourceDecoration(myPolygonDecoration());
	   connection.setConnectionRouter(new ManhattanConnectionRouter());
	   connection.setForegroundColor(ColorConstants.green);
	  
	   addTexttoline(connection,text);
	   return connection;
	}
  public Connection connectbucle(IFigure figure,String text) {
	  FixedAnchor out = new FixedAnchor(figure);
	  out.place = new Point(1,0);
	  FixedAnchor right = new FixedAnchor(figure);
	  right.place = new Point(2,1);
	   PolylineConnection connection = new PolylineConnection();
	   connection.setSourceAnchor(out);
	   connection.setTargetAnchor(right);
	   connection.setSourceDecoration(myPolygonDecoration());
	   connection.setConnectionRouter(new ManhattanConnectionRouter());
	   connection.setForegroundColor(ColorConstants.blue);
	  
	   addTexttoline(connection,text);
	   return connection;
	}
  private PolygonDecoration myPolygonDecoration(){
	  PolygonDecoration deco = new PolygonDecoration();
	  PointList pl = new PointList();
	  pl.addPoint( 0, 0 );
	  pl.addPoint(-2, 2);
	  pl.addPoint(-2,-2);
	  deco.setTemplate( pl );
	 
	  return deco;
	}
	

	/** adds a multiplicity label linked to targte endpoint */
	private void addTexttoline(PolylineConnection c,String text){
	  ConnectionEndpointLocator targetEL = new ConnectionEndpointLocator(c, true);
	  targetEL.setVDistance(10);
	  Label multiplicity = new Label(text);
	  c.add(multiplicity, targetEL);
	}
	private class FixedAnchor extends AbstractConnectionAnchor {
		  Point place;

		  public FixedAnchor(IFigure owner) {
		    super(owner);
		  }

		  public Point getLocation(Point loc) {
		    Rectangle r = getOwner().getBounds();
		    int x = r.x + place.x * r.width / 2;
		    int y = r.y + place.y * r.height / 2;
		    Point p = new PrecisionPoint(x, y);
		    getOwner().translateToAbsolute(p);
		    return p;
		  }
		}



	}