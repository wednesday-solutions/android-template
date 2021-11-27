package com.wednesday.template.repo.util

interface Mapper2<FROM1, FROM2, TO> {

    fun map(from1: FROM1, from2: FROM2): TO
}