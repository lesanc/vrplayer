package name.lixianke.vrplayer;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianke on 2017/7/11.
 */

public class FileManager implements ScanFileCallback {
    private static final String TAG = "FileManager";
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
    public void onSuccess(List<FileInfo> fileList) {
        if (mCallback != null){
            mCallback.onSuccess(fileList);
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
        if (rootFile == null || !rootFile.exists()){
            onFailure();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInfo fileInfo = doScan(rootFile);
                onSuccess(fileInfo.getFileList());
            }
        }).start();
    }

    public void scanAll(){
        File root = Environment.getExternalStorageDirectory();
        scan(root);
    }

    private FileInfo doScan(File file){
        if (file == null || !file.exists()){
            return null;
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setPath(file.getAbsolutePath());

        if (file.isFile()){
            fileInfo.setFolder(false);
            return fileInfo;
        } else if (file.isDirectory()) {
            fileInfo.setFolder(true);
            File[] files = file.listFiles();
            if (files == null || files.length == 0){
                return fileInfo;
            }

            List<FileInfo> list = new ArrayList<>();
            for (int i = 0; i < files.length; i++){
                FileInfo info = doScan(files[i]);
                if (info != null){
                    list.add(info);
                }
            }
            fileInfo.setFileList(list);
            return fileInfo;
        }

        return null;
    }
}
