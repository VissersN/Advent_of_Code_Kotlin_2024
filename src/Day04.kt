fun main() {
    val input = readInput("Day04")
    val splittedInput = input.map { it.toList() }
    var total1 = 0
    var totalMAS = 0

//    part1
//    for (i in splittedInput.indices) {
//        for (j in splittedInput[i].indices) {
//            if (splittedInput[i][j] == 'X') {
//                println("X gevonden op: [$i][$j]")
//                val neighbors = listOf(
//                    i - 1 to j - 1, i - 1 to j, i - 1 to j + 1, // boven
//                    i to j - 1, i to j + 1,     // midden
//                    i + 1 to j - 1, i + 1 to j, i + 1 to j + 1  // onder
//                )
//                for ((mi, mj) in neighbors) {
//                    if (mi in splittedInput.indices && mj in splittedInput[mi].indices) {
//                        if (splittedInput[mi][mj] == 'M') {
//                            println("M gevonden in de buurt van X op: [$mi][$mj]")
//
//                            val dx = mi - i
//                            val dy = mj - j
//
//                            val ai = mi + dx
//                            val aj = mj + dy
//                            if (ai in splittedInput.indices && aj in splittedInput[ai].indices && splittedInput[ai][aj] == 'A') {
//                                println("A gevonden direct aansluitend op M op: [$ai][$aj]")
//
//                                val si = ai + dx
//                                val sj = aj + dy
//
//                                if (si in splittedInput.indices && sj in splittedInput[si].indices && splittedInput[si][sj] == 'S') {
//                                    println("S gevonden direct aansluitend op A op: [$si][$sj]")
//                                    total1++
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//part2
    for (i in splittedInput.indices) {
        for (j in splittedInput[i].indices) {
            if (splittedInput[i][j] == 'A') {
                println("A gevonden op: [$i][$j]")

                // Diagonale richtingen voor beide kanten
                val directions = listOf(
                    listOf(-1 to -1, +1 to +1), // Links-boven naar rechts-onder
                    listOf(-1 to +1, +1 to -1)  // Rechts-boven naar links-onder
                )

                for (direction in directions) {
                    var foundMAS = 0

                    // Check beide richtingen (M en S)
                    val (mDir, sDir) = direction
                    val (mi, mj) = i + mDir.first to j + mDir.second
                    val (si, sj) = i + sDir.first to j + sDir.second

                    if (mi in splittedInput.indices && mj in splittedInput[mi].indices && splittedInput[mi][mj] == 'M') {
                        println("M gevonden in diagonale lijn van A op: [$mi][$mj]")
                    } else {
                        println("Geen M gevonden op: [$mi][$mj]")
                        continue // Ga naar de volgende richting
                    }

                    if (si in splittedInput.indices && sj in splittedInput[si].indices && splittedInput[si][sj] == 'S') {
                        println("S gevonden tegenover M op: [$si][$sj]")
                        foundMAS++
                    } else {
                        println("Geen S gevonden op: [$si][$sj]")
                        continue // Ga naar de volgende richting
                    }

                    // Controleer tweede M en S in dezelfde richting
                    val (mi2, mj2) = mi + mDir.first to mj + mDir.second
                    val (si2, sj2) = si + sDir.first to sj + sDir.second

                    if (mi2 in splittedInput.indices && mj2 in splittedInput[mi2].indices && splittedInput[mi2][mj2] == 'M') {
                        println("Tweede M gevonden in diagonale lijn van A op: [$mi2][$mj2]")
                    } else {
                        println("Geen tweede M gevonden op: [$mi2][$mj2]")
                        continue // Ga naar de volgende richting
                    }

                    if (si2 in splittedInput.indices && sj2 in splittedInput[si2].indices && splittedInput[si2][sj2] == 'S') {
                        println("Tweede S gevonden tegenover M op: [$si2][$sj2]")
                        foundMAS++
                    } else {
                        println("Geen tweede S gevonden op: [$si2][$sj2]")
                    }

                    // Als beide diagonale lijnen compleet zijn
                    if (foundMAS == 2) {
                        println("Patroon MASMAS gevonden bij A op: [$i][$j]")
                        totalMAS++
                        break // Stop met zoeken bij deze A
                    }
                }
            }
        }
    }
println(totalMAS)
}


