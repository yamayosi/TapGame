package jp.ac.ecc.sk3a14.tapgame;

import android.app.Application;
import android.support.annotation.Nullable;

import jp.ac.ecc.sk3a14.tapgame.Model.Player;

/**
 * 作成者 : 副島 祐希
 * 作成日 : 2017-05-08.
 */

public class MyApplication extends Application {
	
	private static Player mPlayer;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		
	}
	
	public static void createPlayer(String name){
		mPlayer = new Player(name);
	}
	
	@Nullable
	public static Player getPlayerInstance(){
		return mPlayer;
	}
}
