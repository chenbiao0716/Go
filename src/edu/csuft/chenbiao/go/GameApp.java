package edu.csuft.chenbiao.go;

/**
 * ��������
 * 
 * @author Administrator
 *
 */
public class GameApp {

	public static void main(String[] args) {
		
		Score score=new Score();
		ResultDialog resultdialog=new ResultDialog();
		//��Ϸ���߼���ģ�ͣ�
		GameModel gameModel=new GameModel(resultdialog);
		
		// ��Ϸ�Ĵ��ڣ����桢��ͼ
		GameFrame gameframe=new GameFrame(gameModel,score);

	}

}
