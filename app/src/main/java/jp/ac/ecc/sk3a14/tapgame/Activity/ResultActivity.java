package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.ac.ecc.sk3a14.tapgame.Model.Player;
import jp.ac.ecc.sk3a14.tapgame.MyApplication;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * 結果画面を表示するActivity
 */
public class ResultActivity extends AppCompatActivity {

    //トップへ戻るボタン
    private Button mButton;
	private TextView mScoreTextView;
	private ImageView mRankImage;
	private Handler mHandler;
	
	private static final String PHP_LINK = "http://192.168.179.3/tapgame.php";
	
	private int mScore;
	private String mUserName;
	private Player mPlayer;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
		
		
		mPlayer = MyApplication.getPlayerInstance();
		if (mPlayer == null) { //instanceが取得できていなかったらTopに戻す
			Intent intent = new Intent();
			intent.setClass(getBaseContext(), TitleActivity.class);
			startActivity(intent);
		}
		
		mHandler = new Handler();
		
        //XML紐付け
        mButton = (Button)findViewById(R.id.btn);
		mScoreTextView = (TextView)findViewById(R.id.score);
		mRankImage = (ImageView)findViewById(R.id.rankImageView);
		
		mScore = mPlayer.getScore();
		mUserName = mPlayer.getName();
		
		mScoreTextView.setText(String.valueOf(mScore));
		
		
		if(mScore >= 110){
			
			//Sランクの画像にセット
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.activity_result_img_s));
		}else if (mScore >= 80){
			
			//Aランクの画像にセット
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.activity_result_img_a));
		}else if (mScore >= 50){
			
			//Bランクの画像にセット
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.activity_result_img_b));
		}else if (mScore >= 30) {
			
			//Cランクの画像にセット
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.activity_result_img_c));
		}else {
			
			//Dランクの画像にセット
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.activity_result_img_d));
		}
		
        //トップへボタンのクリックイベント
        mButton.setOnClickListener(v -> {
                //トップ画面へ遷移
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), TitleActivity.class);
                startActivity(intent);
        });
	    
		//結果を送信するボタン押下時
		findViewById(R.id.submit_btn).setOnClickListener(v -> {
			
			Runnable run = () -> {
				try {
					// httpコネクションを確立し、urlを叩いて情報を取得
					URL url = new URL(PHP_LINK);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					
					con.setDoInput(true);
					con.setDoOutput(true); // ←POSTによるデータ送信を可能にします
					
					con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
					con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
					con.setRequestMethod("POST");
					
					PrintStream ps = new PrintStream(con.getOutputStream());
					ps.print("name=" +  mUserName + "&count=" + mScore); //POSTのパラメータを付与する
					ps.close();
					
					con.getInputStream(); //ここでPOST実行
					
					String message;
					if(con.getResponseMessage().equals("OK")){
						message = "結果を転送しました。";
					} else {
						message = "接続に失敗しました。";
					}
					
					// UIスレッドでToastを実行する
					mHandler.post(() -> Toast.makeText(ResultActivity.this, message, Toast.LENGTH_LONG).show());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
			
			new Thread(run).start();
			
		});
    }
	
    
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction()==KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
				
				//戻るキーを押されたらTitleに戻す
				case KeyEvent.KEYCODE_BACK:
					
					Intent intent = new Intent();
					intent.setClass(getBaseContext(), TitleActivity.class);
					startActivity(intent);
					
					// 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
					return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
}
