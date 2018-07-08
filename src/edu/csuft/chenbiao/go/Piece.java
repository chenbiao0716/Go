package edu.csuft.chenbiao.go;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 旗子
 * 
 * @author Administrator
 *
 */
public class Piece {

	GameModel model;
	
	
	int i,j;
	/**
	 * x 坐标
	 */
	int x;
	
	/**
	 * y坐标
	 */
	int y;
	
	/**
	 * 大小（直径）
	 */
	int size=50;
	
	/**
	 * 颜色 ：默认为黑
	 */
	Boolean isBlack=true;

	/**
	 * 创建旗子
	 * 
	 * @param x  x坐标
	 * @param y  y坐标
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
	 * 绘制
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		//设置画笔颜色
		g.setColor(isBlack?Color.black:Color.white);
		//画圆
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
