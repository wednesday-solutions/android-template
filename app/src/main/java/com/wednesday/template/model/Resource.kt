package com.wednesday.template.model

enum class Status {
    Success, Error, Loading
}

data class Resource<T>(val status: Status, val data: T?, val error: String? = null) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.Success, data)
        }

        fun <T> error(error: String): Resource<T> {
            return Resource(Status.Error, null, error)
        }

        fun <T> loading(data: T): Resource<T> {
            return Resource(Status.Loading, data)
        }
    }
}
