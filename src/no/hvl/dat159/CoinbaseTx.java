package no.hvl.dat159;

public class CoinbaseTx {
	
    //Simplified compared to Bitcoin (nothing significant missing)
	private String coinbase; // "The Times 03/Jan/2009 Chancellor 
	                         //  on brink of second bailout for banks"
	private Output output;
	private String txHash;

	public CoinbaseTx(String coinbase, int value, String address) {
	    this.setCoinbase(coinbase);
	    output.setAddress(address);
	    output.setValue(value);
	    setTxHash(new String(HashUtil.sha256Hash(address)));
	}
	
	@Override
	public String toString() {
	    //TODO For screen output
	    return "";
	}

	public String getCoinbase() {
		return coinbase;
	}

	public void setCoinbase(String coinbase) {
		this.coinbase = coinbase;
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}
	public Output getOutput() {
		return output;
	}
	
}
