package ru.donnu.practice.utills


fun String.filterChars() = this.filter { it != 'f' && it != 'd' }

fun String.isValidManufacture() = toDoubleOrNull() != null || this.isEmpty()