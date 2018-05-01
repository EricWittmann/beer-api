package org.example.beer.api.data;

import java.util.List;

import org.example.beer.api.beans.Beer;
import org.example.beer.api.beans.Brewery;
import org.junit.Assert;
import org.junit.Test;

public class BeerDataTest {

	@Test
	public void testPostConstruct() throws Exception {
		BeerData bd = new BeerData();
		bd.postConstruct();
	}
	
	@Test
	public void testListBeers() throws Exception {
		BeerData bd = new BeerData();
		bd.postConstruct();
		List<Beer> beers = bd.listBeers();
		Assert.assertEquals(2410, beers.size());
		Beer beer = beers.get(0);
		Assert.assertEquals("#001 Golden Amber Lager", beer.getName());
	}

	@Test
	public void testGetBeer() throws Exception {
		BeerData bd = new BeerData();
		bd.postConstruct();
		Beer beer = bd.getBeer(2169);
		Assert.assertNotNull(beer);
		Assert.assertEquals("Rhubarbarian", beer.getName());
	}

	@Test
	public void testListBreweries() throws Exception {
		BeerData bd = new BeerData();
		bd.postConstruct();
		List<Brewery> breweries = bd.listBreweries();
		Assert.assertEquals(558, breweries.size());
		Brewery brewery = breweries.get(0);
		Assert.assertEquals("10 Barrel Brewing Company", brewery.getName());
	}

	@Test
	public void testGetBreweryById() throws Exception {
		BeerData bd = new BeerData();
		bd.postConstruct();
		Brewery brewery = bd.getBrewery(12);
		Assert.assertNotNull(brewery);
		Assert.assertEquals("Blackrocks Brewery", brewery.getName());
	}

}
