package com.example.swagger.common.exception

import com.example.swagger.common.exception.custom.BadRequestException
import com.example.swagger.common.exception.custom.NotUsedException
import com.example.swagger.common.`object`.ResultCode
import com.example.swagger.common.`object`.ResultVo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [BadRequestException::class])
    @Operation(summary = "BadRequest 예외 처리", description = "잘못된 요청에 대한 예외 처리")
    fun missingFormatArgumentExceptionHandler(e: BadRequestException): ResponseEntity<Any> {
        val responseVo = ResultVo<String>()
        if(e.message != null) {
            responseVo.setResponse(e.message.toString())
        }

        when(e) {
            is BadRequestException -> {
                responseVo.code = ResultCode.BAD_REQUEST.code
            }
        }
        return ResponseEntity(responseVo ,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value= [NotUsedException::class])
    @Operation(summary = "NotUsedException 예외 처리", description = "사용하지 않는 파라미터 예외 처리")
    fun notUsedExceptionHandler(e: NotUsedException): ResponseEntity<Any> {
        val responseVo = ResultVo<String>()
        if(e.message != null) {
            responseVo.setResponse(e.message.toString())
        }
        return ResponseEntity(responseVo ,HttpStatus.ACCEPTED)
    }

    // Internal Server Error (500)를 처리하는 핸들러
    @ExceptionHandler(value = [Exception::class])
    @Operation(summary = "Internal Server 예외 처리", description = "서버 내부 오류에 대한 예외 처리")
    fun internalServerErrorHandler(e: RuntimeException): ResponseEntity<Any> {
        val responseVo = ResultVo<String>().apply {
            code = ResultCode.INTERNAL_SERVER_ERROR.code
            if (e.message != null) {
                setResponse(e.message.toString())
            }
        }

        return ResponseEntity(responseVo, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
