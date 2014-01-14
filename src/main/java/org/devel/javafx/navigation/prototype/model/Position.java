/**
 * 
 */
package org.devel.javafx.navigation.prototype.model;

import org.devel.javafx.navigation.prototype.Configuration;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author stefan.illgen
 * 
 */
public class Position {

	private DoubleProperty longitude;
	private DoubleProperty latitude;

	public Position() {
		// lazy init
	}
	
	public Position(Double longitude, Double latitude) {
		// eager init
		setLongitude(longitude);
		setLatitude(latitude);
	}

	public DoubleProperty longitudeProperty() {
		if (longitude == null)
			longitude = new SimpleDoubleProperty(Configuration.DEFAULT_POSITION_LONGITUDE);
		return longitude;
	}

	public double getLongitude() {
		return longitudeProperty().get();
	}

	public void setLongitude(double longitude) {
		longitudeProperty().set(longitude);
	}

	public DoubleProperty latitudeProperty() {
		if (latitude == null)
			latitude = new SimpleDoubleProperty(Configuration.DEFAULT_POSITION_LATITUDE);
		return latitude;
	}

	public double getLatitude() {
		return latitudeProperty().get();
	}

	public void setLatitude(double latitude) {
		latitudeProperty().set(latitude);
	}

}
