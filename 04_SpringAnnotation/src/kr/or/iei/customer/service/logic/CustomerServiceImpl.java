package kr.or.iei.customer.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.iei.customer.domain.Customer;
import kr.or.iei.customer.service.CustomerService;
import kr.or.iei.customer.store.CustomerStore;

@Component("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired	//오토라이드를 쓰면 따로 생성자나 세터를 만들필요 없이 연결됨
	private CustomerStore store;
	
//	public void setStore(CustomerStore store) {
//		this.store = store;
//	}
	
//	public CustomerServiceImpl() {}
	
//	public CustomerServiceImpl(CustomerStore store) {
//		this.store = store;
//	}
	
	@Override
	public List<Customer> findAll() {
		return store.selectAll();
	}

}
