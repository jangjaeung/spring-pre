package kr.or.iei.customer.store.logic;

import java.util.ArrayList;
import java.util.List;

import kr.or.iei.customer.domain.Customer;
import kr.or.iei.customer.store.CustomerStore;

public class CustomerStoreLogic implements CustomerStore{
	
	@Override
	public List<Customer> selectAll() {
		List<Customer> list = new ArrayList<Customer>();
		for(int i = 0; i < 10; i++) {
			Customer cOne = new Customer();
			cOne.setId(i + "");
			cOne.setName(i + "");
			cOne.setAddress(i + "");
			cOne.setEmail(i + "");
			list.add(cOne);
		}
		return list;
	}

}
