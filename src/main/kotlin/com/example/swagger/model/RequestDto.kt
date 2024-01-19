package com.example.swagger.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min


data class GetRequestDto(
        @field:Schema(description = "문구 패턴 테스트", pattern = "swagger|openAPI|docs|fox")
        val test: String,
        @field:Schema(description = "버전은 숫자로")
        val version: Int,
        @field:Schema(description = "생략 가능한 변수")
        val notRequired: String?
)

data class PostRequestDto(
        @field:Schema(description = "Post에선 다른 테스트를 해보자")
        val test: String,
        @field:Min(1) @field:Max(10)
        val version: Int,
        @field:Schema(description = "생략 가능한 변수")
        val notRequired: String?
)
