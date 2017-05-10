package jp.ac.ecc.sk3a14.tapgame.Fragment;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import jp.ac.ecc.sk3a14.tapgame.Activity.GameScreenActivity;
import jp.ac.ecc.sk3a14.tapgame.R;

/**
 * カウントダウン画面
 */
public class CountDownFragment extends Fragment {

    //効果音再生用変数
    private SoundPool mSoundPool;
    private int mSoundId1;
    private int mSoundId2;

    //自身の参照値
    private static CountDownFragment sCountDownFragment;
    //親アクティビティ
    private static GameScreenActivity sAttachActivity;

    //インテント用のキー
    public static final String TAG = "cdf";

    //カウントダウンTextView
    private TextView mCountDownImageView;

    //デフォルトコンストラクタ
    public CountDownFragment() { }

    /**
     * インスタンスを生成するクラスメソッド
     * @return フラグメント
     */
    public static CountDownFragment newInstance() {
        sCountDownFragment = new CountDownFragment();
        return sCountDownFragment;
    }

    /**
     * フラグメントの生成時に呼び出されるメソッド
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int SOUND_POOL_MAX = 2;
        mSoundPool = buildSoundPool(SOUND_POOL_MAX);
        setSoundID(mSoundPool);
    }

    /**
     * soundPoolオブジェクトを作成
     * @param poolMax
     * @return
     */
    private SoundPool buildSoundPool(int poolMax){
        SoundPool soundPool = null;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool(poolMax, AudioManager.STREAM_MUSIC,0);
        }else{
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(poolMax)
                    .build();
        }

        return soundPool;
    }

    /**
     * 音源をメモリに読み込み
     * @param soundPool
     */
    private void setSoundID(SoundPool soundPool){
        mSoundId1 = soundPool.load(getContext(),R.raw.countdown065,1);
        mSoundId2 = soundPool.load(getContext(),R.raw.countdown066,1);
    }

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

        //リソースの参照値取得
        mCountDownImageView = (TextView)view.findViewById(R.id.countDown);

        //カウントダウンスタート
        startCountDown();

        return view;
    }

    /**
     * カウントダウンを始めるメソッド
     */
    private void startCountDown() {
        //threadを作成し、タスクを与え実行
        final ExecutorService executorService = Executors.newSingleThreadExecutor(makeThreadFactory(Thread.MAX_PRIORITY));
        executorService.submit(countDownTask(new Handler()));
    }

    /**
     * カウントダウン実行用スレッドの作成
     * @param priority
     * @return
     */
    private ThreadFactory makeThreadFactory(final int priority){
        return r -> {
                Thread tHread = new Thread(r);
                tHread.setPriority(priority);
                return tHread;
        };
    }

    /**
     * カウントダウンタスクの作成
     * @param handler　UI変更用
     * @return
     */
    private Runnable countDownTask(Handler handler){
        return new Runnable() {
            int counter = 5;
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                for(int i = 0; i < 5;i++) {
                    handler.post(() -> {
                        mCountDownImageView.setText(String.valueOf(counter));
                        if(counter > 0){
                            mSoundPool.play(mSoundId1, 1.0f,1.0f,0,0,1);
                        }else{
                            mSoundPool.play(mSoundId2, 1.0f,1.0f,0,0,1);
                        }
                    });
                    counter--;
                    try {
                        Thread.sleep(1000L);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //使い終わったメモリの解放
                mSoundPool.unload(mSoundId1);
                mSoundPool.unload(mSoundId2);
                mSoundPool.release();
                //自分自身を削除
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(sCountDownFragment);
                transaction.commit();
                //ゲームを開始
                sAttachActivity.startGame();
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sAttachActivity = (GameScreenActivity)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sAttachActivity = null;
    }
}
