package seedu.address.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Expenditure's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInfo(String)}
 */
public class Info {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullInfo;

    /**
     * Constructs a {@code Info}.
     *
     * @param info A valid name.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        fullInfo = info;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullInfo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && fullInfo.equals(((Info) other).fullInfo)); // state check
    }

    @Override
    public int hashCode() {
        return fullInfo.hashCode();
    }

}
