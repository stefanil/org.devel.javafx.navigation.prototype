/**
 * 
 */
package de.saxsys.jfx.mvvm.base.viewmodel;

import java.util.ArrayList;
import java.util.List;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 *
 */
public class CompositeViewModel implements ViewModel {
	
	List<ViewModel> children;
	
	public List<ViewModel> getChildren() {
		if(children == null) {
			children = new ArrayList<ViewModel>();
		}
		return children;
	}
	
	public void getChild(int i) {
		children.get(i);
	}

	public void addChild(ViewModel viewModel) {
		getChildren().add(viewModel);
	}
	
	public void removeChild(int i) {
		getChildren().remove(i);
	}
	
	public void initialize(ViewModel... viewModels) {
		for(ViewModel viewModel : viewModels) {
			children.add(viewModel);
		}
	}
	
}
