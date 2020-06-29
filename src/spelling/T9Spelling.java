package spelling;

import java.util.*;

public class T9Spelling
{
    String      checking_result = "ERROR";
    String[]    lines           = null;
    int         lines_num       = 0;

    public String MainProcess(String input_str)
    {
        String ret_value = "ERROR";

        if( CheckInputStr(input_str) )
        {
            ret_value = Processing(lines_num, lines);
        }
        else
        {
            ret_value = checking_result;
        }

        return ret_value;
    }

    private boolean CheckInputStr(String input_str)
    {
        boolean ret_value = false;

        if (input_str.length() == 0)
        {
            checking_result      = "Input string is empty";
        }
        else
        {
            //String[] delimiter_strings = { "\n", "\r\n" };
            String      delimiter_strings   = "\n";
            String[]    data                = input_str.split(delimiter_strings);

            try
            {
                lines_num   = Integer.parseInt(data[0]);
            }
            catch (NumberFormatException e)
            {
                checking_result      = "Invalid input format";
            }

            if (checking_result != "Invalid input format")
            {
                if (lines_num >= 1 && lines_num <= 100)
                {
                    if( data.length == lines_num + 1)
                    {
                        boolean bad_line_length = false;
                                lines           = new String[lines_num];

                        for( int i = 0; i < lines_num; i++ )
                        {
                            if( data[i + 1].length() == 0 || data[i + 1].length() > 1000)
                            {
                                bad_line_length = true;
                                break;
                            }

                            lines[i] = data[i + 1];
                        }

                        if( !bad_line_length )
                        {
                            checking_result      = "Ok";
                            ret_value   = true;
                        }
                        else
                        {
                            checking_result = "Line length error (must be from 1 to 1000)";
                        }
                    }
                    else
                    {
                        checking_result      = "Wrong number of lines";
                    }
                }
                else
                {
                    checking_result = (lines_num < 1) ? "Lines number must be positive" : "Too large lines number";
                }
            }
            else
            {
                checking_result      = "Invalid input format";
            }

        }

        return ret_value;
    }

    private void FillDictionary(HashMap<Character, String> dictionary)
    {
        dictionary.put('a', "2");
        dictionary.put('b', "22");
        dictionary.put('c', "222");
        dictionary.put('d', "3");
        dictionary.put('e', "33");
        dictionary.put('f', "333");
        dictionary.put('g', "4");
        dictionary.put('h', "44");
        dictionary.put('i', "444");
        dictionary.put('j', "5");
        dictionary.put('k', "55");
        dictionary.put('l', "555");
        dictionary.put('m', "6");
        dictionary.put('n', "66");
        dictionary.put('o', "666");
        dictionary.put('p', "7");
        dictionary.put('q', "77");
        dictionary.put('r', "777");
        dictionary.put('s', "7777");
        dictionary.put('t', "8");
        dictionary.put('u', "88");
        dictionary.put('v', "888");
        dictionary.put('w', "9");
        dictionary.put('x', "99");
        dictionary.put('y', "999");
        dictionary.put('z', "9999");
        dictionary.put(' ', "0");
    }

    private String Processing(int lines_num, String[] input_str)
    {
        String ret_value = "ERROR";

        if( lines_num > 0 && input_str.length == lines_num )
        {
            HashMap<Character, String>    dictionary  = new HashMap<Character, String>();
            ret_value   = "";

            FillDictionary(dictionary);

            boolean break_now = false;

            for ( int i = 0; i < lines_num; i++ )
            {
                if( i != 0 )
                {
                    ret_value += "\n";
                }

                ret_value += "Case #" + String.valueOf(i + 1) + ": ";

                String number_sequence = "";

                for (int j = 0; j < input_str[i].length(); j++)
                {
                    char symbol = input_str[i].charAt(j);
                    String number_sequence_cur = dictionary.get(symbol);

                    if (number_sequence_cur != null)
                    {
                        if (number_sequence.length() != 0)
                        {
                            if (number_sequence.charAt(number_sequence.length() - 1) == number_sequence_cur.charAt(0))
                            {
                                number_sequence += ' ';
                            }
                        }

                        number_sequence += number_sequence_cur;
                    }
                    else
                    {
                        break_now = true;
                        break;
                    }
                }

                if (!break_now)
                {
                    ret_value += number_sequence;
                }
                else
                {
                    ret_value = "Wrong symbol format";
                    break;
                }
            }

            dictionary.clear();
        }

        return ret_value;
    }
}