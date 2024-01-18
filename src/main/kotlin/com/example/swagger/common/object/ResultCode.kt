package com.example.swagger.common.`object`

enum class ResultCode(
        val code: String,
        val description: String
) {
    OK("20000", "성공"),
    NO_DATA("20001", "해당하는 데이터가 없음"),
    ACCEPTED("20002", "요청은 접수하였지만, 처리가 완료되지 않았음"),
    BAD_REQUEST("40000","유효하지 않은 값 전달"),
    INTERNAL_SERVER_ERROR("50000", "내부 서버 에러")
}
