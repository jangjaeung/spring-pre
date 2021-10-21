package kr.or.iei.customer.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.iei.customer.domain.Customer;
import kr.or.iei.customer.service.CustomerService;
import kr.or.iei.customer.service.logic.CustomerServiceImpl;

public class CustommerApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");
		Customer customerOne = (Customer)ctx.getBean("customer");
		System.out.println(customerOne.toString());
		System.out.println("--------------------------------------------------");
		CustomerService service = (CustomerServiceImpl)ctx.getBean("customerService");
		List<Customer> list = service.findAll();
		for(Customer cOne : list) {
			System.out.println(cOne.toString());
		}
	}
}
