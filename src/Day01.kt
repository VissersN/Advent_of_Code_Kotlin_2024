import kotlin.math.abs

fun main() {
    val input = readInput("Day01")
    val seperatedList1 = mutableListOf<Int>()
    val seperatedList2 = mutableListOf<Int>()
    var total: Int = 0
    val splittedInput = input.map { it.split(" ").toList().filter { it.isNotBlank() } }
    splittedInput.forEach {
        seperatedList1.add(it[0].toInt())
        seperatedList2.add(it[1].toInt())
    }

    fun part1() {
        seperatedList1.sort()
        seperatedList2.sort()
        for (i in seperatedList1.indices) {
            val difference = abs(seperatedList1[i] - seperatedList2[i])
            println(difference)
            total += difference
        }
        println("Totale som van verschillen: $total")
    }

    fun part2() {
        for (i in seperatedList1.indices) {
            val numAppereances = seperatedList2.count { it == seperatedList1[i]  }
            total += numAppereances * seperatedList1[i]
        }
        println("Totaal: $total")
    }
    part2()
}
