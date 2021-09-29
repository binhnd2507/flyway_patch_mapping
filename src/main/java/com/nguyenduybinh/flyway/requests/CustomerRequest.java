package com.nguyenduybinh.flyway.requests;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

	private String name;

	private List<Map<String, Object>> contacts;
	
	private Map<String, Object> sex;
}
