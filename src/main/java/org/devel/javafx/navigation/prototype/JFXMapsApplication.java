package org.devel.javafx.navigation.prototype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.devel.javafx.navigation.prototype.util.Properties;
import org.devel.javafx.navigation.prototype.view.SearchRouteView;
import org.devel.javafx.navigation.prototype.viewmodel.SearchRouteViewModel;

import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * @author stefan.illgen
 * 
 */
public class JFXMapsApplication extends Application {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * 
	 */
	public void start(Stage stage) throws Exception {
		new Properties().loadProxyConf();
		loadViewTuple(stage);
	}

	/**
	 * 
	 * @param stage
	 */
	private void loadViewTuple(Stage stage) {
		// load view tuple
		ViewLoader viewLoader = new ViewLoader();
		ViewTuple<SearchRouteViewModel> viewTuple = viewLoader
				.loadViewTuple(SearchRouteView.class);
		// configure the stage
		stage.setScene(new Scene(viewTuple.getView()));
		stage.setTitle(Configuration.APPLICATION_TITLE);
		// stage.setHeight(Configuration.APPLICATION_SCREEN_HEIGHT);
		// stage.setWidth(Configuration.APPLICATION_SCREEN_WIDTH);
		// show the stage
		stage.show();
	}
}
