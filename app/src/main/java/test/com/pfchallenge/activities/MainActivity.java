package test.com.pfchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import test.com.pfchallenge.MainView;
import test.com.pfchallenge.R;
import test.com.pfchallenge.adapters.PfAdapter;
import test.com.pfchallenge.custom.EndlessRecyclerOnScrollListener;
import test.com.pfchallenge.entities.Property;
import test.com.pfchallenge.network.PfHelper;

public class MainActivity extends AppCompatActivity implements MainView {

	private RecyclerView propertyList;
	private ProgressBar progressBar;
	private String selectedOrder;
	private int pageNum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		progressBar = findViewById(R.id.progress);
		propertyList = findViewById(R.id.property_list);
		propertyList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
			@Override
			public void onLoadMore() {
				getPropertiesData();
			}
		});


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			selectedOrder = data.getStringExtra("selectedOrder");
			clearView();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPropertiesData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_sort) {
			Intent sortIntent = new Intent(this, SortActivity.class);
			startActivityForResult(sortIntent, 1);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void clearView() {
		pageNum = 0;
		showProgress();
		((PfAdapter) propertyList.getAdapter()).clearData();
	}

	private void getPropertiesData() {
		PfHelper.getPropertiesList(this, this, pageNum, selectedOrder);
	}

	@Override
	public void showProgress() {
		propertyList.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		propertyList.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
	}

	@Override
	public void updateList(ArrayList<Property> properties) {

		if (propertyList != null) {
			pageNum++;
			hideProgress();
			if (propertyList.getAdapter() == null) {
				propertyList.setLayoutManager(new LinearLayoutManager(this));
				propertyList.setAdapter(new PfAdapter(properties, this));
			} else {
				((PfAdapter) propertyList.getAdapter()).updateListData(properties);
			}
		}
	}
}
