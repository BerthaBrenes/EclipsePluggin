package src.parseoast.views;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class genLabel {
	private String Text;
	private int posx;
	private int posy;
	private Composite parent;
	
	public Composite getParent() {
		return parent;
	}
	public void setParent(Composite parent) {
		this.parent = parent;
	}
	public genLabel (String Texto, int posx, int posy,Composite parent) {
		setText(Texto);
		setPosx(posx);
		setPosy(posy);
		setParent(parent);
	}
	public abstract Label label ();
	
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	
}
