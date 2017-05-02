package jp.ac.ecc.sk3a14.tapgame.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * カウントダウン画面
 */
public class CountDownFragment extends Fragment {

    //インテント用のキー
    public static final String TAG = "cdf";

    //デフォルトコンストラクタ
    public CountDownFragment() { }

    /**
     * インスタンスを生成するクラスメソッド
     * @return フラグメント
     */
    public static CountDownFragment newInstance() { return new CountDownFragment(); }

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
        View view = inflater.inflate(R.layout.fragment_count_down, container, false);

        //カウントダウンスタート
        startCountDown();

        return view;
    }

    /**
     * カウントダウンを始めるメソッド
     */
    private void startCountDown(){

        //5秒で画面遷移するタスクを生成
        TimerTask task = new TimerTask() {
            private int counter = 0;
            public void run() {

                //5秒まで繰り返し
                if(counter < 5){
                    Log.d("counter", String.valueOf(counter));
                    counter++;
                    return;
                }

                //遷移
                Fragment fragment = GamePlayScreenFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment, GamePlayScreenFragment.TAG);
                transaction.commit();

                //タイマーを止める
                this.cancel();
            }
        };

        //タイマーにタスクをセットしてスタート
        (new Timer()).schedule(task, 0L, 1000L);
    }
}
