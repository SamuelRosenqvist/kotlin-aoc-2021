import java.lang.Integer.divideUnsigned
import java.lang.Integer.max
import java.lang.Math.E
import java.lang.Math.abs
import java.math.BigInteger
import kotlin.math.ln
import kotlin.math.pow

fun main() {
    fun advance(fish: MutableList<Int>): MutableList<Int> {
        val newFish = MutableList(fish.count { it == 0 }) { 8 }
        fish.forEachIndexed { index, age ->
            if (age == 0) {
                fish[index] = 6
            } else {
                fish[index]--
            }
        }
        return fish.also { it.addAll(newFish) }
    }

    fun part1(input: MutableList<Int>, days: Int): Int {
        var fish = input
        for (i in 1..days){
            fish = advance(fish)
        }
        return fish.count()
    }

    fun part2(input: MutableList<Int>, days: Int): BigInteger {
        val fishCount = MutableList(9) {BigInteger.valueOf(0)}
        input.forEach { fishCount[it]++ }
        for ( day in 1..days ){
            val newFish = fishCount[0]
            for (i in 1..8) {
                fishCount[i-1] = fishCount[i]
            }
            fishCount[8] = newFish
            fishCount[6] += newFish
        }
        return fishCount.fold(BigInteger.ZERO) {x,y -> x+y}
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")[0].split(',').map { it.toInt() }
    println(part1(testInput.toMutableList(), 18))

    val input = readInput("Day06")[0].split(',').map { it.toInt() }
    println(part2(input.toMutableList(), 256))
}
