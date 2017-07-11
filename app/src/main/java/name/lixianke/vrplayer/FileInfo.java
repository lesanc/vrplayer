package name.lixianke.vrplayer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixianke on 2017/7/11.
 */

public class FileInfo implements Serializable {
    private String name;
    private String path;
    private boolean isFolder;
    private List<FileInfo> fileList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public List<FileInfo> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileInfo> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isFolder=" + isFolder +
                '}';
    }
}
