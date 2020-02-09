package utilities

import java.lang.IllegalArgumentException
import java.lang.Math.toDegrees
import kotlin.math.absoluteValue
import kotlin.math.atan2

class AsteroidMap(map: List<List<Char>>) {
    private val asteroids: Set<Asteroid> = run {
        val list = mutableSetOf<Asteroid>()
        map.forEachIndexed { y, l -> l.forEachIndexed { x, c -> if (c != '.') list.add(Asteroid(x, y)) } }
        list
    }

    private val viewLines: Map<Asteroid, Map<Asteroid, Ray>> = run {
        val lineMap = mutableMapOf<Asteroid, MutableMap<Asteroid, Ray>>()

        asteroids.forEach { a1 ->
            asteroids.forEach { a2 ->
                if (a2 != a1) lineMap.getOrPut(a1, { mutableMapOf() })[a2] = Ray(a1, a2)
            }
        }

        lineMap
    }

    fun getAsteroidWithHighestViewCount(): Pair<Asteroid, Int>? = viewLines
        .map { e -> Pair(e.key, e.value.entries.groupBy { v -> v.value }.size) }
        .maxBy { e -> e.second }

    fun getDestroyedOrder(base: Asteroid): List<Asteroid> {
        val baseViewLines = viewLines[base]?.toMutableMap() ?: error("No view lines found for asteroid $base")

        return asteroids.filter { i -> i != base }
            .sortedWith(compareBy({ obscuredByCount(base, it) }, { baseViewLines[it]!!.degreesFromVertical }))
    }

    private fun obscuredByCount(base: Asteroid, other: Asteroid): Int {
        val baseViewLines = viewLines[base]?.toMutableMap() ?: error("No view lines found for asteroid $base")

        return baseViewLines.entries.count { e ->
            e.key != other
                    && squareDistance(base, e.key) < squareDistance(base, other)
                    && e.value == baseViewLines[other]
        }
    }

    private fun squareDistance(a1: Asteroid, a2: Asteroid): Int {
        val deltaX = a2.x - a1.x
        val deltaY = a2.y - a1.y
        return deltaX * deltaX + deltaY * deltaY
    }
}

class Asteroid(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Asteroid

        return other.x == x && other.y == y
    }

    override fun hashCode(): Int = 31 * x + y

    override fun toString(): String = "($x,$y)"
}

class Ray(val source: Asteroid, private val target: Asteroid) {
    init {
        if (source == target) {
            throw IllegalArgumentException("source and target are identical")
        }
    }

    private val deltaX = target.x - source.x
    private val deltaY = target.y - source.y

    val degreesFromVertical: Double =
        if (deltaX >= 0 && deltaY < 0) toDegrees(atan2(deltaX.toAbsDouble(), deltaY.toAbsDouble()))
        else if (deltaX > 0 && deltaY >= 0) 90 + toDegrees(atan2(deltaY.toAbsDouble(), deltaX.toAbsDouble()))
        else if (deltaX <= 0 && deltaY > 0) 180 + toDegrees(atan2(deltaX.toAbsDouble(), deltaY.toAbsDouble()))
        else 270 + toDegrees(atan2(deltaY.toAbsDouble(), deltaX.toAbsDouble()))

    private fun Int.toAbsDouble() = this.toDouble().absoluteValue

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Ray

        if (other.source != source) return false

        // Checking slope equality without division: (a/b) = (x/y) <=> (ay/by) = (bx/by) <=> ay = bx
        if (deltaX * other.deltaY != other.deltaX * deltaY) return false

        // Slopes are the same. Are we pointing in the same direction?
        return (deltaX > 0 == other.deltaX > 0) && (deltaY > 0 == other.deltaY > 0)
    }

    // todo - cba figuring out a good hashcode. Maybe come back to this if it's slow
    override fun hashCode(): Int = source.hashCode()

    override fun toString(): String = "$source -> $target"
}