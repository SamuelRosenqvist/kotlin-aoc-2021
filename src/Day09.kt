import java.awt.Point

fun main() {

    fun neighbours(input: List<List<Int>>, x: Int, y: Int): List<Int> {
        val out = mutableListOf<Int>()
        listOf(Pair(-1,0), Pair(1,0), Pair(0,-1), Pair(0,1)).forEach { (xd, yd) ->
            out.add(input.getOrNull(y+yd)?.getOrNull(x+xd) ?: Int.MAX_VALUE)
        }
        return out
    }

    fun part1(input: List<List<Int>>): Int {
        var sum = 0
        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, i ->
                if (neighbours(input, x, y).none { it <= i }){
                    sum += i + 1
                }
            }
        }
        return sum
    }

    fun neighbours2(input: List<List<Int>>, x: Int, y: Int): List<Pair<Int, Int>> {
        val maxx = input[0].size
        val maxy = input.size
        val out = mutableListOf<Pair<Int, Int>>()
        listOf(Pair(-1,0), Pair(1,0), Pair(0,-1), Pair(0,1)).forEach { (xd, yd) ->
            val xd = x + xd
            val yd = y + yd
            if ( xd in 0 until maxx && yd in 0 until maxy) {
                out.add( Pair(xd, yd) )
            }
        }
        return out
    }

    fun sumBasin(
        input: List<List<Int>>,
        visited: MutableSet<Pair<Int, Int>>,
        point: Pair<Int, Int>
    ): MutableSet<Pair<Int, Int>> {
        val x = point.first
        val y = point.second

        val n = neighbours2(input, x, y)
        val visit = n.filter { !visited.contains(it) && input[y][x] < 9 }
        if (input[y][x]<9){
            visited.add(point)
        }
        visited.addAll(visit)
        if (visit.isEmpty()) {
            return visited

        }

        return visit.fold(mutableSetOf()) { a, i -> (a + sumBasin(input,visited, i)).toMutableSet() }
    }

    fun part2(input: List<List<Int>>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var basins = mutableSetOf<Set<Pair<Int, Int>>>()

        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, i ->
                if (!visited.contains(Pair(x,y))){
                    val tempn = neighbours(input, x, y)
                    if (tempn.none { it <= i }){
                        val n = sumBasin(input, mutableSetOf(), Pair(x,y))
                        basins.add(n)
                    }
                }
            }
        }
        val sums = basins.map {
            it.filter { input[it.second][it.first]<9 }
        }
        return sums.map { it.size }.sorted().takeLast(3).reduce { acc, i -> acc*i }
    }



    // test if implementation meets criteria from the description, like:
    val input = readInput("day09").map { it.toCharArray().map { it.toString().toInt() } }
    println(part1(input))
    println(part2(input))
}
