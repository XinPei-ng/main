package seedu.address.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.repeat.RepeatLevelParser;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Repeat;

/**
 * Delete repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Deletes the repeat identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REPEAT_SUCCESS = "Deleted Repeat: %1$s";


    private final Index targetIndex;

    public RepeatDeleteCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Repeat> lastShownList = model.getFilteredRepeatList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
        }

        Repeat repeatToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRepeat(repeatToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_REPEAT_SUCCESS, repeatToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepeatDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((RepeatDeleteCommand) other).targetIndex)); // state check
    }
}
