package ru.donnu.practice

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform