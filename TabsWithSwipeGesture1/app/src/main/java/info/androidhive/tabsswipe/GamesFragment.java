package info.androidhive.tabsswipe;

import info.androidhive.tabsswipe.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.telephony.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.R.menu.*;
import android.telephony.TelephonyManager;

public class GamesFragment extends Fragment {
	private EditText usernameField,passwordField;
	private TextView status,role,method;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_games, container, false);
		usernameField = (EditText)rootView.findViewById(R.id.editText1);
		passwordField = (EditText)rootView.findViewById(R.id.editText2);
		status = (TextView)rootView.findViewById(R.id.textView6);
		role = (TextView)rootView.findViewById(R.id.textView7);
		method = (TextView)rootView.findViewById(R.id.textView9);
		Button btn = (Button) rootView.findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				login(v);
			}
		});
		loginPost(rootView);
		setHasOptionsMenu(true);
		return rootView;
	}

	public void popup(){
		LayoutInflater layoutInflater
				= (LayoutInflater)getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.popup, null);
		final PopupWindow popupWindow = new PopupWindow(
				popupView,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
		btnDismiss.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
		popupWindow.showAsDropDown(popupView, 0, 0);
	}
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu,inflater);
	}
	public void login(View view){
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		method.setText("Get Success");
		//new SigninActivity(this,status, role, 0).execute(username, password);
	}

	public void loginPost(View view){
		String phoneImei=getImei();
		method.setText(phoneImei);
		//new SigninActivity(Context);
		//new SigninActivity(this,status, role, 1).execute(phoneImei);
	}
	public String getImei(){
		TelephonyManager telephonyManager=(TelephonyManager) this.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		String phoneImei=telephonyManager.getDeviceId();
		return phoneImei;
	}
}
