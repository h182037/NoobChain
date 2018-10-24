package no.hvl.dat159;

public class Input {

    //Simplified compared to Bitcoin
    //The signature is moved to Transaction, see comment there.
	private String prevTxHash;
	private int prevOutputIndex;
	
	public Input(String prevTxHash, int prevOutputIndex) {
	    this.setPrevTxHash(prevTxHash);
	    this.setPrevOutputIndex(prevOutputIndex);
	}

	@Override
	public String toString() {
        //TODO For screen output
	    return "";
	}

	public int getPrevOutputIndex() {
		return prevOutputIndex;
	}

	public void setPrevOutputIndex(int prevOutputIndex) {
		this.prevOutputIndex = prevOutputIndex;
	}

	public String getPrevTxHash() {
		return prevTxHash;
	}

	public void setPrevTxHash(String prevTxHash) {
		this.prevTxHash = prevTxHash;
	}

    //TODO Getters?
}
