package src.MoViCo;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class DecisionFigure extends ActivityFigure {
	  public FixedAnchor inAnchor, yesAnchor, noAnchor;
	  /**
	   * Genera las Figuras de decisi�n que tendr� ya sea de verdad o falso
	   */
	  public DecisionFigure() {
	    inAnchor = new FixedAnchor(this);
	    inAnchor.place = new Point(1, 0);
	    targetAnchors.put("in_dec", inAnchor);
	    noAnchor = new FixedAnchor(this);
	    noAnchor.place = new Point(2, 1);
	    sourceAnchors.put("no", noAnchor);
	    yesAnchor = new FixedAnchor(this);
	    yesAnchor.place = new Point(1, 2);
	    sourceAnchors.put("yes", yesAnchor);
	  }
	  /**
	   * Pinta la figura en el canvas
	   * 
	   */
	  public void paintFigure(Graphics g) {
	    Rectangle r = bounds;
	    PointList pl = new PointList(4);
	    pl.addPoint(r.x + r.width / 2, r.y);
	    pl.addPoint(r.x, r.y + r.height / 2);
	    pl.addPoint(r.x + r.width / 2, r.y + r.height - 1);
	    pl.addPoint(r.x + r.width, r.y + r.height / 2);
	    g.drawPolygon(pl);
	    g.drawText(message, r.x + r.width / 4 + 5, r.y + 3 * r.height / 8);
	    g.drawText("No", r.x + 6 * r.width / 8, r.y + 3 * r.height / 8);
	    g.drawText("Si", r.x + r.width / 2 - 2, r.y + 3 * r.height / 4);
	  }
	}