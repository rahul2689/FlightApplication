package com.ixigo.HomePage;

import java.util.Collections;
import java.util.Comparator;

import com.ixigo.entities.FlightResponseData;
import com.ixigo.entities.FlightsData;
import com.ixigo.flightapp.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomePageActivity extends Activity {

	private ListView mListViewData;
	private HomePageAdapter mHomePageAdapter;
	private HomePageDataController mHomePageController;
	private ProgressDialog mProgressDialog;
	private FlightResponseData mResponseData = new FlightResponseData();
	private ListView mDialogListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage_activity);
		mListViewData = (ListView) findViewById(R.id.lv_homepage_data);
		mProgressDialog = new ProgressDialog(HomePageActivity.this);
		mProgressDialog.setMessage("Fetching Data");
		mHomePageAdapter = new HomePageAdapter(HomePageActivity.this);
		mListViewData.setAdapter(mHomePageAdapter);
		mHomePageController = new HomePageDataController();
		mHomePageController.getFlightResponseData(callback);
		mProgressDialog.show();
		mProgressDialog.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_filter) {
			createAlertDialog();
			mDialogListView.setOnItemClickListener(mOnItemClickListener());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private OnItemClickListener mOnItemClickListener() {
		return new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String value = (String) mDialogListView.getAdapter().getItem(
						position);
				if (value.equals("Price")) {
					Crouton.makeText(HomePageActivity.this, getResources().getString(R.string.list_sorted),
							Style.ALERT).show();
					getPriceSorted();
				} else if (value.equals("Class")) {
					Crouton.makeText(HomePageActivity.this, getResources().getString(R.string.list_sorted),
							Style.ALERT).show();
					getClassSorted();
				}
			}
		};
	}

	private void getPriceSorted() {
		Collections.sort(mResponseData.getFlightsData(),
				new Comparator<FlightsData>() {

					@Override
					public int compare(FlightsData object1, FlightsData object2) {
						if (Integer.parseInt(object1.getPrice()) == Integer
								.parseInt(object2.getPrice()))
							return 0;
						return Integer.parseInt(object1.getPrice()) < Integer
								.parseInt(object2.getPrice()) ? -1 : 1;
					}
				});
		mHomePageAdapter.updateData(mResponseData.getFlightsData());
		mHomePageAdapter.notifyDataSetChanged();
	}

	private void getClassSorted() {
		Collections.sort(mResponseData.getFlightsData(),
				new Comparator<FlightsData>() {

					@Override
					public int compare(FlightsData object1, FlightsData object2) {
						return object1.getFlightClass().compareTo(
								object2.getFlightClass());
					}
				});
		mHomePageAdapter.updateData(mResponseData.getFlightsData());
		mHomePageAdapter.notifyDataSetChanged();
	}

	private void createAlertDialog() {
		String names[] = { "Price", "Class"};
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				HomePageActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		View convertView = (View) inflater.inflate(R.layout.alert_dialog, null);
		alertDialog.setView(convertView);
		alertDialog.setTitle(getResources().getString(R.string.dialog_title));
		mDialogListView = (ListView) convertView.findViewById(R.id.lv_alert_dialog);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		mDialogListView.setAdapter(adapter);
		alertDialog.show();
	}

	public ICallback<FlightResponseData, String> callback = new ICallback<FlightResponseData, String>() {

		@Override
		public void success(final FlightResponseData responseData) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mProgressDialog.dismiss();
					mResponseData = responseData;
					mHomePageAdapter.updateData(mResponseData);
				}
			});
		}

		@Override
		public void failure(final String errorMsg) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Crouton.makeText(HomePageActivity.this, errorMsg,
							Style.ALERT).show();
				}
			});
		}
	};
}
