package src.MoViCo;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ProcessFigure extends ActivityFigure {
	  public FixedAnchor inAnchor, outAnchor;

	  public ProcessFigure() {
	    inAnchor = new FixedAnchor(this);
	    inAnchor.place = new Point(1, 0);
	    targetAnchors.put("in_proc", inAnchor);
	    outAnchor = new FixedAnchor(this);
	    outAnchor.place = new Point(1, 2);
	    sourceAnchors.put("out_proc", outAnchor);
	  }

	  public void paintFigure(Graphics g) {
	    Rectangle r = bounds;
	    g.drawText(message, r.x + r.width / 4, r.y + r.height / 4);
	    g.drawRectangle(r.x, r.y, r.width - 1, r.height - 1);
	  }
	}