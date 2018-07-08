package edu.csuft.chenbiao.go;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * ��Ϸ����
 * 
 * @author Administrator
 *
 */
public class GameFrame extends JFrame{
	
	/**
	 * ���
	 */
	GamePanel gamepanel;
	
	GameModel model;
	Score score;
	
	public JMenuBar bar;
	public JMenu menu;
	
	public static boolean isfool=true;
	public static boolean ismantoman=false;
	public static boolean isstart=false;
	
	/**
	 * ���췽��:������Ϸ����
	 * @param model 
	 */
	public GameFrame(GameModel model,Score score) {
		
		this.model=model;
		this.score=score;
		//����
		setTitle("������--�±�  ��Ʒ             �����лڰ�");
		//��С
		setSize(616,639);
		setResizable(false);
		//λ��
//		setLocation(200,200);
		setLocationRelativeTo(null);
		
		
		//���ڵĹر�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//����GamePanel
		gamepanel =new GamePanel(model);
		add(gamepanel);
		
		bar=new JMenuBar();
		menu=new JMenu("�Ѷ�");
		JMenu Comp=new JMenu("�ȶ�");
		JMenuItem search=new JMenuItem("��ѯ��ʷ");
		JMenuItem back=new JMenuItem("����");
		JMenuItem restart=new JMenuItem("�ؿ�һ��");
		JMenuItem quit=new JMenuItem("�˳�");
		JMenu Model=new JMenu("ģʽ");
		JMenu help=new JMenu("����");
		JMenu sp=new JMenu("��ʼ/��ͣ");
		JMenu Mquit=new JMenu("�˳�");
		JCheckBoxMenuItem start=new JCheckBoxMenuItem("��ʼ",true);
		sp.add(start);
		JRadioButtonMenuItem mantoman=new JRadioButtonMenuItem("���˶�ս");
		JRadioButtonMenuItem mantomachine=new JRadioButtonMenuItem("�˻���ս",true);
		ButtonGroup gp=new ButtonGroup();
		gp.add(mantomachine);
		gp.add(mantoman);
		help.add(back);
		help.add(restart);
		Mquit.add(quit);
		Comp.add(search);
		bar.add(menu);
		Model.add(mantomachine);
		Model.add(mantoman);
		bar.add(Model);
		bar.add(Comp);
		bar.add(help);
		bar.add(sp);
		bar.add(Mquit);
		JMenuItem fool=new JMenuItem("ɵ���Ѷ�");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		mantoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mantoman.isSelected()) {
					ismantoman=true;
				}
			}
			
		});
		mantomachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mantomachine.isSelected()) {
					ismantoman=false;
				}
			}
			
		});
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamepanel.Restart();
				GameModel.gameover=false;
				GameModel.over=false;
			}
			
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!ismantoman)
					gamepanel.Reback1();
				else
					gamepanel.Reback2();
			}
			
		});
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!GameModel.over)
					GameModel.gameover = !GameModel.gameover;
				isstart=!isstart;
				if(isstart)
					start.setText("��ͣ");
				else
					start.setText("��ʼ");
			}
			
		});
		menu.add(fool);
		add(bar,BorderLayout.NORTH);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.input();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				score.show(model.str);
			}
			
		});
		
		//��ʾ
		setVisible(true);
		int i=JOptionPane.showConfirmDialog(this, "׼���ÿ�ʼ����","��ʼ��ʾ",0);
		if(i!=0)
		{
			GameModel.gameover=true;
			start.setSelected(false);
			start.setText("��ͣ");
			isstart=!isstart;
		}
	}
}
