package com.example.umiacs.cmsc434doodler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class CMSC434Doodler extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener {
    Switch erase;
    private int rProgress, bProgress, gProgress, opacity;
    private float brushSize;
    private DoodleView doodle;
    private SeekBar redSeekBar, blueSeekBar, greenSeekBar, opacitySeekBar, brushSizeSeekBar;
    private Button clear, undo, save;

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cmsc434_doodler);

            doodle = (DoodleView) findViewById(R.id.doodleCanvas);
            redSeekBar = (SeekBar) findViewById(R.id.colorSliderRed);
            blueSeekBar = (SeekBar) findViewById(R.id.colorSliderBlue);
            greenSeekBar = (SeekBar) findViewById(R.id.colorSliderGreen);
            opacitySeekBar = (SeekBar) findViewById(R.id.opacitySeekBar);
            brushSizeSeekBar = (SeekBar) findViewById(R.id.brushSizeSeekBar);
            undo = (Button) findViewById(R.id.undoButton);
            clear = (Button) findViewById(R.id.clearButton);
            save = (Button) findViewById(R.id.saveButton);
            erase = (Switch) findViewById(R.id.eraserSwitch);
            erase.setOnCheckedChangeListener(this);

            //set defaults
            redSeekBar.setProgress(0);
            blueSeekBar.setProgress(0);
            greenSeekBar.setProgress(0);
            brushSizeSeekBar.setProgress(2);
            opacitySeekBar.setProgress(255);

            redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    rProgress = progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Context context = getApplicationContext();
                    CharSequence text = "Color: R: " + rProgress + " G: " + gProgress + " B: "
                            + bProgress + " Alpha: "+opacity;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });

            blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    bProgress = progress;
                    changeColor();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Context context = getApplicationContext();
                    CharSequence text = "Color: R: " + rProgress + " G: " + gProgress + " B: "
                            + bProgress + " Alpha: "+opacity;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });

            greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    gProgress = progress;
                    changeColor();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                Context context = getApplicationContext();
                CharSequence text = "Color: R: " + rProgress + " G: " + gProgress + " B: "
                        + bProgress + " Alpha: "+opacity;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                }
            });

            opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    opacity = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    doodle.changeOpacity(opacity);
                    Context context = getApplicationContext();
                    CharSequence text = "Color: R: " + rProgress + " G: " + gProgress + " B: "
                            + bProgress + " Alpha: "+opacity;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            });

            brushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    brushSize = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    doodle.changeBrushSize((float) brushSize);
                }

            });


            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doodle.undo();
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doodle.clear();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = getApplicationContext();
                    CharSequence text = "Saved to gallery!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            });
    }


    private void changeColor() {
        int color = Color.rgb(rProgress, gProgress, bProgress);
        doodle.changeColor(color);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked) {
            DoodleView.erase = true;
            doodle.setPaintProperties();
            //}
        } else {

            doodle.setPaintProperties();
        }
    }
}




