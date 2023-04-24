package com.wednesday.template.data.core

interface Mapper4<FROM1, FROM2, FROM3, FROM4, TO> {

    fun map(from1: FROM1, from2: FROM2, from3: FROM3, from4: FROM4): TO
}
