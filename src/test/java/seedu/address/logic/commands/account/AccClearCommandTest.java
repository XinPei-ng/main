package seedu.address.logic.commands.account;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;
// import static seedu.address.testutil.TypicalExpenditures.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AccClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new AccClearCommand(), model, AccClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAccountList(), new UserPrefs());
        expectedModel.clearActiveAccount();

        assertCommandSuccess(new AccClearCommand(), model, AccClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
