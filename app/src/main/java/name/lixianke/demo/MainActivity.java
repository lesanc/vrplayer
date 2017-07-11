package name.lixianke.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File root = Environment.getExternalStorageDirectory();
        Log.d(TAG, "onCreate: " + root.getAbsolutePath());
    }
}
