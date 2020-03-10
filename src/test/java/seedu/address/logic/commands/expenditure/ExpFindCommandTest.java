package seedu.address.logic.commands.expenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENDITURES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expenditure.InfoContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ExpFindCommand}.
 */
public class ExpFindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        InfoContainsKeywordsPredicate firstPredicate =
                new InfoContainsKeywordsPredicate(Collections.singletonList("first"));
        InfoContainsKeywordsPredicate secondPredicate =
                new InfoContainsKeywordsPredicate(Collections.singletonList("second"));

        ExpFindCommand findFirstCommand = new ExpFindCommand(firstPredicate);
        ExpFindCommand findSecondCommand = new ExpFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ExpFindCommand findFirstCommandCopy = new ExpFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different expenditure -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_EXPENDITURES_LISTED_OVERVIEW, 0);
        InfoContainsKeywordsPredicate predicate = preparePredicate(" ");
        ExpFindCommand command = new ExpFindCommand(predicate);
        expectedModel.updateFilteredExpenditureList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenditureList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_EXPENDITURES_LISTED_OVERVIEW, 3);
        InfoContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        ExpFindCommand command = new ExpFindCommand(predicate);
        expectedModel.updateFilteredExpenditureList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredExpenditureList());
    }

    /**
     * Parses {@code userInput} into a {@code InfoContainsKeywordsPredicate}.
     */
    private InfoContainsKeywordsPredicate preparePredicate(String userInput) {
        return new InfoContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
