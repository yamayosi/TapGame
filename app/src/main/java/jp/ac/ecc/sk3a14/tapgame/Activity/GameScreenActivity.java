package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import jp.ac.ecc.sk3a14.tapgame.Fragment.CountDownFragment;
import jp.ac.ecc.sk3a14.tapgame.Fragment.GamePlayScreenFragment;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * ゲームプレイ画面のActivity
 */
public class GameScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //カウントダウンフラグメント、ゲームフラグメントを生成してカウントダウン開始
        //カウントダウンフラグメントが上に来るので、ゲームフラグメントはタッチ不可
        Fragment countDownFragment = CountDownFragment.newInstance();
        Fragment gameFragment = GamePlayScreenFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, gameFragment, GamePlayScreenFragment.TAG);
        transaction.add(R.id.container, countDownFragment, CountDownFragment.TAG);
        transaction.commit();
    }

    /**
     * カウントダウンフラグメントからコールバックされるメソッド
     */
    public void startGame(){
        GamePlayScreenFragment gameFragment = (GamePlayScreenFragment)getSupportFragmentManager().findFragmentByTag("gpsf");
        gameFragment.startGame();
    }
}
