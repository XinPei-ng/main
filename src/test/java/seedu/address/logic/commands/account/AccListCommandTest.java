package seedu.address.logic.commands.account;

//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;
// import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;
// import static seedu.address.testutil.TypicalExpenditures.getTypicalAccount;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AccListCommand.
 */
public class AccListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAccountList(), new UserPrefs());
        expectedModel = new ModelManager(model.getAccountList(), new UserPrefs());
    }

    //TODO: SP HAS NO IDEA FOR THE ONE BELOW
    //@Test
    //public void execute_listIsNotFiltered_showsSameList() {
    //    assertCommandSuccess(new AccListCommand(), model, AccListCommand.MESSAGE_SUCCESS, expectedModel);
    //}

    // @Test
    // public void execute_listIsFiltered_showsEverything() {
    //     showExpenditureAtIndex(model, INDEX_FIRST_EXPENDITURE);
    //     assertCommandSuccess(new AccListCommand(), model, AccListCommand.MESSAGE_SUCCESS, expectedModel);
    // }
}
