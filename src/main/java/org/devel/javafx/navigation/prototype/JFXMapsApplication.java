package org.devel.javafx.navigation.prototype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.devel.javafx.navigation.prototype.view.MapView;
import org.devel.javafx.navigation.prototype.viewmodel.MapViewModel;

import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * Hello world!
 * 
 */
public class JFXMapsApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		// load view tuple
		ViewLoader viewLoader = new ViewLoader();
		ViewTuple<MapViewModel> viewTuple = viewLoader.loadViewTuple(MapView.class);
		// configure the stage
		stage.setScene(new Scene(viewTuple.getView()));
		stage.setTitle(Configuration.APPLICATION_TITLE);
		stage.setHeight(Configuration.APPLICATION_SCREEN_HEIGHT);
		stage.setWidth(Configuration.APPLICATION_SCREEN_WIDTH);
		// show the stage
		stage.show();
	}
}
