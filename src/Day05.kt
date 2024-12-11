fun main() {
    val bigInput = readInput("Day05")
    val testInput = readInput("Day05_test")
    val input = testInput

    val indexEmpty = input.indexOf("")

    val orderInstructions = input.subList(0, indexEmpty)
    val pages = input.subList(indexEmpty + 1, input.size)

   pages.forEach {
       val pagesToProdce = it.split(",").map { it.toInt() }
       println("new")
       for (i in pagesToProdce.indices) {
           if (pagesToProdce[i] > pagesToProdce[i]) {}
          println("$i ${pagesToProdce[i]}")
   }
       }
}
