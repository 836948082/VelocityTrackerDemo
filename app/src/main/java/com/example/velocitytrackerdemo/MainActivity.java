package com.example.velocitytrackerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements VelocityView.OnTrackerListener{

    private VelocityView mVelocityView;
    private TextView tv_x,tv_y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mVelocityView.setTrackerListener(this);
    }

    private void initView() {
        tv_x = (TextView) findViewById(R.id.tv_xSpeed);
        tv_y = (TextView) findViewById(R.id.tv_ySpeed);
        mVelocityView = (VelocityView) findViewById(R.id.velocityview);
    }


    @Override
    public void getXSpeed(int speed){
        tv_x.setText("X        "+speed+"px/s");
    }

    @Override
    public void getYSpeed(int speed){
        tv_y.setText("Y        "+speed+"px/s");

    }
}
