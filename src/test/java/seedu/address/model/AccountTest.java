package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenditures.ALICE;
// import static seedu.address.testutil.TypicalExpenditures.getTypicalAccount;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.exceptions.DuplicateExpenditureException;
import seedu.address.testutil.ExpenditureBuilder;

public class AccountTest {

    private final Account account = new Account();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), account.getExpenditureList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.resetData(null));
    }

    // @Test
    // public void resetData_withValidReadOnlyAddressBook_replacesData() {
    //     Account newData = getTypicalAccount();
    //     account.resetData(newData);
    //     assertEquals(newData, account);
    // }

    @Test
    public void resetData_withDuplicateExpenditures_throwsDuplicateExpenditureException() {
        // Two expenditures with the same identity fields
        Expenditure editedAlice = new ExpenditureBuilder(ALICE).build();
        List<Expenditure> newExpenditures = Arrays.asList(ALICE, editedAlice);
        AccountStub newData = new AccountStub(newExpenditures);
        assertThrows(DuplicateExpenditureException.class, () -> account.resetData(newData));
    }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.hasExpenditure(null));
    }

    @Test
    public void hasExpenditure_expenditureNotInAddressBook_returnsFalse() {
        assertFalse(account.hasExpenditure(ALICE));
    }

    @Test
    public void hasExpenditure_expenditureInAddressBook_returnsTrue() {
        account.addExpenditure(ALICE);
        assertTrue(account.hasExpenditure(ALICE));
    }

    @Test
    public void hasExpenditure_expenditureWithSameDifferentDate_returnsFalse() {
        account.addExpenditure(ALICE);
        Expenditure editedAlice = new ExpenditureBuilder(ALICE).withDate(VALID_DATE_BOB).withTag(VALID_TAG_HUSBAND)
                .build();
        assertFalse(account.hasExpenditure(editedAlice));
    }

    @Test
    public void getExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> account.getExpenditureList().remove(0));
    }

    /**
     * A stub ReadOnlyAccount whose expenditures list can violate interface constraints.
     */
    private static class AccountStub implements ReadOnlyAccount {
        private final ObservableList<Expenditure> expenditures = FXCollections.observableArrayList();
        private final ObservableList<Repeat> repeats = FXCollections.observableArrayList();

        AccountStub(Collection<Expenditure> expenditures) {
            this.expenditures.setAll(expenditures);
        }

        @Override
        public ObservableList<Expenditure> getExpenditureList() {
            return expenditures;
        }

        @Override
        public ObservableList<Repeat> getRepeatList() {
            return repeats;
        }
    }

}
