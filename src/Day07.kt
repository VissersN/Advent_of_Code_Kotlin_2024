import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigInteger

fun evaluateExpression(expression: String): Double {
    val e = ExpressionBuilder(expression).build()
    return e.evaluate()
}

fun calculatePattern(size: Int): MutableList<MutableList<Char>> {
    val resultList = mutableListOf<MutableList<Char>>()
    val totalCombinations = 1 shl size // 2^size (shifting 1 left by size bits)

    for (i in 0 until totalCombinations) {
        val binaryString = Integer.toBinaryString(i).padStart(size, '0') // Maak binaire representatie met voorloopnullen
        val charList = binaryString.map { char ->
            when (char) {
                '0' -> '+'
                '1' -> '*'
                else -> char // Dit geval zou niet voorkomen
            }
        }.toMutableList()
        resultList.add(charList)
    }
    return resultList
}

fun main() {
    val bigInput = readInput("Day07")
    val testInput = readInput("Day07_test")
    val input = bigInput
    var sumOfTestValues: BigInteger = BigInteger.ZERO



    val splittedInput = input.map { it.split(":").toList().filter { it.isNotBlank() } }
    splittedInput.forEach {
        val testValue: BigInteger = it[0].toBigInteger()
        println("Testwaarde: $testValue")
        val operators = it[1].split(" ").filter { it.isNotBlank() }.map { it.toBigInteger() }
        println("Operators: $operators")
        if (findFormula(operators, testValue) != BigInteger.ZERO) {
            sumOfTestValues += testValue
        }
        println(sumOfTestValues)

    }
}
fun findFormula(numbers: List<BigInteger>, targetResult: BigInteger): BigInteger {
    // Helper function to evaluate a formula represented as a list of operations
    fun evaluate(numbers: List<BigInteger>, operations: List<String>): BigInteger {
        val finalNumbers = mutableListOf<BigInteger>()
        var currentNumber = numbers[0]

        // Apply || concatenation first by merging numbers when || is encountered
        for (i in operations.indices) {
            if (operations[i] == "||") {
                currentNumber = BigInteger(currentNumber.toString() + numbers[i + 1].toString())
            } else {
                finalNumbers.add(currentNumber)
                currentNumber = numbers[i + 1]
            }
        }
        finalNumbers.add(currentNumber)

        // Apply + and * on the resulting numbers
        var result = finalNumbers[0]
        var opIndex = 0
        for (num in finalNumbers.drop(1)) {
            when (operations.getOrNull(opIndex)) {
                "+" -> result += num
                "*" -> result *= num
            }
            opIndex++
        }

        return result
    }

    // Recursive function to generate all possible operations
    fun generateFormulas(
        numbers: List<BigInteger>,
        operations: MutableList<String>,
        targetResult: BigInteger
    ): BigInteger? {
        if (operations.size == numbers.size - 1) {
            if (evaluate(numbers, operations) == targetResult) {
                return targetResult
            }
            return null
        }

        // Try '+' operation
        operations.add("+")
        val addResult = generateFormulas(numbers, operations, targetResult)
        if (addResult != null) return addResult
        operations.removeAt(operations.size - 1)

        // Try '*' operation
        operations.add("*")
        val multiplyResult = generateFormulas(numbers, operations, targetResult)
        if (multiplyResult != null) return multiplyResult
        operations.removeAt(operations.size - 1)

        // Try '||' operation
        operations.add("||")
        val concatResult = generateFormulas(numbers, operations, targetResult)
        if (concatResult != null) return concatResult
        operations.removeAt(operations.size - 1)

        return null
    }

    // Edge case: if the list is empty or has one number
    if (numbers.isEmpty()) return BigInteger.ZERO
    if (numbers.size == 1) return if (numbers[0] == targetResult) targetResult else BigInteger.ZERO

    // Start generating formulas
    return generateFormulas(numbers, mutableListOf(), targetResult) ?: BigInteger.ZERO
}
