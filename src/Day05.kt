import java.lang.Integer.max
import java.lang.Math.abs

fun main() {
    fun getDiagram(input: List<List<List<Int>>>): MutableList<MutableList<Int>> {
        val maxX = max( input.maxOf { it[0][0] }, input.maxOf { it[1][0] })
        val maxY = max( input.maxOf { it[0][1] }, input.maxOf { it[1][1] })
        return MutableList(maxX+1){ MutableList(maxY+1) { 0 } }
    }

    fun isDiagonal(p0: List<Int>, p1: List<Int>): Boolean {
        return ( abs(p0[0]-p1[0]) == abs(p0[1]-p1[1]) )
    }

    fun downRange(x: Int,y: Int): IntProgression {
        return if (x<y) x..y else x downTo y
    }

    fun part1(input: List<List<List<Int>>>): Int {
        val diagram = getDiagram(input)

        input.forEach { (p0, p1) ->
            if (p0[0] == p1[0]) {
                for (y in downRange(p0[1],p1[1])){
                    diagram[y][p0[0]]++
                }
            } else if ( p0[1] == p1[1] ) {
                for (x in downRange(p0[0],p1[0])){
                    diagram[p0[1]][x]++
                }
            }
        }
        return diagram.flatten().count { it>1 }
    }

    fun part2(input: List<List<List<Int>>>): Int {
        val diagram = getDiagram(input)

        input.forEach { (p0, p1) ->
            if (p0[0] == p1[0]) {
                for (y in downRange(p0[1],p1[1])){
                    diagram[y][p0[0]]++
                }
            } else if ( p0[1] == p1[1] ) {
                for (x in downRange(p0[0],p1[0])){
                    diagram[p0[1]][x]++
                }
            } else if ( isDiagonal(p0,p1) ) {
                for ((x,y) in downRange(p0[0], p1[0]).zip(downRange(p0[1], p1[1]))) {
                    diagram[y][x]++
                }
            }
        }
        return diagram.flatten().count { it>1 }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test").map {
        it.split(" -> ").map { it.split(",").map { it.toInt() }  }
    }
    check(part1(testInput) == 5)

    val input = readInput("Day05").map {
        it.split(" -> ").map { it.split(",").map { it.toInt() }  }
    }
    println(part1(input))
    println(part2(input))
}
