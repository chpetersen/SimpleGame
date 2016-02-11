package nice.simplegame.cpedev.simplegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MyMainThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private GamePanel mGamePanel;
    private boolean mRunning;
    float dt;

    public MyMainThread(SurfaceHolder holder, GamePanel gamePanel) {
        mSurfaceHolder = holder;
        mGamePanel = gamePanel;
        dt = 0;
    }

    void setRunning(boolean runing) {
        mRunning = runing;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (mRunning) {
            if (!mGamePanel.pauseGame) {
                long startDraw = System.currentTimeMillis();
                canvas = null;
                try {
                    canvas = this.mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder) {
                        mGamePanel.Update(dt);
                        mGamePanel.Draw(canvas);
                    }

                } finally {
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }

                }

                long endDraw = System.currentTimeMillis();
                dt = (endDraw - startDraw) / 1000.f;
            }
        }
    }
}
