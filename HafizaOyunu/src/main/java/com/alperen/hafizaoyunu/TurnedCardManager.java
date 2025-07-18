package com.alperen.hafizaoyunu;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.*;
public class TurnedCardManager implements ActionListener
{
	
	private Vector turnedCards;			
	private TurnCounterLabel turnCounterLabel;
	private Timer turnDownTimer;				
	private final int turnDownDelay = 2000;
	private Component mainFrame; 	
	
	private int kartBitti=0;
	public TurnedCardManager(TurnCounterLabel turnCounterLabel)
	{
		
		this.turnCounterLabel = turnCounterLabel;
		// Liste olu�tur
		this.turnedCards = new Vector (2);
		// Zamanlay�c� nesnesi
		this.turnDownTimer = new Timer(this.turnDownDelay, this);
		this.turnDownTimer.setRepeats(false);
		
	}
	
	
	private boolean doAddCard(Card card)
	{
		// Kart Listesi ekle
		this.turnedCards.add(card);
		
		if(this.turnedCards.size() == 2)
		{
					
			this.turnCounterLabel.increment();
			
			Card otherCard = (Card)this.turnedCards.get(0);
			
			if( otherCard.getNum() == card.getNum()) {
				
				this.turnedCards.clear();
				
			}	
			else this.turnDownTimer.start();
		}
		
		
		return true;
	}
	
	
	public boolean turnUp(Card card)
	{

		if(this.turnedCards.size() < 2) return doAddCard(card);

		return false;
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		//Kartlar� geri �evir
		for(int i = 0; i < this.turnedCards.size(); i++ )
		{
			Card card = (Card)this.turnedCards.get(i);
			card.turnDown();
		}
		// Kartlar� unut
		this.turnedCards.clear();
	}

	
		
}