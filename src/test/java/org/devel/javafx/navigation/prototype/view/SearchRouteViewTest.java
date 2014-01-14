/**
 * 
 */
package org.devel.javafx.navigation.prototype.view;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.loadui.testfx.Assertions.verifyThat;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.devel.javafx.navigation.prototype.viewmodel.SearchRouteViewModel;
import org.junit.Test;

/**
 * @author stefan.illgen
 * 
 */
public class SearchRouteViewTest extends BasicAcceptanceTest<SearchRouteView, SearchRouteViewModel> {

	public static final String START_LATITUDE = "51.02681";
	public static final String START_LONGITUDE = "13.70878";
	public static final String FINISH_LATITUDE = "51.05041";
	public static final String FINISH_LONGITUDE = "13.73726";

	public SearchRouteViewTest() {
		super(SearchRouteView.class, "#searchRoutePane");
	}

	@Test
	public void testStart() {
		// Pre
		verifyThat(getParent(), notNullValue());
		Label startLbl = findAwait("#startLbl");
		verifyThat(startLbl.getText(), is("Start:"));
		TextField startTf = findAwait("#startTf", getParent());
		// Action
		click(startTf);
		startTf.setText(START_LATITUDE + " " + START_LONGITUDE);
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
		// Action
		click(finishTf);
		finishTf.setText(FINISH_LATITUDE + " " + FINISH_LONGITUDE);
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
		click( searchBtn );
		// Post: TODO check if route gets drawn
		// verifyThat(getParent(), notNullValue());		
	}

}
