package edu.csuft.chenbiao.go;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * 游戏窗口
 * 
 * @author Administrator
 *
 */
public class GameFrame extends JFrame{
	
	/**
	 * 面板
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
	 * 构造方法:定义游戏窗口
	 * @param model 
	 */
	public GameFrame(GameModel model,Score score) {
		
		this.model=model;
		this.score=score;
		//标题
		setTitle("五子棋--陈彪  作品             落子有悔版");
		//大小
		setSize(616,639);
		setResizable(false);
		//位置
//		setLocation(200,200);
		setLocationRelativeTo(null);
		
		
		//窗口的关闭
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//创建GamePanel
		gamepanel =new GamePanel(model);
		add(gamepanel);
		
		bar=new JMenuBar();
		menu=new JMenu("难度");
		JMenu Comp=new JMenu("比对");
		JMenuItem search=new JMenuItem("查询历史");
		JMenuItem back=new JMenuItem("悔棋");
		JMenuItem restart=new JMenuItem("重开一局");
		JMenuItem quit=new JMenuItem("退出");
		JMenu Model=new JMenu("模式");
		JMenu help=new JMenu("帮助");
		JMenu sp=new JMenu("开始/暂停");
		JMenu Mquit=new JMenu("退出");
		JCheckBoxMenuItem start=new JCheckBoxMenuItem("开始",true);
		sp.add(start);
		JRadioButtonMenuItem mantoman=new JRadioButtonMenuItem("人人对战");
		JRadioButtonMenuItem mantomachine=new JRadioButtonMenuItem("人机对战",true);
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
		JMenuItem fool=new JMenuItem("傻瓜难度");
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
					start.setText("暂停");
				else
					start.setText("开始");
			}
			
		});
		menu.add(fool);
		add(bar,BorderLayout.NORTH);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.input();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				score.show(model.str);
			}
			
		});
		
		//显示
		setVisible(true);
		int i=JOptionPane.showConfirmDialog(this, "准备好开始了吗？","开始提示",0);
		if(i!=0)
		{
			GameModel.gameover=true;
			start.setSelected(false);
			start.setText("暂停");
			isstart=!isstart;
		}
	}
}
