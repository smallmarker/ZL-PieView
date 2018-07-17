package com.zl.pieview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;


/**
 * @author xiaolong
 * @ClassName PieView
 * @Date 2018/7/16
 **/
@SuppressLint("Recycle")
public class PieView extends View {

    //饼图数据
    private List<PieBean> pieBeans;

    //外圆宽度
    private int mOutStrokeWidth;

    //内圆宽度
    private int mInStrokeWidth;

    //画笔
    private Paint mPaint;

    public void setPieBeans(List<PieBean> pieBeans) {
        this.pieBeans = pieBeans;
        invalidate();
    }

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieView, 0, 0);
        mOutStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.PieView_pv_outStrokeWidth,40);
        mInStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.PieView_pv_inStrokeWidth,10);
        typedArray.recycle();
    }

    {
        //初始化画笔并设置抗锯齿模式
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (pieBeans == null || pieBeans.size() == 0) return;
        //获取中心点坐标
        int mCenterXY = Math.min(getWidth() / 2, getHeight() / 2);
        //获得外圆半径
        int mOutRadius = mCenterXY - mOutStrokeWidth / 2;
        RectF mRectF = new RectF(mCenterXY - mOutRadius, mCenterXY - mOutRadius, mCenterXY + mOutRadius, mCenterXY + mOutRadius);
        //设置起始角度
        int startAngle = 0;
        mPaint.setStrokeWidth(mOutStrokeWidth);
        for (PieBean mPieBean : pieBeans) {
            float sweepAngle = 360 * mPieBean.value + 1;
            mPaint.setColor(mPieBean.color);
            canvas.drawArc(mRectF,startAngle,sweepAngle,false,mPaint);
            startAngle += sweepAngle - 1;
        }

        //获取内圆半径
        int mInRadius = mCenterXY - mOutStrokeWidth + mInStrokeWidth / 2;
        mPaint.setStrokeWidth(mInStrokeWidth);
        //设置黑色不透明度20%
        mPaint.setColor(Color.parseColor("#33000000"));
        canvas.drawCircle(mCenterXY, mCenterXY, mInRadius,mPaint);
    }
}
