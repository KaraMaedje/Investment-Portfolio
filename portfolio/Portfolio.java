package portfolio;

/**
 * @author Kara
 */
import javax.swing.JFrame;

import java.io.BufferedReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;

import java.util.StringTokenizer;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
//import private portfolio.MainGui.price2;
//import static portfolio.MainGui.quantity2;
//import static portfolio.MainGui.symbol;

/**
 *
 * @author Kara
 */
public class Portfolio {

    public static ArrayList<Investment> investmentList = new ArrayList<Investment>();
    public static HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
    //private ArrayList<Integer> index = new ArrayList<Integer>();
    public static double totalGain = 0;
    //private MainGui guiAccess = new MainGui();
    
    public static int success = 0;
    public static int sellSuccess = 0;

    /**
     * @param arg the command line arguments
     */
    public static void main(String[] arg) {

        Portfolio investmentPortfolio = new Portfolio();
        MainGui gui = new MainGui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        investmentPortfolio.run(arg);
    }

    public void run(String[] arg) {
        String type;
        String symbol;
        String name;
        String quantityOld;
        String priceOld;
        String bookValueOld;
        
        // Opening the file and loadig its contents
        ArrayList<String> tempArray = new ArrayList<String>();
        String del = ",";
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(arg[0]));
            String line;

            while ((line = inputStream.readLine()) != null) {
                tempArray.add(line);
            }
            System.out.println("File load successful!\n");
            inputStream.close();

        } catch (IOException e) {
            System.out.println("Unable to open file.\n" + e + arg[0]);

            // if no file, create one for output
        }

        // iterate through the array and add to investment array
        for (int i = 0; i < tempArray.size(); i++) {

            String currentLine = tempArray.get(i);
            StringTokenizer tokens = new StringTokenizer(currentLine, ",");

            while (tokens.hasMoreTokens()) {
                type = tokens.nextToken();
                symbol = tokens.nextToken();
                name = tokens.nextToken();
                quantityOld = tokens.nextToken();
                int quantity = Integer.parseInt(quantityOld);
                priceOld = tokens.nextToken();
                double price = Double.parseDouble(priceOld);
                bookValueOld = tokens.nextToken();
                double bookValue = Double.parseDouble(bookValueOld);

                if (type.equals("stock")) {
                    Stock tempStock = new Stock(symbol, name, quantity, price, bookValue);
                    investmentList.add(tempStock);
                }
                if (type.equals("mutualfund")) {
                    MutualFund tempMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
                    investmentList.add(tempMutualFund);
                }
            }
        }
        Hash(); // put everything into the hashmap
        System.out.println("-------------- Printing map: ------------");  // error checking
        System.out.println(map);
        // check it everything worked 
        System.out.println(investmentList.toString());

        /*// Access to Main Menu Begins 
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        int option = 0;
        int option2 = 0;
        int runProgram = 0;
        //double[] gainList = new double[100];
        double gain = 0;

        while ((option != 6) && (runProgram != 1)) {

            System.out.println("-------Investment Portfolio-------");
            System.out.println("Select from the following options");
            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. Update");
            System.out.println("4. getGain");
            System.out.println("5. Search");
            System.out.println("6. Quit");
            System.out.println("Option:");

            option = in.nextInt();

            if (option == 1) {
                System.out.println("Select investment type:");
                System.out.println("1. Stock");
                System.out.println("2. Mutual Fund");
                option2 = in2.nextInt();

                if (option2 == 1) {
                    //buyStock();
                }
                if (option2 == 2) {
                    //buyMutualFund();
                }
            }
            if (option == 2) {
                System.out.println("Select investment type:");
                System.out.println("1. Stock");
                System.out.println("2. Mutual Fund");
                option2 = in2.nextInt();

                if (option2 == 1) {
                    //sellStock();
                }
                if (option2 == 2) {
                    //sellMutualFund();
                }
            }
            if (option == 3) {
                update();
            }
            if (option == 4) {
                getGain();
            }
            if (option == 5) {
                search("td", "Toronto", " ", " ");
            }
        }*/
 /*
        //before ending program write back to file
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(new FileOutputStream(arg[0]));
            
        }catch (FileNotFoundException e){
            //create new file and write to it
        }
        //overwrite file
        for(int i=0; i < investmentList.size(); i++){
            Investment invest = investmentList.get(i);
            outputStream.println(invest.getSymbol()+ "," + invest.getName()+ "," + invest.getQuantity() + "," +  invest.getPrice() + "," + invest.getBookValue());
        } */
    }
    // -------------------------------------------------------------------------
    // --------------------------------FUNCTIONS--------------------------------
    // -------------------------------------------------------------------------

    public static void buyStock(String s, String n, int q, double p) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        String symbol = s;
        String name = n;
        int quantity = q;
        double price = p;
        double bookValue = 0;
        double newBookValue = 0;
        int found = 0;
        int i = 0;

        /*System.out.println("Enter the following fields");
          
        System.out.println("Symbol:");
        symbol = input.nextLine();*/
        if (!investmentList.isEmpty()) {
            //check for a match
            for (i = 0; i < investmentList.size(); i++) {
                Investment existingInvestment = investmentList.get(i);
                if (existingInvestment.getSymbol().equalsIgnoreCase(symbol)) {
                    System.out.println("Stock already exists.");
                    //System.out.println("Quantity:");
                    //quantity = input.nextInt();
                    existingInvestment.setQuantity(existingInvestment.getQuantity() + quantity);
                    //System.out.println("Price:");
                    //price = input.nextDouble();
                    existingInvestment.setPrice(price);
                    newBookValue = ((existingInvestment.getQuantity() * existingInvestment.getPrice()) + 9.99);
                    existingInvestment.setBookValue(newBookValue);
                    System.out.println("Add successful.");
                    System.out.println("---Current Investments---");
                    System.out.println(investmentList.toString());
                    found = 1;
                }
            }
            // list wasn't empty but no match
            if (found == 0) {
                /*System.out.println("Name:");
                name = input.nextLine();
                //input.nextLine();

                System.out.println("Quantity:");
                quantity = input.nextInt();
                //input.nextLine();

                System.out.println("Price");
                price = input.nextDouble();
                //input.nextLine();*/

                // temp stock without bookValue
                Stock tempStock = new Stock(symbol, name, quantity, price, bookValue);
                bookValue = (quantity * price) + (9.99);

                Stock newStock = new Stock(symbol, name, quantity, price, bookValue);
                investmentList.add(newStock);
                newHash(newStock);
                System.out.println("Add successful.");
                System.out.println("---Current Investments---");
                System.out.println(investmentList.toString());
                System.out.println(map);
            }
        }
        if (investmentList.isEmpty()) {

            /*System.out.println("Name:");
            name = input.nextLine();
            //input.nextLine();

            System.out.println("Quantity:");
            quantity = input.nextInt();
            //input.nextLine();

            System.out.println("Price");
            price = input.nextDouble();
            //input.nextLine();*/
            // temp stock without bookValue
            Stock tempStock = new Stock(symbol, name, quantity, price, bookValue);
            bookValue = (quantity * price) + (9.99);

            Stock newStock = new Stock(symbol, name, quantity, price, bookValue);
            investmentList.add(newStock);
            newHash(newStock);
            System.out.println("Add successful.");
            System.out.println("---Current Investments---");
            System.out.println(investmentList.toString());
            System.out.println(map);
        }

    }

    public static void sellStock(String s, int q, double p) {

        //Scanner input = new Scanner(System.in);
        String symbol = s;
        double price = 0;
        price = p;
        int quantity = 0;
        quantity = q;
        double oldPrice = 0;
        int i = 0;
        double sellPayment = 0;
        int match = 0;
        double gain = 0;
        /*
        //System.out.println("Sell Data: " + " " + s + " " + q + " " + p); 
        System.out.println("Enter fields of investment to sell:");
        System.out.println("Symbol:");
        symbol = input.nextLine();
        System.out.println("Price:");
        price = input.nextDouble();
        System.out.println("Quantity to sell:");
        quantity = input.nextInt();*/
        //------------------------------------

        for (i = 0; i < investmentList.size(); i++) {
            Investment existingInvestment = investmentList.get(i);

            if (existingInvestment.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println("Match found");
                match++;

                if ((existingInvestment.getQuantity() == quantity) || (existingInvestment.getQuantity() > quantity)) {
                    sellPayment = ((quantity * existingInvestment.getPrice()) - 9.99);
                    int oldQuantity = existingInvestment.getQuantity();
                    oldPrice = existingInvestment.getPrice();
                    existingInvestment.setQuantity(existingInvestment.getQuantity() - quantity);

                    if (existingInvestment.getQuantity() == 0) {
                        investmentList.remove(existingInvestment);
                        System.out.println("Stock will be deleted.");
                        System.out.println("Revenue:" + sellPayment);
                        sellSuccess = 1;
                    } else {
                        //adjust book value
                        existingInvestment.setPrice(price);
                        double newBookValue = (existingInvestment.getBookValue() * ((existingInvestment.getQuantity() / oldQuantity)) - (9.99));
                        existingInvestment.setBookValue(newBookValue);
                        System.out.println(investmentList.toString());
                        System.out.println("Revenue:" + sellPayment);
                        sellSuccess = 1;

                        if (existingInvestment.getPrice() < oldPrice) {
                            gain = oldPrice - existingInvestment.getPrice();
                            gain = (-(gain));
                            totalGain = totalGain + gain;
                        }
                        if (existingInvestment.getPrice() > oldPrice) {
                            gain = existingInvestment.getPrice() - oldPrice;
                            totalGain = totalGain + gain;
                        }
                    }

                }
                if (quantity > existingInvestment.getQuantity()) {
                    System.out.println("Entered quantity exceeds current total of entered investment.");
                    sellSuccess = 7;
                    break;
                }
            }
        }
        if (match == 0) {
            System.out.println("Stock does not exist.");
            sellSuccess = 7;
        }

    }

    public static void update() {

        int i = 0;
        for (i = 0; i < investmentList.size(); i++) {
            Investment existingInvestment = investmentList.get(i);
            System.out.println("Stock:" + existingInvestment.getSymbol());
            System.out.println("Price:" + existingInvestment.getPrice());
        }
        /*int i = 0;
        double newPrice = 0;
        double oldPrice = 0;
        double gain = 0;
        Scanner priceInput = new Scanner(System.in);
        
        //update all investments
        if (!investmentList.isEmpty()){
            for(i=0; i < investmentList.size(); i++){
                Investment existingInvestment = investmentList.get(i);
                oldPrice = existingInvestment.getPrice();
                System.out.println("Updating all Stock Prices..");
                System.out.println("Investment:" + existingInvestment.getSymbol());
                System.out.println("Enter new price:");
                newPrice = priceInput.nextDouble();
                existingInvestment.setPrice(newPrice);
                if(existingInvestment.getPrice() < oldPrice){
                    gain = oldPrice - existingInvestment.getPrice();
                    gain = (-(gain));
                    totalGain = totalGain + gain;
                    }
                if(existingInvestment.getPrice() > oldPrice){
                    gain = existingInvestment.getPrice() - oldPrice;
                    totalGain = totalGain + gain;
                }
            }
        //print out all new prices (error checking)

    }
        if (investmentList.isEmpty()){
            System.out.println("No existing investments to update.\n");
        }*/
    }

    public static void getGain() {
        System.out.println("Current Gain of all investents:" + totalGain);

    }

    public static void buyMutualFund(String s, String n, int q, double p) {

        Scanner input = new Scanner(System.in);
        int choice = 0;

        String symbol = s;
        String name = n;
        int quantity = q;
        double price = p;
        double bookValue = 0;
        double newBookValue = 0;
        int found = 0;
        int i = 0;

        //input.nextLine();
        /*System.out.println("Enter the following fields");
          
        System.out.println("Symbol:");
        symbol = input.nextLine();*/
        if (!investmentList.isEmpty()) {
            //check for a match
            for (i = 0; i < investmentList.size(); i++) {
                Investment existingInvestment = investmentList.get(i);
                if (existingInvestment.getSymbol().equalsIgnoreCase(symbol)) {
                    System.out.println("MutualFund already exists.");

                    existingInvestment.setQuantity(existingInvestment.getQuantity() + quantity);

                    existingInvestment.setPrice(price);
                    newBookValue = ((existingInvestment.getQuantity() * existingInvestment.getPrice()) + 9.99);
                    existingInvestment.setBookValue(newBookValue);
                    System.out.println("Add successful.");
                    System.out.println("---Current Investments---");
                    System.out.println(investmentList.toString());
                    found = 1;
                }
            }
            // list wasn't empty but no match
            if (found == 0) {
                /*System.out.println("Name:");
                name = input.nextLine();
                //input.nextLine();

                System.out.println("Quantity:");
                quantity = input.nextInt();
                //input.nextLine();

                System.out.println("Price");
                price = input.nextDouble();*/
                //input.nextLine();

                // temp mutualFund without bookValue
                MutualFund tempMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
                bookValue = (quantity * price);

                MutualFund newMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
                investmentList.add(newMutualFund);
                newHash(newMutualFund);
                System.out.println("Add successful.");
                System.out.println("---Current Investments---");
                System.out.println(investmentList.toString());
                System.out.println(map);
            }
        }

        if (investmentList.isEmpty()) {

            /*System.out.println("Name:");
            name = input.nextLine();
            //input.nextLine();

            System.out.println("Quantity:");
            quantity = input.nextInt();
            //input.nextLine();

            System.out.println("Price");
            price = input.nextDouble();*/
            //input.nextLine();
            // temp stock without bookValue
            MutualFund tempMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
            bookValue = (quantity * price);

            MutualFund newMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
            investmentList.add(newMutualFund);
            newHash(newMutualFund);
            System.out.println("Add successful.");
            System.out.println("---Current Investments---");
            System.out.println(investmentList.toString());
            System.out.println(map);
        }
    }

    public static void sellMutualFund(String s, int q, double p) {

        //Scanner input = new Scanner(System.in);
        String symbol = s;
        double price = 0;
        price = p;
        double oldPrice = 0;
        int quantity = 0;
        quantity = q;
        double sellPayment = 0;
        int match = 0;
        double gain = 0;
        int i = 0;

        /*System.out.println("Enter fields of investment to sell:");
        System.out.println("Symbol:");
        symbol = input.nextLine();
        System.out.println("Price:");
        price = input.nextDouble();
        System.out.println("Quantity to sell:");
        quantity = input.nextInt();*/
        for (i = 0; i < investmentList.size(); i++) {
            Investment existingInvestment = investmentList.get(i);

            if (existingInvestment.getSymbol().equals(symbol)) {
                System.out.println("Match found");
                match++;

                if ((existingInvestment.getQuantity() == quantity) || (existingInvestment.getQuantity() > quantity)) {
                    sellPayment = ((quantity * existingInvestment.getPrice()) - 45.00);
                    int oldQuantity = existingInvestment.getQuantity();
                    oldPrice = existingInvestment.getPrice();
                    existingInvestment.setQuantity(existingInvestment.getQuantity() - quantity);

                    if (existingInvestment.getQuantity() == 0) {
                        investmentList.remove(existingInvestment);
                        System.out.println("Mutual Fund will be deleted.");
                        System.out.println("Revenue:" + sellPayment);
                        sellSuccess = 1;
                    } else {
                        //adjust book value
                        existingInvestment.setPrice(price);
                        double newBookValue = (existingInvestment.getBookValue() * ((existingInvestment.getQuantity() / oldQuantity)));
                        existingInvestment.setBookValue(newBookValue);
                        System.out.println(investmentList.toString());
                        System.out.println("Revenue:" + sellPayment);
                        sellSuccess = 1;

                        if (existingInvestment.getPrice() < oldPrice) {
                            gain = oldPrice - existingInvestment.getPrice();
                            gain = (-(gain));
                            totalGain = totalGain + gain;
                        }
                        if (existingInvestment.getPrice() > oldPrice) {
                            gain = existingInvestment.getPrice() - oldPrice;
                            totalGain = totalGain + gain;
                        }
                    }

                }
                if (quantity > existingInvestment.getQuantity()) {
                    System.out.println("Entered quantity exceeds current total of entered investment.");
                    sellSuccess = 7;
                    break;
                }
            }
        }
        if (match == 0) {
            System.out.println("Mutual Fund does not exist.");
            sellSuccess = 7;
        }

    }

    public static void Hash() {
        for (int i = 0; i < investmentList.size(); i++) {
            // for multiple tokens
            ArrayList<String> tempArray = new ArrayList<String>();

            Investment invest = investmentList.get(i);
            StringTokenizer tokens = new StringTokenizer(invest.getName(), " ");

            // if there was more than one name
            while (tokens.hasMoreTokens()) {
                String tok = tokens.nextToken();
                tok = tok.toLowerCase();
                tempArray.add(tok);
            }
            // adding to the hashmap
            for (int y = 0; y < tempArray.size(); y++) {
                String currentTok = tempArray.get(y);
                if (map.containsKey(currentTok)) {
                    // then just add the index of this name into exisiting index list
                    map.get(currentTok).add(i);
                }
                if (!map.containsKey(currentTok)) {
                    ArrayList<Integer> buff = new ArrayList<Integer>();
                    buff.add(i);
                    // add the name to the hashmap plus its index
                    map.put(currentTok, buff);
                }
            }
        }
    }

    public static void newHash(Investment temp) {

        ArrayList<String> tempArray = new ArrayList<String>();

        StringTokenizer tokens = new StringTokenizer(temp.getName(), " ");

        // if there was more than one name
        while (tokens.hasMoreTokens()) {
            String tok = tokens.nextToken();
            tempArray.add(tok);
        }
        // adding to the hashmap
        for (int y = 0; y < tempArray.size(); y++) {
            String currentTok = tempArray.get(y);
            if (map.containsKey(currentTok)) {
                int index = ((investmentList.size()) - 1);
                // then just add the index of this name into exisiting index list
                map.get(currentTok).add(index);
            }

            if (!map.containsKey(currentTok)) {
                ArrayList<Integer> buff = new ArrayList<Integer>();
                int index = ((investmentList.size()) - 1);
                buff.add(index);
                // add the name to the hashmap plus its index
                map.put(currentTok, buff);
            }
        }
    }

    public static void search(String searchSymbol, String keywords, String lowPrice, String highPrice) {

        System.out.println("In here");
        ArrayList<String> tempArray = new ArrayList<String>();
        ArrayList<Integer> intersection = new ArrayList<Integer>();

        String initialRange = " ";
        int i = 0;
        int matches = 0;

        //First set up the initial range to search for
        if ((lowPrice.equals("")) && (highPrice.equals(""))) {      //both empty
            initialRange = "";  //no range, keep empty

        }
        if ((lowPrice != null && !lowPrice.trim().isEmpty()) && (highPrice != null && !highPrice.trim().isEmpty())) {      //both not empty
            initialRange = (lowPrice + "-" + highPrice);  //put in a range

        }
        if ((lowPrice != null && !lowPrice.trim().isEmpty()) && (highPrice.equals(""))) {        //high price empty

            initialRange = lowPrice;
        }
        if ((lowPrice.equals("")) && (highPrice != null && !highPrice.trim().isEmpty())) {       //low price empty

            initialRange = highPrice;
        }
        /*System.out.println(searchSymbol);
        System.out.println(keywords);
        System.out.println(initialRange);*/
        //-------------------------------CASE 1------------------------------------
        if ((searchSymbol.isEmpty()) && (keywords.isEmpty()) && (initialRange.isEmpty())) {
            System.out.println("No match found.");
            success = 7;
        }

        //-------------------------------CASE 2------------------------------------
        // only symbol was entered
        if ((!searchSymbol.isEmpty()) && (keywords.isEmpty()) && (initialRange.isEmpty())) {
            for (i = 0; i < investmentList.size(); i++) {
                Investment existingInvest = investmentList.get(i);
                // check if symbol matches
                for (String symbol : existingInvest.getSymbol().split(" ")) {
                    if (symbol.equalsIgnoreCase(searchSymbol)) {
                        System.out.println("Match found:");
                        success = 1;
                        System.out.println(existingInvest.toString());
                        matches++;
                    }
                }
            }
            if (matches == 0) {
                System.out.println("No matches found.");
                success = 7;
            }
        }

        //-------------------------------------CASE 3------------------------------------------                !!!!!!!!    this
        if ((!searchSymbol.isEmpty()) && (!keywords.isEmpty()) && (initialRange.isEmpty())) {
            //System.out.println("got here");
            //int match = 0;
            boolean match = true;
            int foundAt = 0;
            for (i = 0; i < investmentList.size(); i++) {   // MAIN LOOP FOR ACCESS
                Investment existingInvest = investmentList.get(i);

                //-------Check for Symbol-------
                for (String symbol : existingInvest.getSymbol().split(" ")) {
                    if (symbol.equals(searchSymbol)) {
                        foundAt = i;

                        //starting here 
                        //------Check for keywords-------
                        StringTokenizer tokens = new StringTokenizer(keywords, " ");
                        // if there was more than one name
                        while (tokens.hasMoreTokens()) {
                            String tok = tokens.nextToken();
                            tempArray.add(tok);
                        }
                        // searching the hashmap
                        // first check if all keywords are in the hashmap before checking if they all match
                        ArrayList<Integer> allIndices = new ArrayList<Integer>();

                        //-----------------------------------------------
                        for (int y = 0; y < tempArray.size(); y++) {
                            String currentTok = tempArray.get(y);
                            currentTok = currentTok.toLowerCase();
                            System.out.println(currentTok);

                            ArrayList<Integer> indexList = new ArrayList<Integer>();
                            //if (map.containsKey(currentTok)) {
                            if ((existingInvest.getName().toLowerCase()).contains(currentTok)) {

                                match = true;
                                //System.out.println("here");
                            }
                            if (!(existingInvest.getName().toLowerCase()).contains(currentTok)) {
                                match = false;   //match failed
                                break;   //all tokens don't exists, THUS no matches
                            }
                        }

                    }
                }
            }
            if (match == false) {
                System.out.println("No matches found.");
                success = 7;
            }
            if (match == true) {
                System.out.println(investmentList.get(foundAt).toString());
                foundAt = 0;
                success = 1;
            }
        }
        //-------------------------------CASE 4------------------------------------                     !!!!!!!!   this
        if ((!searchSymbol.isEmpty()) && (!keywords.isEmpty()) && (!initialRange.isEmpty())) {
            int match = 0;
            for (i = 0; i < investmentList.size(); i++) {   // MAIN LOOP FOR ACCESS
                Investment existingInvest = investmentList.get(i);

                //-------Check for Symbol-------
                for (String symbol : existingInvest.getSymbol().split(" ")) {
                    if (symbol.equals(searchSymbol)) {

                        //------Check for keywords-------
                        StringTokenizer tokens = new StringTokenizer(keywords, " ");
                        // if there was more than one name
                        while (tokens.hasMoreTokens()) {
                            String tok = tokens.nextToken();
                            tempArray.add(tok);
                        }
                        // searching the hashmap
                        for (int y = 0; y < tempArray.size(); y++) {
                            String currentTok = tempArray.get(y);
                            if (existingInvest.getName().contains(currentTok)) {
                                ArrayList<String> rangeArray = new ArrayList<String>();

                                if (initialRange.contains("-")) {
                                    //more than one value
                                    StringTokenizer rangeToks = new StringTokenizer(initialRange, "-");

                                    while (rangeToks.hasMoreTokens()) {
                                        String tok = rangeToks.nextToken();
                                        rangeArray.add(tok);
                                    }
                                    Double firstRange = Double.parseDouble(rangeArray.get(0));
                                    Double secondRange = Double.parseDouble(rangeArray.get(1));
                                    //for all investments indexes that match the prior conditions

                                    if ((existingInvest.getPrice() > firstRange) && (existingInvest.getPrice() < secondRange)) {
                                        //match found, print out the investment
                                        System.out.println("Match found:");
                                        System.out.println(existingInvest.toString()); //??
                                        match++;
                                        success = 1;
                                    }
                                }
                                if (!initialRange.contains("-")) {
                                    //for all investments indexes that match the prior condiions
                                    if (existingInvest.getPrice() == Double.parseDouble(initialRange)) {
                                        //match found, print out the investment
                                        System.out.println("Match found:");
                                        System.out.println(existingInvest.toString()); //??
                                        match++;
                                        success = 1;
                                    }
                                }
                            }
                            if (!existingInvest.getName().contains(currentTok)) {
                                // then its not a match
                                break;  // break out of whole loop 
                            }
                        }
                    }
                }
            }
            if (match == 0) {
                System.out.println("No matches found.");
                success = 7;
            }
        }
        //-------------------------------CASE 5------------------------------------
        if ((!searchSymbol.isEmpty()) && (keywords.isEmpty()) && (!initialRange.isEmpty())) {
            int match = 0;
            for (i = 0; i < investmentList.size(); i++) {
                Investment existingInvest = investmentList.get(i);
                // check if symbol matches
                for (String symbol : existingInvest.getSymbol().split(" ")) {
                    if (symbol.equalsIgnoreCase(searchSymbol)) {

                        //now check for range
                        ArrayList<String> rangeArray = new ArrayList<String>();

                        if (initialRange.contains("-")) {
                            //more than one value
                            StringTokenizer rangeToks = new StringTokenizer(initialRange, "-");

                            while (rangeToks.hasMoreTokens()) {
                                String tok = rangeToks.nextToken();
                                rangeArray.add(tok);
                            }
                            Double firstRange = Double.parseDouble(rangeArray.get(0));
                            Double secondRange = Double.parseDouble(rangeArray.get(1));
                            //for all investments indexes that match the prior conditions

                            if ((existingInvest.getPrice() > firstRange) && (existingInvest.getPrice() < secondRange)) {
                                //match found, print out the investment
                                System.out.println("Match found:");
                                System.out.println(existingInvest.toString()); //??
                                match++;
                                success = 1;
                            }
                        }
                        if (!initialRange.contains("-")) {
                            //for all investments indexes that match the prior condiions
                            if (existingInvest.getPrice() == Double.parseDouble(initialRange)) {
                                //match found, print out the investment
                                System.out.println("Match found:");
                                System.out.println(existingInvest.toString()); //??
                                match++;
                                success = 1;
                            }
                        }
                        match++;
                    }
                }
            }
            if (match == 0) {
                System.out.println("No matches found.");
                success = 7;
                        
            }

        }
        //------------------------------------CASE 6 ------------------------------------------
        if ((searchSymbol.isEmpty()) && (keywords.isEmpty()) && (!initialRange.isEmpty())) {

            int match = 0;

            for (i = 0; i < investmentList.size(); i++) {   // MAIN LOOP FOR ACCESS

                Investment existingInvest = investmentList.get(i);
                ArrayList<String> rangeArray = new ArrayList<String>();

                if (initialRange.contains("-")) {
                    //more than one value
                    StringTokenizer rangeToks = new StringTokenizer(initialRange, "-");

                    while (rangeToks.hasMoreTokens()) {
                        String tok = rangeToks.nextToken();
                        rangeArray.add(tok);
                    }
                    Double firstRange = Double.parseDouble(rangeArray.get(0));
                    Double secondRange = Double.parseDouble(rangeArray.get(1));
                    //for all investments indexes that match the prior conditions

                    if ((existingInvest.getPrice() > firstRange) && (existingInvest.getPrice() < secondRange)) {
                        //match found, print out the investment
                        System.out.println("Match found:");
                        success = 1;
                        System.out.println(existingInvest.toString()); //??
                        match++;
                    }
                    /*else{
                        break;
                    }*/
                }
                if (!initialRange.contains("-")) {
                    //for all investments indexes that match the prior condiions
                    if (existingInvest.getPrice() == Double.parseDouble(initialRange)) {
                        //match found, print out the investment
                        System.out.println("Match found:");
                        success = 1;
                        System.out.println(existingInvest.toString()); //??
                        match++;
                    }
                    /*else{
                        break;
                    }*/
                }
            }
            if (match == 0) {
                System.out.println("No matches found.");
                success = 7;
            }
        }
        //------------------------------------CASE 7 ------------------------------------------                !!!!!!!!
        if ((searchSymbol.isEmpty()) && (!keywords.isEmpty()) && (initialRange.isEmpty())) {

            int match = 0;
            //------Check for keywords-------
            StringTokenizer tokens = new StringTokenizer(keywords, " ");
            // if there was more than one name
            while (tokens.hasMoreTokens()) {
                String tok = tokens.nextToken();
                tempArray.add(tok);
            }
            // searching the hashmap
            // first check if all keywords are in the hashmap before checking if they all match
            ArrayList<Integer> allIndices = new ArrayList<Integer>();

            //-----------------------------------------------
            for (int y = 0; y < tempArray.size(); y++) {
                String currentTok = tempArray.get(y);
                currentTok = currentTok.toLowerCase();
                ArrayList<Integer> indexList = new ArrayList<Integer>();
                if (map.containsKey(currentTok)) {
                    indexList = map.get(currentTok);
                    System.out.println(indexList);
                    for (int x = 0; x < indexList.size(); x++) {

                        int saved = indexList.get(x);

                        if (tempArray.size() == 1) {
                            investmentList.get(saved);
                            System.out.println(investmentList.get(saved).toString());
                            match++;

                        }
                        if (tempArray.size() > 1) {
                            allIndices.add(saved);
                        }
                    }
                }
                if (!(map.containsKey(currentTok))) {
                    break;   //all tokens don't exists, THUS no matches
                }
            }
            System.out.println(allIndices);
            //get duplicates, store into a new thing ->those are the intersections, MAGIC
            HashSet<Integer> intersections = new HashSet<>();
            for (int n = 0; n < allIndices.size(); n++) {
                for (int m = n + 1; m < allIndices.size(); m++) {
                    if (allIndices.get(n).equals(allIndices.get(m))) {

                        intersections.add(allIndices.get(n));
                    }
                }
            }
            System.out.println(intersections);
            ArrayList<Integer> things = new ArrayList<>(intersections);
            //now return all matches
            for (int n = 0; n < intersections.size(); n++) {

                int thing = things.get(n);
                //allIndices.add(saved);
                investmentList.get(thing);
                System.out.println(investmentList.get(thing).toString());
                match++;
                success = 1;
            }

            if (match == 0) {
                System.out.println("No matches found.");
                success = 7;
            }
        }

//------------------------------------CASE 8 ------------------------------------------            !!!!!!!!  this 
        if ((searchSymbol.isEmpty()) && (!keywords.isEmpty()) && (!initialRange.isEmpty())) {

            int match = 0;
            //------Check for keywords-------
            StringTokenizer tokens = new StringTokenizer(keywords, " ");
            // if there was more than one name
            while (tokens.hasMoreTokens()) {
                String tok = tokens.nextToken();
                tempArray.add(tok);
            }
            // searching the hashmap
            // first check if all keywords are in the hashmap before checking if they all match
            ArrayList<Integer> allIndices = new ArrayList<Integer>();

            //-----------------------------------------------
            for (int y = 0; y < tempArray.size(); y++) {
                String currentTok = tempArray.get(y);

                ArrayList<Integer> indexList = new ArrayList<Integer>();
                if (map.containsKey(currentTok)) {
                    indexList = map.get(currentTok);
                    System.out.println(indexList);
                    for (int x = 0; x < indexList.size(); x++) {

                        int saved = indexList.get(x);

                        if (tempArray.size() == 1) {
                            investmentList.get(saved);
                            System.out.println(investmentList.get(saved).toString());
                            match++;

                        }
                        if (tempArray.size() > 1) {
                            allIndices.add(saved);
                        }
                    }
                }
                if (!(map.containsKey(currentTok))) {
                    break;   //all tokens don't exists, THUS no matches
                }
            }
            System.out.println(allIndices);
            //get duplicates, store into a new thing ->those are the intersections, MAGIC
            HashSet<Integer> intersections = new HashSet<>();
            for (int n = 0; n < allIndices.size(); n++) {
                for (int m = n + 1; m < allIndices.size(); m++) {
                    if (allIndices.get(n).equals(allIndices.get(m))) {

                        intersections.add(allIndices.get(n));
                    }
                }
            }
            System.out.println(intersections);
            ArrayList<Integer> things = new ArrayList<>(intersections);
            //now return all matches
            for (int n = 0; n < intersections.size(); n++) {

                int possibleMatch = things.get(n);
                Investment test = investmentList.get(possibleMatch);

                //======now check if that match is in the corrent range=======
                ArrayList<String> rangeArray = new ArrayList<String>();

                if (initialRange.contains("-")) {
                    //more than one value
                    StringTokenizer rangeToks = new StringTokenizer(initialRange, "-");

                    while (rangeToks.hasMoreTokens()) {
                        String tok = rangeToks.nextToken();
                        rangeArray.add(tok);
                    }
                    Double firstRange = Double.parseDouble(rangeArray.get(0));
                    Double secondRange = Double.parseDouble(rangeArray.get(1));
                    //for all investments indexes that match the prior conditions

                    if ((test.getPrice() > firstRange) && (test.getPrice() < secondRange)) {
                        //match found, print out the investment
                        System.out.println("Match found:");
                        success = 1;
                        System.out.println(test.toString()); //??
                        match++;
                    }
                }
                if (!initialRange.contains("-")) {
                    //for all investments indexes that match the prior condiions
                    if (test.getPrice() == Double.parseDouble(initialRange)) {
                        //match found, print out the investment
                        System.out.println("Match found:");
                        success = 1;
                        System.out.println(test.toString()); //??
                        match++;
                    }
                }
            }
            if (match == 0) {
                System.out.println("No matches");
                success = 1;
            }
        }
    }
}
