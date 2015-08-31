package info.androidhive.tabsswipe;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/7/30.
 */
public class GetListActivity extends AsyncTask<String,Void,String> {
    private ListView listView;
    private List<Movie> movieList;
    private String link="http://192.168.2.110/android.php?root=";
    private String addon;
    private TextView middleText;
    private SwipeListAdapter adapter;
    private  int rank=11;
    private String title="hahaha";
    private URI uri;
    public TopRatedFragment topRatedFragment;
    public GetListActivity(TopRatedFragment t,List<Movie> lv){
        topRatedFragment=t;
        movieList=lv;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... strings) {
        try{

        /*
            addon="aa";
            link=link+addon;
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(link));
            HttpResponse response = client.execute(httpGet);
            BufferedReader stringBuffer=new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while ((line = stringBuffer.readLine()) != null) {
                sb.append(line);
                break;
            }
            stringBuffer.close();
            title=sb.toString();*/
          //  listView = (ListView)topRatedFragment.getActivity().findViewById(R.id.listView);
          //  Movie m = new Movie(rank, title);
           // movieList.add(0, m);

          //  if(adapter==null) middleText.setText("adapter");
          //  if(listView==null) middleText.setText("listview");
          //  if(movieList==null) middleText.setText("movieList");
           //
            adapter = new SwipeListAdapter(topRatedFragment.getActivity(), movieList);
            listView.setAdapter(adapter);
        }
        catch (Exception e){
            middleText.setText(e.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {

    }
}
