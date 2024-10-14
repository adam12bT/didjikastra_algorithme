package didjikastra_algorithme;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;


public class node extends JButton {
	node parent;
	int row;
	int col;
	boolean solide;
	boolean start;
	boolean goal;
	boolean open;
	boolean visisted;
	public node(int col,int row) {
		this.col=col;
		this.row=row;
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		//addActionListener(this);
	}
	public void setastar() {
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
		setText("start");
		start=true;
	}
	public void setagoal() {
		setBackground(Color.YELLOW);
		setForeground(Color.WHITE);
		setText("goal");
		goal=true;
	}
	public void setasolide() {
	    setBackground(Color.BLACK);
	    setForeground(Color.BLACK);
	
		solide=true;
	}
	public void setasopen() {
		open=true; 
	}
	public void setvisited() {
		if(start==false && goal ==false) {
			setBackground(Color.ORANGE);
			setForeground(Color.BLACK);

		}
		visisted=true;
	}
	public void setaspath() {
		setBackground(Color.green);
		setForeground(Color.BLACK);
	}
	public void actionPerformed(ActionEvent e) {
		 setasolide(); // Set the node as "solid."
		

		
	}



	

}



