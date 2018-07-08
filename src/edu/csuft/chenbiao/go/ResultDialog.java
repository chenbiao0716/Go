package edu.csuft.chenbiao.go;

import javax.swing.*;

public class ResultDialog extends JDialog {
	
	public JLabel lab;
	
	public ResultDialog() {
		super(new JFrame(),"½á¹û",true);
		this.setSize(300,80);
		this.setLocationRelativeTo(null);
		lab=new JLabel("",JLabel.CENTER);
		this.add(lab);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
	}
	public void show(String a) {
		lab.setText(a);
		this.setVisible(true);
	}
}
