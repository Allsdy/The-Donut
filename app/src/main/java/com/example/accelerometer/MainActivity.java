package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public class GraphicView extends View {
        private Donut d;

        class Donut{
            private int r1;
            private int r2;
            private float degCircle;
            private float degRotateY;
            private float degRotateX = 0f;
            private float degRotateZ = 0f;

            public Donut(int r1, int r2, float degCircle, float degRotateY){
                this.r1 = r1;
                this.r2 = r2;
                this.degCircle = degCircle;
                this.degRotateY = degRotateY;
            }

            public void draw(Canvas canvas){
//                canvas.drawCircle(this.x, this.y, this.r, this.p);
                Paint p = new Paint(R.color.purple_700);

                for(float i = 0f; i < 2 * Math.PI; i += (Math.PI / 12)){
                    for(float j = 0f; j < 2 * Math.PI; j += (Math.PI / 12)){

                        //Static Donut
//                        canvas.drawCircle(
//                                (float)((this.r2 + this.r1 * Math.cos(j)) * Math.cos(i)) + 550,
//                                (float)(this.r1 * Math.sin(j)) + 800,
//                                3,
//                                p
//                        );

                        //Donut rotating along x axis
//                        canvas.drawCircle(
//                                (float)((this.r2 + this.r1 * Math.cos(j)) * Math.cos(i)) + 550,
//                                (float)(this.r1 * Math.sin(j) * Math.cos(this.degRotateX) - (this.r2 + this.r1 * Math.cos(j)) * Math.sin(i) * Math.sin(this.degRotateX)) + 800,
//                                3,
//                                p
//                        );

                        //Donut rotating along x and z axis
                        canvas.drawCircle(
                                (float)((r2 + r1 * Math.cos(j)) * Math.cos(i) * Math.cos(degRotateZ) +
                                        (r1 * Math.sin(j) * Math.cos(degRotateX) - (r2 + r1 * Math.cos(j)) * Math.sin(i) * Math.sin(degRotateX)) * Math.sin(degRotateZ)) + 550,
                                (float)((r1 * Math.sin(j) * Math.cos(degRotateX) - (r2 + r1 * Math.cos(j)) * Math.sin(i) * Math.sin(degRotateX)) * Math.cos(degRotateZ) -
                                        (r2 + r1 * Math.cos(j)) * Math.cos(i) * Math.sin(degRotateZ)) + 800,
                                3,
                                p
                        );
                    }
                }

                degRotateX += (Math.PI / 72);
                if(degRotateX == 2 * Math.PI){
                    degRotateX = 0f;
                }

                degRotateZ += (Math.PI / 72);
                if(degRotateZ == 2 * Math.PI){
                    degRotateZ = 0f;
                }

            }

        }

        public GraphicView(Context context){
            super(context);
            d = new Donut(60, 100, 0f, 0f);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            d.draw(canvas);

            invalidate();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);

        ConstraintLayout root = (ConstraintLayout)findViewById(R.id.drwaView);
        GraphicView myView = new GraphicView(this);
        root.addView(myView);
    }


}