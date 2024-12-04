fun main() {
    val input = readInput("Day04")
    val splittedInput = input.map { it.toList() }
    var total1 = 0

//    part1
    println(splittedInput.size)
    for (i in splittedInput.indices) {
        for (j in splittedInput[i].indices) {
            if (splittedInput[i][j] == 'X') {
                println("X gevonden op: [$i][$j]")
                val neighbors = listOf(
                    i - 1 to j - 1, i - 1 to j, i - 1 to j + 1, // boven
                    i to j - 1, i to j + 1,     // midden
                    i + 1 to j - 1, i + 1 to j, i + 1 to j + 1  // onder
                )
                for ((mi, mj) in neighbors) {
                    if (mi in splittedInput.indices && mj in splittedInput[mi].indices) {
                        if (splittedInput[mi][mj] == 'M') {
                            println("M gevonden in de buurt van X op: [$mi][$mj]")

                            val dx = mi - i
                            val dy = mj - j

                            val ai = mi + dx
                            val aj = mj + dy
                            if (ai in splittedInput.indices && aj in splittedInput[ai].indices && splittedInput[ai][aj] == 'A') {
                                println("A gevonden direct aansluitend op M op: [$ai][$aj]")
                                
                                val si = ai + dx
                                val sj = aj + dy

                                if (si in splittedInput.indices && sj in splittedInput[si].indices && splittedInput[si][sj] == 'S') {
                                    println("S gevonden direct aansluitend op A op: [$si][$sj]")
                                    total1++
                                }
                            }
                        }
                    }
                }
            }
        }
    }
println(total1)
}


