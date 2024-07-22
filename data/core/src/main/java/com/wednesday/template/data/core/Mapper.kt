package com.wednesday.template.data.core

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO

    fun map(from: List<FROM>): List<TO> = from.map { map(it) }
}
