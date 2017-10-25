package MoViCo;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class TerminatorFigure extends ActivityFigure {
	  public FixedAnchor inAnchor, outAnchor;

	  public TerminatorFigure() {
	    inAnchor = new FixedAnchor(this);
	    inAnchor.place = new Point(1, 0);
	    targetAnchors.put("in_term", inAnchor);
	    outAnchor = new FixedAnchor(this);
	    outAnchor.place = new Point(1, 2);
	    sourceAnchors.put("out_term", outAnchor);
	  }

	  public void paintFigure(Graphics g) {
	    Rectangle r = bounds;
	    g.drawArc(r.x + r.width / 8, r.y, r.width / 4, r.height - 1, 90, 180);
	    g.drawLine(r.x + r.width / 4, r.y, r.x + 3 * r.width / 4, r.y);
	    g.drawLine(r.x + r.width / 4, r.y + r.height - 1,
	        r.x + 3 * r.width / 4, r.y + r.height - 1);
	    g.drawArc(r.x + 5 * r.width / 8, r.y, r.width / 4, r.height - 1, 270,
	        180);
	    g.drawText(message, r.x + 3 * r.width / 8, r.y + r.height / 8);
	  }
	}
