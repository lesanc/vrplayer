package name.lixianke.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import name.lixianke.vrplayer.FileManager;
import name.lixianke.vrplayer.ScanFileCallback;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileManager fm = FileManager.getInstance();
        fm.setCallback(new ScanFileCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onCreate: FileManager scanAll onSuccess");
            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onAbort() {

            }
        });
        fm.scanAll();
    }
}
