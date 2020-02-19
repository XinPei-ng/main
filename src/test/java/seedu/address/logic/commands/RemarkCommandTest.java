package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.RemarkCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Remark;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class RemarkCommandTest {

   private RemarkCommand remarkCommand;
   private Remark remark;

   private Model expected;
   private Model model;


    @BeforeEach
   void setUp() throws ParseException {

        expected = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize("remark 1 r/remark",
                PREFIX_REMARK);

        Index index1;
        index1 =  ParserUtil.parseIndex(argMultimap.getPreamble());

        remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        remarkCommand = new RemarkCommand(index1,remark);


    }

    @Test
    void execute_addRemarkUnfilteredList_success() {

        assertCommandSuccess(remarkCommand, model, RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, expected);

    }
}