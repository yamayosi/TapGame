package jp.ac.ecc.sk3a14.tapgame.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import jp.ac.ecc.sk3a14.tapgame.Activity.ResultActivity;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 *
 */
public class GamePlayScreenFragment extends Fragment {

    public static final String TAG = "gpsf";

    //タップした回数
    private TextView mCounter;

    //タップ対象の画像
    private ImageView mTarget;

    //制限時間
    private final Long TIME_LIMIT = 10000L;

    public GamePlayScreenFragment() { }

    public static GamePlayScreenFragment newInstance() { return new GamePlayScreenFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_play_screen, container, false);

        initFields(view);

        startGame();

        return view;
    }

    /**
     * キャストなしでfindViewByIdを使えるようにする
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    private <T extends View> T findViewById(View view, @IdRes int id){ return (T)view.findViewById(id); }

    //変数の初期化
    private void initFields(View view){

        //タップ回数を表示するTextView
        mCounter = findViewById(view, R.id.counter);
        mCounter.setText("0");

        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(3, Color.parseColor("#000000"));
        mCounter.setBackground(drawable);

        //タップする画像
        mTarget = findViewById(view, R.id.target);

        //クリックでカウントアップ
        mTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(mCounter.getText().toString());
                count++;
                mCounter.setText(String.valueOf(count));
            }
        });
    }

    /**
     * ゲームスタート
     */
    private void startGame(){

        //制限時間
        TimerTask task = new TimerTask() {
            public void run() {

                //画面遷移
                Intent intent = new Intent();
                intent.setClass(getContext(), ResultActivity.class);
                startActivity(intent);

                //タイマーを止める
                this.cancel();
            }
        };

        //タイマーにタスクをセットしてスタート
        (new Timer()).schedule(task, TIME_LIMIT);
    }
}
