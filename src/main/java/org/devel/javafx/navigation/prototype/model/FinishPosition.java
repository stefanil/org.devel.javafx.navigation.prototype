/**
 * 
 */
package org.devel.javafx.navigation.prototype.model;

/**
 * @author stefan.illgen
 *
 */
public class FinishPosition extends Position {

	protected final double POSITION_LONGITUDE = 13.73726;
	protected final double POSITION_LATITUDE = 51.05041;
	
	public double getDefaultLatitude() {
		return POSITION_LATITUDE;
	}
	
	public double getDefaultLongitude() {
		return POSITION_LONGITUDE;
	}
	
}
