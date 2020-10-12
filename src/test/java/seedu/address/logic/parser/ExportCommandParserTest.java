package seedu.address.logic.parser;

import static seedu.address.logic.commands.ExportCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

/**
 * Test cases for export command.
 */
public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgsEmail_returnsExportCommand() {
        assertParseSuccess(parser, "export email", new ExportCommand("email"));
    }

    @Test
    public void parse_validArgsPhone_returnsExportCommand() {
        assertParseSuccess(parser, "export phone", new ExportCommand("phone"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_USAGE);
    }
}
