package com.lenabru.keyboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Lena Brusilovski on 30-May 2017.
 */

public class HistoryAdapter
		extends ArrayAdapter<HistoryLine> {

	public HistoryAdapter(@NonNull Context context) {
		super(context, android.R.layout.simple_list_item_1);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		}
		vh = (ViewHolder) convertView.getTag();
		vh.textView.setText(getItem(position).display());
		return convertView;
	}

	private static class ViewHolder {

		private TextView textView;

		public ViewHolder(View v) {
			textView = (TextView) v.findViewById(android.R.id.text1);
		}
	}
}
