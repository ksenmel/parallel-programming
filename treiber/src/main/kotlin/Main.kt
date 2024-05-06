import elimination.EliminationBackoffStack
import stack.LockFreeStack
import java.util.Random

fun main() {
    val stack = EliminationBackoffStack<Int>(10)

    stack.push(5)
    stack.push(3)

    println(stack.top())

    stack.pop()
    println(stack.top())

    stack.pop()
    println(stack.top())

}
