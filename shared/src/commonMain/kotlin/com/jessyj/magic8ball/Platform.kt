package com.jessyj.magic8ball

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform