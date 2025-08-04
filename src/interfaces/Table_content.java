package interfaces;

import javafx.beans.property.SimpleStringProperty;

public class Table_content{
	private final SimpleStringProperty b0;
	private final SimpleStringProperty b1;
	private final SimpleStringProperty b2;
	private final SimpleStringProperty b3;
	
	public Table_content(String b0, String b1, String b2, String b3) {
		this.b0 = new SimpleStringProperty(b0);
		this.b1 = new SimpleStringProperty(b1);
		this.b2 = new SimpleStringProperty(b2);
		this.b3 = new SimpleStringProperty(b3);
	}
	
	public String getB0() { return b0.get(); }
    public void setB0(String b0) { this.b0.set(b0); }
    
    public String getB1() { return b1.get(); }
    public void setB1(String b1) { this.b1.set(b1); }
    
    public String getB2() { return b2.get(); }
    public void setB2(String b2) { this.b2.set(b2); }
    
    public String getB3() { return b3.get(); }
    public void setB3(String b3) { this.b3.set(b3); }
    
}