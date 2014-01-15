/**
 * 
 */
package org.devel.javafx.navigation.prototype.view;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.loadui.testfx.Assertions.verifyThat;

import java.util.concurrent.Callable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import org.devel.javafx.navigation.prototype.viewmodel.SearchRouteViewModel;
import org.junit.Test;
import org.loadui.testfx.exceptions.NoNodesVisibleException;
import org.loadui.testfx.utils.TestUtils;

/**
 * @author stefan.illgen
 * 
 */
public class SearchRouteViewTest extends
		BasicAcceptanceTest<SearchRouteView, SearchRouteViewModel> {

	public static final String START_LATITUDE = "51.02681";
	public static final String START_LONGITUDE = "13.70878";
	public static final String FINISH_LATITUDE = "51.05041";
	public static final String FINISH_LONGITUDE = "13.73726";

	public SearchRouteViewTest() {
		super(SearchRouteView.class, "#searchRouteVBox");
	}

	protected void wait4Parent() {
		super.wait4Parent();

		// TODO wait until WebEngine gets loaded correctly 
		TestUtils.awaitCondition(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				try {
					WebView webView = find("#mapWebView", getParent());
					return webView != null;
	//				return webView.getEngine().getLoadWorker().getState() == State.SUCCEEDED;
				} catch (NoNodesVisibleException e) {
					// nothing
				}
				return false;
			}
		}, AWAIT_TIMEOUT_IN_SECONDS);
	}

	@Test
	public void testStart() {
		// Pre
		verifyThat(getParent(), notNullValue());
		Label startLbl = findAwait("#startLbl");
		verifyThat(startLbl.getText(), is("Start:"));
		TextField startTf = findAwait("#startTf", getParent());
		verifyThat(startTf.getText(),
				is(START_LATITUDE + " " + START_LONGITUDE));
		// Action
		push(startTf, "");
		push(startTf, START_LATITUDE + " " + START_LONGITUDE);
		// Post: TODO check if start point gets drawn
		// verifyThat(getParent(), notNullValue());
	}

	@Test
	public void testFinish() {
		// Pre
		verifyThat(getParent(), notNullValue());
		Label finishLbl = findAwait("#finishLbl");
		verifyThat(finishLbl.getText(), is("Finish:"));
		TextField finishTf = findAwait("#finishTf", getParent());
		verifyThat(finishTf.getText(), is(FINISH_LATITUDE + " "
				+ FINISH_LONGITUDE));
		// Action
		push(finishTf, "");
		push(finishTf, FINISH_LATITUDE + " " + FINISH_LONGITUDE);
		// Post: TODO check if start point gets drawn
		// verifyThat(getParent(), notNullValue());
	}

	@Test
	public void testSearch() {
		// Pre
		verifyThat(getParent(), notNullValue());
		Button searchBtn = findAwait("#searchBtn", getParent());
		verifyThat(searchBtn.getText(), is("Search"));
		// Action
		click(searchBtn);
		// Post: TODO check if route gets drawn
		// verifyThat(getParent(), notNullValue());
	}

}
