package info.androidhive.tabsswipe;

import info.androidhive.tabsswipe.adapter.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TopRatedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView listView;
	private SwipeListAdapter adapter;
	//private TextView middleText;
	private List<Movie> movieList = new ArrayList<>();
	private String URL = "http://192.168.2.110/android.php?root=";
	private String offSet="aa";
	public int rank = 5;
	public String title = "idk";
	public void onRefresh() {
		fetchMovies();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
		listView = (ListView)rootView.findViewById(R.id.listView);
		final Context context=rootView.getContext();
		listView.setOnItemClickListener(
				new AdapterView.OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
											int position, long id) {
						try{
						Intent intent = new Intent(context,LocalListActivity.class);
						startActivity(intent);
						 }
						catch (Exception e){
							title=e.toString();
						}
						//Take action here.
					}
				}
		);
		swipeRefreshLayout = (SwipeRefreshLayout) rootView;
//		middleText=(TextView)rootView.findViewById(R.id.idk);
		movieList = new ArrayList<>();
//		adapter = new SwipeListAdapter(this.getActivity(), movieList);
		if(adapter!=null){
//			idk.setText("idk");
		}
		try{
			swipeRefreshLayout.setOnRefreshListener(this);
			swipeRefreshLayout.post(new Runnable() {
										@Override
										public void run() {
											swipeRefreshLayout.setRefreshing(true);
											fetchMovies();
										}
									}
			);
		}
		catch(Exception e){

		}


/*
		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
		swipeRefreshLayout = (SwipeRefreshLayout) rootView;
		adapter = new SwipeListAdapter(this.getActivity(), movieList);
		listView.setAdapter(adapter);

		swipeRefreshLayout.setOnRefreshListener(this);
*/
		/**
		 * Showing Swipe Refresh animation on activity create
		 * As animation won't start on onCreate, post runnable is used


		swipeRefreshLayout.post(new Runnable() {
									@Override
									public void run() {
										swipeRefreshLayout.setRefreshing(true);

										fetchMovies();
									}
								}
		);
*/
		return rootView;
	}
	private void fetchMovies() {
//		swipeRefreshLayout.setRefreshing(true);
//		movieList = new ArrayList<>();
//		String finalUrl = URL + offSet;
//		new GetListActivity(this,middleText).execute();
		movieList = new ArrayList<>();
		rank = rank+1;
		Movie m = new Movie(rank, title);
		movieList.add(0, m);
		//new GetListActivity(this,movieList).execute();
		adapter = new SwipeListAdapter(this.getActivity(), movieList);
		listView.setAdapter(adapter);
		swipeRefreshLayout.setRefreshing(false);
	}


}
