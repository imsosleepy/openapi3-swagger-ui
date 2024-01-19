package com.example.swagger.controller

import com.example.swagger.common.exception.custom.BadRequestException
import com.example.swagger.common.exception.custom.NotUsedException
import com.example.swagger.common.`object`.ResultCode
import com.example.swagger.model.GetRequestDto
import com.example.swagger.model.PostRequestDto
import com.example.swagger.model.TestResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "OpenAPI 3 - Swagger Test API", description = "테스트 API입니다.")
@RestController
class SwaggerController {

    @Operation(summary = "Get Test API", description = "This is GET API")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "사용하지 않는 파라미터", content = [Content()])]
    )
    @GetMapping("/get-test")
    fun getTest(@Valid @ParameterObject getRequestDto: GetRequestDto, bindingResult: BindingResult): TestResponseDto {
        if(bindingResult.hasErrors()) {
            throw BadRequestException(ResultCode.BAD_REQUEST.description)
        }
        if(getRequestDto.test == "fox") {
            throw NotUsedException(ResultCode.ACCEPTED.description)
        }
        val testResult = getRequestDto.test +" 이것은 get 테스트다!!"
        val versionResult = getRequestDto.version + 1
        return TestResponseDto(testResult, versionResult)
    }

    @Operation(summary = "Post Test API", description = "This is POST API")
    @PostMapping("post-test")
    @SecurityRequirement(name = "bearerAuth")
    fun postTest(@Valid @RequestBody postRequestDto: PostRequestDto): TestResponseDto {
        val testResult = postRequestDto.test + "이것은 post 테스트다!!";
        val versionResult = postRequestDto.version + 1;
        return TestResponseDto(testResult, versionResult)
    }
}