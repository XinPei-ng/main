package seedu.address.logic.parser.expenditure;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.expenditure.ExpEditCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExpEditCommand object
 */
public class ExpEditCommandParser implements Parser<ExpEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExpEditCommand
     * and returns an ExpEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExpEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INFO, PREFIX_AMOUNT, PREFIX_DATE, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpEditCommand.MESSAGE_USAGE), pe);
        }

        EditExpenditureDescriptor editExpenditureDescriptor = new EditExpenditureDescriptor();
        if (argMultimap.getValue(PREFIX_INFO).isPresent()) {
            editExpenditureDescriptor.setInfo(ParserUtil.parseInfo(argMultimap.getValue(PREFIX_INFO).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editExpenditureDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editExpenditureDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editExpenditureDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (!editExpenditureDescriptor.isAnyFieldEdited()) {
            throw new ParseException(ExpEditCommand.MESSAGE_NOT_EDITED);
        }

        return new ExpEditCommand(index, editExpenditureDescriptor);
    }



}
