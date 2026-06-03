#! /bin/zsh
curl --location --request POST 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Connection: keep-alive' \
--data-raw '{
    "account": "admin",
    "password": "123"
}'
