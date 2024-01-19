package com.example.swagger.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
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

@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class SwaggerConfig {

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

                    // 기존 응답들을 유지하면서 표준 응답을 추가
                    apiResponses.addApiResponse("400", createStandardApiResponse("400", "Bad Request"))
                    apiResponses.addApiResponse("500", createStandardApiResponse("500", "Internal Server Error"))
                }
            }
        }
    }

    private fun createStandardApiResponse(code: String, description: String): ApiResponse {
        val exampleContent = when (code) {
            "400" -> """
            {
                "code": "400",
                "message": "잘못된 요청입니다.",
                "timestamp": "2023-01-01T12:00:00Z"
            }
            """.trimIndent()

            "500" -> """
            {
                "code": "500",
                "message": "서버 오류가 발생했습니다.",
                "timestamp": "2023-01-01T12:00:00Z"
            }
            """.trimIndent()

            else -> ""
        }

        val example = Example().apply {
            value = exampleContent
        }

        val mediaType = MediaType().apply {
            addExamples("example", example)
            schema = Schema<Any>() // ErrorResponse의 스키마를 참조하거나 일반 Object 스키마를 사용할 수 있습니다.
        }

        val content = Content().apply {
            addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType)
        }

        return ApiResponse().apply {
            this.description = description
            this.content = content
        }
    }
}
