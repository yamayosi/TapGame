package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * Created by yKicchan on 2017/04/28.
 */

/**
 * 結果画面を表示するActivity
 */
public class ResultActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mButton = (Button)findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), TitleActivity.class);
                startActivity(intent);
            }
        });
    }
}
