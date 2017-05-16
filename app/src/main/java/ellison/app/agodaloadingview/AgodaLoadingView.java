package ellison.app.agodaloadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class AgodaLoadingView extends View {
    private int radius = 55;
    private int[] circleCenterXY, circleRightXY, circleUpXY, circleLeftXY, circleBottomXY;
    private int[] originCenterXY, originRightXY, originUpXY, originLeftXY, originBottomXy;

    private int TODO_STEP_UP = 0;
    private int TODO_STEP_CENTER = -1;
    private int TODO_STEP_BOTTOM = -1;
    private int TODO_STEP_RIGHT = -1;
    private int TODO_STEP_LEFT= -1;

    private int move_unit = 20;
    private int collison_unit = (int)(radius);
    private Paint paint = new Paint();
    private int center_x, center_y;
    private Handler handler;
    private Runnable runnable;
    private int[] colors = new int[5];
    private final String[] defaulstColors = new String[]{"#FFC107", "#03A9F4", "#B71C1C", "#7B1FA2", "#388E3C"};

    public AgodaLoadingView(Context context) {
        super(context);
        init();
    }

    public AgodaLoadingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void reLayout(){
        int width = this.getWidth();
        int height = this.getHeight();

        handler = new Handler();

        center_x = width/2;
        center_y = height/2;

        radius = width/13;

        originCenterXY = new int[]{center_x, center_x};
        originRightXY = new int[]{center_x + radius * 3, center_y};
        originUpXY  = new int[]{center_x, center_y - radius * 3};
        originLeftXY = new int[]{center_x - radius * 3, center_y};
        originBottomXy = new int[]{center_x, center_y + radius * 3};

        circleCenterXY = new int[]{center_x, center_x};
        circleRightXY = new int[]{center_x + radius * 3, center_y};
        circleBottomXY = new int[]{center_x, center_y + radius * 3};
        circleLeftXY = new int[]{center_x - radius * 3, center_y};
        circleUpXY = new int[]{center_x, center_y - radius * 3};
    }

    private void init(){
        for(int i=0;i<defaulstColors.length;i++){
            colors[i] = Color.parseColor(defaulstColors[i]);
        }
    }

    public void setCircleColors(String[] colors){
        for(int i=0;i<colors.length;i++){
            this.colors[i] = Color.parseColor(colors[i]);
        }
    }

    public void setCircleColors(int[] colors){
        this.colors = colors;
    }

    private boolean moveToNext(int[] from, int[] next){
        int fromX = from[0];
        int fromY = from[1];

        int nextX = next[0];
        int nextY = next[1];

        if(fromX==nextX && fromY==nextY){
            return true;
        }

        
        if(fromX == nextX){
            if(nextY>fromY){
                int nextValue = fromY + move_unit;
                if(nextValue>=nextY){
                    nextValue = nextY;
                }
                from[1] = nextValue;

            }else{
                int nextValue = fromY - move_unit;
                if(nextValue<=nextY){
                    nextValue = nextY;
                }
                from[1] = nextValue;
            }
        }
        else if(fromY == nextY){
            if(nextX>fromX){
                int nextValue = fromX + move_unit;
                if(nextValue>=nextX){
                    nextValue = nextX;
                }
                from[0] = nextValue;
            }else{
                int nextValue = fromX - move_unit;
                if(nextValue<=nextX){
                    nextValue = nextX;
                }
                from[0] = nextValue;
            }
        }else{
            if(nextX>fromX){
                int nextValue = fromX + move_unit;
                if(nextValue>=nextX){
                    nextValue = nextX;
                }
                from[0] = nextValue;
            }else{
                int nextValue = fromX - move_unit;
                if(nextValue<=nextX){
                    nextValue = nextX;
                }
                from[0] = nextValue;
            }

            if(nextY>fromY){
                int nextValue = fromY + move_unit;
                if(nextValue>=nextY){
                    nextValue = nextY;
                }
                from[1] = nextValue;
            }else{
                int nextValue = fromY - move_unit;
                if(nextValue<=nextY){
                    nextValue = nextY;
                }
                from[1] = nextValue;
            }
        }
        invalidate();
        return false;

    }

    public void startLoading(){
        runnable = new Runnable() {
            @Override
            public void run() {
                //up
                if(TODO_STEP_UP == 0){
                    if(center_y <= circleUpXY[1] + collison_unit){
                        TODO_STEP_CENTER = 0;
                    }
                    moveToNext(circleUpXY, originCenterXY);
                }
                else if(TODO_STEP_UP == 1){
                    moveToNext(circleUpXY, originRightXY);
                }
                else if(TODO_STEP_UP == 2){
                    if(center_x <= circleUpXY[0] + collison_unit){
                        TODO_STEP_BOTTOM = 2;
                    }
                    if(moveToNext(circleUpXY, originUpXY)){
                        TODO_STEP_UP = -1;
                        TODO_STEP_BOTTOM = 2;
                    }
                }

                //center
                if(TODO_STEP_CENTER == 0){
                    if((center_y + radius * 3) < circleCenterXY[1] + collison_unit){
                        TODO_STEP_BOTTOM = 0;
                    }
                    moveToNext(circleCenterXY, originBottomXy);
                }else if(TODO_STEP_CENTER == 1){
                    if((center_x + radius * 3) < circleCenterXY[0]  + collison_unit) {
                        TODO_STEP_UP = 2;
                        TODO_STEP_CENTER = -1;
                    }
                    moveToNext(circleCenterXY, originRightXY);
                }else if(TODO_STEP_CENTER == 2){
                    if(moveToNext(circleCenterXY, originCenterXY)){
                        resetSteps();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        TODO_STEP_UP = 0;
                    }
                }

                //bottom
                if(TODO_STEP_BOTTOM == 0){
                    if((center_x + radius * 3) < circleBottomXY[0] + collison_unit) {
                        TODO_STEP_RIGHT = 0;
                    }
                    moveToNext(circleBottomXY, originRightXY);
                }
                else if(TODO_STEP_BOTTOM == 1){
                    if(center_x > circleBottomXY[0] - collison_unit) {
                        TODO_STEP_RIGHT = 1;
                    }
                    if(moveToNext(circleBottomXY, originUpXY)){
                        resetSteps();
                        TODO_STEP_RIGHT = 1;
                    }
                }
                else if(TODO_STEP_BOTTOM == 2){
                    if(moveToNext(circleBottomXY, originLeftXY)){
                        resetSteps();
                        TODO_STEP_BOTTOM = 3;
                        TODO_STEP_LEFT = 1;
                    }
                }
                else if(TODO_STEP_BOTTOM == 3){
                    moveToNext(circleBottomXY, originBottomXy);
                }

                //right
                if(TODO_STEP_RIGHT == 0){
                    if(moveToNext(circleRightXY, originUpXY)){
                        resetSteps();
                        TODO_STEP_UP = 1;
                        TODO_STEP_BOTTOM = 1;
                        TODO_STEP_LEFT = 0;
                    }
                }
                else if(TODO_STEP_RIGHT == 1){
                    if(center_y == circleRightXY[1] + collison_unit) {
                        TODO_STEP_BOTTOM = 1;
                    }

                    if(moveToNext(circleRightXY, originLeftXY)){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        TODO_STEP_RIGHT = 2;
                    }

                }else if(TODO_STEP_RIGHT == 2){
                    if(center_x < circleRightXY[0] + collison_unit) {
                        TODO_STEP_CENTER = 1;
                    }

                    moveToNext(circleRightXY, originBottomXy);
                }else if(TODO_STEP_RIGHT == 3){
                    if(center_y > circleRightXY[1] - collison_unit) {
                        TODO_STEP_CENTER = 2;
                    }

                    if(moveToNext(circleRightXY, originRightXY)){
                        resetSteps();
                        TODO_STEP_CENTER = 2;
                    }

                }
                //left
                if(TODO_STEP_LEFT == 0){
                    moveToNext(circleLeftXY, originCenterXY);
                }
                else if(TODO_STEP_LEFT == 1){
                    if((center_x - radius * 3) < circleLeftXY[0] + collison_unit) {
                        TODO_STEP_RIGHT = 3;
                    }
                    if(moveToNext(circleLeftXY, originLeftXY)){
                        resetSteps();
                        TODO_STEP_RIGHT = 3;
                    }
                }

                handler.postDelayed(this, 30);
            }
        };
        handler.postDelayed(runnable, 100);

    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);

        //circle center
        paint.setColor(colors[0]);
        canvas.drawCircle(circleCenterXY[0], circleCenterXY[1], radius, paint);

        //circle right
        paint.setColor(colors[1]);
        canvas.drawCircle(circleRightXY[0], circleRightXY[1], radius, paint);

        //circle up
        paint.setColor(colors[2]);
        canvas.drawCircle(circleUpXY[0], circleUpXY[1], radius, paint);

        //circle left
        paint.setColor(colors[3]);
        canvas.drawCircle(circleLeftXY[0], circleLeftXY[1], radius, paint);

        //circle bottom
        paint.setColor(colors[4]);
        canvas.drawCircle(circleBottomXY[0], circleBottomXY[1], radius, paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        reLayout();
        invalidate();

        startLoading();
    }

    private void resetSteps(){
        TODO_STEP_UP = -1;
        TODO_STEP_CENTER = -1;
        TODO_STEP_BOTTOM = -1;
        TODO_STEP_RIGHT = -1;
        TODO_STEP_LEFT= -1;
    }

}
