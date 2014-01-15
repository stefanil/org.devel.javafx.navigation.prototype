/**
 * 
 */
package org.devel.javafx.navigation.prototype.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author stefan.illgen
 */
public class Route {

	private ObjectProperty<Position> start;
	private ObjectProperty<Position> finish;

	public ObjectProperty<Position> startProperty() {
		if(start == null)
			start = new SimpleObjectProperty<Position>(new StartPosition());
		return start;
	}
	
	public Position getStart() {
		return startProperty().get();
	}

	public void setStart(Position start) {
		startProperty().set(start);
	}
	
	public ObjectProperty<Position> finishProperty() {
		if(finish == null)
			finish = new SimpleObjectProperty<Position>(new FinishPosition());
		return finish;
	}

	public Position getFinish() {
		return finishProperty().get();
	}

	public void setFinish(Position finish) {
		finishProperty().set(finish);
	}
	
}
