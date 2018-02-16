package test.com.pfchallenge.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Sara_R on 16/02/2018.
 */

public class PropertyFinder {

	private int total;
	@SerializedName("res")
	private ArrayList<Property> properties;
}
