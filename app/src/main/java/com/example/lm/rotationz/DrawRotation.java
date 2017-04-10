package com.example.lm.rotationz;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LM on 2017/4/6.
 */

public class DrawRotation extends View{
    private float mInitialDeg;
    private List<Circle> mCircleList;
    private Paint mPaint;
    private Camera mCamera;

    public DrawRotation(Context context) {
        super(context);

    }

    public DrawRotation(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCircleList=new ArrayList<>();
        mPaint=new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(),android.R.color.black));
        mPaint.setStrokeWidth(2);
        mCamera=new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Circle circle : mCircleList){
            circle.draw(canvas,mPaint);
            canvas.drawLine(0,0,circle.getCenterX(),circle.getCenterY(),mPaint);
        }

        Matrix matrix=new Matrix();
        mCamera.save();
        mCamera.rotateZ(mInitialDeg);
        mInitialDeg+=5;
        mCamera.getMatrix(matrix);
        mCamera.restore();
        Matrix viewMatrix=new Matrix();
        viewMatrix.setValues(new float[]{getMeasuredWidth()/2,0,0,getMeasuredHeight()/2,0,0,1,0,0});
        matrix.preConcat(viewMatrix);
        float[] resultValues=new float[9];
        matrix.getValues(resultValues);

        Circle circle=new Circle(resultValues[0],resultValues[3],30);
        mCircleList.add(circle);
        circle.draw(canvas,mPaint);
        canvas.drawLine(0,0,circle.getCenterX(),circle.getCenterY(),mPaint);

    }

    static class Circle{
        float mCenterX,mCenterY,mRadius;

        public Circle(float centerX,float centerY,float radius){
            mCenterX=centerX;
            mCenterY=centerY;
            mRadius=radius;
        }

        public void draw(Canvas canvas,Paint paint){
            canvas.drawCircle(mCenterX,mCenterY,mRadius,paint);
        }

        public float getCenterX() {
            return mCenterX;
        }

        public float getCenterY() {
            return mCenterY;
        }
    }

    public void next(){
        invalidate();
    }

}
