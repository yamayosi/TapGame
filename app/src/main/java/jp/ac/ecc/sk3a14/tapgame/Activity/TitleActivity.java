package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * タイトル画面用のActivity
 */
public class TitleActivity extends AppCompatActivity {

    //スタートボタン
    private Button mButton;

    //初めに名前を入力するEditText
    private EditText mEditText;

    //名前入力ダイアログ
    AlertDialog mDialog;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //変数の初期化
        initFields();

        //スタートボタンをクリックしたときのダイアログを表示
        mButton.setOnClickListener(v -> mDialog.show());
    }

    /**
     * 変数を初期化するメソッド
     * XMLと紐付けする
     */
    private void initFields() {

        //スタートボタン
        mButton = (Button) findViewById(R.id.startButton);

        //名前入力用
        mEditText = new EditText(this);

        //名前入力欄、OKボタン、キャンセルボタンの設定
        mDialog = new AlertDialog.Builder(this)
                .setMessage("名前を入力してください!")
                .setView(mEditText)
                .setPositiveButton("OK", (dialog, witch) -> {
                    //OKのときゲーム画面に遷移
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), GameScreenActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> { })
                .create();
    }
}
