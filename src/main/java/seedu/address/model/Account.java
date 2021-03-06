package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.UniqueExpenditureList;
import seedu.address.model.expenditure.exceptions.RepeatNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class Account implements ReadOnlyAccount, ReportableAccount {

    private final UniqueExpenditureList expenditures;
    private HashMap<YearMonth, Budget> budgetList = new HashMap<>();
    private ObservableList<Repeat> repeats;
    private final String accountName;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        expenditures = new UniqueExpenditureList();
        repeats = FXCollections.observableArrayList();
    }

    public Account() {
        accountName = null;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }


    /**
     * Creates an Account using the Expenditures in the {@code toBeCopied}
     */
    public Account copyAccountWithNewName(String newName) {
        Account toBeCopied = new Account(newName);
        toBeCopied.resetData(this);
        return toBeCopied;
    }

    public String getAccountName() {
        return accountName;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expenditure list with {@code expenditures}.
     * {@code expenditures} must not contain duplicate expenditures.
     */

    public void setExpenditures(List<Expenditure> expenditures) {
        this.expenditures.setExpenditures(expenditures);
    }

    /**
     * Resets the existing data of this {@code Account} with {@code newData}.
     */
    public void resetData(ReadOnlyAccount newData) {
        requireNonNull(newData);
        setExpenditures(newData.getExpenditureList());
    }

    //// expenditure-level operations

    /**
     * Returns true if a expenditure with the same identity as {@code expenditure} exists in the address book.
     */

    public boolean hasExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        return expenditures.contains(expenditure);
    }


    /**
     * Adds a expenditure to the dayToDayExpenditure.
     * The expenditure must not already exist in the address book.
     */
    public void addExpenditure(Expenditure expenditure) {
        expenditures.add(expenditure);
    }

    /**
     * Adds a repeat to the repeatList.
     */
    public void addRepeat(Repeat repeat) {
        repeats.add(repeat);
    }

    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the address book.
     * The expenditure identity of {@code editedExpenditure} must not be the same as another
     * existing expenditure in the address book.
     */
    public void setExpenditure(Expenditure target, Expenditure editedExpenditure) {
        requireNonNull(editedExpenditure);

        expenditures.setExpenditure(target, editedExpenditure);
    }

    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the address book.
     * The expenditure identity of {@code editedExpenditure} must not be the same as another
     * existing expenditure in the address book.
     */
    public void setRepeat(Repeat target, Repeat editedRepeat) {
        requireNonNull(editedRepeat);
        repeats.set(repeats.indexOf(target), editedRepeat);
    }

    /**
     * Removes {@code key} from this {@code Account}.
     * {@code key} must exist.
     */
    public void removeExpenditure(Expenditure key) {
        expenditures.remove(key);
    }

    /**
     * Removes {@code Repeat} from this {@code repeatItem}.
     */
    public void removeRepeat(Repeat repeat) {
        if (!repeats.remove(repeat)) {
            throw new RepeatNotFoundException();
        }
    }

    /**
     * Add or reset a budget to the budgetList.
     * @param budget contains amount and the yearMonth.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        //This can use to reset the budget too.
        this.budgetList.put(budget.getYearMonth(), budget);
    }

    /**
     * Obtain the budget object for a given yearMonth.
     * @param yearMonth the target month you are looking for.
     * @return If the budget is within the [@code budgetList], return it.
     *         Else return a budget object with 0 amount.
     */
    public Budget getBudget(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        if (this.budgetList.containsKey(yearMonth)) {
            return this.budgetList.get(yearMonth);
        } else {
            // If the budget is not being set for that given yearMonth.
            // Return a new budget with 0 amount.
            return new Budget(yearMonth, 0);
        }

    }

    /**
     * Obtain the budget object for a given yearMonth.
     * @param yearMonth the target month you are looking for.
     * @return If the budget is within the [@code budgetList], return it.
     *         Else return a budget object with 0 amount.
     */
    public Budget getBudget(String yearMonth) throws ParseException {
        requireNonNull(yearMonth);
        try {
            YearMonth targetYearMonth = ParserUtil.parseYearMonth(yearMonth);
            return getBudget(yearMonth);
        } catch (Exception e) {
            throw new ParseException("Year Month need to be in a format of : YYYY-MM");
        }
    }

    /**
     * Calculate total expenditure amount for a given YearMonth.
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount for all the expenditure.
     */
    private double calculateMonthlyExpenditure(YearMonth givenYearMonth) {
        return this.expenditures.calculateExpenditureAmount(givenYearMonth);
    }

    /**
     * Calculate total repeat amount for a given YearMonth.
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount for all the repeat.
     */
    private double calculateMonthlyRepeat(YearMonth givenYearMonth) {
        double total = 0;
        for (int i = 0; i < repeats.size(); i++) {
            total += this.repeats.get(i).calculateForGivenYearMonth(givenYearMonth);
        }
        return total;
    }

    /**
     * Calculate total amount of a given YearMonth.
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount.
     */
    public double getTotalMonthlySpending(YearMonth givenYearMonth) {
        return calculateMonthlyExpenditure(givenYearMonth) + calculateMonthlyExpenditure(givenYearMonth);
    }


    //// util methods

    @Override
    public String toString() {
        // return expenditures.asUnmodifiableObservableList().size() + " expenditures";
        // TODO: refine later
        return "Account: " + accountName;
    }

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return expenditures.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Repeat> getRepeatList() {
        return FXCollections.unmodifiableObservableList(repeats);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Account // instanceof handles nulls
                && accountName.equals(((Account) other).accountName)
                && expenditures.equals(((Account) other).expenditures));
    }

    @Override
    public int hashCode() {
        return expenditures.hashCode();
    }

    @Override
    public ObservableList<Repeat> getRepeatByDate(LocalDate date) {
        return FXCollections.observableArrayList(
                repeats.stream().filter(repeat -> repeat.isOn(date)).collect(Collectors.toList()));
    }

    @Override
    public UniqueExpenditureList getExpByDate(String date) {
        return new UniqueExpenditureList(
                getExpenditureStream()
                    .filter(exp -> exp.getDate().toString().equals(date))
                    .collect(Collectors.toList())
        );
    }

    @Override
    public UniqueExpenditureList getExpByDate(LocalDate date) {
        return getExpByDate(date.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public Map<String, UniqueExpenditureList> getExpFromToInclusive(String startDate, String endDate) {
        return getExpFromToInclusive(new Date(startDate), new Date(endDate));
    }

    @Override
    public Map<String, UniqueExpenditureList> getExpFromToInclusive(Date start, Date end) {
        Map<String, UniqueExpenditureList> expMap = new HashMap<>();
        getExpenditureStream()
                .filter(exp -> Date.isEqualOrBefore(start, exp.getDate())
                            && Date.isEqualOrBefore(exp.getDate(), end))
                .forEach(exp -> {
                    String date = exp.getDate().toString();
                    if (!expMap.containsKey(date)) {
                        UniqueExpenditureList expList = new UniqueExpenditureList();
                        expList.add(exp);
                        expMap.put(date, expList);
                    } else {
                        expMap.get(date).add(exp);
                    }
                });
        return expMap;
    }

    private Stream<Expenditure> getExpenditureStream() {
        return StreamSupport.stream(expenditures.spliterator(), false);
    }
}
