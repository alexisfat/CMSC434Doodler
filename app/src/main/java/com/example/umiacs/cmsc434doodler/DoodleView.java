package com.example.umiacs.cmsc434doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;


/**
 * Created by umiacs on 11/2/16.
 */

class DoodleView extends View {
    private Paint _paintDoodle = new Paint();
    private Path _path = new Path();
    private ArrayList<Path> _paths;
    private ArrayList<Paint> _paintDoodles;
   // private ArrayList<Path> _allPaths;
   // private ArrayList<Paint> _allPaints;
    private int _opacity;
    private int _color;
    private float _brushSize;
    public static boolean erase;

  //  private int undoCount=0;
  //  private List<Paint> undoPaints = new ArrayList<>();
   // private List<Path> undoPaths = new ArrayList<>();



    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        _color = Color.BLACK;
        _opacity = 100;
        _brushSize = 2;
        erase = false;
        _path = new Path();
        _paintDoodle = new Paint();
        _paintDoodle.setStyle(Paint.Style.STROKE);

        _paths = new ArrayList<>();
        _paintDoodles = new ArrayList<>();
        _paintDoodle.setColor(_color);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeJoin(Paint.Join.ROUND);
        _paintDoodle.setStrokeWidth(_brushSize);
        _paintDoodle.setAlpha(_opacity);
    }

    public void draw() {
        if(!erase) {
            _paintDoodle = new Paint();
            _path = new Path();
            _paintDoodle.setAntiAlias(true);
            _paintDoodle.setStyle(Paint.Style.STROKE);
            _paintDoodle.setColor(_color);
            _paintDoodle.setStrokeWidth(_brushSize);
            _paintDoodle.setAlpha(_opacity);
        } else {
            _paintDoodle = new Paint();
            _path = new Path();
            _paintDoodle.setAntiAlias(true);
            _paintDoodle.setStyle(Paint.Style.STROKE);
            _paintDoodle.setARGB(255, 249, 249, 249);
           // _paintDoodle.setColor(Color.rgb(249,249,249));
            _paintDoodle.setStrokeWidth(100);
           // _paintDoodle.setAlpha(255);
        }
        //invalidate();
    }





    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawLine(0,0, getWidth(), getHeight(), _paintDoodle);
      //  _paintDoodle.setStyle(Paint.Style.STROKE);
        //System.out.println("OnDraw Method " +_paintDoodle.getStyle());
        canvas.drawPath(_path, _paintDoodle);

        for(int i=0; i<_paths.size(); i++){
            canvas.drawPath(_paths.get(i), _paintDoodles.get(i));
        }
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //_path.addCircle(touchX, touchY, (float)_brushSize/16, Path.Direction.CCW );
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);

                break;
            case MotionEvent.ACTION_UP:
                _paths.add(_path);
                _paintDoodles.add(_paintDoodle);
                _path = new Path();
                _paintDoodle = new Paint();
                _paintDoodle.setStyle(Paint.Style.STROKE);
                break;
        }
        System.out.println(_paintDoodle.getStyle());
        invalidate();
        return true;
    }

    public void changeOpacity(int op){
        _opacity = Math.abs(255-op);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        draw();

    }

    public void changeBrushSize(float brush){
        _brushSize = brush;
        _paintDoodle.setStyle(Paint.Style.STROKE);
        draw();
    }

    public void changeColor(int color){
        _color = color;
        _paintDoodle.setStyle(Paint.Style.STROKE);
        draw();
    }

    public void undo(){
        if(_paths.size() > 0 && _paintDoodles.size() > 0){
            _paths.remove(_paths.size() - 1);
            _paintDoodles.remove(_paintDoodles.size() - 1);
            invalidate();
        }
    }

    /*public void redo(){
        //_path.rewind();
        /*if(_allPaints.size() > 0 && _allPaths.size() > 0) {
            _paintDoodles.add(_allPaints.get(_allPaints.size() - undoCount + 1));
            _paths.add(_allPaths.get(_allPaths.size() - undoCount + 1));
        }
        if(!undoPaths.isEmpty() && !undoPaints.isEmpty()){

            _paths.add(undoPaths.get(undoCount));
            _paintDoodles.add(undoPaints.get(undoCount));
        }
        invalidate();
    }*/


    public void clear(){
        _paths.clear();
        _paintDoodles.clear();
        invalidate();
    }

}
