package models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import controller.cardController;

public class Card extends JLabel implements MouseListener{
	Icon faceIcon;
	Icon backIcon;
	boolean faceUp = false;
	int num;
	int iconWidthHalf,iconHeightHalf;
	boolean mousePressedOnMe=false;
	private Object controller;
	
	
	
	
	public Card(cardController controller,Icon face,Icon back,int num){
		super(back);
		this.faceIcon=face;
		this.backIcon = back;
		this.num=num;
		this.addMouseListener(this);
		this.iconHeightHalf=back.getIconHeight()/2;
		this.iconWidthHalf=face.getIconWidth()/2;
		this.controller=controller;
		
		
	}
	public int getNum(){
		return num;
	}
	private boolean overIcon(int x,int y){
		int distX=Math.abs(x-(this.getWidth()/2));
		int distY=Math.abs(y-(this.getHeight()/2));
		if(distX > this.iconHeightHalf||distY>this.iconWidthHalf)
			return false;
		return true;
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(overIcon(arg0.getX(),arg0.getY())) this.turnUp();
		
		
	}



	public void turnUp() {
		// TODO Auto-generated method stub
		if(this.faceUp) return;
		this.faceUp=true;
		this.faceUp=((cardController) this.controller).turnUp(this);
		if(this.faceUp) this.setIcon(this.faceIcon);
		
	}
	public void turnDown() {
		// TODO Auto-generated method stub
		if(!this.faceUp) return;
		this.setIcon(this.backIcon);
		this.faceUp=false;
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.mousePressedOnMe=false;
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(overIcon(arg0.getX(),arg0.getY())) this.mousePressedOnMe=true;
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.mousePressedOnMe){
			this.mousePressedOnMe=false;
			this.mouseClicked(arg0);
		}
		
	}
	

}
