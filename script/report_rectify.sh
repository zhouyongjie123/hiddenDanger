#! /bin/zsh
curl --location --request POST 'http://localhost:8081/risk/rectify/report' \
--header "token: $1" \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Connection: keep-alive' \
--data-raw '{
    "hiddenRiskId": "2061685144050700290",
    "measureContent": "灭火器已更换",
    "responsiblePersonId": "2036347045152862209",
    "startTime": "2026-04-12 15:57:10",
    "completionTime": "2026-04-20 15:57:13",
    "effectDescription": "灭火器已更换",
    "approvalFlowCreateDto": {
    "processName": "井下变电站灭火器超期未年检审批流程",
    "graph": {
        "originalGraph":[
           [0, 1, 0, 0, 0],
           [2, 0, 1, 0, 0],
           [0, 2, 0, 1, 0],
           [0, 2, 0, 0, 1],
           [0, 0, 0, 0, 0]
    },
        ]
    "approverIds": ["2036347045152862209","2036347045152862209","2048412208764858370"]
    }
}'
