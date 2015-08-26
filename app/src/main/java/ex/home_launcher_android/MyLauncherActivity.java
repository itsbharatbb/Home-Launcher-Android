package ex.home_launcher_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyLauncherActivity extends Activity {
    RelativeLayout launcherLayout;
    GestureDetector detector;
    GestureDetector.OnGestureListener ogl = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getY() > e2.getY()){
                startActivity(new Intent(MyLauncherActivity.this,AllAppsActivity.class));
                overridePendingTransition(0,0);
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_launcher);
        launcherLayout = (RelativeLayout) findViewById(R.id.launcherLayout);
        detector = new GestureDetector(this,ogl);

    }

/*
* OnTouchEvent is a method which passes touches to detector
*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return this.detector.onTouchEvent(event);
    }



}
