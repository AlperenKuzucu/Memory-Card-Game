package com.alperen.hafizaoyunu;
import javax.swing.JLabel;

public class TurnCounterLabel extends JLabel
{
	
	private int numTurns = 0;
	private final String DESCRIPTION = "D�n��: ";
	
	
	private void update()
	{
		setText(DESCRIPTION + Integer.toString(this.numTurns));
	}
	
	
	public TurnCounterLabel()
	{
		super();
		reset();
	}
	
	
	public void increment()
	{
		this.numTurns++;
		update();
	}
	
	
	public void reset()
	{
		this.numTurns = 0;
		update();
	}
	
}