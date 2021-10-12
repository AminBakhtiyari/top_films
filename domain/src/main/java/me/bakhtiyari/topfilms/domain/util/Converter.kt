package me.bakhtiyari.topfilms.domain.util

interface Converter<out T, E> {
    @Throws(TypeCastException::class)
    fun convert(objects: E): T
}