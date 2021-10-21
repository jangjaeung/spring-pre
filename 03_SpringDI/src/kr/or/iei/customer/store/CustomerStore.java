package kr.or.iei.customer.store;

import java.util.List;

import kr.or.iei.customer.domain.Customer;

public interface CustomerStore {
	public List<Customer> selectAll();
}
