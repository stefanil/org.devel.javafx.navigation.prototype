/**
 * 
 */
package org.devel.javafx.navigation.prototype.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.devel.javafx.navigation.prototype.model.Route;
import org.devel.javafx.navigation.prototype.viewmodel.converter.PositionStringConverter;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class SearchRouteViewModel implements ViewModel {

	private StringProperty start;
	private StringProperty finish;
	private MapViewModel mapViewModel;

	public StringProperty startProperty() {
		if (start == null)
			start = new SimpleStringProperty();
		return start;
	}

	public String getStart() {
		return start.get();
	}

	public void setStart(String start) {
		this.start.set(start);
	}

	public StringProperty finishProperty() {
		if (finish == null)
			finish = new SimpleStringProperty();
		return finish;
	}

	public String getFinish() {
		return finish.get();
	}

	public void setFinish(String finish) {
		this.finish.set(finish);
	}

	public void initialize(MapViewModel mapViewModel) {
		
		this.mapViewModel = mapViewModel;
		
		Route route = new Route();
		Bindings.bindBidirectional(startProperty(), route.startProperty(),
				new PositionStringConverter());
		Bindings.bindBidirectional(finishProperty(), route.finishProperty(),
				new PositionStringConverter());
	}
}
