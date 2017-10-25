package MoViCo;

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class ActivityFigure extends Figure {
	  public Rectangle r = new Rectangle();

	  public Hashtable targetAnchors = new Hashtable();

	  public Hashtable sourceAnchors = new Hashtable();

	  public String message = new String();
	  /**
	   * Setea el nombre que tendr� la caja o figure del diagrama
	   * @param msg
	   */
	  public void setName(String msg) {
	    message = msg;
	    repaint();
	  }
	  public String getName () {
		  return message;
	  }
	  
	  public ConnectionAnchor ConnectionAnchorAt(Point p) {
	    ConnectionAnchor closest = null;
	    long min = Long.MAX_VALUE;
	    Hashtable conn = getSourceConnectionAnchors();
	    conn.putAll(getTargetConnectionAnchors());
	    Enumeration e = conn.elements();
	    while (e.hasMoreElements()) {
	      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
	      Point p2 = c.getLocation(null);
	      long d = p.getDistance2(p2);
	      if (d < min) {
	        min = d;
	        closest = c;
	      }
	    }
	    return closest;
	  }

	  public ConnectionAnchor getSourceConnectionAnchor(String name) {
	    return (ConnectionAnchor) sourceAnchors.get(name);
	  }

	  public ConnectionAnchor getTargetConnectionAnchor(String name) {
	    return (ConnectionAnchor) targetAnchors.get(name);
	  }

	  public String getSourceAnchorName(ConnectionAnchor c) {
	    Enumeration e = sourceAnchors.keys();
	    String name;
	    while (e.hasMoreElements()) {
	      name = (String) e.nextElement();
	      if (sourceAnchors.get(name).equals(c))
	        return name;
	    }
	    return null;
	  }

	  public String getTargetAnchorName(ConnectionAnchor c) {
	    Enumeration e = targetAnchors.keys();
	    String name;
	    while (e.hasMoreElements()) {
	      name = (String) e.nextElement();
	      if (targetAnchors.get(name).equals(c))
	        return name;
	    }
	    return null;
	  }

	  public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
	    ConnectionAnchor closest = null;
	    long min = Long.MAX_VALUE;
	    Enumeration e = getSourceConnectionAnchors().elements();
	    while (e.hasMoreElements()) {
	      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
	      Point p2 = c.getLocation(null);
	      long d = p.getDistance2(p2);
	      if (d < min) {
	        min = d;
	        closest = c;
	      }
	    }
	    return closest;
	  }

	  public Hashtable getSourceConnectionAnchors() {
	    return sourceAnchors;
	  }

	  public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
	    ConnectionAnchor closest = null;
	    long min = Long.MAX_VALUE;
	    Enumeration e = getTargetConnectionAnchors().elements();
	    while (e.hasMoreElements()) {
	      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
	      Point p2 = c.getLocation(null);
	      long d = p.getDistance2(p2);
	      if (d < min) {
	        min = d;
	        closest = c;
	      }
	    }
	    return closest;
	  }

	  public Hashtable getTargetConnectionAnchors() {
	    return targetAnchors;
	  }
	}