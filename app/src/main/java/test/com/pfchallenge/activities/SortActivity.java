package test.com.pfchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import test.com.pfchallenge.R;


public class SortActivity extends AppCompatActivity implements View.OnClickListener {

	private String selectedOrder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sort_activity);
		setUpView();
		selectedOrder = savedInstanceState != null ? savedInstanceState.getString("selectedOrder", "") : "";
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("selectedOrder", selectedOrder);
	}

	private void setUpView() {
		findViewById(R.id.pd_button).setOnClickListener(this);
		findViewById(R.id.pa_button).setOnClickListener(this);
		findViewById(R.id.bd_button).setOnClickListener(this);
		findViewById(R.id.ba_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pd_button:
				selectedOrder = "pd";
				break;
			case R.id.pa_button:
				selectedOrder = "pa";
				break;
			case R.id.bd_button:
				selectedOrder = "bd";
				break;
			case R.id.ba_button:
				selectedOrder = "ba";
				break;
		}
	}

	@Override
	public void onBackPressed() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("selectedOrder", selectedOrder);
		setResult(RESULT_OK, resultIntent);
		super.onBackPressed();
	}

	public String getSelectedOrder() {
		return selectedOrder;
	}
}
