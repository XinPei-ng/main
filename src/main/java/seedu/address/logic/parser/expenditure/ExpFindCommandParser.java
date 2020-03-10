package seedu.address.logic.parser.expenditure;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.expenditure.ExpFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.InfoContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ExpFindCommand object
 */
public class ExpFindCommandParser implements Parser<ExpFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExpFindCommand
     * and returns a ExpFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExpFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ExpFindCommand(new InfoContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
