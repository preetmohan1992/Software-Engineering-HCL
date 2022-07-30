# The API

This is a simple REST API that consist of three endpoints that accept and return JSON. 
It uses JWT to secure some of them and H2 as the in-memory database to store resources.
Once you run it, the API would be available at `localhost:8080` by default.

## Endpoints
### Login

Allows you to authenticate with the service though username and password (hardcoded for test purposes).
It retrieves a JWT token that should be passed as bearer for the secured endpoints.

```http
POST /auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
	"username": "test",
	"password": "1234"
}
```

### Create Order
Allows you to create an order. If it successful, it will retrieve the order created along with the ID. 

```http
POST /orders HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer <token>
{
	"description": "1x Cheese Pizza",
	"food": true,
	"vip": false,
	"pickup": {
		"latitude": 41.385063,
		"longitude": 2.173404
	},
	"delivery": {
		"latitude": 41.406110,
		"longitude": 2.196864
	}
}
``` 

### Get Order by id
Allows you to retrieve an order already created by ID. 

```http
GET /orders/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer <token>
``` 


# Test it

The API is not fully tested and it may have some bugs. Create an automated test suite testing the endpoints described above.
 
Try being creative by finding endpoints that don’t work as expected, covering expected http error codes and responses.
There are no restrictions on the framework to be used. 

Describe the tests structure and whatever is necessary.
We should be able to run the whole test suite using a CLI command and see the results at the end.

## What we'll value

- Your tests compile, run and they actually make calls to the API verifying the results received.
- Accuracy of the test scenarios provided given the API functionality. 
- Good quality and maintainable code, built for reuse and scale.
- You should be able to defend your work explaining the test scenarios picked and the suite architecture.

## Notes

- Ideally, you shouldn't need to modify any of the provided classes

