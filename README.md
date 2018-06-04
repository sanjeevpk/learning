# learning
This is a sample project for learning about springboot, spring data jpa one to many bi-directional relationship mapping.

Refer to the company and product controllers for learning and levaraging about the relationship mapping between these etities.

Saving a company details:

POST
http://localhost:8080/companies

{
	"name":"Apple",
	"products":[
		{
			"name":"iPhone"
		},
		{
			"name":"iTunes"
		}
	]	
}

Displaying all companies details
GET
http://localhost:8080/companies

Get all the products of a particular company
GET
http://localhost:8080/companies/1/products

Get a particular product that belongs to a particular company
GET
http://localhost:8080/companies/1/products/1
