package name.lixianke.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import name.lixianke.vrplayer.FileInfo;
import name.lixianke.vrplayer.R;

/**
 * Created by xiankeli on 2017/7/11.
 */

public class FileAdapter extends BaseAdapter {
    private Activity mContext;
    private List<FileInfo> mFileList;

    public FileAdapter(Activity context, List<FileInfo> fileList) {
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
        LinearLayout layout = null;



        if (view != null){
            layout = (LinearLayout)view;
        } else {
            LayoutInflater inflater = mContext.getLayoutInflater();
            layout = (LinearLayout)inflater.inflate(R.layout.layout_list_item, null);
        }

        TextView tv = (TextView)layout.findViewById(R.id.file_name);
        tv.setText(mFileList.get(i).getName());

        return layout;
    }
}
