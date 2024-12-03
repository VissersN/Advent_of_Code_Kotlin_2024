fun main() {
    val regex = Regex("mul\\([0-9]+,[0-9]+\\)")
    val regexInstruction = Regex("do\\(\\)|don't\\(\\)")
    val input = readInput("Day03").toString()

//    println(part1(matches))
    println(part2(input, regexInstruction, regex))
}

fun part1(input: String, regex: Regex): Int {
    return regex.findAll(input)
        .map { match ->
            match.value.removePrefix("mul(").removeSuffix(")")
                .split(",")
                .let { (a, b) -> a.toInt() * b.toInt() }
        }
        .sum()
}

fun part2(input: String, regexInstruction: Regex, regex: Regex): Int {
    val matches = regexInstruction.findAll(input)
    var doMultiplication = true
    var start = 0
    var result = 0

    for (match in matches) {
        when (match.value) {
            "do()" -> if (!doMultiplication) {
                doMultiplication = true
                start = match.range.first
            }
            "don't()" -> if (doMultiplication) {
                doMultiplication = false
                val lineWithDos = input.substring(start, match.range.last)
                result += part1(lineWithDos, regex)
            }
        }
    }

    if (doMultiplication) {
        result += part1(input.substring(start), regex)
    }

    return result
}
