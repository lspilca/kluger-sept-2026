# Spring Security Authorization Exercise

This is a simple Spring Boot application for practicing endpoint-level and method-level authorization.

## Student Tasks

Authorization rules are intentionally incomplete. Please follow the exercise instructions supplied by the instructor to:

* Configure endpoint-level authorization rules in `SecurityConfig`.
* Enable method-level security.
* Add `@PreAuthorize` expressions to `OrderService` methods.
* Use role and authority checks.
* Use the custom `orderSecurity` bean in SpEL expressions.

## Running the application

You can run the application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

## Available users

| Username | Password | Roles / Authorities    |
| -------- | -------- | ---------------------- |
| alice    | password | CUSTOMER               |
| bob      | password | CUSTOMER, ORDER_REFUND |
| manager  | password | MANAGER                |
| admin    | password | ADMIN                  |

## Useful curl commands

All endpoints use HTTP Basic authentication.

### Products

```bash
curl http://localhost:8080/api/products
```

### Orders

Get a specific order:
```bash
curl -u alice:password http://localhost:8080/api/orders/1
```

Create a new order:
```bash
curl -X POST \
  -u alice:password \
  -H "Content-Type: application/json" \
  -d '{"product":"Smartphone", "total":800.00}' \
  http://localhost:8080/api/orders
```

Cancel an order:
```bash
curl -X PUT -u manager:password http://localhost:8080/api/orders/1/cancel
```

Refund an order:
```bash
curl -X PUT -u bob:password http://localhost:8080/api/orders/2/refund
```

Update order status:
```bash
curl -X PUT \
  -u manager:password \
  -H "Content-Type: application/json" \
  -d '{"status":"SHIPPED"}' \
  http://localhost:8080/api/orders/1/status
```

### Admin Reports

```bash
curl -u admin:password http://localhost:8080/api/admin/reports
```
