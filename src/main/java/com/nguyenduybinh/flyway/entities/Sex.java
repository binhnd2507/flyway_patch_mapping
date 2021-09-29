package com.nguyenduybinh.flyway.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sex {

	private Boolean isFlag;
	
	private String gender;
	
	public static Sex from(Customer customer) {
		Sex sex = new Sex();
		sex.fillWith(customer);
		return sex;
	}

	private void fillWith(Customer customer) {
		if(customer == null) {
			return;
		}
		this.isFlag = customer.getIsFlag();
		this.gender = customer.getGender();
	}
}
