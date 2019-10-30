package com.manas.rentalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.service.TrendingProductService;

@RequestMapping("/api/v1/trendingProduct")
@RestController
public class TrendingProductController {

	@Autowired
	private TrendingProductService trendingProductService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public List<Product> getTrendingProducts(){
		return trendingProductService.getTrendingProducts();		
		
	}
	
}
