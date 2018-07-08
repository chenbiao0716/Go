package edu.csuft.chenbiao.go;

/**
 * 程序的起点
 * 
 * @author Administrator
 *
 */
public class GameApp {

	public static void main(String[] args) {
		
		Score score=new Score();
		ResultDialog resultdialog=new ResultDialog();
		//游戏的逻辑（模型）
		GameModel gameModel=new GameModel(resultdialog);
		
		// 游戏的窗口：界面、视图
		GameFrame gameframe=new GameFrame(gameModel,score);

	}

}
