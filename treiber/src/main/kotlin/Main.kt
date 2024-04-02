import stack.LockFreeStack

fun main() {
    val stack = LockFreeStack<Int>()

    stack.push(5)
    stack.push(3)

    println(stack.top())

    stack.pop()
    println(stack.top())

    stack.pop()
    println(stack.top())

}