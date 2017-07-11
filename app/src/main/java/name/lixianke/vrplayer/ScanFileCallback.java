package name.lixianke.vrplayer;

import java.util.List;

/**
 * Created by lixianke on 2017/7/11.
 */

public interface ScanFileCallback {
    void onSuccess(List<FileInfo> fileList);
    void onFailure();
    void onAbort();
}
