package edu.csuft.chenbiao.go;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * ���壨������
 * 
 * @author Administrator
 *
 */
public class GamePanel extends JPanel {

	GameModel model;


	Piece piece1 = null;
	/**
	 * �ڲ��ࣺ���ļ�����
	 * 
	 * @author Administrator
	 *
	 */
	class Listener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO �Զ����ɵķ������
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

										// �������ػ�
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
				// �˻����ˣ� ����
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
			// ��Ϸ����
			else
				return;
		}
	}

	/**
	 * ����
	 */
	Image bg;

	/**
	 * �б��洢���е�����
	 */
	ArrayList<Piece> pieceList = new ArrayList<>();

	/**
	 * GamePanel���췽��
	 * 
	 * @param model
	 */
	public GamePanel(GameModel model) {
		this.model = model;
		try {
			bg = ImageIO.read(new File("res/go.png"));

		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

		// ע���¼�������¼���������
		addMouseListener(new Listener());
	}

	/**
	 * ����
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		// �и���Ĳ�����������
		Graphics2D g = (Graphics2D) graphics;
		// ������Ⱦ���� �����
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// ��������
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

		// ��������
		// g.fillOval(piece.x, piece.y, 50, 50);
		// piece.paint(g);
		for (Piece piece : pieceList)
			piece.paint(g);
	}

	/**
	 * �Ƿ��������
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
	 * �ı����λ��
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
		
		//���ö�ά����Ϊ��
		Piece p=pieceList.get(--i);
		model.data[p.j][p.i]=0;
		//ɾ�����һ������
		pieceList.remove(i);
		
		//���ö�ά����Ϊ��
		Piece c=pieceList.get(--i);
		model.data[c.j][c.i]=0;
		//ɾ�����һ������
		pieceList.remove(i);
		repaint();
	}
	public void Reback2() {
		if(GameModel.over)
			return ;
		int i=pieceList.size();
		//���ö�ά����Ϊ��
		Piece a=pieceList.get(--i);
		model.data[a.j][a.i]=0;
		//ɾ�����һ������
		pieceList.remove(i);
		repaint();
	}
}