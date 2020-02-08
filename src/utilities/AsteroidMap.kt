package utilities

import java.lang.IllegalArgumentException

class AsteroidMap( map:List<List<Char>> )
{
    private val asteroids : Set<Asteroid> = run {
        val list = mutableSetOf<Asteroid>()
        map.forEachIndexed { y,l -> l.forEachIndexed { x,c -> if ( c != '.' ) list.add( Asteroid( x, y ) ) } }
        list
    }

    private val viewLines : Map<Asteroid, Set<Ray>> = run {
        val lineMap = mutableMapOf<Asteroid, MutableSet<Ray>>()

        asteroids.forEach {
                a1 -> asteroids.forEach {
                        a2 -> if (a2 != a1) lineMap.getOrPut( a1, { mutableSetOf() } ).add( Ray( a1, a2 ) )
                }
        }

        lineMap
    }

    fun getAsteroidWithHighestViewCount() : Pair<Asteroid, Int>? = viewLines.map { e -> Pair(e.key, e.value.size) }.maxBy { e -> e.second }
}

class Asteroid( val x:Int, val y:Int )
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Asteroid

        return other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }

    override fun toString(): String {
        return "($x,$y)"
    }
}

class Ray(val source:Asteroid, val target:Asteroid )
{
    init {
        if ( source == target )
        {
            throw IllegalArgumentException( "source and target are identical" )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Ray

        return other.source == source && gradientsMatch( source, target, other.target )
    }

    private fun gradientsMatch(source:Asteroid, firstTarget:Asteroid, secondTarget:Asteroid ) : Boolean
    {
        val firstDeltaX = firstTarget.x - source.x
        val firstDeltaY = firstTarget.y - source.y
        val secondDeltaX = secondTarget.x - source.x
        val secondDeltaY = secondTarget.y - source.y

        // Checking slope equality without division: (a/b) = (x/y) <=> (ay/by) = (bx/by) <=> ay = bx
        if ( firstDeltaX * secondDeltaY != secondDeltaX * firstDeltaY ) return false

        // Slopes are the same. Are we pointing in the same direction?
        return ( firstDeltaX > 0 == secondDeltaX > 0 ) && ( firstDeltaY > 0 == secondDeltaY > 0 )
    }

    override fun hashCode(): Int {
        // todo - cba figuring out a good hashcode. Maybe come back to this if it's slow
        return source.hashCode()
    }

    override fun toString(): String {
        return "$source -> $target"
    }
}