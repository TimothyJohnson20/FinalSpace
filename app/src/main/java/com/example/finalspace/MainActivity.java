package com.example.finalspace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private boolean hasRestarted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constants.VIEW_WIDTH = displayMetrics.widthPixels;
        Constants.VIEW_HEIGHT = displayMetrics.heightPixels;
        setContentView(new GamePanel(this));
    }
    /*** @Override
    protected void onRestart() {
        Log.d("hmmm", "onResume: happening");
        if(hasRestarted) {GamePanel.triggerRebirth(this);}
        else {hasRestarted = true;}
        super.onRestart();
    }**/
}
