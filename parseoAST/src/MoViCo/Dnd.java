package MoViCo;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class Dnd extends MouseMotionListener.Stub implements MouseListener {
	  public Dnd(IFigure figure) {
	    figure.addMouseMotionListener(this);
	    figure.addMouseListener(this);
	  }

	  Point start;

	  public void mouseReleased(MouseEvent e) {
	  }

	  public void mouseClicked(MouseEvent e) {
	  }

	  public void mouseDoubleClicked(MouseEvent e) {
	  }

	  public void mousePressed(MouseEvent e) {
	    start = e.getLocation();
	  }

	  public void mouseDragged(MouseEvent e) {
	    Point p = e.getLocation();
	    Dimension d = p.getDifference(start);
	    start = p;
	    Figure f = ((Figure) e.getSource());
	    f.setBounds(f.getBounds().getTranslated(d.width, d.height));
	  }
	}