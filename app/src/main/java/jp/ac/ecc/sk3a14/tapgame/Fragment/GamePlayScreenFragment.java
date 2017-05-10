package jp.ac.ecc.sk3a14.tapgame.Fragment;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;

import jp.ac.ecc.sk3a14.tapgame.Activity.Crosslink;
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

    //音声周り
    private AudioAttributes mAudioAttributes;
    private SoundPool mSoundPool;
    private int tapEffect;

    //デフォルトコンストラクタ
    public GamePlayScreenFragment() {
    }

    /**
     * インスタンスを生成するクラスメソッド
     *
     * @return インスタンス
     */
    public static GamePlayScreenFragment newInstance() {
        return new GamePlayScreenFragment();
    }

    /**
     * フラグメントの生成時に呼び出されるメソッド
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初めてUIを描画するタイミングで呼ばれるメソッド
     *
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

        return view;
    }

    /**
     * キャストなしでfindViewByIdを使えるようにする
     *
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    private <T extends View> T findViewById(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    //変数の初期化
    private void initFields(View view) {

        //タップ回数を表示するTextView
//        mCountView = findViewById(view, R.id.counter);
        count = 0;
//        mCountView.setText(String.valueOf(count));

        //タップする画像
        mTarget = findViewById(view, R.id.target);

        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tapEffect = mSoundPool.load(getContext(), R.raw.tap1, 1);


        //クリックでカウントアップ
        mTarget.setOnClickListener(v -> {

            //タップ時のアニメーション。
            YoYo.with(Techniques.RubberBand).duration(200).playOn(mTarget);
            count++;
            //タップ時の音
            mSoundPool.play(tapEffect, 0.5f, 0.5f, 0, 0, 1);
//            mCountView.setText(String.valueOf(count));
        });
    }

    /**
     * ゲームスタート
     */
    public void startGame() {

        //制限時間
        TimerTask task = new TimerTask() {
            public void run() {

                MyApplication.getPlayerInstance().setScore(count);

                //画面遷移
                Intent intent = new Intent();
                intent.setClass(getContext(), Crosslink.class);
                startActivity(intent);

                //タイマーを止める
                this.cancel();
            }
        };

        //タイマーにタスクをセットしてスタート
        (new Timer()).schedule(task, TIME_LIMIT);
    }
}
