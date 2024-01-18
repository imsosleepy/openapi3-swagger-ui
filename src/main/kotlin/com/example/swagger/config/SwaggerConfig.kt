package com.example.swagger.config

import com.example.swagger.common.`object`.ResultVo
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Configuration
class SwaggerConfig {

    val badRequestExceptionValue = """
{
    "code": "40000",
    "response": "유효하지 않은 값 전달",
    "time": "string"
}
""".trimIndent()

    val internalExceptionValue = """
{
    "code": "50000",
    "response": "string data",
    "time": "string"
}
""".trimIndent()

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .components(Components())
                .info(configurationInfo())
    }

    private fun configurationInfo(): Info {
        return Info()
                .title("OpenAPI3 UI 테스트")
                .description("OpenAPI3 - Springdoc을 사용한 Swagger UI 테스트")
                .version("1.0.0")
    }

    @Bean
    fun customiseResponses(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi: OpenAPI ->
            openApi.paths.orEmpty().values.forEach { pathItem ->
                pathItem.readOperations().forEach { operation ->
                    val apiResponses: ApiResponses = operation.responses

                    apiResponses.addApiResponse("400", createApiResponse(
                            "Bad Request",
                            "BadRequestExample",
                            badRequestExceptionValue
                    ))
                    apiResponses.addApiResponse("500", createApiResponse(
                            "Internal Server Error",
                            "InternalServerErrorExample",
                            internalExceptionValue
                    ))
                }
            }
        }
    }

    private fun createApiResponse(description: String, exampleName: String, exampleValue: String): ApiResponse {
        val example = Example().apply {
            this.value = exampleValue.trimIndent()
        }
        val mediaType = MediaType().apply {
            this.addExamples(exampleName, example)
            this.schema = Schema<Any>().`$ref`("com.example.swagger.model.ErrorResponse")
        }
        val content = Content().apply {
            this.addMediaType(APPLICATION_JSON_VALUE, mediaType)
        }
        return ApiResponse().apply {
            this.description = description
            this.content = content
        }
    }
}
