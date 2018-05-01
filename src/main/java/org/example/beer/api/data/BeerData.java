package org.example.beer.api.data;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.example.beer.api.beans.Beer;
import org.example.beer.api.beans.Brewery;
import org.relique.io.TableReader;

@ApplicationScoped
public class BeerData implements TableReader {
	
	private Connection connection;
	
	public BeerData() {
	}
	
	@PostConstruct
	public void postConstruct() throws Exception {
		Class.forName("org.relique.jdbc.csv.CsvDriver");
		String connectionUrl = "jdbc:relique:csv:class:" + getClass().getName();
		connection = DriverManager.getConnection(connectionUrl);
	}

	@Override
	public Reader getReader(Statement statement, String tableName) throws SQLException {
		return new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/" + tableName + ".csv"));
	}

	@Override
	public List<String> getTableNames(Connection connection) throws SQLException {
		return Arrays.asList("beers", "breweries");
	}

	/**
	 * Lists all beers.
	 * @throws Exception
	 */
	public List<Beer> listBeers() throws Exception {
	    Statement stmt = connection.createStatement();
	    ResultSet results = stmt.executeQuery("SELECT * FROM beers ORDER BY name ASC");
	    List<Beer> rval = new ArrayList<>();
	    while (results.next()) {
	    	Beer beer = readBeer(results);
	    	rval.add(beer);
	    }
	    stmt.close();
	    return rval;
	}
	
	/**
	 * Lists all breweries.
	 * @throws Exception
	 */
	public List<Brewery> listBreweries() throws Exception {
		Statement stmt = connection.createStatement();
	    ResultSet results = stmt.executeQuery("SELECT * FROM breweries ORDER BY name ASC");
	    List<Brewery> rval = new ArrayList<>();
	    while (results.next()) {
	    	Brewery brewery = readBrewery(results);
	    	rval.add(brewery);
	    }
	    stmt.close();
	    return rval;
	}
	
	public Beer getBeer(int beerId) throws Exception {
	    Statement stmt = connection.createStatement();
	    ResultSet results = stmt.executeQuery(String.format("SELECT b.* FROM beers AS b WHERE b.id = '%d'", beerId));
	    if (results.next()) {
	    	Beer beer = readBeer(results);
	    	return beer;
	    }
	    return null;
	}

	public Brewery getBrewery(int breweryId) throws Exception {
	    Statement stmt = connection.createStatement();
	    ResultSet results = stmt.executeQuery(String.format("SELECT b.* FROM breweries AS b WHERE b.brewery_id = '%d'", breweryId));
	    if (results.next()) {
	    	Brewery brewery = readBrewery(results);
	    	return brewery;
	    }
	    return null;
	}

	private Beer readBeer(ResultSet results) throws Exception {
		Beer beer = new Beer();
		beer.setAbv(results.getDouble("abv"));
		beer.setBreweryId(results.getInt("brewery_id"));
		beer.setIbu(results.getDouble("ibu"));
		beer.setId(results.getInt("id"));
		beer.setName(results.getString("name"));
		beer.setOunces(results.getDouble("ounces"));
		beer.setStyle(results.getString("style"));
		return beer;
	}

	private Brewery readBrewery(ResultSet results) throws Exception {
		Brewery brewery = new Brewery();
		brewery.setName(results.getString("name"));
		brewery.setCity(results.getString("city"));
		brewery.setState(results.getString("state"));
		return brewery;
	}

}
