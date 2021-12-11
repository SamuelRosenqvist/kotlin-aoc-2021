import java.awt.Point
import java.math.BigInteger

fun main() {

    fun score(char: Char): Int =
        when (char) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }

    fun partner(char: Char): Char =
        when (char) {
            ')' -> '('
            ']' -> '['
            '}' -> '{'
            '>' -> '<'
            else -> 'ඞ'
        }

    fun checkRow(input: CharArray): Int {
        val d = mutableListOf<Char>()
        for (c in input) {
            if (c in ")]}>") {
                if (d.last() != partner(c)){
                    return score(c)
                } else {
                    d.removeLast()
                }
            } else {
                d.add(c)
            }
        }
        return 0
    }

    fun part1(input: List<CharArray>): Int {
        var score = 0
        for (row in input) {
            score += checkRow(row)
        }
        return score
    }

    fun getValue(c: Char): Int =
        when (c) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> -1
        }

    fun autoComplete(row: CharArray): BigInteger {
        val d = mutableListOf<Char>()
        for (c in row) {
            if (c in ")]}>") {
                d.removeLast()
            } else {
                d.add(c)
            }
        }
        return d.reversed().fold(BigInteger.ZERO) { a, x -> BigInteger.valueOf(5).multiply(a) + getValue(x).toBigInteger()}
    }

    fun part2(input: List<CharArray>): BigInteger {
        val scores = mutableListOf<BigInteger>()
        for (row in input) {
            if (checkRow(row)==0){
                scores.add(autoComplete(row))
            }
        }
        return scores.sorted()[scores.size/2]
    }



    // test if implementation meets criteria from the description, like:
    val input = readInput("day10").map { it.toCharArray() }
    println(part1(input))
    println(part2(input))
}
