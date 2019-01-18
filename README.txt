Name: Kara Maedje
ID#: 0969222
Course: CIS*2430
Date: November 29th, 2017
Description: Assignment 3-README.txt

---PROGRAM DESCRIPTION---

The portfoio package functions as an Investment Portfolio that stores investments
and provides menue options to the user. The program begins by reading in an assumed 
comma seperated file through the command line in parameter 1. If no file is read, 
the program will generate its own for writing to upon closing the program. After the 
file is read, each investment will be stored into one array of type Investment. The
investments are also stored into a hashmap. After these steps the file is closed and
the user is brought to a GUI where they are greeted by a welcome messages and short
description of the program. They can select between 6 different options on the command 
menu:

1. Buy
2. Sell
3. Update
4. getGain
5. Search
6. Quit

The program ends when the user selects "Quit" or the "X" icon built into the Gui. When 
the quit option is selected, all investments in the investment list will be written
back to the file, overiding any exisiting investments.



---PROGRAM BUILD----

I always ran my program through netbeans. The only required field is the file in arg[0]
which I specified under Run->Set Project Configuration->Custom. The file
is named "test.txt". I have included it in my zipped file submission.

Most of my calculation functionality is done in my portfolio file wile the MainGui file 
contains the Gui aspect of the code.


---TEST PLAN---

FUNCTION/AREA OF INPUT: Command menuu options
INPUT: Click on Buy
EXPECTED OUTPUT: Gui for Buying will appear
OUTPUT: Gui appears, user can enter in text fields

FUNCTION/AREA OF INPUT: Selling symbol input
INPUT: null
EXPECTED OUTPUT: skip and wait for next value
OUTPUT: Run-time error

FUNCTION/AREA OF INPUT: newHash()
INPUT: type Investment passed through when buying
EXPECTED OUTPUT: function will run without error
OUTPUT: Function ran correctly

FUNCTION/AREA OF INPUT: Selecting Stock or Mutual Fund when selling
INPUT: "dsfsdfsDF"
EXPECTED OUTPUT: Ignore and wait for valid input
OUTPUT: Runtime error

FUNCTION/AREA OF INPUT: Update buttons
INPUT: testing is prev and next work as expected
EXPECTED OUTPUT: prev = deactivated if no prev invest, vise versa for next
OUTPUT: Buttons work as expected

FUNCTION/AREA OF INPUT: Entering in search fields
INPUT: empty string for ranges
EXPECTED OUTPUT: Accept as empty string and move on
OUTPUT: Does as expected, skips range search



---ASSUMPTIONS---

- I structued my input file as a comma separated file and the program expects the file
  be formatted this way in order to parse the contents into the invsestment array.
  (E.g. type,symbol,name,....)
- The file is read in through arg[0]
- When entering the low and high prices, I assumed the low price will actually be lower
  then the high price, vise-versa



---SIDE NOTES---

- My searching doesn't work perfectly, out of 8 possible cases, if the user enteres only keyword and range
  or all the fields, my progams struggles with matching the keywords to all the matches for the other fields.
  I was gladly able to get the keyword search to work however in all other cases, for one keyword or multiple.
  I did not comment out the possible cases that don't work so that they can be tested however I just wanted to
  make is known here that I am aware of the error and that I will most likely loose marks. The problem cases
  are titled "CASE 3" and "CASE 4" in my search function in the portfolio file.
- To see how searching works correctly, you can follow along the output in the side termnial, where the search
  results are more accurate then in the gui, where I was having trouble accessing values from the investment list.
- Error checking is moderate, the program runs with expected cases but may run into error on
  potential unexpected cases (E.g. passing in a string where a number is expected when searching
  for a range)
- I ran into a lot of trouble with my excepion handling and therefor wasn't able to comlpete all the 
  required abstract and exception handeling
- When writing back out to the file, I coudldn't get the investment type, so one field is missing,
  the type field. Writing file output is incomplete.
- Gui structure is generally complete, I lack titles on some of the menu panels though. 