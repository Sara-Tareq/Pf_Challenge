package test.com.pfchallenge.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Property implements Parcelable {

	private int id;
	private String price;
	private String currency;
	private int bathrooms;
	private int bedrooms;
	@SerializedName("thumbnail_big")
	private String image;

	public Property() {
	}

	public int getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getPropertyPrice() {
		return price.concat(" ").concat(currency);
	}

	public String getPropertyBathrooms() {
		return String.valueOf(bathrooms).concat(" Bathrooms");
	}

	public String getPropertyBedrooms() {
		return String.valueOf(bedrooms).concat(" Bedrooms");
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.price);
		dest.writeString(this.currency);
		dest.writeInt(this.bathrooms);
		dest.writeInt(this.bedrooms);
		dest.writeString(this.image);
	}

	protected Property(Parcel in) {
		this.id = in.readInt();
		this.price = in.readString();
		this.currency = in.readString();
		this.bathrooms = in.readInt();
		this.bedrooms = in.readInt();
		this.image = in.readString();
	}

	public static final Parcelable.Creator<Property> CREATOR = new Parcelable.Creator<Property>() {
		@Override
		public Property createFromParcel(Parcel source) {
			return new Property(source);
		}

		@Override
		public Property[] newArray(int size) {
			return new Property[size];
		}
	};
}