package com.wednesday.template.data.core

interface Mapper3<FROM1, FROM2, FROM3, TO> {

    fun map(from1: FROM1, from2: FROM2, from3: FROM3): TO
}
