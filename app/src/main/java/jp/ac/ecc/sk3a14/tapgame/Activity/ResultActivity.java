package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * 結果画面を表示するActivity
 */
public class ResultActivity extends AppCompatActivity {

    //トップへ戻るボタン
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //XML紐付け
        mButton = (Button)findViewById(R.id.btn);

        //トップへボタンのクリックイベント
        mButton.setOnClickListener(v -> {
                //トップ画面へ遷移
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), TitleActivity.class);
                startActivity(intent);
        });
    }
}
