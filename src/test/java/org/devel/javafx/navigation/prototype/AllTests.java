package org.devel.javafx.navigation.prototype;

import org.devel.javafx.navigation.prototype.model.AllModelTests;
import org.devel.javafx.navigation.prototype.view.AllViewTests;
import org.devel.javafx.navigation.prototype.viewmodel.AllViewModelTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author stefan.illgen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AllModelTests.class, AllViewModelTests.class, AllViewTests.class })
public class AllTests {

}
