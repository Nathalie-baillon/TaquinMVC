package Vue;

import javax.swing.Icon;
import javax.swing.JButton;


public class JButtonPosition extends JButton
{
	private int i;
	private int j;
	public JButtonPosition(int i,int j)
		{
		super();
		this.i=i;
		this.j=j;
		}
	public JButtonPosition(Icon ic,int i,int j)
		{
		super(ic);
		this.i=i;
		this.j=j;
		}
	
	
	public int getI()
	{
		return i;
	}
	
	public int getJ()
	{
		return j;
	}
}

