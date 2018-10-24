package no.hvl.dat159;

public class CoinbaseTx {
	
    //Simplified compared to Bitcoin (nothing significant missing)
	private String coinbase; // "The Times 03/Jan/2009 Chancellor 
	                         //  on brink of second bailout for banks"
	private Output output;
	private String txHash;

	public CoinbaseTx(String coinbase, int value, String address) {
		this.coinbase = coinbase;
		output = new Output(value, address);
	    this.txHash = HashUtil.base64Encode(HashUtil.sha256Hash(coinbase + value));
	}
	
	@Override
	public String toString() {
	    //TODO For screen output
	    return "";
	}

	public String getCoinbase() {
		return coinbase;
	}

	public String getTxHash() {
		return txHash;
	}

	public Output getOutput() {
		return output;
	}
	
}
