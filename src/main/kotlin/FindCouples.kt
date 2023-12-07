fun main() {
    val array = listOf<Int>(1, 3, 5, 7, 3, 4, 8)
    val value = 12
    findPair(array, value)
}

fun findPair(array: List<Int>, value: Int) {
    val watched = mutableSetOf<Int>()
    var isFound = false
    array.forEach { element ->
        val delta = value - element
        if (watched.contains(delta)) {
            print("$value = $delta + $element\n")
            isFound = true
        }
        watched.add(element)
    }
    if (!isFound) {
        print("No pair")
    }
}