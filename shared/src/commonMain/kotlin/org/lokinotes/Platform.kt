package org.lokinotes

expect val platform: String

class Greeting {
    fun greeting() = "Hello, $platform!"
}