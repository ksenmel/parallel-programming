package stack

import elimination.EliminationBackoffStack
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.LoggingLevel
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.jupiter.api.Test

class EliminationBackoffStackTest {
    private val stack =
        EliminationBackoffStack<Int>(10)

    @Operation
    fun top() = stack.top()

    @Operation
    fun push(value: Int) = stack.push(value)

    @Operation
    fun pop() = stack.pop()

    @Test
    fun test() =
        StressOptions()
            .iterations(10)
            .invocationsPerIteration(10)
            .threads(8)
            .actorsPerThread(3)
            .logLevel(LoggingLevel.INFO)
            .check(this::class.java)
}