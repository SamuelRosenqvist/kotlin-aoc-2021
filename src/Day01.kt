
fun main() {
    fun part1(input: List<Int>): Int {
        var larger = 0
        for (index in input.indices.drop(1)) {
            if (input[index] > input[index - 1]) {
                larger += 1
            }
        }
        return larger
    }

    fun part2(input: List<Int>): Int {
        var larger = 0
        val inputW = input.windowed(3)
        for (index in inputW.indices.drop(1)) {
            if (inputW[index].sum() > inputW[index - 1].sum()) {
                larger += 1
            }
        }
        return larger
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
