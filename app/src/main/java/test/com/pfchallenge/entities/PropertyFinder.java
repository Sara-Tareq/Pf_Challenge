package test.com.pfchallenge.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PropertyFinder implements Parcelable {

	private int total;
	@SerializedName("res")
	private ArrayList<Property> properties;

	public PropertyFinder() {
	}

	public int getTotal() {
		return total;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.total);
		dest.writeTypedList(this.properties);
	}

	protected PropertyFinder(Parcel in) {
		this.total = in.readInt();
		this.properties = in.createTypedArrayList(Property.CREATOR);
	}

	public static final Parcelable.Creator<PropertyFinder> CREATOR = new Parcelable.Creator<PropertyFinder>() {
		@Override
		public PropertyFinder createFromParcel(Parcel source) {
			return new PropertyFinder(source);
		}

		@Override
		public PropertyFinder[] newArray(int size) {
			return new PropertyFinder[size];
		}
	};
}
