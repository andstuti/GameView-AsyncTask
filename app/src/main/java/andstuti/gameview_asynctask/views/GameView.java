package andstuti.gameview_asynctask.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import andstuti.gameview_asynctask.models.GameLoop;

/**
 * Created by andstuti on 2016-06-26.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = GameView.class.getSimpleName();

    private Context mContext;
    private SurfaceHolder mSurfaceHolder;

    private int mSurfaceWidth;
    private int mSurfaceHeight;

    private GameLoop mGameLoop;

    public GameView(Context context) {
        super(context);
        this.mContext = context;
        this.mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.mSurfaceWidth = width;
        this.mSurfaceHeight = height;

        mGameLoop = new GameLoop(mContext, mSurfaceHolder, mSurfaceWidth, mSurfaceHeight);
        mGameLoop.execute();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mGameLoop.cancel(true);
    }

}