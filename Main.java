import java.util.Random;

public class Main implements Runnable{
	
	// Frameクラスのインスタンス
	Frame frame;
	// 移動方向
	int dx[] = {0, -1, 1, 0};
	int dy[] = {1, 0, 0, -1};
	// 移動速度
	int speed = 3;
	
	// メイン関数
	public static void main(String[] args) {
		new Main();
	}
	
	// コンストラクタ
	public Main() {
		// インスタンスの作成
		frame = new Frame();
		// スレッドの作成
		Thread thread = new Thread(this);
		thread.start();
	}
	
	// メインループ
	public void run() {
		// 移動完了フラグ
		boolean isMoved = true;
		// 移動方向のインデックス
		int direction = 0;
		// 現在の移動量
		int step = 0;
		// 目的地への移動量
		int nowStep = 0;
		// 移動方向
		int mx = 0, my = 0;

		// メインループ
		while(true) {
			try {
				// ドラッグアンドドロップされたとき
				if(frame.IsDrug()) {
					isMoved = true;
				}
				else {
					// 移動完了したとき
					if (isMoved) {
						// 移動方向をランダムで決める
						direction = new Random().nextInt(4);
						// 移動量をランダムで決める
						// +1 で 0 が入らないようにする
						// *3 で 3っ歩ずつ動くようにする
						step = (new Random().nextInt(10)+1)*3;
						// 現在の移動量をリセット
						nowStep = 0;
						// 移動方向を代入
						mx = dx[direction] * speed;
						my = dy[direction] * speed;
						// 移動完了フラグを False に
						isMoved = false;
					}
					else {
						// 移動量に達していいない時
						if (nowStep < step) {
							// 画面外か判定させる
							if(frame.outScreen(mx, my)) isMoved = true;
							// 移動処理の呼び出し
							frame.move(mx, my, (nowStep++ + 1)%3, direction);
						}
						else {
							// 移動完了フラグを True に
							isMoved = true;
						}
					}
				}
				// 描画の更新
				frame.PanelRepaint();
				// 50ms 待機
				Thread.sleep(50);
			} catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
}