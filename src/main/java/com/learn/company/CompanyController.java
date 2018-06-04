package com.learn.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

import com.learn.product.Product;
import com.learn.product.ProductRepository;


@RestController
public class CompanyController {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	@RequestMapping("/company/test")
	public String test(){
		return "Testing";
	}
	
	@RequestMapping("/companies")
	public List<Company> findAllCompanies(){
		return (List<Company>) companyRepository.findAll();
		
		/*List<Company> companies = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		
		companies = template.query("select id, name from company", map, new RowMapper<Company>() {

			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company company = new Company();
				company.setName(rs.getString("name"));
				company.setId(rs.getInt("id"));
				
				map.put("companyId", company.getId());
				final List<Product> products = (List<Product>) template.query("select id, name, company from product where company=:companyId", map, new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
						Product product = new Product();
						product.setName(rs.getString("name"));
						product.setId(rs.getInt("id"));
						product.setCompany(company);
						return product;
					}
					
				});
				company.setProducts(products);
				
				return company;
			}
		});
		
		
		return companies;
	*/
	}
	
	@RequestMapping("/companies/{id}")
	public Company getACompany(){
		Integer i =1;
		return companyRepository.findOne(i);
	}
	
	@RequestMapping(value="/companies", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void saveCompany(@RequestBody Company company){
		companyRepository.save(company);
		for (Product product : company.getProducts()) {
			product.setCompany(company);
			productRepository.save(product);
		}
		
	}
	
	@RequestMapping(value="/companies/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateCompany(@RequestBody Company company, @PathVariable Integer id){
		Company oldCompany = companyRepository.findOne(id);
		oldCompany.setName(company.getName());
		oldCompany.setProducts(company.getProducts());
		companyRepository.save(oldCompany);
		if(oldCompany.getProducts() != null){
			for (Product product : oldCompany.getProducts()) {
				product.setCompany(oldCompany);
				productRepository.save(product);
			}
		}
	}
}
