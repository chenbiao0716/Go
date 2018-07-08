package edu.csuft.chenbiao.go;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 画板（画布）
 * 
 * @author Administrator
 *
 */
public class GamePanel extends JPanel {

	GameModel model;


	Piece piece1 = null;
	/**
	 * 内部类：鼠标的监听器
	 * 
	 * @author Administrator
	 *
	 */
	class Listener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
			super.mouseClicked(e);
			
			if (!GameModel.gameover) {
				if (!GameFrame.ismantoman) {
					int x = e.getX();
					int y = e.getY();
					for (int i = 0; i < 9; i++) {
						if (x <= (62 + 65 * i) && x >= (16 + 65 * i)) {
							for (int n = 0; n < 9; n++) {
								if (y <= (54 + 63 * n) && y >= (24 + 63 * n)) {
									piece1 = new Piece(e.getX(), e.getY());
								}
							}
						}
					}

					if (piece1 != null) {
						if (notExist(piece1)) {
							pieceList.add(piece1);
							model.update(piece1);
							repaint();
							if (model.isBlackWin() || model.isWhiteWin()) {
								model.result();
							}
							if(!GameModel.gameover) {
							Piece piece2 = new Piece(piece1);
							while (true) {
									if (notExist(piece2)) {
										piece2.isBlack = false;
										pieceList.add(piece2);
										model.update(piece2);

										if (model.isBlackWin() || model.isWhiteWin()) {
											model.result();
										}

										// 画布的重绘
										repaint();
										break;
									} else
										piece2 = change(piece2);
								}
							}
						} else
							return;

					}
				}
				// 人机人人： 人人
				else {
					Piece piece1 = null;
					int x = e.getX();
					int y = e.getY();
					for (int i = 0; i < 9; i++) {
						if (x <= (62 + 65 * i) && x >= (16 + 65 * i)) {
							for (int n = 0; n < 9; n++) {
								if (y <= (54 + 63 * n) && y >= (24 + 63 * n)) {
									piece1 = new Piece(e.getX(), e.getY());
								}
							}
						}
					}

					if (piece1 != null) {
						if (notExist(piece1)) {
							piece1.isBlack = pieceList.size() % 2 == 0;
							pieceList.add(piece1);
							model.update(piece1);
							repaint();
						}
					}
				}

			}
			// 游戏结束
			else
				return;
		}
	}

	/**
	 * 棋盘
	 */
	Image bg;

	/**
	 * 列表：存储所有的旗子
	 */
	ArrayList<Piece> pieceList = new ArrayList<>();

	/**
	 * GamePanel构造方法
	 * 
	 * @param model
	 */
	public GamePanel(GameModel model) {
		this.model = model;
		try {
			bg = ImageIO.read(new File("res/go.png"));

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		// 注册事件（鼠标事件）监听器
		addMouseListener(new Listener());
	}

	/**
	 * 绘制
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		// 有更多的参数可以设置
		Graphics2D g = (Graphics2D) graphics;
		// 设置渲染参数 抗锯齿
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 绘制棋盘
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

		// 绘制棋子
		// g.fillOval(piece.x, piece.y, 50, 50);
		// piece.paint(g);
		for (Piece piece : pieceList)
			piece.paint(g);
	}

	/**
	 * 是否存在棋子
	 * 
	 * @param p
	 * @return
	 */
	public boolean notExist(Piece p) {

		if (pieceList.isEmpty())
			return true;
		for (int i = 0; i < pieceList.size(); i++) {
			if (p.equals(pieceList.get(i)))
				return false;
		}
		return true;
	}

	/**
	 * 改变白子位置
	 * 
	 * @param p
	 */
	public Piece change(Piece p) {
		int x = (int) ((Math.random() - 0.5) * 4);
		int y = (int) ((Math.random() - 0.5) * 4);

		if ((p.x + x * 65 > 0) && (p.x + x * 65 < 560) && (p.y + y * 63 > 0) && (p.y + y * 63 < 560))
			p = new Piece(p.x + x * 65, p.y + y * 63);
		return p;
	}

	public void Restart() {
		pieceList.clear();
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				model.data[i][j]=0;
		repaint();
	}
	
	public void Reback1() {
		if(GameModel.over)
			return ;
		int i=pieceList.size();
		
		//设置二维数组为零
		Piece p=pieceList.get(--i);
		model.data[p.j][p.i]=0;
		//删除最后一颗棋子
		pieceList.remove(i);
		
		//设置二维数组为零
		Piece c=pieceList.get(--i);
		model.data[c.j][c.i]=0;
		//删除最后一颗棋子
		pieceList.remove(i);
		repaint();
	}
	public void Reback2() {
		if(GameModel.over)
			return ;
		int i=pieceList.size();
		//设置二维数组为零
		Piece a=pieceList.get(--i);
		model.data[a.j][a.i]=0;
		//删除最后一颗棋子
		pieceList.remove(i);
		repaint();
	}
}