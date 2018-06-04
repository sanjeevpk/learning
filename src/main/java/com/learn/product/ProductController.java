package com.learn.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.company.Company;
import com.learn.company.CompanyRepository;
import com.learn.product.Product;
import com.learn.product.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	@RequestMapping("/product/test")
	public String test(){
		return "Testing";
	}
	
	@RequestMapping("/companies/{id}/products")
	public List<Product> findAllProducts(@PathVariable Integer id){
		return (List<Product>) productRepository.findByCompanyId(id);
//		List<Product> products = new ArrayList<Product>();
//		Map<String, Object> map = new HashMap<>();
//		map.put("companyId", id);
//		products = template.query("select id, name, company from product where company=:companyId", map, new RowMapper<Product>() {
//
//			@Override
//			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				return product;
//			}
//		});
//		return products;
	}
	
	@RequestMapping("/companies/{companyId}/products/{productId}")
	public Product getOneProduct(@PathVariable Integer companyId, @PathVariable Integer productId){
		return productRepository.findOne(productId);
	}
	
	@RequestMapping(value="/companies/{companyId}/products/{productId}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(@RequestBody Product product, @PathVariable Integer productId){
		Product originalProduct = productRepository.findOne(productId);
		originalProduct.setName(product.getName());
		productRepository.save(originalProduct);
	}
	
//	@RequestMapping(value="/companies", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public void saveCompany(@RequestBody Company company){
//		companyRepository.save(company);
//		for (Product product : company.getProducts()) {
//			product.setCompany(company);
//			productRepository.save(product);
//		}
		
}

