package edu.csuft.chenbiao.go;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * ����
 * 
 * @author Administrator
 *
 */
public class Piece {

	GameModel model;
	
	
	int i,j;
	/**
	 * x ����
	 */
	int x;
	
	/**
	 * y����
	 */
	int y;
	
	/**
	 * ��С��ֱ����
	 */
	int size=50;
	
	/**
	 * ��ɫ ��Ĭ��Ϊ��
	 */
	Boolean isBlack=true;

	/**
	 * ��������
	 * 
	 * @param x  x����
	 * @param y  y����
	 */
	public Piece(int x, int y) {
		super();
		
		i=x/65;
		j=y/63;
		
		this.x=39+i*65;
		this.y=36+j*63;
		
		this.x = this.x-(size/2);
		this.y = this.y-(size/2);
	}

	public Piece(Piece piece1) {
		this.x=piece1.x;
		this.y=piece1.y;
	}

	/**
	 * ����
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		//���û�����ɫ
		g.setColor(isBlack?Color.black:Color.white);
		//��Բ
//		int i=model.i;
//		int j=model.j;
//		if(model.data[i][j]==0)
			g.fillOval(x, y, size, size);
		
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return x == piece.x &&
                y == piece.y;
    }
}
