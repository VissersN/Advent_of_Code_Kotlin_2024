import kotlin.math.abs

fun main() {
    val input = "41078 18 7 0 4785508 535256 8154 447"
    val splittedInput = input.split(" ").map { it.toLong() }.toMutableList()
    println(splittedInput)

    for (i in 1..25) {

        val newElements = mutableListOf<Pair<Int, Long>>() // Opslag voor nieuwe elementen

        splittedInput.forEachIndexed { index, i ->
            // Controleer en update specifieke waarden
            if (i == 0L) {
                splittedInput[index] = 1
            } else if (i.toString().length % 2 == 0) { // Controleer of de lengte van de waarde even is
                println("Splitting: $i")

                // Eerste helft
                val firstHalf = i.toString().substring(0, i.toString().length / 2).toLong()

                // Tweede helft
                val secondHalf = i.toString().substring(i.toString().length / 2).toLong()

                // Voeg de nieuwe elementen toe aan de buffer
                newElements.add(index to secondHalf)

                // Vervang het huidige element met de eerste helft
                splittedInput[index] = firstHalf
            } else {
                // Voer de vermenigvuldiging veilig uit met Long
                val multiplied = splittedInput[index] * 2024
                splittedInput[index] = multiplied
            }
        }

        // Voeg nieuwe elementen toe na het itereren
        newElements.reversed().forEach { (index, value) ->
            splittedInput.add(index + 1, value)
        }

        println("Updated list: $splittedInput")
    }

    println(splittedInput.size)
}
