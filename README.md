## Launching the project
After cloning the project run `./gradlew bootRun` in the command line or use your IDE\
If you have any problem with postgres or docker check port `5432` and make sure you have no process using this port.
If another process is using the port you should stop it to run the application.

## Test data
After application start two roles and admin will be added automatically\
To add users there is a sign up endpoint available. An account for the user is created automatically after user creation

### Automatic test users creation
If you don't want to add users using sign up endpoint, you can uncomment
`addTestUsers()` function call in `SetUpDataLoader` class. The function will create two users
```
{
    "email": "test_user@test.com",
    "password": "test_user"
}
```
and
```
{
    "email": "test@test.com",
    "password": "test"
}
```
An account for the user is created automatically after user creation.

## Authorization
Add `Authorization: Bearer <token>` header. The screenshot shows how it looks in `Postman`\
![token_paste](token_paste.png)

## Available API endpoints
* User sign up `POST` `api/auth/signup` with a JSON containing email and password in the request body
* Log in `POST` `api/auth/login` with a JSON containing email and password in the request body\
After logging in you will get a token in the response body
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNzIyNzAyNDE4LCJleHAiOjE3MjI3MDYwMTh9.2_zwHHwQq1EHFsnYu8IlDo1Dkuej_dD7rxB0EVCxnwQ",
    "expiresIn": 3600000
}
```
Copy the token for future authorization.

### Available admin API endpoints
These endpoints are available only with admin token (when you log in as admin)\
Admin credentials are:
```
{
    "email": "admin@test.com",
    "password": "test"
}
```
* Get list of all accounts `GET` `api/admin/accounts`
* Block account by id `PUT` `api/admin/accounts/{id}/block` where `id` is an id of the account to block
* Unblock account by id `PUT` `api/admin/accounts/{id}/unblock` where `id` is an id of the account to unblock

### Available user API endpoints
These endpoints are available only with user token (when you log in as user)
* Get account `GET` `api/accounts`
* Deposit money to the account `PUT` `api/accounts/deposit` with a JSON containing amount to deposit
```
{
    "amount": "9.99"
}
```
* Withdraw money from the account `PUT` `api/accounts/withdraw` with a JSON containing amount to withdraw.\
When account is blocked it is not possible to do any operations with the account. Also it is not possible to withdraw money if there is not enough money

## Errors
If you get a `JWT expired` error, you should log in again and get a new token for authorization