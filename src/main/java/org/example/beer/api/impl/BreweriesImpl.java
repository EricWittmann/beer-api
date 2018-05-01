package org.example.beer.api.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.example.beer.api.Breweries;
import org.example.beer.api.beans.Beer;
import org.example.beer.api.beans.Brewery;

/**
 * Implementation of {@link Breweries}.
 */
@ApplicationScoped
public class BreweriesImpl implements Breweries {

	@Override
	public List<Brewery> listAllBreweries(String state, String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBrewery(Brewery data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Brewery getBrewery(int breweryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBrewery(int breweryId, Brewery data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBrewery(int breweryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Beer> listBreweryBeers(int breweryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBeerToBrewery(int breweryId, Beer data) {
		// TODO Auto-generated method stub
		
	}

}
