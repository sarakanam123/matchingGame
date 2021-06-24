package views;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.cardController;
import models.Card;

public class matchingGame implements ActionListener{
	private JFrame mainFrame;
	private Container mainContentPane;
	private ImageIcon cardIcon[];

	public matchingGame(){
		//main window
		this.mainFrame=new JFrame("Matching Game");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(400,500);
		this.mainContentPane=this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane,BoxLayout.PAGE_AXIS));
		//menu bar;
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		//game menu
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		//we need to create generic submenu creater
		newMenuItem("New Game",gameMenu,this);
		newMenuItem("Exit",gameMenu,this);
		//About menu
		/*JMenu aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		newMenuItem("Help",aboutMenu,this);
		newMenuItem("About",aboutMenu,this);
		newMenuItem("etc..",aboutMenu,this);*/
		// load cards
		
		this.cardIcon = loadCardIcons();
		
		
	}
	
	
	private ImageIcon[] loadCardIcons() {
		// TODO Auto-generated method stub
		ImageIcon icon[]=new ImageIcon[9];
		for(int i=0;i<9;i++){
			String fileName="cards/card"+i+".jpg";
			icon[i]=new ImageIcon(fileName);
		}
		return icon;
	}
	public JPanel makeCards(){
		JPanel panel = new JPanel(new GridLayout(4,4));
		ImageIcon backIcon=this.cardIcon[8];
		cardController controller = new cardController();
		int cardsToAdd[]=new int[16];
		for(int i=0;i<8;i++){
			cardsToAdd[2*i]=i;
			cardsToAdd[2*i+1]=i;
		}
		
		randomizeCardArray(cardsToAdd);
		
		for(int i=0;i<cardsToAdd.length;i++){
			int num = cardsToAdd[i];
			Card newCard=new Card(controller,this.cardIcon[num],backIcon,num);
			panel.add(newCard);
		}
		return panel;
	}


	private void randomizeCardArray(int[] t) {
		// TODO Auto-generated method stub
		Random randomizer = new Random();
		for(int i=0;i<t.length;i++){
			int d=randomizer.nextInt(t.length);
			int s=t[d];
			t[d]=t[i];
			t[i]=s;
		}
		
	}


	private void newMenuItem(String string, JMenu menu,ActionListener listener) {
		// TODO Auto-generated method stub
		JMenuItem newItem = new JMenuItem(string);
		newItem.setActionCommand(string);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}


	public void newGame(){
		//show main window
		this.mainContentPane.removeAll();
		this.mainContentPane.add(makeCards());
		this.mainFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("New Game")) newGame();
		if(arg0.getActionCommand().equals("Exit")) System.exit(0);
		
	}
}
