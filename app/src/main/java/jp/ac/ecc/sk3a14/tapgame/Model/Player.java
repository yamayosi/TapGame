package jp.ac.ecc.sk3a14.tapgame.Model;

/**
 * Created by yKicchan on 2017/04/28.
 */

/**
 * プレイヤー情報を扱うクラス
 */
public class Player {
    
    //プレイヤーの名前
    private String name;
    
    //スコア
    private int score;

    /**
     * プレイヤー情報を初期化するコンストラクタ
     * score はデフォルトで 0
     * @param name 名前
     */
    public Player(String name){
        
        //名前を設定
        this.name = name;

        //スコアを初期化(花澤香菜は100点スタート)
        if(name.equals("花澤香菜")){
            this.score = 100;
        } else {
            this.score = 0;
        }
    }

    /**
     * プレイヤーの名前を返すメソッド
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * スコアをセットするメソッド
     * @param score スコア
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * スコアを返すメソッド
     * @return スコア
     */
    public int getScore() {
        return score;
    }
}
