package name.lixianke.vrplayer;

/**
 * Created by lixianke on 2017/7/11.
 */

public interface ScanFileCallback {
    void onSuccess();
    void onFailure();
    void onAbort();
}
