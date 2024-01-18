package com.example.swagger.common.exception.custom

class BadRequestException : RuntimeException {
    constructor() : super()
    constructor(errorMessage: String) : super(errorMessage)
}
