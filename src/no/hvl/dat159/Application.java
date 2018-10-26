package no.hvl.dat159;

public class Application {
    
    private static UTXO utxo = new UTXO();
	
	public static void main(String[] args) throws Exception {
	    
        /*
         * In this assignment, we are going to look at how to represent and record
         * monetary transactions. We will use Bitcoin as the basis for the assignment,
         * but there will be some simplifications.
         * 
         * We are skipping the whole blockchain this time, and instead focus on the
         * transaction details, the UTXOs and how money movements are represented.
         * 
         * (If you want to, you can of course extend the assignment by collecting the
         * individual transactions into blocks, create a Merkle tree for the block
         * header, validate, mine  and add the block to a blockchain.)
         * 
         */

        // 0. To get started, we need a few (single address) Wallets. Create 2 wallets.
        //    Think of one of them as the "miner" (the one collecting "block rewards").
		Wallet person = new Wallet("Person", utxo);
		System.out.println("Created wallet 1: "+"\n"+person.toString()+"\n");
		Wallet miner = new Wallet("Miner", utxo);
		System.out.println("Created wallet 2: "+"\n"+miner.toString()+"\n");

		
        // 1. The first "block" (= round of transactions) contains only a coinbase
        //    transaction. Create a coinbase transaction that adds a certain
        //    amount to the "miner"'s address. Update the UTXO-set (add only).
		System.out.println("Block 1:");
		CoinbaseTx faucetTx = new CoinbaseTx("Joke... is funny", 50, miner.getAddress()); 
		System.out.println("Created Coinbase Transaction 1: "+"\n\t"+"value: 50 \n"+faucetTx.toString());
		System.out.println("Block UTXO end status:");
		utxo.addOutputFrom(faucetTx);
		utxo.printUTXO();
		System.out.println("Block Wallet end status: ");
		System.out.println("Wallet 1: "+"\n"+person.toString()+"\n");
		System.out.println("Wallet 2: "+"\n"+miner.toString()+"\n");

	
		
        // 2. The second "block" contains two transactions, the mandatory coinbase
        //    transaction and a regular transaction. The regular transaction shall
        //    send ~20% of the money from the "miner"'s address to the other address.
	   System.out.println("Block 2: ");
       CoinbaseTx coinbaseTx = new CoinbaseTx("Time is money, friend", 50, miner.getAddress());
       System.out.println("Created Coinbase Transaction 2: "+"\n\t"+"value: 50 \n"+coinbaseTx.toString());
       utxo.addOutputFrom(coinbaseTx);

        //    Validate the regular transaction created by the "miner"'s wallet:
        //      - All the content must be valid (not null++)!!!
        //      - All the inputs are unspent and belongs to the sender
        //      - There are no repeating inputs!!!
        //      - All the outputs must have a value > 0
        //      - The sum of inputs equals the sum of outputs
        //      - The transaction is correctly signed by the sender
        //      - The transaction hash is correct
       
       Transaction regTx = miner.createTransaction(20, person.getAddress());
       System.out.println("Created Transaction 1: "+"\n"+"\t"+"value: 20 \n"+regTx.toString());
        	//    Update the UTXO-set (both add and remove).
       if(!regTx.isValid()) {
    	   System.out.println("Bad coder is bad, transaction is not good, no");
       }
       
       utxo.addAndRemoveOutputsFrom(regTx);
       System.out.println("Block UTXO end status:");
       utxo.printUTXO();
       System.out.println("Block Wallet end status: ");
       System.out.println("Wallet 1: "+"\n"+person.toString()+"\n");
       System.out.println("Wallet 2: "+"\n"+miner.toString()+"\n");
       
       
        // 3. Do the same once more. Now, the "miner"'s address should have two or more
        //    unspent outputs (depending on the strategy for choosing inputs) with a
        //    total of 2.6 * block reward, and the other address should have 0.4 ...
        
       CoinbaseTx coinbaseTax = new CoinbaseTx("The WTF/minute is a measure of success", 70, miner.getAddress());
       System.out.println("Created Coinbase Transaction 3: "+"\n\t"+"value: 70 \n"+coinbaseTax.toString());
       Transaction regTrx = miner.createTransaction(40, person.getAddress());
       System.out.println("Created Transaction 2: "+"\n"+"\t"+"value: 40 \n"+regTrx.toString());

        //    Validate the regular transaction ...
       if(!regTrx.isValid()) {
    	   System.out.println("You done goofed, transaction is angry");
       }
       
        //    Update the UTXO-set ...
       utxo.addOutputFrom(coinbaseTax);
       utxo.addAndRemoveOutputsFrom(regTrx);
       System.out.println("Block 3:");

       System.out.println("Block UTXO end status:");
       utxo.printUTXO();
       System.out.println("Block Wallet end status: ");
       System.out.println("Wallet 1: "+"\n"+person.toString()+"\n");
       System.out.println("Wallet 2: "+"\n"+miner.toString()+"\n");
       
        // 4. Make a nice print-out of all that has happened, as well as the end status.
        //
        //      for each of the "block"s (rounds), print
        //          "block" number
        //          the coinbase transaction
        //              hash, message
        //              output
        //          the regular transaction(s), if any
        //              hash
        //              inputs
        //              outputs
        //      End status: the set of unspent outputs
        //      End status: for each of the wallets, print
        //          wallet id, address, balance
       
       
	}
}
