package seedu.address.logic.commands.expenditure;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Expenditure;

/**
 * Deletes a expenditure identified using it's displayed index from the address book.
 */
public class ExpDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the expenditure identified by the index number used in the displayed expenditure list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "Deleted Expenditure: %1$s";

    private final Index targetIndex;

    public ExpDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Expenditure> lastShownList = model.getFilteredExpenditureList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
        }

        Expenditure expenditureToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteExpenditure(expenditureToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENDITURE_SUCCESS, expenditureToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((ExpDeleteCommand) other).targetIndex)); // state check
    }
}
