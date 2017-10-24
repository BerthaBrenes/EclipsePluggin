package MoViCo;

import org.eclipse.draw2d.IFigure;

class FigureFactory {
	  public static IFigure createTerminatorFigure() {
	    return new TerminatorFigure();
	  }

	  public static IFigure createDecisionFigure() {
	    return new DecisionFigure();
	  }

	  public static IFigure createProcessFigure() {
	    return new ProcessFigure();
	  }

	  public static PathFigure createPathFigure() {
	    return new PathFigure();
	  }

	  public static ChartFigure createChartFigure() {
	    return new ChartFigure();
	  }
	}