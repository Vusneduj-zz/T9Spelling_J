import spelling.T9Spelling;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class T9SpellingTest {

    @Test
    void TestEmptyString() {
        String  input_str    = "";
        String  exp_value    = "Input string is empty";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestBadStringBeginSymAndNum()
    {
        String  input_str    = "a4\nabs";
        String  exp_value    = "Invalid input format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestBadStringBeginNumAndSym()
    {
        String  input_str    = "4a\nabs";
        String  exp_value    = "Invalid input format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestBadStringBeginSymsAndNewString()
    {
        String  input_str    = "ba\nabs";
        String  exp_value    = "Invalid input format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestBadStringBeginNewStringAndSyms()
    {
        String  input_str    = "\nabs";
        String  exp_value    = "Invalid input format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestBadStringBeginSymsOnly()
    {
        String  input_str    = "abs";
        String  exp_value    = "Invalid input format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLinesNumberLess()
    {
        String  input_str    = "4\nsdf\nwerw\nsdaf";
        String  exp_value    = "Wrong number of lines";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLinesNumberGreater()
    {
        String  input_str    = "4\nsdf\nwerw\nsdaf\nwerf\nfagf";
        String  exp_value    = "Wrong number of lines";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLinesNumberNegative()
    {
        String  input_str    = "-5\nsdf\nwerw\nsdaf\nwerf\nfagf";
        String  exp_value    = "Lines number must be positive";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLinesNumberPositive()
    {
        String  input_str    = "101\nsdf\nwerw\nsdaf\nwerf\nfagf";
        String  exp_value    = "Too large lines number";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLineLengthTooShort()
    {
        String  input_str    = "3\n\ndsaf\nxcb";
        String  exp_value    = "Line length error (must be from 1 to 1000)";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongLineLengthTooLarge()
    {
        String  input_str    = "1\n";
        for( int i = 0; i < 1001; i++ )
        {
            input_str += 'a';
        }
        String  exp_value    = "Line length error (must be from 1 to 1000)";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongUpCaseSyms()
    {
        String  input_str    = "2\naSf\ndsfaw";
        String  exp_value    = "Wrong symbol format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestWrongOtherSyms()
    {
        String  input_str    = "2\na,f\ndsfaw";
        String  exp_value    = "Wrong symbol format";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestDataFromTask()
    {
        String  input_str    = "4\nhi\nyes\nfoo  bar\nhello world";
        String  exp_value    = "Case #1: 44 444\nCase #2: 999337777\nCase #3: 333666 6660 022 2777\nCase #4: 4433555 555666096667775553";

        T9Spelling  spelling    = new T9Spelling();
        String      ret_value   = spelling.MainProcess(input_str);

        assertEquals(exp_value, ret_value);
    }

    @Test
    void TestDataFromFile() throws IOException
    {
        String  file_path_in    = "./test.in";
        String  file_path_out   = "./test.out";

        File    input_file  = new File(file_path_in);
        File    output_file = new File(file_path_out);

        if( input_file.exists() && output_file.exists() )
        {
            String  input_str       = new String(Files.readAllBytes(Paths.get(file_path_in)), StandardCharsets.UTF_8);
            String  exp_value       = new String(Files.readAllBytes(Paths.get(file_path_out)), StandardCharsets.UTF_8);

            input_str = input_str.replace("\r\n", "\n");
            exp_value = exp_value.replace("\r\n", "\n");

            T9Spelling  spelling    = new T9Spelling();
            String      ret_value   = spelling.MainProcess(input_str);

            assertEquals(exp_value, ret_value);
        }
        else
        {
            assertTrue(false);
        }
    }
}