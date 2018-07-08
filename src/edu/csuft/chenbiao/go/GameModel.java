package edu.csuft.chenbiao.go;

import java.io.*;

/**
 * ��Ϸ���߼�������
 * 
 * @author Administrator
 *
 */
public class GameModel {

	ResultDialog resultdialog;
	
	int i,j;
	public static String str;
	
	public static boolean gameover=false;
	public static boolean over=false;
	
	public GameModel(ResultDialog resultdialog) {
		this.resultdialog=resultdialog;
	}
	
	/**
	 * 9*9�Ķ�ά����
	 */
	int [][] data=new int[9][9];
	
	boolean isE;
	
	/**
	 * ��ʾ����ģ��
	 */
	public void show() {
		System.out.println("------------------------");
		for(int[] row:data) {
			for(int e:row) 
				System.out.print(e+"\t");
			
			System.out.println();
		}
	}

	/**
	 * �����µ�������Ҫ����ģ��
	 * 
	 * @param piece
	 */
	public void update(Piece piece) {
		j=(piece.x+piece.size/2)/65;
		i=(piece.y+piece.size/2)/63;
		
		data[i][j]=piece.isBlack?1:2;
	}
	
	
	/**
	 * �Ƿ�Ӯ��
	 */
	public boolean isBlackWin() {
		for(int i=0;i<5;i++)
			for(int j=0;j<9;j++) {
				if((data[i][j]==data[i+1][j])&&(data[i+1][j]==data[i+2][j])&&(data[i+2][j]==data[i+3][j])&&(data[i+3][j]==data[i+4][j])&&(data[i][j]==1))
					return true;
			}
		for(int i=0;i<9;i++)
				for(int j=0;j<5;j++) {
				if((data[i][j]==data[i][j+1])&&(data[i][j+1]==data[i][j+2])&&(data[i][j+2]==data[i][j+3])&&(data[i][j+3]==data[i][j+4])&&(data[i][j]==1))
					return true;
				}
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++) {
				if((data[i][j]==data[i+1][j+1])&&(data[i+1][j+1]==data[i+2][j+2])&&(data[i+2][j+2]==data[i+3][j+3])&&(data[i+3][j+3]==data[i+4][j+4])&&(data[i][j]==1))
					return true;
				}
		for(int i=0;i<5 ;i++)
			for(int j=8;j>3;j--) {
				if((data[i][j]==data[i+1][j-1])&&(data[i+1][j-1]==data[i+2][j-2])&&(data[i+2][j-2]==data[i+3][j-3])&&(data[i+3][j-3]==data[i+4][j-4])&&(data[i][j]==1))
					return true;
				}
		return false;
	}
	public boolean isWhiteWin() {
		for(int i=0;i<5;i++)
			for(int j=0;j<9;j++) {
				if((data[i][j]==data[i+1][j])&&(data[i+1][j]==data[i+2][j])&&(data[i+2][j]==data[i+3][j])&&(data[i+3][j]==data[i+4][j])&&(data[i][j]==2))
					return true;
			}
		for(int i=0;i<9;i++)
				for(int j=0;j<5;j++) {
				if((data[i][j]==data[i][j+1])&&(data[i][j+1]==data[i][j+2])&&(data[i][j+2]==data[i][j+3])&&(data[i][j+3]==data[i][j+4])&&(data[i][j]==2))
					return true;
				}
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++) {
				if((data[i][j]==data[i+1][j+1])&&(data[i+1][j+1]==data[i+2][j+2])&&(data[i+2][j+2]==data[i+3][j+3])&&(data[i+3][j+3]==data[i+4][j+4])&&(data[i][j]==2))
					return true;
				}
		for(int i=0;i<5;i++)
			for(int j=8;j>3;j--) {
				if((data[i][j]==data[i+1][j-1])&&(data[i+1][j-1]==data[i+2][j-2])&&(data[i+2][j-2]==data[i+3][j-3])&&(data[i+3][j-3]==data[i+4][j-4])&&(data[i][j]==2))
					return true;
				}
		return false;
	}
	
	public void result()
	{
		if (isBlackWin()) {
			resultdialog.show("����Ӯ�ˣ���Ϸ����!");
			gameover=true;
			over=true;
			try {
				record(""+"����Ӯ\n");
				input();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		else
		{
			resultdialog.show("����Ӯ�ˣ���Ϸ������");
			gameover=true;
			over=true;
			try {
				record(""+"����Ӯ\n");
				input();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void record(String s) throws IOException {
		
		FileOutputStream fout=null;
		try {
			fout=new FileOutputStream("score",true);
			
			fout.write(s.getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		fout.close();
	}
	public void input() throws IOException {
		
		FileReader reader = new FileReader("score");
		BufferedReader bReader = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String s = "";
		while ((s =bReader.readLine()) != null) {
			sb.append(s + "\n");
		}
		bReader.close();
		str = sb.toString();

	}
}
