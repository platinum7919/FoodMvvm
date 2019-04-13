package com.foodmvvm.utils

fun String.nullIfEmpty(): String? {
    return if (isEmpty()) null else this
}

fun String.nullIfEmptyOrBlank(): String? {
    return if (isEmpty() || isBlank()) null else this
}