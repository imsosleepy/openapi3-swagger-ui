package com.example.swagger.common.exception.custom

class NotUsedException : RuntimeException {
    constructor() : super()
    constructor(errorMessage: String) : super(errorMessage)
}
