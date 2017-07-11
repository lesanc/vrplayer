package name.lixianke.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import name.lixianke.vrplayer.FileInfo;

/**
 * Created by xiankeli on 2017/7/11.
 */

public class FileAdapter extends BaseAdapter {
    private Context mContext;
    private List<FileInfo> mFileList;

    public FileAdapter(Context context, List<FileInfo> fileList) {
        this.mContext = context;
        this.mFileList = fileList;
    }

    @Override
    public int getCount() {
        return mFileList.size();
    }

    @Override
    public Object getItem(int i) {
        return mFileList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tv = null;

        if (view != null){
            tv = (TextView)view;
        } else {
            tv = new TextView(mContext);
        }

        tv.setHeight(100);
        tv.setText(mFileList.get(i).getName());

        return tv;
    }
}
