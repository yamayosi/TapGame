package jp.ac.ecc.sk3a14.tapgame.Fragment;

import android.content.Intent;
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
import jp.ac.ecc.sk3a14.tapgame.MyApplication;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * ゲームのプレイ画面
 */
public class GamePlayScreenFragment extends Fragment {

    //インテント用のキー
    public static final String TAG = "gpsf";

    //タップした回数を表示する
    private TextView mCountView;
    private int count;

    //タップ対象の画像
    private ImageView mTarget;

    //制限時間
    private final Long TIME_LIMIT = 10000L;

    //デフォルトコンストラクタ
    public GamePlayScreenFragment() { }

    /**
     * インスタンスを生成するクラスメソッド
     * @return インスタンス
     */
    public static GamePlayScreenFragment newInstance() { return new GamePlayScreenFragment(); }

    /**
     * フラグメントの生成時に呼び出されるメソッド
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    /**
     * 初めてUIを描画するタイミングで呼ばれるメソッド
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return フラグメントレイアウトのルートView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_play_screen, container, false);

        //変数の初期化
        initFields(view);

        //ゲームスタート
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
        mCountView = findViewById(view, R.id.counter);
        count = 0;
        mCountView.setText(String.valueOf(count));

        //タップする画像
        mTarget = findViewById(view, R.id.target);

        //クリックでカウントアップ
        mTarget.setOnClickListener(v -> {
                count++;
                mCountView.setText(String.valueOf(count));
        });
    }

    /**
     * ゲームスタート
     */
    private void startGame(){

        //制限時間
        TimerTask task = new TimerTask() {
            public void run() {
	
	            MyApplication.getPlayerInstance().setScore(count);
	            
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
