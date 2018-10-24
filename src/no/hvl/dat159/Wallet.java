package no.hvl.dat159;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;

public class Wallet {

    private String id;
    private KeyPair keyPair;
    private long newTxOutputs;
    //A refererence to the "global" complete utxo-set
    private Map<Input, Output> utxoMap;
    private String address;
    private long balance = 0;
    
    public Wallet(String id, UTXO utxo) {
        keyPair = DSAUtil.generateRandomDSAKeyPair();
        this.setId(id);
        this.utxoMap = utxo.getMap();
        this.address = HashUtil.addressFromPublicKey(keyPair.getPublic());
    }

    public String getAddress() {
    	return address;
    }

    public Transaction createTransaction(long value, String address) throws Exception {

        //TODO - This is a big one
        
        // 1. Collect all UTXO for this wallet and calculate balance
    	Map<Input, Output> myUTXO = collectMyUtxo();
    	long myBalance = calculateBalance(myUTXO.values());
        // 2. Check if there are sufficient funds --- Exception?
    	if(myBalance < value) System.out.println("Poor man is poor");
    	
        // 3. Choose a number of UTXO to be spent --- Strategy?
    	newTxOutputs = 0;
    	ArrayList<Input> chosenTxs = new ArrayList<Input>();
        while(newTxOutputs < value) {
        	myUTXO.forEach((input, output) -> {
        		newTxOutputs += output.getValue();
        		chosenTxs.add(input);
        	});
        }

        // 4. Calculate change
        long change = newTxOutputs - value;
        
        // 5. Create an "empty" transaction
    	Transaction tx = new Transaction(keyPair.getPublic());
        // 6. Add chosen inputs
    	chosenTxs.forEach((input)-> {
    		tx.addInput(input);
    	});
        // 7. Add 1 or 2 outputs, depending on change
    	tx.addOutput(new Output(value, address));
    	if(change > 0) tx.addOutput(new Output(change, getAddress()));
        // 8. Sign the transaction
    	tx.signTxUsing(keyPair.getPrivate());
        // 9. Calculate the hash for the transaction
    	tx.calculateTxHash();
        // 10. return
        return tx;
        
        // PS! We have not updated the UTXO yet. That is normally done
        // when appending the block to the blockchain, and not here!
        // Do that manually from the Application-main.
    }

    @Override
    public String toString() {
        return null;
    }

    public long getBalance() {
        return balance;
    }
        
    private long calculateBalance(Collection<Output> outputs) {
    	outputs.forEach((output) -> {
        	balance += output.getValue();
        });
        return balance;
    }
    private Map<Input, Output> collectMyUtxo() {
    	Map<Input, Output> collected = utxoMap;
    	
    	Iterator<Map.Entry<Input, Output>> it = collected.entrySet().iterator();
    	while(it.hasNext()) {
    		Map.Entry<Input, Output> entry = it.next();
    		if(entry.getValue().getAddress()!=HashUtil.addressFromPublicKey(keyPair.getPublic())) {
    			collected.remove(entry.getKey());
    		}
    	}
        return collected;
     
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
