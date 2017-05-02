package jp.ac.ecc.sk3a14.tapgame.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jp.ac.ecc.sk3a14.tapgame.Model.Player;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * タイトル画面用のActivity
 */
public class TitleActivity extends AppCompatActivity {

    public static final String KEY = "player";

    //スタートボタン
    private Button mButton;

    //初めに名前を入力するEditText
    private EditText mEditText;

    //名前入力ダイアログ
    AlertDialog mDialog;

    private Player mPlayer;

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

        //スタートボタンをクリックしたときの処理
        mButtonClick();
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
                .setPositiveButton("OK", dialogOK())
                .setNegativeButton("Cancel", dialogCancel())
                .create();
    }

    /**
     * スタートボタンクリック
     * ゲーム画面に遷移する
     */
    private void mButtonClick() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ダイアログ表示
                mDialog.show();
            }
        });
    }

    /**
     * ダイアログOKのとき
     * @return
     */
    private DialogInterface.OnClickListener dialogOK() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //プレイヤー情報の生成
                mPlayer = new Player(mEditText.getText().toString());

                //ゲーム画面に遷移
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), GameScreenActivity.class);
                startActivity(intent);
            }
        };
    }

    /**
     * ダイアログキャンセルのとき
     * @return
     */
    private DialogInterface.OnClickListener dialogCancel() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
    }
}
