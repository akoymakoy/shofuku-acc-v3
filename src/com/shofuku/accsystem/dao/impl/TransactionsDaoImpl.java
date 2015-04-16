package com.shofuku.accsystem.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;


public class TransactionsDaoImpl extends BaseHibernateDaoImpl {

	
	//add specific HQL / criteria calls here if any
	private Transaction getCurrentTransaction(Session session){
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
		}catch(RuntimeException runtimeExecption){
			tx = session.getTransaction();
		}		
		return tx;
	}
	
	public List<String> getDefaultAccountEntriesForTransactions(String transactionType,Session session){
		int x = 0;
		Transaction tx = null;
		try {
			tx=getCurrentTransaction(session);
			Query query = session.createSQLQuery("SELECT ACCOUNT_CODE FROM T903_DEFAULT_TRANSACTIONS WHERE TRANSACTION_TYPE = '"+transactionType+"'");
			return query.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		} 

	}
	
	public boolean addTransactionList(List<com.shofuku.accsystem.domain.financials.Transaction> transactionsList,Session session){
			
		//note: this is hibernate transaction
		Transaction tx = null;
			try {
				tx=getCurrentTransaction(session);
				Iterator itr = transactionsList.iterator();
				while(itr.hasNext()) {
					session.save(itr.next());
				}
				return true;
			} catch (RuntimeException re) {
				if (null != tx) {
					tx.rollback();
				}
				re.printStackTrace();
			} 
			return false;
	}
	
	public boolean updateTransactionList(List<com.shofuku.accsystem.domain.financials.Transaction> transactionsList,Session session){
		
		//note: this is hibernate transaction
		Transaction tx = null;
			try {
				tx=getCurrentTransaction(session);
				Iterator itr = transactionsList.iterator();
				while(itr.hasNext()) {
					session.merge(itr.next());
				}
				return true;
			} catch (RuntimeException re) {
				if (null != tx) {
					tx.rollback();
				}
				re.printStackTrace();
			} 
			return false;
	}
}
