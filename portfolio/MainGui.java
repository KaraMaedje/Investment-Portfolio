package portfolio;

/**
 * @author Kara
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.exit;

public class MainGui extends JFrame {

    private Portfolio portfolio = new Portfolio();

    private int counter = 0;

    private String symbol;
    private String name;
    private String quantity;
    private String price;
    private int quantity2;
    private double price2;
    private String lowPrice;
    private String highPrice;
    private static JTextField textField1 = new JTextField(9);
    private static JTextField textField2 = new JTextField(9);
    private static JTextField textField3 = new JTextField(9);
    private static JTextField textField4 = new JTextField(9);

    private static JTextField textField5 = new JTextField(13);
    private static JTextField textField6 = new JTextField(13);
    private static JTextField textField7 = new JTextField(13);

    private static JTextField textField8 = new JTextField(16);
    private static JTextField textField9 = new JTextField(16);
    private static JTextField textField10 = new JTextField(16);

    //buy gui 
    private static JButton buyResetButton = new JButton("Reset");
    private static JButton buyButton = new JButton("Buy");
    private static String[] investmentStrings = {"Stock", "MutualFund"};
    private static JComboBox comboBox = new JComboBox(investmentStrings);
    JTextArea buyMessage = new JTextArea(8, 40);

    //sell gui
    private static JButton sellButton = new JButton("Sell");
    private static JButton sellResetButton = new JButton("Reset");
    private JTextArea sellMessage = new JTextArea(8, 40);

    //update
    private JButton prevButton = new JButton("Prev");
    private JButton nextButton = new JButton("Next");
    private JButton saveButton = new JButton("Save");
    private JTextArea updateMessage = new JTextArea(8, 40);
    
    //getGain
    private JTextArea getGainMessage = new JTextArea(8, 40);
    private JTextField textField11 = new JTextField(16);
    
    //search
    private JTextArea searchMessage = new JTextArea(8, 40);
    private JButton searchButton = new JButton("Search");
    private JButton searchResetButton = new JButton("Reset");
    static JTextField textField12 = new JTextField(14);
    static JTextField textField13 = new JTextField(14);
    static JTextField textField14 = new JTextField(14);
    static JTextField textField15 = new JTextField(14);

    //----------------------------------------------------
    private JFrame main = new JFrame("Investment Portfolio");
    private JPanel panelCont = new JPanel();   // container panel
    private static JPanel welcomePanel = new JPanel();
    private static JPanel buyPanel = new JPanel();
    private static JPanel sellPanel = new JPanel();
    private static JPanel updatePanel = new JPanel();
    private static JPanel getGainPanel = new JPanel();
    private static JPanel searchPanel = new JPanel();
    CardLayout cl = new CardLayout();


    /*
    public static void main(String[], args)
    {
        MainGui gui = new MainGui();
        gui.setVisible(true);
    }
     */
    public MainGui() {

        main.setSize(500, 400);
        panelCont.setLayout(cl);

        panelCont.add(welcomePanel, "1");
        panelCont.add(buyPanel, "2");
        panelCont.add(sellPanel, "3");
        panelCont.add(updatePanel, "4");
        panelCont.add(getGainPanel, "5");
        panelCont.add(searchPanel, "6");

        cl.show(panelCont, "1");
        JTextArea welcome = new JTextArea(14, 40);
        welcome.setLineWrap(true);
        welcome.setWrapStyleWord(true);
        welcome.setText("\n\n\n\n\nWelcome to Investment Portfolio.\n\n\nChoose a command from the \"Commands\" menu "
                + "to buy or sell an investment, update prices for all investments, get gain for the "
                + "portfolio, search for relevant investments, or quit the program.\n");
        welcome.setEditable(false);
        welcomePanel.add(welcome);
        welcomePanel.setBackground(Color.WHITE);

        JMenu commandMenu = new JMenu("Commands");

        JMenuItem buyChoice = new JMenuItem("Buy");
        commandMenu.add(buyChoice);
        JMenuItem sellChoice = new JMenuItem("Sell");
        commandMenu.add(sellChoice);
        JMenuItem updateChoice = new JMenuItem("Update");
        commandMenu.add(updateChoice);
        JMenuItem getGainChoice = new JMenuItem("getGain");
        commandMenu.add(getGainChoice);
        JMenuItem searchChoice = new JMenuItem("Search");
        commandMenu.add(searchChoice);
        JMenuItem quitChoice = new JMenuItem("Quit");
        commandMenu.add(quitChoice);

        // Create a bar with the Commands Menu
        JMenuBar bar = new JMenuBar();
        bar.add(commandMenu);
        main.setJMenuBar(bar);

        // Call all GUI functions and set them up
        buyGui();
        sellGui();
        updateGui();
        getGainGui();
        searchGui();

        // Add action listeners for commands menu
        buyChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
                buyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //System.out.println("in here1");
                        //if (ae.getSource() == buyButton) {
                        //JComboBox cb = (JComboBox) ae.getSource();
                        String investType = comboBox.getSelectedItem().toString();
                        System.out.println("type");
                        //String investType = (String) cb.getSelectedItem();
                        //System.out.println("investment: " + investType);
                        if (investType.equals("Stock")) {
                            //if buy button is clicked, get fields 
                            symbol = textField1.getText();
                            name = textField2.getText();
                            quantity = textField3.getText();
                            quantity2 = Integer.parseInt(quantity);
                            price = textField4.getText();
                            price2 = Double.parseDouble(price);
                            System.out.println("Stock Data:" + symbol + name + quantity2 + price2);   //error checking
                            portfolio.buyStock(symbol, name, quantity2, price2);
                            buyMessage.append("Buying of stock was successful.\n\n");
                            textField1.setText("");
                            textField2.setText("");
                            textField3.setText("");
                            textField4.setText("");
                        }
                        if (investType.equals("MutualFund")) {  //change for MF
                            //if buy button is clicked, get fields 
                            symbol = textField1.getText();
                            name = textField2.getText();
                            quantity = textField3.getText();
                            quantity2 = Integer.parseInt(quantity);
                            price = textField4.getText();
                            price2 = Double.parseDouble(price);
                            System.out.println("MutualFund Data:" + symbol + name + quantity2 + price2);   //error checking
                            portfolio.buyMutualFund(symbol, name, quantity2, price2);
                            buyMessage.append("Buy of mutualfund was successful.\n\n");
                            textField1.setText("");
                            textField2.setText("");
                            textField3.setText("");
                            textField4.setText("");
                        }
                        //}
                    }
                });
                buyResetButton.addActionListener(new ActionListener() {
                    @Override
                    
                    public void actionPerformed(ActionEvent ae) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                    }
                });
            }
        });

        sellChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "3");
                sellButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        //if sell button is clicked, get fields 
                        symbol = textField5.getText();
                        quantity = textField6.getText();
                        quantity2 = Integer.parseInt(quantity);
                        price = textField7.getText();
                        price2 = Double.parseDouble(price);
                        //System.out.println("Data: " + " " +symbol + " " + quantity2 + " " + price2);   //error checking
                        portfolio.sellStock(symbol, quantity2, price2); //need to actually check if stock or MF
                        textField5.setText("");
                        textField6.setText("");
                        textField7.setText("");
                        if (portfolio.sellSuccess == 1){
                            sellMessage.append("Investment: " + " " + symbol + " was successfully sold.\n\n");
                        }
                        if (portfolio.sellSuccess == 7){
                            sellMessage.append("No matches found\n\n");
                        }

                    }
                });
                sellResetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        textField5.setText("");
                        textField6.setText("");
                        textField7.setText("");
                    }
                });
            }
        });
        updateChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "4");

                int i = 0;
                double newPrice = 0;
                double oldPrice = 0;
                double gain = 0;

                Investment existingInvestment = portfolio.investmentList.get(counter);
                textField8.setText(existingInvestment.getSymbol());
                textField9.setText(existingInvestment.getName());

                if (counter == 0) {
                    prevButton.setEnabled(false);
                }
                if (counter != 0) {
                    prevButton.setEnabled(true);
                }
                if (portfolio.investmentList.isEmpty()) {
                    System.out.println("No existing investments to update.\n");
                }

                prevButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        //go through investment list, grab symbol and name fields and display them in text area
                        //if the investment is first, disable 
                        if (counter != 0) {
                            Investment existingInvestment = portfolio.investmentList.get(counter - 1);
                            counter--;

                            textField8.setText(existingInvestment.getSymbol());
                            textField9.setText(existingInvestment.getName());

                            if (counter < (portfolio.investmentList.size())) {
                                nextButton.setEnabled(true);
                            } else {
                                nextButton.setEnabled(false); //disable it
                            }
                        }
                        if (counter == 0) {
                            prevButton.setEnabled(false); //keep disabled
                            if (counter < (portfolio.investmentList.size())) {
                                nextButton.setEnabled(true);
                            } else {
                                nextButton.setEnabled(false); //disable it

                            }
                        }
                    }
                });
                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (counter < portfolio.investmentList.size()) {
                            Investment existingInvestment = portfolio.investmentList.get(counter + 1);
                            counter++;
                            if (counter == 0) {
                                prevButton.setEnabled(false);
                            }
                            if (counter > 0) {
                                prevButton.setEnabled(true);
                            }
                            //System.out.println("Line" + counter);

                            textField8.setText(existingInvestment.getSymbol());
                            textField9.setText(existingInvestment.getName());
                        }
                        if (counter == (portfolio.investmentList.size() - 1)) {
                            nextButton.setEnabled(false); //disable it
                            if (counter == 0) {
                                prevButton.setEnabled(false);
                            }
                            if (counter > 0) {
                                prevButton.setEnabled(true);
                            }
                        }

                    }
                });
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //SAVE STUFF
                        Investment toChangeInvestment = portfolio.investmentList.get(counter);
                        double gain = 0;

                        for (int k = 0; i < portfolio.investmentList.size(); k++) {
                            Investment existingInvestment = portfolio.investmentList.get(k);
                            if (existingInvestment.getSymbol().equals((textField8.getText()))) {

                                double oldPrice = existingInvestment.getPrice();
                                String newPrice = textField10.getText();
                                double newPrice2 = Double.parseDouble(newPrice);
                                existingInvestment.setPrice(newPrice2);
                                textField10.setText("");
                                updateMessage.append(existingInvestment.toString()+ "\n");

                                System.out.println(existingInvestment.toString());
                                break;
                                /*if(existingInvestment.getPrice() < oldPrice){
                                        gain = oldPrice - existingInvestment.getPrice();
                                        gain = (-(gain));
                                        portfolio.totalGain = portfolio.totalGain + gain;
                                        }
                                    if(existingInvestment.getPrice() > oldPrice){
                                        gain = existingInvestment.getPrice() - oldPrice;
                                        portfolio.totalGain = portfolio.totalGain + gain;
                                    }*/
                            }
                        }
                        //portfolio.update();
                    }
                });
            }
        });
        getGainChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "5");
                double i = portfolio.totalGain;
                String gain;
                gain = String.valueOf(i);
                textField11.setText(gain);
                textField11.setEditable(false);
            }
        });
        searchChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "6");
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        symbol = textField12.getText();
                        name = textField13.getText();
                        lowPrice = textField14.getText();
                        highPrice = textField15.getText();
                        portfolio.search(symbol, name, lowPrice, highPrice);
                        //System.out.println("Search Data: " + " " +symbol + " " + name + " " + lowPrice + " " + highPrice);   //error checking
                        if (portfolio.success == 1){
                            searchMessage.append("Investment: " + " " + symbol + " was found.\n\n");
                        }
                        if (portfolio.success == 7){
                            searchMessage.append("No matches found\n\n");
                        }
                        
                    }
                });
                searchResetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        textField12.setText("");
                        textField13.setText("");
                        textField14.setText("");
                        textField15.setText("");
                    }
                });
            }
        });
        quitChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                exit(0);
            }
        });

        main.add(panelCont);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //main.pack();
        main.setVisible(true);
    }

    void buyGui() {
        //Big Panel
        //JPanel base = new JPanel();
        buyPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        buyPanel.add(topPanel);

        //Message panel stuff
        JPanel lowerPanel = new JPanel();
        buyPanel.add(lowerPanel);

        JLabel messageLabel = new JLabel("Messages");
        lowerPanel.add(messageLabel);
        lowerPanel.add(buyMessage);

        JScrollPane scroll = new JScrollPane(buyMessage);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.add(scroll);
        
        //Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        topPanel.add(leftPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        Font font1 = new Font("SansSerif", Font.BOLD, 15);

        JLabel title = new JLabel("Buy an investment");
        title.setFont(font1);
        leftPanel.add(title, c);
        c.gridy++;
        c.gridy++;
        JLabel type = new JLabel("Type ");
        type.setFont(font1);
        leftPanel.add(type, c);
        c.gridy++;

        JLabel symbol = new JLabel("Symbol ");
        symbol.setFont(font1);
        leftPanel.add(symbol, c);
        c.gridy++;
        JLabel name = new JLabel("Name ");
        name.setFont(font1);
        leftPanel.add(name, c);
        c.gridy++;
        JLabel quantity = new JLabel("Quantity ");
        quantity.setFont(font1);
        leftPanel.add(quantity, c);
        c.gridy++;
        JLabel price = new JLabel("Price ");
        price.setFont(font1);
        leftPanel.add(price, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        //String[] investmentStrings = {"Stock", "MutualFund"};
        //comboBox(investmentStrings);
        c.gridy++;
        c.gridy++;
        leftPanel.add(comboBox, c);
        c.gridy++;

        leftPanel.add(textField1, c);
        c.gridy++;
        leftPanel.add(textField2, c);
        c.gridy++;
        leftPanel.add(textField3, c);
        c.gridy++;
        leftPanel.add(textField4, c);
        c.gridy++;

        //Right Panel
        /*JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);*/
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1));
        //rightPanel.setPreferredSize(new Dimension(50, 70));
        //rightPanel.setBackground(Color.PINK);

        JPanel topRight = new JPanel();
        //topRight.setBackground(Color.LIGHT_GRAY);
        JPanel secondRight = new JPanel();
        //secondRight.setBackground(Color.LIGHT_GRAY);
        JPanel thirdRight = new JPanel();
        //thirdRight.setBackground(Color.LIGHT_GRAY);
        JPanel lowerRight = new JPanel();
        //.setBackground(Color.LIGHT_GRAY);

        thirdRight.add(buyButton);
        secondRight.add(buyResetButton);

        rightPanel.add(topRight);
        rightPanel.add(secondRight);
        rightPanel.add(thirdRight);
        rightPanel.add(lowerRight);

        topPanel.add(rightPanel);

        //main.add(buyPanel, BorderLayout.CENTER);
        setVisible(true);

    }

    void sellGui() {
        //Big Panel
        //JPanel base = new JPanel();
        sellPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        sellPanel.add(topPanel);

        //Message panel stuff
        JPanel lowerPanel = new JPanel();
        sellPanel.add(lowerPanel);

        JLabel messageLabel = new JLabel("Messages");
        lowerPanel.add(messageLabel);

        //JTextArea message = new JTextArea(8, 40);
        lowerPanel.add(sellMessage);

        JScrollPane scroll = new JScrollPane(sellMessage);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.add(scroll);

        //Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        topPanel.add(leftPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        
        JLabel symbol = new JLabel("Symbol ");
        symbol.setFont(font1);
        leftPanel.add(symbol, c);
        c.gridy++;

        JLabel quantity = new JLabel("Quantity ");
        quantity.setFont(font1);
        leftPanel.add(quantity, c);
        c.gridy++;

        JLabel price = new JLabel("Price ");
        price.setFont(font1);
        leftPanel.add(price, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        leftPanel.add(textField5, c);
        c.gridy++;
        leftPanel.add(textField6, c);
        c.gridy++;
        leftPanel.add(textField7, c);
        c.gridy++;

        //Right Panel
        /*JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);*/
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1));
        // JButton reset = new JButton("Reset");
        //JButton sellButton = new JButton("Sell");

        JPanel topRight = new JPanel();
        JPanel secondRight = new JPanel();
        JPanel thirdRight = new JPanel();
        JPanel lowerRight = new JPanel();

        thirdRight.add(sellButton);
        secondRight.add(sellResetButton);

        rightPanel.add(topRight);
        rightPanel.add(secondRight);
        rightPanel.add(thirdRight);
        rightPanel.add(lowerRight);

        topPanel.add(rightPanel);

        setVisible(true);

    }

    void updateGui() {

        //Big Panel
        //JPanel base = new JPanel();
        updatePanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        updatePanel.add(topPanel);

        //Message panel stuff
        JPanel lowerPanel = new JPanel();
        updatePanel.add(lowerPanel);

        JLabel messageLabel = new JLabel("Messages");
        lowerPanel.add(messageLabel);

        lowerPanel.add(updateMessage);

        JScrollPane scroll = new JScrollPane(updateMessage);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.add(scroll);

        //Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        topPanel.add(leftPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        JLabel type = new JLabel("Symbol ");
        type.setFont(font1);
        leftPanel.add(type, c);
        c.gridy++;

        JLabel symbol = new JLabel("Name ");
        symbol.setFont(font1);
        leftPanel.add(symbol, c);
        c.gridy++;

        JLabel price = new JLabel("Price ");
        price.setFont(font1);
        leftPanel.add(price, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        leftPanel.add(textField8, c);
        c.gridy++;
        leftPanel.add(textField9, c);
        c.gridy++;
        leftPanel.add(textField10, c);
        c.gridy++;

        //Right Panel
        /*JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);*/
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1));

        JPanel topRight = new JPanel();
        //topRight.setBackground(Color.LIGHT_GRAY);
        JPanel secondRight = new JPanel();
        //secondRight.setBackground(Color.LIGHT_GRAY);
        JPanel thirdRight = new JPanel();
        //thirdRight.setBackground(Color.LIGHT_GRAY);
        JPanel lowerRight = new JPanel();
        //lowerRight.setBackground(Color.LIGHT_GRAY);
        setVisible(true);

        secondRight.add(prevButton);
        thirdRight.add(nextButton);
        lowerRight.add(saveButton);

        rightPanel.add(topRight);
        rightPanel.add(secondRight);
        rightPanel.add(thirdRight);
        rightPanel.add(lowerRight);

        setVisible(true);
        topPanel.add(rightPanel);

        setVisible(true);

    }

    void getGainGui() {
        //Big Panel
        //JPanel base = new JPanel();
        getGainPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        getGainPanel.add(topPanel);

        //Message panel stuff
        JPanel lowerPanel = new JPanel();
        getGainPanel.add(lowerPanel);

        JLabel messageLabel = new JLabel("Individual Gains");
        lowerPanel.add(messageLabel);

        //JTextArea getGainMessage = new JTextArea(8, 40);
        lowerPanel.add(getGainMessage);

        JScrollPane scroll = new JScrollPane(getGainMessage);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.add(scroll);

        //Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        topPanel.add(leftPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        JLabel type = new JLabel("Total gain: ");
        type.setFont(font1);
        leftPanel.add(type, c);
        c.gridy++;

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        leftPanel.add(textField11, c);
        c.gridy++;

        setVisible(true);

    }

    void searchGui() {
        //Big Panel
        //JPanel base = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        searchPanel.add(topPanel);

        //Message panel stuff
        JPanel lowerPanel = new JPanel();
        searchPanel.add(lowerPanel);

        JLabel messageLabel = new JLabel("Search results");
        lowerPanel.add(messageLabel);

        //JTextArea message = new JTextArea(8, 40);
        lowerPanel.add(searchMessage);

        JScrollPane scroll = new JScrollPane(searchMessage);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.add(scroll);

        //Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        topPanel.add(leftPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        JLabel symbol = new JLabel("Symbol ");
        symbol.setFont(font1);
        leftPanel.add(symbol, c);
        c.gridy++;

        JLabel keywords = new JLabel("Keywords ");
        keywords.setFont(font1);
        leftPanel.add(keywords, c);
        c.gridy++;

        JLabel lowPrice = new JLabel("Low Price ");
        lowPrice.setFont(font1);
        leftPanel.add(lowPrice, c);
        c.gridy++;

        JLabel highPrice = new JLabel("High Price ");
        highPrice.setFont(font1);
        leftPanel.add(highPrice, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        leftPanel.add(textField12 , c);
        c.gridy++;
        leftPanel.add(textField13, c);
        c.gridy++;
        leftPanel.add(textField14, c);
        c.gridy++;
        leftPanel.add(textField15, c);
        c.gridy++;

        //Right Panel
        /*JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);*/
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1));

        JPanel topRight = new JPanel();
        JPanel secondRight = new JPanel();
        JPanel thirdRight = new JPanel();
        JPanel lowerRight = new JPanel();
        setVisible(true);

        thirdRight.add(searchButton);
        secondRight.add(searchResetButton);

        rightPanel.add(topRight);
        rightPanel.add(secondRight);
        rightPanel.add(thirdRight);
        rightPanel.add(lowerRight);

        setVisible(true);
        topPanel.add(rightPanel);

        setVisible(true);

    }
}
