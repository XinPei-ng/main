package seedu.address.logic.parser.expenditure;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.expenditure.ExpDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExpDeleteCommand object
 */
public class ExpDeleteCommandParser implements Parser<ExpDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExpDeleteCommand
     * and returns a ExpDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExpDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ExpDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            seedu.address.logic.commands.expenditure.ExpDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
