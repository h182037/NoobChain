package no.hvl.dat159;

public class Output {

    //Simplified compared to Bitcoin - The address should be a script
	private long value;
	private String address;
	
	public Output(long value, String address) {
	    this.setValue(value);
	    this.setAddress(address);
	}
 
	@Override
	public String toString() {
        //TODO For screen output
        return "";
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
