package andstuti.gameview_asynctask.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by andstuti on 2016-06-26.
 */
public class GameLoop extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private SurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private int mSurfaceHeight;

    public GameLoop(Context context, SurfaceHolder surfaceHolder, int surfaceWidth, int surfaceHeight) {
        this.mContext = context;
        this.mSurfaceHolder = surfaceHolder;
        this.mSurfaceWidth = surfaceWidth;
        this.mSurfaceHeight = surfaceHeight;
    }

    private Random random;
    private Paint white;
    private Paint black;

    private float cx;
    private float cy;
    private float radius;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        random = new Random();

        white = new Paint();
        white.setARGB(255, 255, 255, 255);

        black = new Paint();
        black.setARGB(255, 0, 0, 0);

        radius = 10.f;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (true) {
            if(!mSurfaceHolder.getSurface().isValid())
                break;
            publishProgress(mSurfaceHolder.lockCanvas());
        }
        return null;
    }

    private void publishProgress(Canvas canvas) {
        cx = radius + random.nextFloat() * (mSurfaceWidth - radius*2);
        cy = radius + random.nextFloat() * (mSurfaceHeight - radius*2);

        canvas.drawPaint(white);

        canvas.drawCircle(cx, cy, radius, black);

        if(!isCancelled())
            mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

}