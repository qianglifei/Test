package com.beikong.hdunemployedperson.CustomView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.beikong.hdunemployedperson.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义饼状图
 *
 */

public class PieGraphView extends View {

    //获取  画文字，饼图，以及延伸线的饼图
    private Paint textPaint,piePaint,linePaint,piePaintSmall,piePaintMiddle;

    //定义center and the radius of the pie
    private int pieCenterX,pieCenterY,pieRadius,pieRadiusSmall,pieRadiusMinSmall;
    
    //The oval to draw the oval in
    /**
     * RectF用于表示坐标系中的一块矩形区域，并可以对其进行一些简单的操作，在这块矩形区域，用左上和右下俩个坐标点表示
     * 他是用Float类型作为数值
     */
    private RectF pieOval;
    private RectF pieOvalSmall;
    private RectF selectOval;
    private RectF selectOvalSmall;
    private RectF pieOvalMiddle;

    private static final float PI = 3.1415f;

    private static final int PART_ONE = 1;

    private static final int PART_TWO = 2;

    private static final int PART_THREE = 3;

    private static final int PART_FOUR = 4;

    /**
     * 当前角度
     */
    int curAngel;

    private List<Point> points = null;

    private float smallMargin;

    private int screenW,screenH;

    private boolean isClick = false;

    private int[] mPieColors = new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,
            Color.MAGENTA,Color.CYAN};

    private  PieItemBean[] mPieItems;

    private float totalValue;

    private int select = -1;




    public void setOnSpecialTypeClickListener(OnSpecialTypeClickListener listener) {
        this.mListener = listener;
    }

    private OnSpecialTypeClickListener mListener;

    public interface OnSpecialTypeClickListener {
        void onSpecialTypeClick(String type);
    }

    public PieGraphView(Context context) {
        super(context);
        init(context);
    }

    public PieGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PieGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        //获取屏幕的宽度和高度
        screenW = displayMetrics.widthPixels;
        screenH = displayMetrics.heightPixels;

        pieCenterX = screenW / 2;
        pieCenterY = screenH / 8;
        pieRadius = screenW / 6;
        pieRadiusSmall = screenW / 14;
        pieRadiusMinSmall = screenW / 12;



        smallMargin = dip2px(context,5);

        pieOval = new RectF();
        pieOvalSmall = new RectF();
        selectOval = new RectF();
        selectOvalSmall = new RectF();
        pieOvalMiddle = new RectF();

        pieOval.left = pieCenterX - pieRadius;
        pieOval.top = pieCenterY - pieRadius;
        pieOval.right = pieCenterX + pieRadius;
        pieOval.bottom = pieCenterY + pieRadius;

        pieOvalSmall.left = pieCenterX - pieRadiusSmall;
        pieOvalSmall.top = pieCenterY - pieRadiusSmall;
        pieOvalSmall.right = pieCenterX + pieRadiusSmall;
        pieOvalSmall.bottom = pieCenterY + pieRadiusSmall;

        pieOvalMiddle.left = pieCenterX - pieRadiusMinSmall;
        pieOvalMiddle.top = pieCenterY - pieRadiusMinSmall;
        pieOvalMiddle.right = pieCenterX + pieRadiusMinSmall;
        pieOvalMiddle.bottom = pieCenterY + pieRadiusMinSmall;

        //the paint to draw text
        textPaint = new Paint();
        textPaint.setAntiAlias(true);  //设置成抗锯齿
        textPaint.setTextSize(dip2px(context,16));


        //The paint to draw circle
        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setStyle(Paint.Style.FILL);

        //The paint to draw a small circle
        piePaintSmall = new Paint();
        piePaintSmall.setAntiAlias(true);
        piePaintSmall.setStyle(Paint.Style.FILL);
        piePaintSmall.setColor(getResources().getColor(R.color.colorGreen));

        //The Paint to draw line to show the conCreate text
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(dip2px(context,1));

        //画透明圆的画笔
        piePaintMiddle = new Paint();
        piePaintMiddle.setAntiAlias(true);
        piePaintMiddle.setStyle(Paint.Style.FILL);
        piePaintMiddle.setColor(getResources().getColor(R.color.colorWhite));
    }

    //The degree position of the last item arc's center.
    private  float lastDegree = 0;
    //The count of the continues 'small' item.
    private int addTimes = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画饼状图开始的角度
        float start = -90.0f;
        float sweep;
        int startNumber = 0;
        if (mPieItems != null && mPieItems.length > 0) {
            for (int i = 0; i < mPieItems.length; i++) {
                //画饼状图
                sweep = mPieItems[i].getItemValue() / totalValue * 360;
                if (select >= 0 && i == select){
                    //重新绘制饼图，实现饼图的弹出效果
                    selectOval.left = pieCenterX - pieRadius;
                    selectOval.top = pieCenterY - pieRadius;
                    selectOval.right = pieCenterX + pieRadius;
                    selectOval.bottom = pieCenterY + pieRadius;

                    selectOvalSmall.left = pieCenterX - pieRadiusSmall;
                    selectOvalSmall.top = pieCenterY - pieRadiusSmall;
                    selectOvalSmall.right = pieCenterX + pieRadiusSmall;
                    selectOvalSmall.bottom = pieCenterY + pieRadiusSmall;

                    Point point = points.get(select);
                    int middle = (point.x + point.y) / 2;

                    if (middle <= 0) {
                        int top = (int) (Math.sin(Math.toRadians(middle)) * 30);
                        int left = (int) (Math.cos(Math.toRadians(middle)) * 30);

                        selectOval.left += left;
                        selectOval.right += left;
                        selectOval.top += top;
                        selectOval.bottom += top;

                        selectOvalSmall.left += left;
                        selectOvalSmall.right += left;
                        selectOvalSmall.top += top;
                        selectOvalSmall.bottom += top;
                    }
                    if (middle > 0 && middle <= 90) {
                        middle = 180 - middle;
                        int top = (int) (Math.sin(Math.toRadians(middle)) * 20);
                        int left = (int) (Math.cos(Math.toRadians(middle)) * 70);

                        selectOval.left -= left;
                        selectOval.right -= left;
                        selectOval.top += top;
                        selectOval.bottom += top;

                        selectOvalSmall.left -= left;
                        selectOvalSmall.right -= left;
                        selectOvalSmall.top += top;
                        selectOvalSmall.bottom += top;
                    }
                    if (middle > 90 && middle <= 180) {
                        middle = 270 - middle;
                        int left = (int) (Math.sin(Math.toRadians(middle)) * 30);
                        int top = (int) (Math.cos(Math.toRadians(middle)) * 30);
                        selectOval.left -= left;
                        selectOval.right -= left;
                        selectOval.top -= top;
                        selectOval.bottom -= top;

                        selectOvalSmall.left -= left;
                        selectOvalSmall.right -= left;
                        selectOvalSmall.top -= top;
                        selectOvalSmall.bottom -= top;
                    }
                    if (middle > 180 && middle <= 270) {
                        middle = 360 - middle;
                        int top = (int) (Math.sin(Math.toRadians(middle)) * 30);
                        int left = (int) (Math.cos(Math.toRadians(middle)) * 30);
                        selectOval.left += left;
                        selectOval.right += left;
                        selectOval.top -= top;
                        selectOval.bottom -= top;

                        selectOvalSmall.left += left;
                        selectOvalSmall.right += left;
                        selectOvalSmall.top -= top;
                        selectOvalSmall.bottom -= top;
                    }
                    //绘制弹出的部分扇形
                    piePaint.setColor(mPieColors[i % mPieItems.length]);
                    canvas.drawArc(selectOval, start,sweep, true, piePaint);
                    DrawOthers(canvas, start, startNumber, i, sweep);
                    canvas.drawArc(selectOvalSmall,start,sweep,true,piePaintSmall);
                    select = -1;
                    isClick = false;
                }else {
                    //画扇形
                    if (!isClick){
                        if (start <= curAngel){
                            piePaint.setColor(mPieColors[i % mPieColors.length]);
                            canvas.drawArc(pieOval,start,curAngel - start, true, piePaint);
                        }
                    }else {
                        piePaint.setColor(mPieColors[i % mPieColors.length]);
                        canvas.drawArc(pieOval,start,sweep, true, piePaint);
                    }
                    //画线以及文字
                    DrawOthers(canvas, start, startNumber, i, sweep);
                    //保存每个扇形的起始角度和结束角度
                    points.get(i).x = (int) start;
                    points.get(i).y = (int) (start + sweep);

                }
                //每个扇形的起始角度
                start += sweep;
            }

            //画内部的圆
            canvas.drawArc(pieOvalSmall,0,360,true,piePaintSmall);
            //画中间的透明圆环
            canvas.drawArc(pieOvalMiddle,0,360,true,piePaintMiddle);
        }
      //
    }

    private void DrawOthers(Canvas canvas, float start, int startNumber, int i, float sweep) {
        //draw line away from the pie
        float radians = (float) ((start + sweep / 2) / 180 * Math.PI);
        float lineStartX = pieCenterX + pieRadius * 0.7f * (float) (Math.cos(radians));
        float lineStartY = pieCenterY + pieRadius * 0.7f * (float) (Math.sin(radians));

        float lineStopX, lineStopY;
        float rate;

        if (getOffset(start + sweep / 2) > 60) {
            rate = 1.3f;
        } else if (getOffset(start + sweep / 2) > 30) {
            rate = 1.2f;
        } else {
            rate = 1.1f;
        }

        //If the item is very small, make the text further away from the pie to avoid being hided by other text.
        if (start + sweep / 2 - lastDegree < 30) {
            addTimes++;
            rate += 0.2f * addTimes;
        } else {
            addTimes = 0;
        }

        lineStopX = pieCenterX + pieRadius * rate * (float) (Math.cos(radians));
        lineStopY = pieCenterY + pieRadius * rate * (float) (Math.sin(radians));
        linePaint.setColor(mPieColors[i % mPieColors.length]);


        canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, linePaint);


        //write text
        String itemTypeText = mPieItems[i].getItemType();
        String itemPercentText = Utility.formatFloat(mPieItems[i].getItemValue() / totalValue * 100) + "%";


        float itemTypeTextLen = textPaint.measureText(itemTypeText);
        float itemPercentTextLen = textPaint.measureText(itemPercentText);
        float lineTextWidth = Math.max(itemTypeTextLen, itemPercentTextLen);

        float textStartX = lineStopX;
        float textStartY = lineStopY - smallMargin;
        float percentStartX = lineStopX;
        float percentStartY = lineStopY + textPaint.getTextSize();

        if (lineStartX > pieCenterX) {
            textStartX += (smallMargin + Math.abs(itemTypeTextLen - lineTextWidth) / 2);
            percentStartX += (smallMargin + Math.abs(itemPercentTextLen - lineTextWidth) / 2);
        } else {
            textStartX -= (smallMargin + lineTextWidth - Math.abs(itemTypeTextLen - lineTextWidth) / 2);
            percentStartX -= (smallMargin + lineTextWidth - Math.abs(itemPercentTextLen - lineTextWidth) / 2);
        }

        //画每个饼图所占的比例

        if(sweep >= curAngel - start){
            if (curAngel - start > 0){
                canvas.drawText(Utility.formatFloat(((curAngel - start) / 360) * 100) + "%", textStartX, textStartY, textPaint);
            }else {
                canvas.drawText("",textStartX,textStartY,textPaint);
            }
        }else {
            canvas.drawText(itemPercentText, textStartX, textStartY, textPaint);
        }

        //画文字
        canvas.drawText(itemTypeText, percentStartX, percentStartY, textPaint);

        //画文字下方的下划线
        float textLineStopX = lineStopX;
        if (lineStartX > pieCenterX) {
            textLineStopX += (lineTextWidth + smallMargin * 2);
        } else {
            textLineStopX -= (lineTextWidth + smallMargin * 2);
        }
        if (start < curAngel){
            canvas.drawLine(lineStopX, lineStopY, textLineStopX, lineStopY, linePaint);
        }
        lastDegree = start + sweep / 2;
    }


    /**
     * dp2px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public float getOffset(float radius){
        int a = (int) (radius % 360 / 90);
        switch (a){
            case 0:
                return radius;
            case 1:
                return 180 - radius;
            case 2:
                return radius - 180;
            case 3:
                return  360- radius;
        }
        return radius;
    }

    public PieItemBean[] getPieItems(){
        return mPieItems;
    }

    public void setPieItems(PieItemBean[] pieItems){
        this.mPieItems = pieItems;
        totalValue = 0;
        points = new ArrayList<>();
        for (PieItemBean item : mPieItems){
            totalValue += item.getItemValue();
            Point point = new Point();
            points.add(point);
        }
        startAnimation(-90,360);
    }

    private void startAnimation(int StartAngle,int Angle) {

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new AngelEvaluator(),StartAngle,Angle);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                curAngel = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }


    /**
     * 圆环的点击效果
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                doOnSpecialTypeClick(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void doOnSpecialTypeClick(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        double alfa = 0;
        float startArc = 0;
        //点击的位置到圆心距离的平方
        double distance = Math.pow(eventX - pieCenterX, 2) + Math.pow(eventY - pieCenterY, 2);
        //判断点击的坐标是否在环内
        if (distance < Math.pow(pieRadius, 2) && distance > Math.pow(pieRadiusSmall, 2)) {
            int which = touchOnWhichPart(event);
            switch (which) {
                case PART_ONE:
                    alfa = Math.atan2(eventX - pieCenterX, pieCenterY - eventY) * 180 / PI;
                    isClick = true;
                    break;
                case PART_TWO:
                    alfa = Math.atan2(eventY - pieCenterY, eventX - pieCenterX) * 180 / PI + 90;
                    isClick = true;
                    break;
                case PART_THREE:
                    alfa = Math.atan2(pieCenterX - eventX, eventY - pieCenterY) * 180 / PI + 180;
                    isClick = true;
                    break;
                case PART_FOUR:
                    alfa = Math.atan2(pieCenterY - eventY, pieCenterX - eventX) * 180 / PI + 270;
                    isClick = true;
                    break;

            }

            //圆环的点击效果
            for (int i = 0; i < mPieItems.length ; i++) {
                startArc += (mPieItems[i].getItemValue() / totalValue * 360);
                if (alfa != 0 && alfa < startArc){
                    if (mListener != null){
                        select = i;
                        invalidate();
                        mListener.onSpecialTypeClick(mPieItems[i].getItemType());
                    }
                    break;
                }
            }

        }


    }

    /**
     *    4 |  1
     * -----|-----
     *    3 |  2
     * 圆被分成四等份，判断点击在圆的哪一部分
     */
    private int touchOnWhichPart(MotionEvent event) {
        if (event.getX() > pieCenterX) {
            if (event.getY() > pieCenterY) return PART_TWO;
            else return PART_ONE;
        } else {
            if (event.getY() > pieCenterY) return PART_THREE;
            else return PART_FOUR;
        }
    }
}
