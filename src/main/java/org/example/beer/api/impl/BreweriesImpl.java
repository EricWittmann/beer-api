package org.example.beer.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.example.beer.api.Breweries;
import org.example.beer.api.beans.Beer;
import org.example.beer.api.beans.Brewery;
import org.example.beer.api.data.BeerData;

/**
 * Implementation of {@link Breweries}.
 */
@ApplicationScoped
public class BreweriesImpl implements Breweries {

	@Inject
	private BeerData beerData;

	@Override
	public List<Brewery> listAllBreweries(String state, String city) {
		try {
			List<Brewery> breweries = beerData.listBreweries();
			return breweries.stream().filter(beer -> {
				if (state != null && !beer.getState().toLowerCase().contains(state.toLowerCase())) {
					return false;
				}
				if (city != null && !beer.getCity().toLowerCase().contains(city.toLowerCase())) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addBrewery(Brewery data) {
	}

	@Override
	public Brewery getBrewery(int breweryId) {
		try {
			return beerData.getBrewery(breweryId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateBrewery(int breweryId, Brewery data) {
	}

	@Override
	public void deleteBrewery(int breweryId) {
	}

	@Override
	public List<Beer> listBreweryBeers(int breweryId) {
		try {
			List<Beer> beers = beerData.listBeers();
			return beers.stream().filter(beer -> {
				if (beer.getBreweryId() == breweryId) {
					return true;
				}
				return false;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addBeerToBrewery(int breweryId, Beer data) {
	}

}
