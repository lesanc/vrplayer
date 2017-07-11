package name.lixianke.vrplayer;

import android.os.Environment;

import java.io.File;

/**
 * Created by lixianke on 2017/7/11.
 */

public class FileManager {
    public static FileManager instance = new FileManager();

    public FileManager getInstance(){
        return instance;
    }

    public void scan(String rootPath){

    }

    public void scan(File rootFile){

    }

    public void scanAll(){
        File root = Environment.getExternalStorageDirectory();
        scan(root);
    }
}
