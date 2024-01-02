package io.github.zeqky.catan.core

abstract class Attachable {
    private var attachment: Any? = null

    fun <T: Any> attach(instance: T) {
        attachment = instance
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> attachment(): T {
        return attachment as T
    }
}