import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.temporal.ChronoUnit

val tasks = (0..9).toList()
val delays = tasks.map { (0..999).random().toLong() }
val sumTime = delays.sum()
val maxDelay = delays.max()

fun main() {
    val start = now()
    //runBlocking {
        //val results = tasks.asyncMap { longCalculateSuspend(it) }.toList()
        val results = tasks.asyncMap { longCalculate(it) }.toList()
        //val results = tasks.map { longCalculate(it) }.toList()

        val tookTime = ChronoUnit.MILLIS.between(start, now())
        println("plan: ${delays.mapIndexed { index, l -> "$index-$l" }}")
        println("fact: $results")
        println("Took time: $tookTime | max: $maxDelay | ${getWithSign(tookTime - maxDelay)}")
        println("Summ time: $sumTime  ${getWithSign(tookTime - sumTime)}")
    //}
}

suspend fun longCalculateSuspend(number: Int): String {
    val start = now()
    println("$number |->..")
    delay(delays[number])
    return buildInfo(number, start)
}

fun longCalculate(number: Int): String {
    val start = now()
    println("$number |->..")
    runBlocking { delay(delays[number]) }
    return buildInfo(number, start)
}

fun buildInfo(number: Int, start: LocalDateTime): String {
    val tookTime = ChronoUnit.MILLIS.between(start, now())
    print("$number ..->| f: $tookTime ms | p: ${delays[number]} ms | ${getWithSign(tookTime - delays[number])}")
    println("| thread: ${Thread.currentThread().name}")
    return "$number-${tookTime}"
}

fun getWithSign(value: Long) = if (value > 0) "+$value" else "$value"