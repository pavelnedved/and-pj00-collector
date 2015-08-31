package info.androidhive.tabsswipe.asynctask;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import info.androidhive.tabsswipe.LocalListActivity;
import info.androidhive.tabsswipe.R;

/**
 * Created by user on 2015/8/6.
 */
public class LocalListAsync extends AsyncTask<String,Void,String>{
    private TextView localtitle;
    private TextView localtext;
    private LocalListActivity localListActivity;
    public LocalListAsync(LocalListActivity lv){
        localListActivity=lv;
        localtitle=(TextView)lv.findViewById(R.id.localtitle);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
