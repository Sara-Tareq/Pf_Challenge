package test.com.pfchallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import test.com.pfchallenge.R;
import test.com.pfchallenge.entities.Property;

/**
 * Created by Sara_R on 16/02/2018.
 */

public class PfAdapter extends RecyclerView.Adapter<PfAdapter.PropertyViewHolder> {

	ArrayList<Property> properties;
	Context context;

	public PfAdapter(ArrayList<Property> properties, Context context) {
		this.properties = properties;
		this.context = context;
	}

	@Override
	public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new PropertyViewHolder(LayoutInflater.from(context).inflate(R.layout.property_item, parent, false));
	}

	@Override
	public void onBindViewHolder(PropertyViewHolder holder, int position) {

		Property property = properties.get(position);

		holder.propertyPrice.setText(property.getPropertyPrice());

		holder.propertyBathrooms.setText(property.getPropertyBathrooms());

		holder.propertyBedrooms.setText(property.getPropertyBedrooms());

		Picasso.with(context).load(property.getImage()).into(holder.propertyImage);

	}

	public void updateListData(ArrayList<Property> newProperties){
		properties.addAll(newProperties);
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return properties != null ? properties.size() : 0;
	}

	class PropertyViewHolder extends RecyclerView.ViewHolder {

		ImageView propertyImage;
		TextView propertyPrice, propertyBathrooms, propertyBedrooms;

		public PropertyViewHolder(View itemView) {
			super(itemView);

			propertyImage = itemView.findViewById(R.id.property_img);
			propertyPrice = itemView.findViewById(R.id.property_price);
			propertyBathrooms = itemView.findViewById(R.id.property_bathroom);
			propertyBedrooms = itemView.findViewById(R.id.property_bedroom);
		}
	}
}
