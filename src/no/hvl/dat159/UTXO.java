package no.hvl.dat159;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UTXO {
    
    //Why is this a Map and not a Set?
    //  The values in this map are the UTXOs (unspent Outputs)
    //  When removing UTXOs, we need to identify which to remove.
    //  Since the Inputs are references to UTXOs, we can use those
    //  as keys.
	private static Map<Input, Output> map = new HashMap<>();
	
	public void printUTXO() {
		for (Entry<Input, Output> map : map.entrySet())
            System.out.println(map.getKey() + " " + map.getValue() + "\n\t" + "id: "+(map.getKey().getPrevOutputIndex()+1));
		
	}
	
	public void addOutputFrom(CoinbaseTx ctx) {
		map.put(new Input(ctx.getTxHash(), 0),ctx.getOutput());
	}

    public void addAndRemoveOutputsFrom(Transaction tx) {
    	List<Input> inputs = tx.getInputs();
    	List<Output> outputs = tx.getOutputs();
    	for(Output o : outputs) {
    		map.put(new Input(tx.getTxHash(), outputs.indexOf(o)) ,o);
    		
    	}
    	for(Input i : inputs) {
    		map.remove(i);
    	}
    }
    
    public static Map<Input, Output> getMap(){
    	return map;
    }
    //TODO Getters?
}
