package com.wednesday.template.repo.util.mapper

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO

    fun map(from: List<FROM>): List<TO> = from.map(::map)
}