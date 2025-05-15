package org.cats.onlinebank

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform