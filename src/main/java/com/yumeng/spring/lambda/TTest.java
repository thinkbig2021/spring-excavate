package com.yumeng.spring.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
		transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
		transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
		transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
		transactions.add(new Transaction(2, 20, Transaction.Type.A));
		transactions.add(new Transaction(4, 40, Transaction.Type.C));
		//transactions.sort(c);
		
		 List<Integer> transactionsIds =
	                transactions.parallelStream().filter(t -> t.getType() == Transaction.Type.GEOCERY)
	                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
	                        .map(w->{ return w.getId();})
	                        .collect(Collectors.toList());
	        System.out.println(transactionsIds);//[6, 5, 3, 1]

	}

}
