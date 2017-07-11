package name.lixianke.vrplayer;

import android.os.Environment;

import java.io.File;

/**
 * Created by lixianke on 2017/7/11.
 */

public class FileManager implements ScanFileCallback {
    public static FileManager instance = new FileManager();

    public static FileManager getInstance(){
        return instance;
    }

    private ScanFileCallback mCallback;

    public ScanFileCallback getCallback() {
        return mCallback;
    }

    public void setCallback(ScanFileCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onSuccess() {
        if (mCallback != null){
            mCallback.onSuccess();
        }
    }

    @Override
    public void onFailure() {
        if (mCallback != null){
            mCallback.onFailure();
        }
    }

    @Override
    public void onAbort() {

    }

    public void scan(String rootPath){
        scan(new File(rootPath));
    }

    public void scan(final File rootFile){
        if (rootFile == null){
            onFailure();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                onSuccess();
            }
        }).start();
    }

    public void scanAll(){
        File root = Environment.getExternalStorageDirectory();
        scan(root);
    }


}
