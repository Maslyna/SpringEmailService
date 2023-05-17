# CONTRIBUTING
- ###### ! In this example you must use environment variables or hardcode them in application.properties
- ###### ENV: `EMAIL_USER` and `EMAIL_PASSWORD`

## Example:
1) Create new user sending `POST` request for `localhost:8080/register` endpoint.

```
BODY:
{ 
   "login" : "real.email@gmail.com", 
   "password" : "password"
}
```

```
RESPONSE BODY:
{
    "id": 1,
    "login": "real.email@gmail.com",
    "password": "$2a$10$jRgBYYGkcUBCx90Kg.lpyOFC9zCAkLXA5.03ThfQrgUetQxPCECM2",
    "uuid": "random-uuid",
    "authority": "ROLE_USER",
    "isLocked": false
}
```
   
2) Delete user sending `DELETE` request for `localhost:8080/{user-id}/delete` endpoint.
- After that you will achieve message with LINK, after going at this link you will restore your account.
  - After 3 minutes account will automatically delete.


###### P.S. In this service you also can send different emails to another peoples with `POST` request for `localhost:8080/send-email` endpoint with body:
```
{
    "to" : "sanci324@gmail.com",
    "subject" : "test email spring boot service sender",
    "text" : "hi, how are you doing? My name is John Doe."
}
```
###### P.S.2  In this service you also can send different emails to another peoples with `POST` request for `localhost:8080/send-file` endpoint with params:
- @Param String to
- @Param String subject
- @Param text
- @Body file (any MultiPartFile)