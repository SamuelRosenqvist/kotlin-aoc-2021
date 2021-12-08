import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        return input.count { listOf( 2,3,4,7 ).contains( it.length ) }
    }

    fun String.containsAll(s1: String): Boolean {
        s1.toCharArray().forEach {
            if (!this.contains(it)){
                return false
            }
        }
        return true
    }

    fun String.stringEquals(s2: String): Boolean = this.containsAll(s2) && this.length == s2.length

    fun decode(line: List<String>): List<String> {
        val code = MutableList(10) {""}
        code[1] = line.filter { it.length == 2 }.firstOrNull() ?: ""
        code[7] = line.filter { it.length == 3 }.firstOrNull() ?: ""
        code[4] = line.filter { it.length == 4 }.firstOrNull() ?: ""
        code[8] = line.filter { it.length == 7 }.firstOrNull() ?: ""

        val num235 = line.filter { it.length == 5 }
        code[3] = num235.filter { it.containsAll(code[1]) }.firstOrNull() ?: ""
        val bd = code[4].filter { !code[1].contains(it) }
        code[5] = num235.filter { it.containsAll(bd) }.firstOrNull() ?: ""
        code[2] = (num235 - code[5] - code[3]).firstOrNull() ?: ""

        val num069 = line.filter { it.length == 6 }
        code[9] = num069.filter { it.containsAll(code[4]) }.firstOrNull() ?: ""
        code[6] = num069.filter { !it.containsAll(code[1]) }.firstOrNull() ?: ""
        code[0] = (num069 - code[9] - code[6]).firstOrNull() ?: ""

        return code
    }

    fun part2(input: List<List<String>>, digit: List<List<String>>): Int {
        var sum = 0
        input.forEachIndexed { index, s ->
            val line = s + digit[index]
            val code = decode(line)
            val lineDigit = digit[index]
            sum += lineDigit.foldIndexed(0) { index, acc, s ->
                acc + ( 10.0.pow(lineDigit.size - index - 1) * code.indexOfFirst { it.stringEquals(s) } ).toInt()
            }.toInt()
        }
        return sum
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test").flatMap { it.split('|')[1].split(' ').filter { it.isNotEmpty() } }
    println(part1(testInput))

    val input = readInput("Day08").flatMap { it.split('|')[1].split(' ').filter { it.isNotEmpty() } }
    println(part1(input))

    val digits = readInput("Day08").map { it.split('|')[1].split(' ').filter { it.isNotEmpty() } }
    val input2 = readInput("Day08").map { it.split('|')[0].split(' ').filter { it.isNotEmpty() } }

    println(part2(input2, digits))
}
