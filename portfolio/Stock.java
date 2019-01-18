package portfolio;
/**
 * @author Kara
 */
public class Stock extends Investment {
    
    public Stock(String symbol, String name, int quantity, double price, double bookValue){
        super(symbol, name, quantity, price, bookValue);
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Stock)) return false;
        else{
            Stock b = (Stock) o;
            if (!(this.getSymbol().equals(b.getSymbol()))) return false;
            if (!(this.getName().equals(b.getName()))) return false;
            if (!(this.getQuantity()==(b.getQuantity()))) return false;
            if (!(this.getPrice()==(b.getPrice()))) return false;
            if (!(this.getBookValue()==(b.getBookValue()))) return false;
            
            return true;
        }
    }
}
