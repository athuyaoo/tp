package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BLOCKROOM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOCKROOM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATRICULATION_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MATRICULATION_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MATRICULATION_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_GROUP_DESC_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_GROUP_DESC_DANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_GROUP_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_GROUP_DANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.studentgroup.StudentGroup;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withStudentGroups(VALID_STUDENT_GROUP_BASKETBALL)
                .withGender(VALID_GENDER_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple student groups - all accepted
        Person expectedPersonMultipleStudentGroups = new PersonBuilder(BOB).withGender(VALID_GENDER_BOB)
                .withStudentGroups(VALID_STUDENT_GROUP_BASKETBALL, VALID_STUDENT_GROUP_DANCE)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL
                + MATRICULATION_NUMBER_DESC_BOB + GENDER_DESC_BOB,
                new AddCommand(expectedPersonMultipleStudentGroups));

        // multiple matriculation number - last matriculation number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL
                + MATRICULATION_NUMBER_DESC_AMY + MATRICULATION_NUMBER_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BLOCKROOM_DESC_BOB + STUDENT_GROUP_DESC_BASKETBALL + MATRICULATION_NUMBER_DESC_BOB
                + GENDER_DESC_AMY + GENDER_DESC_BOB,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero student groups
        Person expectedPerson = new PersonBuilder(AMY).withStudentGroups().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + BLOCKROOM_DESC_AMY + GENDER_DESC_AMY + MATRICULATION_NUMBER_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB + MATRICULATION_NUMBER_DESC_BOB, expectedMessage);

        // missing matriculation number prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + VALID_MATRICULATION_NUMBER_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + VALID_GENDER_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_GENDER_BOB
                + VALID_MATRICULATION_NUMBER_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL
                + MATRICULATION_NUMBER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL
                + MATRICULATION_NUMBER_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL
                + MATRICULATION_NUMBER_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL + MATRICULATION_NUMBER_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + INVALID_GENDER
                + STUDENT_GROUP_DESC_DANCE + STUDENT_GROUP_DESC_BASKETBALL + MATRICULATION_NUMBER_DESC_BOB,
                Gender.MESSAGE_CONSTRAINTS);

        // invalid student group
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + INVALID_STUDENT_GROUP_DESC + VALID_STUDENT_GROUP_BASKETBALL + MATRICULATION_NUMBER_DESC_BOB,
                StudentGroup.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB
                + MATRICULATION_NUMBER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB + STUDENT_GROUP_DESC_DANCE
                + STUDENT_GROUP_DESC_BASKETBALL + MATRICULATION_NUMBER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // invalid matriculation number
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BLOCKROOM_DESC_BOB + GENDER_DESC_BOB + STUDENT_GROUP_DESC_DANCE
                + STUDENT_GROUP_DESC_BASKETBALL
                + INVALID_MATRICULATION_NUMBER_DESC, MatriculationNumber.MESSAGE_CONSTRAINTS);
    }
}
