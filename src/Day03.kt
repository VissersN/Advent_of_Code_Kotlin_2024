fun main() {
    val regex = Regex("mul\\([0-9]+,[0-9]+\\)")
    val regexInstruction = Regex("do\\(\\)|don't\\(\\)")
    val input = readInput("Day03").toString()

//    println(part1(matches))
    println(part2(input, regexInstruction, regex))
}

fun part1(input: String, regex: Regex): Int {
    val matches = regex.findAll(input)
    var interMediateResult = 0
    matches.forEach { match ->
        val stringed = match.value.removePrefix("mul(").removeSuffix(")")
        val parts = stringed.split(",")
        val part1 = parts[0].toInt()
        val part2 = parts[1].toInt()
        val product = part1 * part2
        interMediateResult += product
    }

    return interMediateResult
}

fun part2(input: String, regexInstruction: Regex, regex: Regex): Int {
    val matchesInstructed = regexInstruction.findAll(input)
    var doMultiplication = true
    var start = 0
    var end = 0
    var result = 0
    val lastRange = input.length
    for (match in matchesInstructed) {
        if (match.value == "do()") {
            if (!doMultiplication) {
                doMultiplication = true
                start = match.range.first
            }
        }
        if (match.value == "don't()") {
            if (doMultiplication) {
                doMultiplication = false
                end = match.range.last
                val lineWithDos = input.substring(start, end)
                result += part1(lineWithDos, regex)
            }
        }
    }
    if (start >= end) {
        end = lastRange
        val lineWithDos = input.substring(start, end)
        result += part1(lineWithDos, regex)
    }
    return result
}
