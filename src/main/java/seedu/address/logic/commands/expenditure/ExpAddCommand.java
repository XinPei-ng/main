package seedu.address.logic.commands.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Expenditure;

/**
 * Adds a expenditure to the address book.
 */
public class ExpAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a expenditure to the address book. "
            + "Parameters: "
            + PREFIX_INFO + "INFO "
            + PREFIX_ID + "ID "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INFO + "John Doe "
            + PREFIX_ID + "98765432 "
            + PREFIX_AMOUNT + "johnd@example.com "
            + PREFIX_DATE + "2019-09-11 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New expenditure added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This expenditure already exists in the address book";

    private final Expenditure toAdd;

    /**
     * Creates an ExpAddCommand to add the specified {@code Expenditure}
     */
    public ExpAddCommand(Expenditure expenditure) {
        requireNonNull(expenditure);
        toAdd = expenditure;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpenditure(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addExpenditure(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpAddCommand // instanceof handles nulls
                && toAdd.equals(((ExpAddCommand) other).toAdd));
    }
}
