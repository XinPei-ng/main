package seedu.address.logic.commands.general;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;


/**
 * Switch to a date.
 */
public class GoCommand extends Command {

    public static final String COMMAND_WORD = "go";

    public static final String MESSAGE_SUCCESS = "We are at : %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go to a specific date\n"
            + "the specified date format YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 2001-09-11";


    private final LocalDate toDate;
    private final boolean fromUi;

    public GoCommand(LocalDate toDate, boolean fromUi) {
        this.toDate = toDate;
        this.fromUi = fromUi;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateActiveDate(toDate);
        if (fromUi) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDate));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDate), toDate);
        }
    }

}
