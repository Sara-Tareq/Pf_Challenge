package test.com.pfchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import test.com.pfchallenge.R;


public class SortActivity extends AppCompatActivity implements View.OnClickListener {

	private Button pdButton, paButton, bdButton, baButton;
	private String selectedOrder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sort_activity);
		setUpView();
	}

	private void setUpView() {
		pdButton = findViewById(R.id.pd_button);
		pdButton.setOnClickListener(this);

		paButton = findViewById(R.id.pa_button);
		paButton.setOnClickListener(this);

		bdButton = findViewById(R.id.bd_button);
		bdButton.setOnClickListener(this);

		baButton = findViewById(R.id.ba_button);
		baButton.setOnClickListener(this);
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
}
