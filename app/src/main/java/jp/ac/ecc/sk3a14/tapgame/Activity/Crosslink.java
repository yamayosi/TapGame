package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.easyandroidanimations.library.PuffOutAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;

import jp.ac.ecc.sk3a14.tapgame.Model.Player;
import jp.ac.ecc.sk3a14.tapgame.MyApplication;
import jp.ac.ecc.sk3a14.tapgame.R;

public class Crosslink extends AppCompatActivity {

    private ConstraintLayout mConstraintLayout;
    private TextView mUserTextView;
    private TextView mScoreTextView;

    private int mScore;
    private String mUserName;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crosslink);

        mPlayer = MyApplication.getPlayerInstance();
        if (mPlayer == null) { //instanceが取得できていなかったらTopに戻す
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), TitleActivity.class);
            startActivity(intent);
        }

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.crosslink_area);
        mScoreTextView = (TextView) findViewById(R.id.score_text);
        mUserTextView = (TextView) findViewById(R.id.user_name);

        mScore = mPlayer.getScore();
        mUserName = mPlayer.getName();

        mUserTextView.setText(mUserName);

        mScoreTextView.setVisibility(View.INVISIBLE);
        mScoreTextView.setText(String.valueOf(mScore));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new ScaleInAnimation(mScoreTextView).animate();
                mScoreTextView.setVisibility(View.VISIBLE);
            }
        }, 700);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new PuffOutAnimation(mConstraintLayout).animate();
                Intent intent = new Intent();
                intent.setClass(getApplication(), ResultActivity.class);
                startActivity(intent);
            }
        }, 3000);


    }

}
