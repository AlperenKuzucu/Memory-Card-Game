package com.alperen.hafizaoyunu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Haf�zaOyunu implements ActionListener
{
	
	private JFrame mainFrame;					
	private Container mainContentPane;			
	private TurnCounterLabel turnCounterLabel;
	private ImageIcon cardIcon[]; // 0-7 aras� resimler 8. ise kart arkas�
	private String ResimDizini="futbol";
	
	
	private static void newMenuItem(String text, JMenu menu, ActionListener listener)
	{
		JMenuItem newItem = new JMenuItem(text);
		newItem.setActionCommand(text);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}
	
	
	private ImageIcon[] loadCardIcons()
	{
		// Resimler i�in dizi
		ImageIcon icon[] = new ImageIcon[9];
		
		for(int i = 0; i < 9; i++ )
		{
			// Dosyadan al�nan resmi ikon a atay�n
			String fileName = "resimler/"+ResimDizini+"/card" + i + ".jpg";
			icon[i] = new ImageIcon(fileName);
			//Resim uyu�mazsa error
			if(icon[i].getImageLoadStatus() == MediaTracker.ERRORED)
			{
				
				JOptionPane.showMessageDialog(this.mainFrame
					, "The image " + fileName + " could not be loaded."
					, "Haf�za Oyunu Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		return icon;
	}

	
	public Haf�zaOyunu()
	{
		// Oyun Ekran�
		this.mainFrame = new JFrame("Haf�za Oyunu");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(900,900);
		this.mainContentPane = this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS)); 
		
		this.turnCounterLabel = new TurnCounterLabel();
		
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		// Men�
		JMenu gameMenu = new JMenu("Men�");
		menuBar.add(gameMenu);
		newMenuItem("Yeni Oyun", gameMenu, this);
		newMenuItem("��k��", gameMenu, this);


		this.cardIcon = loadCardIcons(); 
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		dprintln("actionPerformed " + e.getActionCommand());
		if(e.getActionCommand().equals("Yeni Oyun")) yeniOyun();
		else if(e.getActionCommand().equals("��k��")) System.exit(0);
	}
	
	
	static public void dprintln( String message )
	{
		//System.out.println( message );
	}
	
	
	public static void randomizeIntArray(int[] a)
	{
		Random randomizer = new Random();
		// Dizi yineleme
		for(int i = 0; i < a.length; i++ )
		{
			// Takas etme
			int d = randomizer.nextInt(a.length);
			// swap the entries
			int t = a[d];
			a[d] = a[i];
			a[i] = t;
		}
	}
	
	
	public JPanel makeCards()
	{
		// 4x4 l�k panel
		JPanel panel = new JPanel(new GridLayout(4, 4));
		
		TurnedCardManager turnedManager = new TurnedCardManager(this.turnCounterLabel);
		// T�m kartlar�n arkas�na ayn� resmi ata
		ImageIcon backIcon = this.cardIcon[8];
		
		//Dizi kartlar�na numara ver 0,0-1-1 ...
		int cardsToAdd[] = new int[16];
		for(int i = 0; i < 8; i++)
		{
			cardsToAdd[2*i] = i;
			cardsToAdd[2*i + 1] = i;
		}
		// Random
		randomizeIntArray(cardsToAdd);
		
		// Her Kart� nesne yap
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// 0-7 aras� random say�
			int num = cardsToAdd[i];
			// Kart Nesnesi 
			Card newCard = new Card(turnedManager, this.cardIcon[num], backIcon, num);
			// Panele kartlar� ekle
			panel.add(newCard);
		}
		
		return panel;
	}
	
	
	public void yeniOyun()
	{
		// D�n��� s�f�rla
		this.turnCounterLabel.reset();
		
		this.mainContentPane.removeAll();
		
        this.mainContentPane.add(makeCards());
		
		this.mainContentPane.add(this.turnCounterLabel);
		// �lk oyunda penceryi g�ster
		this.mainFrame.setVisible(true);
	}
}

