package org.berkaykaramehmetoglu.economoney

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform