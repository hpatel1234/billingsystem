package com.assignment.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.dao.ProductDAO;
import com.assignment.domainObjects.Product;

@Repository("productDao")
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Product getProduct(String productCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where id=:id");
		query.setString("id", productCode);
		return (Product)query.uniqueResult();
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
