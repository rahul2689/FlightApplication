package com.ixigo.HomePage;

import java.util.ArrayList;
import java.util.List;

import com.ixigo.entities.FlightResponseData;
import com.ixigo.entities.FlightsData;
import com.ixigo.flightapp.R;
import com.ixigo.utils.UiHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomePageAdapter extends BaseAdapter {

	private Context mContext;
	private FlightResponseData mFlightResponseData;
	private List<FlightsData> mFlightsData;
	private String time;

	public HomePageAdapter(Context context) {
		mContext = context;
		mFlightsData = new ArrayList<FlightsData>();
	}

	public void updateData(FlightResponseData responseData) {
		mFlightResponseData = responseData;
		mFlightsData = responseData.getFlightsData();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mFlightsData.size();
	}

	@Override
	public FlightsData getItem(int position) {
		return mFlightsData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.flights_data, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mDepartureTimeTv.setText(convertTimeToHours(mFlightsData
				.get(position).getDepartureTime()));
		viewHolder.mArrivalTimeTv.setText(convertTimeToHours(mFlightsData.get(
				position).getArrivalTime()));
		viewHolder.mFightNameTv.setText(getFlightName(mFlightsData
				.get(position).getAirlineCode()));
		viewHolder.mFlightDurationTv.setText(setDuration(position));
		viewHolder.mFlightClassTv.setText(mFlightsData.get(position)
				.getFlightClass());
		viewHolder.mPriceTv.setText(mFlightsData.get(position).getPrice());
		UiHelper.getInstance().applyRupeeFont(viewHolder.mPriceTv, 0);
		viewHolder.mFlightOriginTv.setText(getAirport(mFlightsData
				.get(position).getOriginCode()));
		viewHolder.mFlightDestinationTv.setText(getAirport(mFlightsData.get(
				position).getDestinationCode()));
		return convertView;
	}

	private String setDuration(int position) {
		long departTime = mFlightsData.get(position).getDepartureTime();
		long arrivalTime = mFlightsData.get(position).getArrivalTime();
		long duration = arrivalTime - departTime ; 
		long seconds = duration / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long hrs = hours % 24;
		long  mins = minutes % 60;
		if (mins == 0) {
			return time = hrs + " " + "hrs";
		}
		return time = hrs + " " + "hrs" + " " + mins + "mins" ;
	}

	private String getAirport(String originCode) {
		if (originCode.equals("DEL")) {
			return mFlightResponseData.getAirportMap().getDepartureAirport();
		} else {
			return mFlightResponseData.getAirportMap().getArrivalAirport();
		}
	}

	private String getFlightName(String code) {
		if (code.equals("G8")) {
			return mFlightResponseData.getAirlineMap().getG8CodeValue();
		} else if (code.equals("SJ")) {
			return mFlightResponseData.getAirlineMap().getSJCodeValue();
		} else if (code.equals("AI")) {
			return mFlightResponseData.getAirlineMap().getAICodeValue();
		} else if (code.equals("JA")) {
			return mFlightResponseData.getAirlineMap().getJACodeValue();
		} else {
			return mFlightResponseData.getAirlineMap().getINCodeValue();
		}
	}

	private String convertTimeToHours(long timeInMillis) {
		long seconds = timeInMillis / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long hrs = hours % 24;
		long  mins = minutes % 60;
		if (mins == 0) {
			String min = "00";
			return time = hrs + ":" + min ;
		}
		time = hrs + ":" + mins;
		return time;
	}

	class ViewHolder {
		private TextView mDepartureTimeTv;
		private TextView mArrivalTimeTv;
		private TextView mFightNameTv;
		private TextView mFlightDurationTv;
		private TextView mFlightClassTv;
		private TextView mPriceTv;
		private TextView mFlightOriginTv;
		private TextView mFlightDestinationTv;

		public ViewHolder(View view) {
			mDepartureTimeTv = (TextView) view
					.findViewById(R.id.tv_flight_departure_time);
			mArrivalTimeTv = (TextView) view
					.findViewById(R.id.tv_flight_arrival_time);
			mFightNameTv = (TextView) view.findViewById(R.id.tv_flight_name);
			mFlightDurationTv = (TextView) view
					.findViewById(R.id.tv_flight_duration);
			mFlightClassTv = (TextView) view.findViewById(R.id.tv_flight_class);
			mPriceTv = (TextView) view.findViewById(R.id.tv_flight_price);
			mFlightOriginTv = (TextView) view
					.findViewById(R.id.tv_flight_origin);
			mFlightDestinationTv = (TextView) view
					.findViewById(R.id.tv_flight_destination);

		}
	}

	public void updateData(List<FlightsData> flightsData) {
		mFlightsData = flightsData;
	}
}
