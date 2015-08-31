package info.androidhive.tabsswipe;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MoviesFragment extends Fragment implements LocationListener, SwipeRefreshLayout.OnRefreshListener {
	private TextView method;
//	private TextView locTextView;
	private LocationManager locationManager;
	double lat=0;
	double lng=0;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView localList;
	private TextView tv;
	private SwipeListAdapter adapter;
	private List<Movie> movieList = new ArrayList<>();
	public int rank = 5;
	public String title = "idk";
	private String provider;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
		tv=(TextView)rootView.findViewById(R.id.test00);
		try{
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = getLastKnownLocation();
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
				buildAlertMessageNoGps();
			}
		}
		localList = (ListView)rootView.findViewById(R.id.localList);
		swipeRefreshLayout = (SwipeRefreshLayout) rootView;
		movieList = new ArrayList<>();

			swipeRefreshLayout.setOnRefreshListener(this);
			swipeRefreshLayout.post(new Runnable() {
										@Override
										public void run() {
											swipeRefreshLayout.setRefreshing(true);
											getNewList();
										}
									}
			);
		}
		catch(Exception e){
		tv.setText(e.toString());
		}
		return rootView;

	}
	public void onRefresh(){
		getNewList();
	}




	public void onLocationChanged(Location location) {
		lat = location.getLatitude();
		lng = location.getLongitude();
		//locTextView.setText(getLocationName(lat,lng));
	}
	public void onStatusChanged(String provider, int status, Bundle extras) {


	}
	public void onProviderEnabled(String provider) {
		Toast.makeText(this.getActivity(), "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this.getActivity(), "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	private Location getLastKnownLocation() {
		locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getProviders(true);
		Location bestLocation = null;
		for (String provider : providers) {
			Location l = locationManager.getLastKnownLocation(provider);
			if (l == null) {
				continue;
			}
			if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
				bestLocation = l;
			}
		}
		return bestLocation;
	}
	public String getLocationName(double lattitude, double longitude) {
		String cityName = "Not Found";
		if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			buildAlertMessageNoGps();
			return cityName;
		}
		try {
		Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());

			List<Address> addresses = gcd.getFromLocation(lattitude, longitude,
					10);
			for (Address adrs : addresses) {
				if (adrs != null) {
					String city = adrs.getLocality();
					if (city != null && !city.equals("")) {
						cityName = city;
						System.out.println("city ::  " + cityName);
					} else {
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cityName;
	}
	public void getNewList(){
		movieList = new ArrayList<>();
		rank = rank+1;
		title=getLocationName(lat,lng);
		Movie m = new Movie(rank, title);
		movieList.add(0, m);
		//new GetListActivity(this,movieList).execute();
		adapter = new SwipeListAdapter(this.getActivity(), movieList);
		localList.setAdapter(adapter);
		swipeRefreshLayout.setRefreshing(false);
	}
	private void buildAlertMessageNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
						startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}
}
