package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    Paint mPaint;

    DataModel mDataModel;

    private List<DataModel.Bean> mDatas;


    private int rectSize;

    private int between = 20;

    private int rectWidth;


    private float[] mLines = {50, 50, 50, 600, 50, 600, 700, 600};


    private int startX = 50;
    private int startY = 600;


    private Point mPoint;


    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);


        mDataModel = new DataModel();
        mDatas = mDataModel.getData();

        rectSize = mDatas.size();

        rectWidth = (700 - 50) / rectSize;

        for (int i = 0; i < mDatas.size(); i++) {
            int x = startX + i * rectWidth + rectWidth / 2;
            mPoint = new Point(x, startY + 20);
            mDatas.get(i).setPoint(mPoint);
        }


    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        canvas.drawLines(mLines, mPaint);

        drawTxt(canvas);

        drawRec(canvas);

    }


    private void drawTxt(Canvas canvas) {
        mPaint.setTextSize(10);
        for (DataModel.Bean b : mDatas) {
            canvas.drawText(b.versionName, b.getPoint().x, b.getPoint().y, mPaint);
        }
    }

    private void drawRec(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mDatas.size(); i++) {
            DataModel.Bean b = mDatas.get(0);
            mPaint.setColor(b.color);
            float y=b.parent*10;
            canvas.drawRect(b.getPoint().x-rectWidth/2+between,y,b.getPoint().x+rectWidth-between,startY,mPaint);

        }

    }


    public class DataModel {

        public List<Bean> getData() {
            List<Bean> result = new ArrayList<>();
            result.add(new Bean("Froyo", 0.1f, Color.BLUE));
            result.add(new Bean("GingerBread", 2f, 0xffac00b1));
            result.add(new Bean("Ice Cream Sandwich", 1.9f, 0xff9e9e9e));
            result.add(new Bean("Jelly Bean", 18.9f, 0xff009c8a));
            result.add(new Bean("KitKat", 31.6f, 0xff0090f7));
            result.add(new Bean("Lollopop", 35.4f, 0xffff0013));
            result.add(new Bean("Marshmallow", 10.1f, 0xffffc200));

            return result;
        }

        public class Bean {
            public String versionName;
            public float parent;
            public int color;
            private Point point;

            public Bean(String versionName, float parent, int color) {
                this.versionName = versionName;
                this.parent = parent / 100;
                this.color = color;
            }

            public Point getPoint() {
                return point;
            }

            public void setPoint(Point point) {
                this.point = point;
            }
        }
    }
}
