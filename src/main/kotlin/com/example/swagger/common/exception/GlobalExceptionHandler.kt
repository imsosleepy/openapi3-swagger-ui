package com.example.swagger.common.exception

import com.example.swagger.common.exception.custom.BadRequestException
import com.example.swagger.common.`object`.ResultCode
import com.example.swagger.common.`object`.ResultVo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [BadRequestException::class])
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

    // Internal Server Error (500)를 처리하는 핸들러
    @ExceptionHandler(value = [Exception::class])
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
