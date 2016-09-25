package com.phicomm.android.driversurvey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HistogramView extends View implements Runnable {

	private static final String TAG = HistogramView.class.getSimpleName();

	private int viewWidth; // 控件高度
	private int viewHeight; // 控件宽度

	private String rateBackgroundColorLeft; // 进度条背景颜色
	private String rateBackgroundColorRight;

	private int rateHeight; // 进度条的高
	private int rateWidth; // 进度条的宽

	private int rateAnimValue; // 进度条动画高度
	private int rateAnimValueRight;// 右边进度条动画高度(左边起始位置)

	private double progress; // 设置进度 1为最大值

	private Handler handler = new Handler(); // 动画handler

	private int animRate = 5; // 动画速度，以每1毫秒计
	private float animRateFast; // 快的那条的动画速度，相对于animRate取值，以同时到达

	private int animTime = 1; // 动画延迟执行时间

	private Canvas canvas; // 画布

	public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public HistogramView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HistogramView(Context context) {
		super(context);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 初始化控件和进度条的相关参数
		viewWidth = w;
		viewHeight = h;
		// 这里吧orientation写死成竖直
		rateWidth = (int) (w * progress);
		// animRateRight = (float) animRate * (viewWidth - rateWidth)/
		// rateWidth;
		rateHeight = h;
		rateAnimValueRight = viewWidth;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.canvas = canvas;

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);

		Paint paintRight = new Paint();
		paintRight.setAntiAlias(true);
		paintRight.setStyle(Paint.Style.FILL);

		// Log.d(TAG, "onDraw rateBackgroundColor=====" + rateBackgroundColor);
		if (rateBackgroundColorLeft != null) {
			drawViewWithColor(paint, paintRight);
		}
	}

	private void drawViewWithColor(Paint paint, Paint paintRight) {
		paint.setColor(Color.parseColor(rateBackgroundColorLeft));
		paintRight.setColor(Color.parseColor(rateBackgroundColorRight));
		// Log.d(TAG, "rateBackgoundColor======" + rateBackgroundColor);

		// postDelayed方法还没学过
		// 吧一个实现runnable接口的对象塞进去开一个新线程,延时delayMills执行
		handler.postDelayed(this, animTime);
		canvas.drawRect(0, 0, rateAnimValue, viewHeight, paint);
		canvas.drawRect(rateAnimValueRight, 0, viewWidth, viewHeight,
				paintRight);

	}

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public String getRateBackgroundColorLeft() {
		return rateBackgroundColorLeft;
	}

	public void setRateBackgroundColorLeft(String rateBackgroundColor) {
		this.rateBackgroundColorLeft = rateBackgroundColor;
	}

	public String getRateBackgroundColorRight() {
		return rateBackgroundColorRight;
	}

	public void setRateBackgroundColorRight(String rateBackgroundColorRight) {
		this.rateBackgroundColorRight = rateBackgroundColorRight;
	}

	public int getRateHeight() {
		return rateHeight;
	}

	public void setRateHeight(int rateHeight) {
		this.rateHeight = rateHeight;
	}

	public int getRateWidth() {
		return rateWidth;
	}

	public void setRateWidth(int rateWidth) {
		this.rateWidth = rateWidth;
	}

	public int getRateAnimValue() {
		return rateAnimValue;
	}

	public void setRateAnimValue(int rateAnimValue) {
		this.rateAnimValue = rateAnimValue;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public int getAnimRate() {
		return animRate;
	}

	public void setAnimRate(int animRate) {
		this.animRate = animRate;
	}

	public int getAnimTime() {
		return animTime;
	}

	public void setAnimTime(int animTime) {
		this.animTime = animTime;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void run() {
		drawTwoBar();
	}

	public void drawTwoBar() {
		// 低速矩形条速度固定，高速矩形条速度由这个固定速度而定，保证两边同时到达“会面”
		if (rateWidth < (viewWidth / 2)) {

			if (rateWidth == 0) {
				animRateFast = 20;
			} else {
				animRateFast = (float) animRate * (viewWidth - rateWidth)
						/ rateWidth;
			}

			if (rateAnimValue <= (rateWidth - 10)
					|| rateAnimValueRight >= (rateWidth + 10)) {
				if (rateAnimValue <= (rateWidth - 10)) {
					rateAnimValue += animRate;
				}
				if (rateAnimValueRight >= (rateWidth + 10)) {
					rateAnimValueRight -= animRateFast;
				}
				invalidate();

			} else {
				handler.removeCallbacks(this);
				rateAnimValue = 0;
				rateAnimValueRight = viewWidth;
			}
		} else {

			if (progress == 1) {
				animRateFast = 20;
			} else {
				animRateFast = (float) animRate * rateWidth
						/ (viewWidth - rateWidth);
			}

			if (rateAnimValue <= (rateWidth - 10)
					|| rateAnimValueRight >= (rateWidth + 10)) {
				if (rateAnimValue <= (rateWidth - 10)) {
					rateAnimValue += animRateFast;
				}
				if (rateAnimValueRight >= (rateWidth + 10)) {
					rateAnimValueRight -= animRate;
				}
				invalidate();

			} else {
				handler.removeCallbacks(this);
				rateAnimValue = 0;
				rateAnimValueRight = viewWidth;
			}
		}
	}

}
