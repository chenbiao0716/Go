package edu.csuft.chenbiao.go;

import javax.swing.*;

public class Score extends JDialog {
	public JTextArea textarea;
	public Score() {
		super(new JFrame(),"�ɼ���¼",true);
		this.setLocation(500, 300);
		this.setSize(300,300);
		textarea=new JTextArea("");
		JScrollPane pan=new JScrollPane(textarea);
		this.add(pan);
	}
	public void show(String s) {
		textarea.setText(s);;
		this.setVisible(true);
	}
}
