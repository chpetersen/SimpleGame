package nice.simplegame.cpedev.simplegame;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public MyMainThread mThread;
    public boolean pauseGame;
    private Background mBackground;
    public float mShipspeed;
    private Ship mShip;

    public GamePanel(Context context, Game game, int screenWidth, int screenHeight) {
        super(context);
        getHolder().addCallback(this);
        mThread = new MyMainThread(getHolder(), this);
        setFocusable(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.moon);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, true);

        mBackground = new Background(resized, screenWidth, this);
        mShip = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),
                         100, 0, screenWidth, screenHeight);
        mShipspeed = screenWidth / 2.f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mShip.mUp = true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            mShip.mUp = false;
        }
        return true;
    }

    void Draw(Canvas canvas) {
        if (!pauseGame) {
            if (canvas != null) {
                mBackground.draw(canvas);
                mShip.draw(canvas);
            }
        }

    }

    void Update(float dt) {
        mBackground.update(dt);
        mShip.update(dt);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.setRunning(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
}
