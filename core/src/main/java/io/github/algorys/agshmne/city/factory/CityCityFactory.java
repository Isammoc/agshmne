package io.github.algorys.agshmne.city.factory;

import io.github.algorys.agshmne.city.City;
import io.github.algorys.agshmne.city.ICityFactory;
import io.github.algorys.agshmne.city.CityType;
import io.github.algorys.agshmne.tools.Outils;

public class CityCityFactory implements ICityFactory {

	@Override
	public City createCity() {
		return new City(CityType.City, Outils.dice(3) + 5);
	}

}