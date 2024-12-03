fun main() {
    val input = readInput("Day02")
    val numIsSafe = countSafes(input)
    var result = 0
    val unsafeEntriesList = input
        .map { it.split(" ").map(String::toInt) }
        .filter { numbers ->
            val sortedNumbers = numbers.sorted()
            !(numbers == sortedNumbers || numbers == sortedNumbers.reversed()) ||
                    !sortedNumbers.windowed(2).all { (a, b) -> b - a in 1..3 }
        }.toMutableList()
    println("Unsafe result: ${unsafeEntriesList.size}")
    unsafeEntriesList.forEach { unsafeEntry ->
        for (i in unsafeEntry.indices) {
            val toCheck = unsafeEntry.toMutableList().apply {removeAt(i) }.toList()
            if ((toCheck.sorted() == toCheck || toCheck.sorted().reversed() == toCheck) &&
                    toCheck.sorted().windowed(2).all { (a, b) -> b - a in 1..3 })
                    {
                        result++
                        return@forEach
                    }
        }
    }
    println("Safe result: $numIsSafe")
    println("Unsafe result: $result")
}

private fun countSafes(input: List<String>): Int {
    val numIsSafe = input
        .map { it.split(" ").map(String::toInt) }
        .count { numbers ->
            val sortedNumbers = numbers.sorted()
            (numbers == sortedNumbers || numbers == sortedNumbers.reversed()) &&
                    sortedNumbers.windowed(2).all { (a, b) -> b - a in 1..3 }
        }
    return numIsSafe
}

