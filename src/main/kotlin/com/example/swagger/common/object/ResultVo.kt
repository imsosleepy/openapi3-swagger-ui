package com.example.swagger.common.`object`


import org.springframework.util.ObjectUtils
import java.text.SimpleDateFormat
import java.util.*
class ResultVo<T> {
    val time: String?
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS")
            return simpleDateFormat.format(Date())
        }
    var code: String? = null
        get() = if (ObjectUtils.isEmpty(field)) "200000" else field

    private var response: T? = null

    @Suppress("UNCHECKED_CAST")
    fun getResponse(): T? {
        return if (ObjectUtils.isEmpty(response)) HashMap<Any, Any>() as T else response
    }

    fun setCode(code: String?): ResultVo<T> {
        this.code = code
        return this
    }

    fun setResponse(response: T): ResultVo<T> {
        this.response = response
        return this
    }
}
