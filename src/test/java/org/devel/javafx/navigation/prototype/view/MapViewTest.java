package org.devel.javafx.navigation.prototype.view;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.loadui.testfx.Assertions.verifyThat;

import java.util.concurrent.Callable;

import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebView;

import org.devel.javafx.navigation.prototype.viewmodel.MapViewModel;
import org.junit.Test;
import org.loadui.testfx.utils.TestUtils;

/**
 * 
 * @author stefan.illgen
 *
 */
public class MapViewTest extends BasicAcceptanceTest<MapView, MapViewModel> {
	
	/**
	 * 
	 */
	public MapViewTest() {
		super(MapView.class, "#mapViewHBox");
	}

	/**
	 * 
	 */
	@Test
	public void testMapExistence() {
		// Pre
		verifyThat(getParent(), notNullValue());
		// Action
		// Post
		final WebView mapWebView = findAwait("#mapWebView");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				TestUtils.awaitCondition(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return mapWebView.getEngine().getLoadWorker().getState() == State.SUCCEEDED;
					}		
				}, AWAIT_TIMEOUT_IN_SECONDS);
				verifyThat(mapWebView.getEngine().getLoadWorker().getState(), is(State.SUCCEEDED));
			}
		});
	}

}
