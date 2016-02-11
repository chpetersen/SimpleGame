package nice.simplegame.cpedev.simplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    Bitmap mBackBitmap;
    int x, y, mScreenWidth, mCountBackground;
    GamePanel mRootGamePanel;

    public Background(Bitmap bitmap, int screenWidth, GamePanel gamePanel) {
        x = 0;
        y = 0;
        mBackBitmap = bitmap;
        mScreenWidth = screenWidth;
        mCountBackground = screenWidth / mBackBitmap.getWidth() + 1;
        mRootGamePanel = gamePanel;
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < mCountBackground + 1; i++) {
            if (canvas != null) {
                canvas.drawBitmap(mBackBitmap, mBackBitmap.getWidth() * i + x, y, null);
            }
        }
        if (Math.abs(x) > mBackBitmap.getWidth()) {
            x = x + mBackBitmap.getWidth();
        }

    }

    public void update(float dt) {
        x = (int) (x - mRootGamePanel.mShipspeed * dt);
    }
}
