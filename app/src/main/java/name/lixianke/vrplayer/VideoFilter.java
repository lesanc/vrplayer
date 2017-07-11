package name.lixianke.vrplayer;

/**
 * Created by lixianke on 2017/7/11.
 */

public class VideoFilter implements Filter {
    private static final String[] suffixs = {".mp4", ".avi", "rmvb", "mkv"};

    @Override
    public boolean rule(FileInfo fileInfo) {
        for (int i = 0; i < suffixs.length; i++){
            if (fileInfo.getPath() != null && fileInfo.getPath().toLowerCase().endsWith(suffixs[i])){
                return true;
            }
        }
        return false;
    }
}
