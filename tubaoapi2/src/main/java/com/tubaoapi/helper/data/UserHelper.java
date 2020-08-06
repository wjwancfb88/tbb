package com.tubaoapi.helper.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tubaoapi.model.Customer;
import com.tubaoapi.model.Supplier;
import com.tubaoapi.model.User;
import com.tubaoapi.modules.utils.Collections3;
import com.tubaoapi.service.CustomerService;
import com.tubaoapi.service.SupplierService;

@Component
public class UserHelper {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SupplierService supplierService;
	
	public void setCustomers(List<User> users){
		List<String> customerIds = Collections3.extractToList(users, "customerId");
		if(customerIds.size()==0){
			return;
		}
		List<Customer> customers = customerService.findByIds(customerIds);
		Map<String,Customer> map = new HashMap<>();
		for(Customer customer:customers){
			map.put(customer.getId(), customer);
		}
		for(User user:users){
			if(StringUtils.isBlank(user.getCustomerId())){
				continue;
			}
			user.setCustomer(map.get(user.getCustomerId()));
		}
	}
	
	public void setSuppliers(List<User> users){
		List<String> supplierIds = Collections3.extractToList(users, "supplierId");
		if(supplierIds.size()==0){
			return;
		}
		List<Supplier> suppliers = supplierService.findByIds(supplierIds);
		Map<String,Supplier> map = new HashMap<>();
		for(Supplier supplier:suppliers){
			map.put(supplier.getId(), supplier);
		}
		for(User user:users){
			if(StringUtils.isBlank(user.getSupplierId())){
				continue;
			}
			user.setSupplier(map.get(user.getSupplierId()));
		}
	}
}
