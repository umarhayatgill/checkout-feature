# checkout-feature

In order to run this prjoect on localhost you need to checkout this repository and hava JVM installed. Use following command to start the server

```bash
./gradlew run
```

It will start a server on **localhost:8080**

You can now use curl command from terminal to call checkout endpoint, for example

```shell 
curl --location --request POST 'http://localhost:8080/checkout' --header 'Content-Type: application/json' --data-raw '["001","001","001","004","003"]'
```

It should return 

```json
{
  "price" : 280
}
```

You can play around with different variations of item ids in the array. The code has good coverage of unit and integration tests so to help developer sleep better.
