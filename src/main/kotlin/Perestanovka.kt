var counter: Int = 0

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    shift("", list)
}

fun shift(currRes: String, list: List<Int>) {
    val prefix = if (currRes.isEmpty()) "" else "$currRes,"
    if (list.count() == 1) {
        numberedPrint("$prefix${list[0]}")
        return
    }
    for (i in list.indices) {
        val list2 = list.toMutableList()
        list2.removeAt(i)
        shift("$prefix${list[i]}", list2)
    }
}

fun numberedPrint(string: String) {
    println("${++counter}) $string")
}
