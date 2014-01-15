/**
 * 
 */
package org.devel.javafx.navigation.prototype.model;



/**
 * @author stefan.illgen
 *
 */
public class StartPosition extends Position {

	
	protected final static double POSITION_LONGITUDE = 13.70878;
	protected final static double POSITION_LATITUDE = 51.02681;
	
	public double getDefaultLatitude() {
		return POSITION_LATITUDE;
	}
	
	public double getDefaultLongitude() {
		return POSITION_LONGITUDE;
	}
	
}
