package com.shofuku.accsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.shofuku.accsystem.domain.financials.AccountEntryProfile;


public class AccountEntryDaoImpl extends BaseHibernateDaoImpl {

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
	
	
	public int getChildrenCountByParentCode(String  parentCode,Session session) {

		Transaction tx = null;
		try {
			getCurrentTransaction(session);
			Criteria criteria = session.createCriteria(AccountEntryProfile.class).add(
					Restrictions.eq("parentCode", parentCode));
			List childrenList = criteria.list();
			if(childrenList!=null) {
				return childrenList.size();
			}
		} catch (RuntimeException re) {
			tx.rollback();
			re.printStackTrace();
		}
		return 0;

	}


	public List<AccountEntryProfile> listAccountEntriesFromList(
			List accountCodes, Session session) {
		Transaction tx = null;
		try {
			tx = getCurrentTransaction(session);
			Query query = session
					.createQuery("FROM AccountEntryProfile  WHERE accountCode IN (:accountCodes)");
			query.setParameterList("accountCodes",accountCodes);
			return query.list();
		} catch (RuntimeException re) {
			if (null != tx)
				tx.rollback();
			re.printStackTrace();
		}

		return null;
	}
	
	public List listByParameterLike(Class clazz, String propertyName,
			String value,Session session) {

		Transaction tx = null;
		try {
			tx=getCurrentTransaction(session);
			Criteria criteria = session.createCriteria(clazz).add(
					Restrictions.or(
							Restrictions.like(propertyName, "%" + value + "%")
									.ignoreCase(), Restrictions.or(Restrictions
									.like(propertyName, "%" + value)
									.ignoreCase(),
									Restrictions
											.like(propertyName, value + "%")
											.ignoreCase())));
					criteria.addOrder(Order.asc(propertyName));
			return criteria.list();
		} catch (RuntimeException re) {
			tx.rollback();
			re.printStackTrace();
		}
		return null;

	}
	
	public List listAlphabeticalAccountEntryProfileChildrenAscByParameter(Session session) {

		Transaction tx = null;
		try {
			tx=getCurrentTransaction(session);
			Criteria criteria = session.createCriteria(AccountEntryProfile.class).add(Restrictions.not(Restrictions.like("parentCode", "0")));
					criteria.addOrder(Order.asc("name"));
			return criteria.list();
		} catch (RuntimeException re) {
			tx.rollback();
			re.printStackTrace();
		}
		return null;

	}
	
}
