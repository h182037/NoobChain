package no.hvl.dat159;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Map;

public class Wallet {

    private String id;
    private KeyPair keyPair;
    
    //A refererence to the "global" complete utxo-set
    private Map<Input, Output> utxoMap;
    
    private long result = 0;
    
    public Wallet(String id, UTXO utxo) {
        keyPair = DSAUtil.generateRandomDSAKeyPair();
        this.id = id;
        this.utxoMap = utxo.getMap();
        
    }

    public String getAddress() {
    	return id;
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public Transaction createTransaction(long value, String address) throws Exception {

        //TODO - This is a big one
        
        // 1. Collect all UTXO for this wallet and calculate balance
        // 2. Check if there are sufficient funds --- Exception?
        // 3. Choose a number of UTXO to be spent --- Strategy?
        // 4. Calculate change
        // 5. Create an "empty" transaction
    	Transaction tx = new Transaction(keyPair.getPublic());
        // 6. Add chosen inputs
    	
        // 7. Add 1 or 2 outputs, depending on change
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
        return result;
    }
        
    private long calculateBalance(Collection<Output> outputs) {
    	outputs.forEach((output) -> {
        	result = result + output.getValue();
        });
        return result;
    }

    private Map<Input, Output> collectMyUtxo() {
    	Map<Input, Output> collected = null;
    	utxoMap.forEach((input, output) -> {
    		if(output.getAddress() == this.getAddress()) {
    			collected.put(input, output);
    		}
    	});
        return collected;
    }

}
