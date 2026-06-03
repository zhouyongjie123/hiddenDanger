#! /bin/zsh
curl --location --request POST 'http://localhost:8081/risk/report' \
--header "token: $1" \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Connection: keep-alive' \
--data-raw '{
    "name": "井下变电所灭火器超期未年检",
    "description": "中央变电所内3具干粉灭火器指针偏向红区，且标签显示已过效期。",
    "location": "中央变电所",
    "riskLevel": "LOW",
    "riskType": "OTHER",
    "responsibleDepartmentId": "2048412264347774978",
    "responsiblePersonId": "2048412208764858370",
    "discoveryTime": "2026-04-25 16:45:00",
    "rectificationDeadline": "2026-05-02 16:45:00",
    "source": "DAILY_CHECK"
  }'
