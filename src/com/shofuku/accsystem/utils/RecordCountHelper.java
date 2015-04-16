package com.shofuku.accsystem.utils;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shofuku.accsystem.dao.BaseHibernateDao;
import com.shofuku.accsystem.dao.impl.BaseHibernateDaoImpl;

public class RecordCountHelper {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public RecordCountHelper(){
	}
	
	public String getLastCustomerByInitialLetter(char firstLetter){
		Session session = getSession();
		try{
			BaseHibernateDao dao = new BaseHibernateDaoImpl();
			firstLetter = String.valueOf(firstLetter).toUpperCase().charAt(0);
			String lastSupplier = dao.getLastCustomerByInitialLetter(firstLetter,session);
		int maxCount=0;
		try{
		maxCount = Integer.valueOf(lastSupplier.substring(2,lastSupplier.length()))+1;
		}catch(Exception e){
			maxCount=1;
		}
		return "C"+firstLetter+maxCount;
		} catch (Exception e) {
			return "";
		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}
	}
	
	public String getLastSupplierByInitialLetter(char firstLetter){
		Session session = getSession();
		try{
		BaseHibernateDao dao = new BaseHibernateDaoImpl();
		firstLetter = String.valueOf(firstLetter).toUpperCase().charAt(0);
		String lastSupplier = dao.getLastSupplierByInitialLetter(firstLetter,session);
		int maxCount=0;
		try{
		maxCount = Integer.valueOf(lastSupplier.substring(2,lastSupplier.length()))+1;
		}catch(Exception e){
			maxCount=1;
		}
		return "S"+firstLetter+maxCount;
		} catch (Exception e) {
			return "";
		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}
	}
	
	public String getPrefix(String subModule, String prefix){
		Session session = getSession();
		try{
		BaseHibernateDao dao = new BaseHibernateDaoImpl();
		String count = Integer.valueOf(dao.getRecordCount(subModule,session)+1).toString();
		int maxDigits =6;
		while(maxDigits>count.length()){
			prefix = prefix + "0";
			maxDigits--;
		}
		prefix = prefix + count;
		
		return prefix;
		} catch (Exception e) {
			return "";
		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}
	}
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void updateCount(String subModule,String action){
		Session session = getSession();
		try{
		BaseHibernateDao dao = new BaseHibernateDaoImpl();
		int count = dao.getRecordCount(subModule,session);
		
		if(action.equalsIgnoreCase("add")){
			count++;
		}
			dao.getUpdateCount(subModule,count,session);
	} catch (Exception e) {
	} finally {
		if(session.isOpen()){
			session.close();
			session.getSessionFactory().close();
		}
	}
}
	
	
	public int getOrderingTemplateMaxRows() {
		Session session = getSession();
		try{
			BaseHibernateDao dao = new BaseHibernateDaoImpl();
			String count = Integer.valueOf(dao.getRecordCount("OrderingTemplateMaxRows",session)+1).toString();
			return Integer.valueOf(count);
			} catch (Exception e) {
				//if exception occurs or record doesnt exist, default to 100 for now
				return 100;
			} finally {
				if(session.isOpen()){
					session.close();
					session.getSessionFactory().close();
				}
			}
	}
	
	public int getCustomerStockLevelTemplateMaxRows() {
		Session session = getSession();
		try{
			BaseHibernateDao dao = new BaseHibernateDaoImpl();
			String count = Integer.valueOf(dao.getRecordCount("CustomerStockLevelTemplateMaxRows",session)+1).toString();
			return Integer.valueOf(count);
			} catch (Exception e) {
				//if exception occurs or record doesnt exist, default to 100 for now
				return 100;
			} finally {
				if(session.isOpen()){
					session.close();
					session.getSessionFactory().close();
				}
			}
	}
	
	public int getAccountingRulesCount() {
		Session session = getSession();
		try{
			BaseHibernateDao dao = new BaseHibernateDaoImpl();
			return dao.getMaxRows("t702_accounting_rules", session);
			} catch (Exception e) {
				//if exception occurs or record doesnt exist, default to 100 for now
				return 100;
			} finally {
				if(session.isOpen()){
					session.close();
					session.getSessionFactory().close();
				}
			}
	}
}