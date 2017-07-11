package name.lixianke.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import name.lixianke.vrplayer.FileInfo;
import name.lixianke.vrplayer.FileManager;
import name.lixianke.vrplayer.ScanFileCallback;
import name.lixianke.vrplayer.VideoFilter;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileManager fm = FileManager.getInstance();
        fm.setCallback(new ScanFileCallback() {
            @Override
            public void onSuccess(List<FileInfo> fileList) {
                Log.d(TAG, "onCreate: FileManager scanAll onSuccess");
                for (FileInfo file : fileList) {
                    Log.d(TAG, file.toString());
                }
            }

            @Override
            public void onFailure() {
                Log.d(TAG, "onCreate: FileManager scanAll onFailure");
            }

            @Override
            public void onAbort() {
                Log.d(TAG, "onCreate: FileManager scanAll onAbort");
            }
        });
        fm.scanAll(new VideoFilter());
    }
}
