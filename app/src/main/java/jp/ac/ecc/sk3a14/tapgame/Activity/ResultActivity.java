package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * 結果画面を表示するActivity
 */
public class ResultActivity extends AppCompatActivity {

    //トップへ戻るボタン
    private Button mButton;
	private TextView mScoreTextView;
	private Timer mTimer;
	private Handler mHandler;
	private ImageView mRankImage;
	
	private int mScore;
	private String UserName;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

		mHandler = new Handler();
        //XML紐付け
        mButton = (Button)findViewById(R.id.btn);
		mScoreTextView = (TextView)findViewById(R.id.score);
		mRankImage = (ImageView)findViewById(R.id.rankImageView);
		
		
		mScore = getIntent().getIntExtra("score", 1000); //スコアの取得
		mScoreTextView.setText("" + mScore);
		
		if(mScore >= 110){
			
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.s));
		}else if (mScore >= 80){
			//A
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.a));
		}else if (mScore >= 50){
			//B
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.b));
		}else if (mScore >= 30) {
			//C
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.c));
		}else {
			mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.d));
		}
        //トップへボタンのクリックイベント
        mButton.setOnClickListener(v -> {
                //トップ画面へ遷移
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), TitleActivity.class);
                startActivity(intent);
        });
	    
    }
    
}
