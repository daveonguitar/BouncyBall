package com.daveonguitar.android.bouncyball;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BouncyBallView extends View {

    private int xMin = 0;   // This
    private int xMax;       // view's
    private int yMin = 0;   // bounds
    private int yMax;       //

    private List<Ball> balls;

    public BouncyBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        balls = new ArrayList<>();

        int numBalls = getBalls();

        for (int i = 0; i < numBalls; i++)
            balls.add(this.addBall());
    }

    public Ball addBall() {
        Ball ball;

        int alpha = 64 + (int) (Math.random() * (255 - 64)); // always at least 1/4 solid
        int red = 5 + (int) (Math.random() * (255 - 5));
        int green = 5 + (int) (Math.random() * (255 - 5));
        int blue = 5 + (int) (Math.random() * (255 - 5));

        int velX = 1 + (int) (Math.random() * 20);
        int velY = 1 + (int) (Math.random() * 20);
        int radius = 2 + (int) (Math.random() * 50);

        ball = new Ball(0, 0, radius, velX, velY, alpha, red, green, blue);

        return ball;
    }

    // called to draw the View. Also called by invalidate()
    @Override
    protected void onDraw(Canvas canvas) {
        // draw the ball

        for (int i = 0; i < balls.size(); i++)
            balls.get(i).draw(canvas);

        // Update the position of the ball, including collision detection and reaction

        for (int i = 0; i < balls.size(); i++)
            balls.get(i).update(xMin, xMax, yMin, yMax);

        // delay
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {}

        invalidate();   // Force a redraw
    }

    // called back when the view is first created or its size changes
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // set the movement bounds for the ball
        this.xMax = w - 1;
        this.yMax = h - 1;
    }

    public int getBalls() {
        int numBalls = 1 + (int) (Math.random() * 100);
        return numBalls;
    }
}
