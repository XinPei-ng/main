package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Expenditure.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code expenditure}.
     */
    public static String getAddCommand(Expenditure expenditure) {
        return ExpAddCommand.COMMAND_WORD + " " + getPersonDetails(expenditure);
    }

    /**
     * Returns the part of command string for the given {@code expenditure}'s details.
     */
    public static String getPersonDetails(Expenditure expenditure) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INFO + expenditure.getInfo().fullInfo + " ");
        sb.append(PREFIX_ID + expenditure.getId().value + " ");
        sb.append(PREFIX_AMOUNT + Double.toString(expenditure.getAmount().value) + " ");
        sb.append(PREFIX_DATE + expenditure.getDate().value + " ");
        expenditure.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpenditureDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditExpenditureDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getInfo().ifPresent(info -> sb.append(PREFIX_INFO).append(info.fullInfo).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.value).append(" "));
        descriptor.getDate().ifPresent(address -> sb.append(PREFIX_DATE).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
