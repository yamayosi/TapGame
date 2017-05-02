package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import jp.ac.ecc.sk3a14.tapgame.Fragment.CountDownFragment;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * ゲームプレイ画面のActivity
 */
public class GameScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //フラグメントを生成してカウントダウン開始
        Fragment fragment = CountDownFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment, CountDownFragment.TAG);
        transaction.commit();
    }
}
