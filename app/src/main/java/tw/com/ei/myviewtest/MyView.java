package tw.com.ei.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/7/31.
 */

public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines,recycle;
    //要XML內SET的到這個VIEW的時候，最少的建構子要代入AttributeSet...(第二個屬性)
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("simon","A");
            }
        });
        lines=new LinkedList<>();
        recycle=new LinkedList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint =new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(30);
        for(LinkedList<HashMap<String,Float>>line: lines) {
            for(int i=1;i<line.size();i++){
                HashMap<String,Float> p0=line.get(i-1);
                HashMap<String,Float> p1=line.get(i);
                canvas.drawLine(p0.get("x"),p0.get("y")
                        ,p1.get("x"),p1.get("y"),paint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x,y;
        x=event.getX();
        y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:

                HashMap<String,Float>point =new HashMap<>();
                point.put("x",x);
                point.put("y",y);
                lines.getLast().add(point);
                lines.add(lines.getLast());
                break;
            case MotionEvent.ACTION_DOWN:
                recycle.clear();
                LinkedList<HashMap<String,Float>> line=new LinkedList<>();
                HashMap<String,Float>point2 =new HashMap<>();
                point2.put("x",x);
                point2.put("y",y);
                line.add(point2);
                lines.add(line);
            break;
        }
        invalidate();


        return true;
        //return super.onTouchEvent(event);
    }
    public void doClear(){
        lines.clear();
        invalidate();
    }
    public void unDo(){
        if(lines.size()>0){
            recycle.add(lines.removeLast());
        }
        invalidate();
    }
    public void reDo(){
        if(recycle.size()>0){
            lines.add(recycle.removeLast());
        }
        invalidate();
    }

}
