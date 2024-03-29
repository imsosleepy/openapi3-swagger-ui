package com.example.swagger.model

import io.swagger.v3.oas.annotations.media.Schema

data class TestResponseDto(
        @Schema(description = "응답에 들어가는 테스트")
        val test: String,
        @Schema(description = "응답에 들어가는 버전")
        val version: Int
)

@Schema(description = "표준 에러 응답 포맷")
data class ErrorResponse(
        @Schema(description = "에러 코드")
        val code: String,
        @Schema(description = "에러 메시지")
        val message: String,
        @Schema(description = "에러 발생 시간")
        val timestamp: String)