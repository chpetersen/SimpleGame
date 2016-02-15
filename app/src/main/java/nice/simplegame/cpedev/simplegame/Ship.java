package nice.simplegame.cpedev.simplegame;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;

public class Ship {

    private Bitmap mBitmap;
    private int x, y, mSpeed, inc, mScreenWidth, mScreenHeight;
    public ArrayList<Bitmap> mBoomAnimation = null;
    boolean mDeath, mUp;
    public float mVertSpeed;

    float animationTime = 0;
    float mTotalAnimationTime = 1;
    float numFrames;

    public Ship(Bitmap bitmap, int x, int y, int screenWidth, int screenHeight) {
        mBitmap = bitmap;
        this.x = x;
        this.y = y;
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        inc = 0;
        mSpeed = 1;
        mDeath = false;
        mVertSpeed = 0;
    }

    public void setBoomAnimaton(ArrayList<Bitmap> animation) {
        mBoomAnimation = new ArrayList<>(animation);

        numFrames = mBoomAnimation.size() + 1;
    }

    public void draw(Canvas canvas) {
        int centerCoordinate = mBitmap.getWidth() / 2;
        if (!mDeath) {
            canvas.drawBitmap(mBitmap, x - centerCoordinate, y - centerCoordinate, null);
        } else {
            int index = (int) (animationTime * mTotalAnimationTime);
            if (index < numFrames) {
                canvas.drawBitmap(mBoomAnimation.get(index), x - centerCoordinate, y -
                        centerCoordinate, null);
            }
        }
    }

    public void update(float dt) {
        if (mDeath) {
            animationTime += dt;
        } else {
            mVertSpeed += mScreenHeight / 2 * dt;
            if (mUp) {
                mVertSpeed -= mScreenHeight * dt * 2;
            }
            y += mVertSpeed * dt;

            if (y - (mBitmap.getHeight() / 2) > mScreenWidth) {
                y = 0 - (mBitmap.getHeight() / 2);
            }
        }
    }

    public boolean bump(Point OTL, Point OTR, Point OBR, Point OBL) {
        Point TL = new Point(), TR = new Point(), BL = new Point(), BR = new Point();

        ArrayList<Point> pointArrayList = new ArrayList<>();
        pointArrayList.add(OTL);
        pointArrayList.add(OTR);
        pointArrayList.add(OBL);
        pointArrayList.add(OBR);

        getPoint(TL, TR, BL, BR);

        for (int i = 0; i < pointArrayList.size(); i++) {
            Point point = pointArrayList.get(i);
            if (BR.x >= point.x && TL.x <= point.x && TL.y <= point.y && BR.y >= point.y) {
                return true;
            }
        }

        pointArrayList.clear();
        pointArrayList.add(TL);
        pointArrayList.add(TR);
        pointArrayList.add(BL);
        pointArrayList.add(BR);

        for (int i = 0; i < pointArrayList.size(); i++) {
            Point point = pointArrayList.get(i);
            if (OBR.x >= point.x && OTL.x <= point.x && OTL.y <= point.y && OBR.y >= point.y) {
                return true;
            }
        }

        return false;
    }

    private void getPoint(Point tl, Point tr, Point bl, Point br) {
        tl.x = x - mBitmap.getWidth() / 2;
        tl.y = y - mBitmap.getHeight() / 2;

        tr.x = x + mBitmap.getWidth() / 2;
        tr.y = y - mBitmap.getHeight() / 2;

        bl.x = x - mBitmap.getWidth() / 2;
        bl.y = y + mBitmap.getHeight() / 2;

        br.x = x + mBitmap.getWidth() / 2;
        br.y = y + mBitmap.getHeight() / 2;
    }


}
