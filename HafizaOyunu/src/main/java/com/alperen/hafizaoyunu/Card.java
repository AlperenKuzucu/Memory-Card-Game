package com.alperen.hafizaoyunu;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

public class Card extends JLabel implements MouseListener
{
	TurnedCardManager turnedManager;
	Icon onYuz;
	Icon arkaYuz;
	boolean faceUp = false; 			// Ba�lang��ta kart kapal�
	int num; 							
	int iconWidthHalf, iconHeightHalf; 	
	boolean mousePressedOnMe = false;
	
	
	
	public Card(TurnedCardManager turnedManager, Icon �n, Icon arka, int num)
	{
		// Ba�lang��ta kart�n arkas�n� g�ster
		super(arka);
		
		this.turnedManager = turnedManager;
		this.onYuz = �n;
		this.arkaYuz = arka;
		this.num = num;
		// Mouse Tiklerini almak i�in:
		this.addMouseListener(this);
		
		this.iconWidthHalf = arka.getIconWidth() / 2;
		this.iconHeightHalf = arka.getIconHeight() / 2;
	}
		
	
	public void turnUp()
	{
		
		 
		// Kart Zaten a��ksa 
		if(this.faceUp) return;
		// kart�n d�nmesi i�in izin
		this.faceUp = this.turnedManager.turnUp(this);
		// Kart D�nd�r�ld� resmi de�i�tir
		if(this.faceUp) this.setIcon(this.onYuz);
	}
	
	
	public void turnDown()
	{
		
		// Kart zaten kapal�
		if(!this.faceUp) return;
		// Resmi de�i�tir
		this.setIcon(this.arkaYuz);
		//Kart �imdi kapal�
		this.faceUp = false;
	}
	
	
	public int getNum() { return num; }
	
	
	private boolean overIcon(int x, int y)
	{
		// Hesaplama
		int distX = Math.abs(x - (this.getWidth() / 2));
		int distY = Math.abs(y - (this.getHeight() / 2));
		// D�� simge b�lgesi
		if(distX > this.iconWidthHalf || distY > this.iconHeightHalf )
			return false;
		// �� simge b�lgesi
		return true;
	}
	
	
	public void mouseClicked(MouseEvent e)
	{
		// Mouse Simgenin �st�nde kart� a�
		if(overIcon(e.getX(), e.getY())) this.turnUp();
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		// Mouse tikini al
		if(overIcon(e.getX(), e.getY())) this.mousePressedOnMe = true;
	}
	
	
	public void mouseReleased(MouseEvent e)
	{
		if(this.mousePressedOnMe)
		{
			//Mouse art�k bas�l� de�il
			this.mousePressedOnMe = false;
			// Mouse Tiki
			this.mouseClicked(e);
		}
		
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	
	public void mouseExited(MouseEvent e)
	{
		// �nceki Mouse Tiklerini unut
		this.mousePressedOnMe = false;
	}
		
	
    
}