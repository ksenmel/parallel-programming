package common

interface Stack<T> {

    fun push(x: T)

    fun pop(): T?

    fun top(): T?
}