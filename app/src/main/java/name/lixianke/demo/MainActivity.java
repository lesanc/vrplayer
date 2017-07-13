package name.lixianke.demo;

import android.app.Activity;
import android.content.Intent;
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
    private FileInfo mLastFolder;

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
                        findViewById(R.id.loading).setVisibility(View.GONE);
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

    private void showList(final List<FileInfo> fileList){
        if (mVideoListView != null && fileList != null && fileList.size() > 0){
            mLastFolder = fileList.get(0).getParent().getParent();

            mVideoListView.setAdapter(new FileAdapter(this, fileList));
            mVideoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d(TAG, "onItemClick: " + i);
                    FileInfo fileInfo = fileList.get(i);

                    if (fileInfo.isFolder()){
                        showList(fileInfo.getFileList());
                    } else {
                        goPlay(fileInfo);
                    }
                }
            });
        }
    }

    private void goPlay(FileInfo fileInfo){
        Intent intent = new Intent(MainActivity.this, VideoPlayActivity.class);
        intent.putExtra("path", fileInfo.getPath());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mLastFolder != null){
            showList(mLastFolder.getFileList());
        } else {
            super.onBackPressed();
        }
    }
}
