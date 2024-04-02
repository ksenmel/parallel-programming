package stack

import common.Node
import common.Stack
import java.util.concurrent.atomic.AtomicReference

class LockFreeStack<T> : Stack<T> {
    private val head = AtomicReference<Node<T>?>()

    override fun push(x: T) {
        val newH = Node(x)

        do {
            val tmpH = head.get()
            newH.next = tmpH
        } while (!(head.compareAndSet(tmpH, newH)))

        return
    }

    override fun pop(): T? {
        while (true) {
            val tmpH = head.get() ?: return null
            if (head.compareAndSet(tmpH, tmpH.next)) {
                return tmpH.value
            }
        }
    }

    override fun top(): T? {
        val tmpH = head.get() ?: return null
        return tmpH.value
    }
}