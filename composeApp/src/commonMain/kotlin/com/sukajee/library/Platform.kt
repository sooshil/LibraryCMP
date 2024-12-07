package com.sukajee.library

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform