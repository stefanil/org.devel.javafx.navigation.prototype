import org.devel.javafx.navigation.prototype.model.AllModelTests;
import org.devel.javafx.navigation.prototype.view.AllViewTests;
import org.devel.javafx.navigation.prototype.viewmodel.AllViewModelTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for integrating model and view model tests.
 * 
 * @author stefan.illgen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AllViewTests.class, AllViewModelTests.class, AllModelTests.class }) 
public class AllTests {

}
