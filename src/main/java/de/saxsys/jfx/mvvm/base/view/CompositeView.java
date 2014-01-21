/**
 * 
 */
package de.saxsys.jfx.mvvm.base.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.base.viewmodel.CompositeViewModel;



/**
 * @author stefan.illgen
 *
 */
public class CompositeView<ViewModelType extends CompositeViewModel> extends View<ViewModelType> {
	
	List<View<ViewModelType>> children;
	
	public List<View<ViewModelType>> getChildren() {
		if(children == null) {
			children = new ArrayList<View<ViewModelType>>();
		}
		return children;
	}

	public void addChild(View<ViewModelType> view) {
		getChildren().add(view);
	}
	
	public void removeChild(int i) {
		getChildren().remove(i);
	}

	public void setChildren(List<View<ViewModelType>> children) {
		this.children = children;
	}

	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		
		for( View<ViewModelType> view : getChildren() ) {
			// initialize sub view (is automatically done)
			view.initialize(url, arg1);
			// propagate view model
			getCompositeViewModel().initialize(view.getViewModel());
		}
		
	}
	
	public ViewModelType getCompositeViewModel() {
		return getViewModel();
	}

}
