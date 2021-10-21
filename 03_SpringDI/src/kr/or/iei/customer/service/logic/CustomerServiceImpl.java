package kr.or.iei.customer.service.logic;

import java.util.List;

import kr.or.iei.customer.domain.Customer;
import kr.or.iei.customer.service.CustomerService;
import kr.or.iei.customer.store.CustomerStore;

public class CustomerServiceImpl implements CustomerService{
	 
	private CustomerStore store;
	
	public void setStore(CustomerStore store) {
		this.store = store;
	}
	
//	public CustomerServiceImpl() {}
	
//	public CustomerServiceImpl(CustomerStore store) {
//		this.store = store;
//	}
	
	@Override
	public List<Customer> findAll() {
		return store.selectAll();
	}

}
