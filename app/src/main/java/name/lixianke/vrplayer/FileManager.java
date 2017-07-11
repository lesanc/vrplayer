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

    public void scan(String rootPath, final Filter filter){
        scan(new File(rootPath), filter);
    }

    public void scan(final File rootFile, final Filter filter){
        if (rootFile == null || !rootFile.exists()){
            onFailure();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInfo fileInfo = doScan(rootFile, filter);
                onSuccess(fileInfo.getFileList());
            }
        }).start();
    }

    public void scanAll(){
        scanAll(null);
    }

    public void scanAll(Filter filter){
        File root = Environment.getExternalStorageDirectory();
        scan(root, filter);
    }

    private FileInfo doScan(File file, Filter filter){
        if (file == null || !file.exists() || file.isHidden()){
            return null;
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setPath(file.getAbsolutePath());

        if (file.isFile()){
            if (filter != null && !filter.rule(fileInfo)){
                return null;
            }
            fileInfo.setFolder(false);
            return fileInfo;
        } else if (file.isDirectory()) {
            fileInfo.setFolder(true);
            File[] files = file.listFiles();
            if (files != null) {
                List<FileInfo> list = new ArrayList<>();
                for (int i = 0; i < files.length; i++) {
                    FileInfo info = doScan(files[i], filter);
                    if (info != null) {
                        info.setParent(fileInfo);
                        list.add(info);
                    }
                }

                if (list.size() > 0) {
                    fileInfo.setFileList(list);
                    return fileInfo;
                }
            }
        }

        return null;
    }
}
