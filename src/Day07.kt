
fun main() {

    fun part1(input: List<Int>): Int {
        val lengths = MutableList(input.maxOrNull()!!) { 0 }
        for (i in input) {
            lengths.mapIndexed { index, _ ->
                lengths [index] += kotlin.math.abs(i - index)
            }
        }
        return lengths.minByOrNull { it }!!
    }

    fun part2(input: List<Int>): Int {
        val lengths = MutableList(input.maxOrNull()!!) { 0 }
        for (i in input) {
            lengths.mapIndexed { index, _ ->
                val n = kotlin.math.abs(i - index)
                lengths [index] += (n * ( n + 1)) / 2
            }
        }
        return lengths.minByOrNull { it }!!
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")[0].split(',').map { it.toInt() }
    println(part1(testInput))

    val input = readInput("Day07")[0].split(',').map { it.toInt() }

    println(part1(input))
    println(part2(input))
}
