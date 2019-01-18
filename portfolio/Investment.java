package portfolio;
/**
 * @author Kara
 */
public class Investment {
    protected String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    
    public Investment(String symbol, String name, int quantity, double price, double bookValue){ 
        // Constructor that creates an investment 
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }
    
    // All Setters 
    @Override
    public String toString(){
        return ("Symbol: " + symbol + "\n" + "Name: " + name + "\n" +"Quantity: " + quantity + "\n" + "Price: " + price + "\n" + "Book Value: " + bookValue + "\n" );
    }
    
    public String setSymbol(String symbol){
        return this.symbol = symbol;
    }
    
    public String setName(String name){
        return this.name = name;
    }
    
    public int setQuantity(int quantity){
        return this.quantity = quantity;
    }
    
    public double setPrice(double price){
        return this.price = price;
    }
    
    public double setBookValue(double bookValue){
        return this.bookValue = bookValue;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Investment)) return false;
        else{
            Investment b = (Investment) o;
            if (!(this.getSymbol().equals(b.getSymbol()))) return false;
            if (!(this.getName().equals(b.getName()))) return false;
            if (!(this.getQuantity()==(b.getQuantity()))) return false;
            if (!(this.getPrice()==(b.getPrice()))) return false;
            if (!(this.getBookValue()==(b.getBookValue()))) return false;
            
            return true;
        }
    }
    // All Getters 
    public String getSymbol(){
        return symbol;
    }
    
    public String getName(){
        return name;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public double getPrice(){
        return price;
    }
    
    public double getBookValue(){
        return bookValue;
    }   

    String getSymbol(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getName(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
