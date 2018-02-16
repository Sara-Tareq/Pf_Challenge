package test.com.pfchallenge.entities;

import com.google.gson.annotations.SerializedName;

public class Property {

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
}