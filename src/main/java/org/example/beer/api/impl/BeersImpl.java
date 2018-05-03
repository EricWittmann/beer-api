package org.example.beer.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.example.beer.api.Beers;
import org.example.beer.api.beans.Beer;
import org.example.beer.api.data.BeerData;

/**
 * Implementation of {@link Beers}.
 */
@ApplicationScoped
public class BeersImpl implements Beers {
	
	@Inject
	private BeerData beerData;

	@Override
	public Beer getBeer(int beerId) {
		try {
			return beerData.getBeer(beerId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateBeer(int beerId, Beer data) {
	    System.out.println("Updating beer: " + data);
	}

	@Override
	public void deleteBeer(int beerId) {
	}

	@Override
	public List<Beer> listAllBeers(String style, float ounces) {
		try {
			List<Beer> beers = beerData.listBeers();
			return beers.stream().filter(beer -> {
				if (style != null && !beer.getStyle().toLowerCase().contains(style.toLowerCase())) {
					return false;
				}
				if (ounces > 0 && !beer.getOunces().equals(ounces)) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addBeer(Beer data) {
	    System.out.println("Adding beer: " + data);	}

}
