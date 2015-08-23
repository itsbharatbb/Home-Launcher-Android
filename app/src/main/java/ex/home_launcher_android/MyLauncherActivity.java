package ex.home_launcher_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MyLauncherActivity extends Activity {
    RelativeLayout launcherLayout;
    GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_launcher);
        launcherLayout = (RelativeLayout) findViewById(R.id.launcherLayout);
        detector = new GestureDetector(this,ogl);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    GestureDetector.OnGestureListener ogl = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Toast.makeText(getBaseContext(),"onShowPress",Toast.LENGTH_SHORT).show();

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(getBaseContext(),"OnSingleTap",Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Toast.makeText(getBaseContext(), "OnScroll", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(getBaseContext(),"onLongPress",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(getBaseContext(),"OnFling",Toast.LENGTH_SHORT).show();
            return true;
        }
    };



}
