package elimination

import java.util.concurrent.TimeUnit
import java.util.Random

class EliminationArray<T> (capacity: Int) {
    private val exchanger = Array(capacity) { LockFreeExchanger<T>() }

    companion object {
        private const val duration = 2L;
    }

    private val random = Random()

    fun visit(value: T, range: Int): T? {
        val slot = random.nextInt(range)
        return exchanger[slot].exchange(value, duration, TimeUnit.MILLISECONDS)
    }
}

