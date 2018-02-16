package test.com.pfchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import test.com.pfchallenge.adapters.PfAdapter;
import test.com.pfchallenge.custom.EndlessRecyclerOnScrollListener;
import test.com.pfchallenge.entities.Property;
import test.com.pfchallenge.network.PfHelper;

public class MainActivity extends AppCompatActivity implements MainView {

	RecyclerView propertyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		propertyList = findViewById(R.id.property_list);
		propertyList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
			@Override
			public void onLoadMore() {
				getPropertiesData();
			}
		});

		getPropertiesData();

	}

	private void getPropertiesData() {
		PfHelper.getPropertiesList(this, this);
	}

	@Override
	public void updateList(ArrayList<Property> properties) {

		if (propertyList != null) {
			if (propertyList.getAdapter() == null) {
				propertyList.setLayoutManager(new LinearLayoutManager(this));
				propertyList.setAdapter(new PfAdapter(properties, this));
			} else {
				((PfAdapter) propertyList.getAdapter()).updateListData(properties);
			}
		}
	}
}
