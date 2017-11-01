package src.MoViCo;

import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;

public class PathFigure extends PolylineConnection {
	  public PathFigure() {
	    setTargetDecoration(new PolylineDecoration());
	    setConnectionRouter(new ManhattanConnectionRouter());
	  }
	}