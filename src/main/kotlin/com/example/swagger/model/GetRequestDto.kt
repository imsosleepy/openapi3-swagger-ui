package com.example.swagger.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern


data class GetRequestDto(
        @field:Schema(description = "문구 패턴 테스트")
        @field:Pattern(regexp = "swagger|openAPI|docs|fox")
        val test: String,
        @field:Schema(description = "버전은 숫자로")
        val version: Int,
        @field:Schema(description = "생략 가능한 변수")
        val notRequired: String?
)
