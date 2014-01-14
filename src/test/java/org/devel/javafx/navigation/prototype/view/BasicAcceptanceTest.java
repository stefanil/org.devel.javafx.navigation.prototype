/**
 * 
 */
package org.devel.javafx.navigation.prototype.view;

import java.util.concurrent.Callable;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;

import org.devel.javafx.navigation.prototype.Configuration;
import org.devel.javafx.navigation.prototype.viewmodel.MapViewModel;
import org.junit.After;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.loadui.testfx.exceptions.NoNodesVisibleException;
import org.loadui.testfx.utils.TestUtils;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * @author stefan.illgen
 *
 */
public class BasicAcceptanceTest<T extends View<MapViewModel>> extends GuiTest {

	/**
	 * 
	 */
	public static final int AWAIT_TIMEOUT_IN_SECONDS = 10;
	
	private ViewTuple<MapViewModel> viewTuple;
	private Node parent;
	private String parentId;
	private Class<T> viewClazz;
	
	/**
	 * 
	 * @param viewClazz
	 * @param parentId
	 */
	public BasicAcceptanceTest(Class<T> viewClazz, String parentId) {
		super();
		this.parentId = parentId;
		this.viewClazz = viewClazz;
	}

	/**
	 * 
	 */
	@Override
	protected Parent getRootNode() {
        ViewLoader viewLoader = new ViewLoader();
		viewTuple = viewLoader.loadViewTuple(viewClazz);//MapView.class);
		return viewTuple.getView();
	}
	
	/**
	 * 
	 * @return
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
     * Returns the mvvmFX {@link ViewTuple} representing a selected planning board.
     * 
     * @return the mvvmFX {@link ViewTuple} representing a selected planning board
     */
    public ViewTuple<MapViewModel> getTuple() {
        return viewTuple;
    }
    
    /**
     * Prepares the stage and binds the view model.
     */
    @Override
    public void setupStage() throws Throwable {

        super.setupStage();
        stage.setWidth(Configuration.APPLICATION_SCREEN_WIDTH);
        stage.setHeight(Configuration.APPLICATION_SCREEN_HEIGHT);
        stage.setX(0.0);
        stage.setY(0.0);

        // create model and view model for testing (lazy bind) inside the one and only UI thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createBindViewModel();
            }
        });
        // and ..
        wait4Parent();
    }

    /**
     * Creates and bind the model to the view model. Subclasses may override this to set a custom model for testing.
     * 
     * Override this method to create and bind a custom view model.
     */
    protected void createBindViewModel() {
        // nothing yet
    }

    /**
     * Waits until the node tree is build, i.e. until the following conditions are fulfilled:
     * 
     * <ul>
     * <li>the parent node with id <code>#vBoxPlanningBoard</code> was found</li>
     * <li>the amount of children of the parent is 8</li>
     * <ul>
     * 
     * The conditions are checked via polling. The maximum time to wait is 10 seconds.
     */
    protected void wait4Parent() {
        TestUtils.awaitCondition(new Callable<Boolean>() {

			@Override
            public Boolean call() throws Exception {
                try {
                    parent = find(parentId);
                    return parent != null; // && parent.getChildrenUnmodifiable().size() == 8;
                } catch (NoNodesVisibleException e) {
                    // nothing
                } catch (IndexOutOfBoundsException e) {
                    // nothing
                }
                return false;
            }
        }, AWAIT_TIMEOUT_IN_SECONDS);
    }

    /**
     * Reset state of stage.
     */
    @After
    public void tearDown() {
        parent = null;
    }
    
    // ########### helpers ######
    
    /**
     * Tries to find a {@link Node} for a given query via polling the query request for at most 10 seconds.
     * 
     * @param query the query string
     * @return the queried node
     * 
     * @throws NoNodesFoundException, if no nodes were found
     */
    public static <T extends Node> T findAwait(final String query) {
        TestUtils.awaitCondition(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    find(query);
                    return true;
                } catch (NoNodesVisibleException e) {
                    return false;
                }
            }
        }, AWAIT_TIMEOUT_IN_SECONDS);
        return find(query);
    }

    /**
     * Tries to find a {@link Node} for a given query via polling the query request for at most 10 seconds.
     * 
     * @param query the query string
     * @param parent a parent of the node to find
     * @return the queried node
     * 
     * @throws NoNodesFoundException, if no nodes were found
     */
    public static <T extends Node> T findAwait(final String query, final Object parent) {
        TestUtils.awaitCondition(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    find(query, parent);
                    return true;
                } catch (NoNodesVisibleException e) {
                    return false;
                }
            }
        }, AWAIT_TIMEOUT_IN_SECONDS);
        return find(query, parent);
    }

}
