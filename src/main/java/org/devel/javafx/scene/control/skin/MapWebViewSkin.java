/**
 * 
 */
package org.devel.javafx.scene.control.skin;

import javafx.scene.control.SkinBase;

import org.devel.javafx.scene.control.RouteMapView;

/**
 * Skin, that defines a default preferred size of 600.
 * 
 * @author stefan.illgen
 *
 */
public class MapWebViewSkin extends SkinBase<RouteMapView> {

	public MapWebViewSkin(RouteMapView control) {
		super(control);
	}
	
	@Override
	protected double computePrefWidth(double height, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (height < 0) {
			return 600;
		} else
			return super.computePrefWidth(height, topInset, rightInset,
					bottomInset, leftInset);
	}

	@Override
	protected double computePrefHeight(double width, double topInset,
			double rightInset, double bottomInset, double leftInset) {
		if (width < 0) {
			return 600;
		} else
			return super.computePrefHeight(width, topInset, rightInset,
					bottomInset, leftInset);
	}
	
	

}
