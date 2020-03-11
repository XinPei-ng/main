package seedu.address.logic.commands.expenditure;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ExpAddCommand}.
 */
public class ExpAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Expenditure validExpenditure = new PersonBuilder().build();
        Model expectedModel = new ModelManager(model.getAccount(), new UserPrefs());
        expectedModel.addExpenditure(validExpenditure);


        assertCommandSuccess(new ExpAddCommand(validExpenditure), model,
                String.format(ExpAddCommand.MESSAGE_SUCCESS, validExpenditure), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Expenditure expenditureInList = model.getAccount().getExpenditureList().get(0);
        assertCommandFailure(new ExpAddCommand(expenditureInList), model, ExpAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
