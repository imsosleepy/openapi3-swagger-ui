package com.example.swagger.controller

import com.example.swagger.model.GetRequestDto
import com.example.swagger.model.PostRequestDto
import com.example.swagger.model.TestResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "OpenAPI 3 - Swagger Test API", description = "테스트 API입니다.")
@RestController
class SwaggerController {

    @Operation(
            summary = "Get Test API",
            description = "This is GET API",
            )
    @GetMapping("/get-test")
    fun getTest(@Valid @ParameterObject getRequestDto: GetRequestDto): TestResponseDto {
        val testResult = getRequestDto.test +" 이것은 get 테스트다!!"
        val versionResult = getRequestDto.version + 1
        return TestResponseDto(testResult, versionResult)
    }

    @Operation(
            summary = "Post Test API",
            description = "This is POST API")
    @PostMapping("post-test")
    fun postTest(@Valid @RequestBody postRequestDto: PostRequestDto): TestResponseDto {
        val testResult = postRequestDto.test + "이것은 post 테스트다!!";
        val versionResult = postRequestDto.version + 1;
        return TestResponseDto(testResult, versionResult)
    }
}