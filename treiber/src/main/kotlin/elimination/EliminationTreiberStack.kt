package elimination

import common.Stack
import common.Node

import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicReference


class EliminationBackoffStack<T>(private val capacity: Int) : Stack<T> {
    private val eliminationArray = EliminationArray<T?>(capacity)
    private val head: AtomicReference<Node<T>?> = AtomicReference(null)

    private var successes: Int = 0
    private var timeouts: Int = 0
    private var range: Int = capacity

    private fun recordEliminationTimeout() {
        timeouts++
        if (timeouts > 10) {
            timeouts = 0
            if (range > 1) range--
        }
    }

    private fun recordEliminationSuccess() {
        successes++
        if (successes > 5) {
            successes = 0
            if (range < capacity) range++
        }
    }

    private fun tryPush(node: Node<T>): Boolean {
        val oldH = head.get()
        node.next = oldH
        return (head.compareAndSet(oldH, node))
    }

    override fun push(value: T) {
        val node = Node(value)
        while (true) {
            if (tryPush(node)) return
            else eliminationArray.visit(value, capacity)
        }
    }

    private fun tryPop(): Node<T>? {
        val oldH = head.get() ?: return null
        val newH = oldH.next
        return if (head.compareAndSet(oldH, newH)) oldH else null
    }

    @Throws(TimeoutException::class)
    override fun pop(): T? {
        while (true) {

            val returnNode = tryPop()

            if (returnNode != null) {
                return returnNode.value
            } else try {
                val otherValue = eliminationArray.visit(null, range)
                if (otherValue != null) {
                    recordEliminationSuccess()
                    return otherValue
                }
            } catch (ex: TimeoutException) {
                recordEliminationTimeout()
            }
        }
    }

    override fun top(): T? = head.get()?.value
}