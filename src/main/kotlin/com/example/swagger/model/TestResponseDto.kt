package com.example.swagger.model

import io.swagger.v3.oas.annotations.media.Schema

data class TestResponseDto(
        @Schema(description = "응답에 들어가는 테스트")
        val test: String,
        @Schema(description = "응답에 들어가는 버전")
        val version: Int
)
