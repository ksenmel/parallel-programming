package stack

import common.Node
import common.Stack
import java.util.concurrent.atomic.AtomicReference

class LockFreeStack<T> : Stack<T> {
    private val head = AtomicReference<Node<T>?>()

    override fun push(value: T) {
        val newH = Node(value)

        while (true) {
            val lastH = head.get()
            newH.next = lastH
            if (head.compareAndSet(lastH, newH)) {
                return
            }
        }
    }

    override fun pop(): T? {
        while (true) {
            val lastH = head.get() ?: return null
            if (head.compareAndSet(lastH, lastH.next)) {
                return lastH.value
            }
        }
    }

    override fun top(): T? {
        val lastH = head.get() ?: return null
        return lastH.value
    }
}