package test.com.pfchallenge;

import org.junit.Test;

import test.com.pfchallenge.entities.Property;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

	Property property = new Property("12000","USD",3,2);
	@Test
	public void testPropertyPrice(){
		assertEquals("12000 USD",property.getPropertyPrice());
	}

	@Test
	public void testPropertyBathrooms(){
		assertEquals("3 Bathrooms",property.getPropertyBathrooms());
	}

	@Test
	public void testPropertyBedrooms(){
		assertEquals("2 Bedrooms",property.getPropertyBedrooms());
	}
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}
}