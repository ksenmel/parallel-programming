import elimination.EliminationBackoffStack
import stack.LockFreeStack
import java.util.Random

fun main() {
    val stack = EliminationBackoffStack<Int>(3)
    val random = Random()
    val threadsNum = listOf(1, 8, 12, 16)

    for(threadNum in threadsNum) {
        val threads = List(threadNum) {
            Thread {
                repeat(10) {
                    when (random.nextInt(2)) {
                        0 -> stack.push(random.nextInt(100))
                        1 -> stack.pop()
                    }
                }
            }
        }
        val start = System.currentTimeMillis()
        threads.forEach { it.start() }
        threads.forEach { it.join() }
        println(System.currentTimeMillis() - start)
    }

}
