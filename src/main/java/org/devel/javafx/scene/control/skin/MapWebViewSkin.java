/**
 * 
 */
package org.devel.javafx.scene.control.skin;

import javafx.scene.control.SkinBase;

import org.devel.javafx.scene.control.RouteMapView;

/**
 * Skin, that defines the skin with the following configrution, if no values are
 * set (i.e. -1):
 * 
 * <ul>
 * 	<li>.minHeight(100.0)</li>
 * 	<li>.minWidth(100.0)</li>
 * 	<li>.prefHeight(300.0)</li> 
 * 	<li>.prefWidth(640.0)</li>
 * </ul>
 * 
 * @author stefan.illgen
 * 
 */
public class MapWebViewSkin extends SkinBase<RouteMapView> {

	public MapWebViewSkin(RouteMapView control) {
		super(control);
	}

	@Override
	protected double computeMinWidth(double height, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (height < 0) {
			return 100;
		} else
			return super.computeMinWidth(height, topInset, rightInset,
					bottomInset, leftInset);
	}

	@Override
	protected double computeMinHeight(double width, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (width < 0) {
			return 100;
		} else
			return super.computeMinHeight(width, topInset, rightInset,
					bottomInset, leftInset);
	}

	@Override
	protected double computePrefWidth(double height, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (height < 0) {
			return 300;
		} else
			return super.computePrefWidth(height, topInset, rightInset,
					bottomInset, leftInset);
	}

	@Override
	protected double computePrefHeight(double width, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (width < 0) {
			return 400;
		} else
			return super.computePrefHeight(width, topInset, rightInset,
					bottomInset, leftInset);
	}

}
