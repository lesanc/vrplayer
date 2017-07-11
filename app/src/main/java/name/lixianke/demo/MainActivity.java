package name.lixianke.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import name.lixianke.vrplayer.FileInfo;
import name.lixianke.vrplayer.FileManager;
import name.lixianke.vrplayer.R;
import name.lixianke.vrplayer.ScanFileCallback;
import name.lixianke.vrplayer.VideoFilter;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private ListView mVideoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);
        mVideoListView = (ListView)findViewById(R.id.videolist);

        scanVideos();
    }

    private void scanVideos() {
        FileManager fm = FileManager.getInstance();
        fm.setCallback(new ScanFileCallback() {
            @Override
            public void onSuccess(final List<FileInfo> fileList) {
                Log.d(TAG, "onCreate: FileManager scanAll onSuccess");
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showList(fileList);
                    }
                });
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

    private void showList(List<FileInfo> fileList){
        if (mVideoListView != null && fileList != null && fileList.size() > 0){
            mVideoListView.setAdapter(new FileAdapter(this, fileList));

            mVideoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d(TAG, "onItemClick: " + i);
                }
            });
        }
    }
}
