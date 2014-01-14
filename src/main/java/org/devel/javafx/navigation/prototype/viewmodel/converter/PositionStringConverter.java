/**
 * 
 */
package org.devel.javafx.navigation.prototype.viewmodel.converter;

import javafx.util.StringConverter;

import org.devel.javafx.navigation.prototype.model.Position;

/**
 * @author stefan.illgen
 *
 */
public class PositionStringConverter extends StringConverter<Position> {

	@Override
	public Position fromString(String string) {
		String[] splitted = string.split(" ");
		return (splitted.length == 2) ? new Position(Double
				.valueOf(splitted[0]), Double
				.valueOf(splitted[1])) : new Position();
	}

	@Override
	public String toString(Position position) {
		return position.getLatitude() + " "
				+ position.getLongitude();
	}
	
}
