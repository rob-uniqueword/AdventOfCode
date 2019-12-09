package utilities

import java.awt.Point

fun calculateWirePoints( wirePath:List<Pair<Char,Int>> ) : Map<Point,Int>
{
    val points = mutableMapOf<Point,Int>()
    val point = Point( 0, 0 )
    var length = 0

    for ( instruction:Pair<Char,Int> in wirePath )
    {
        for ( i in 1..instruction.second )
        {
            when ( instruction.first )
            {
                'R' -> point.x++
                'L' -> point.x--
                'U' -> point.y++
                'D' -> point.y--
            }

            points.putIfAbsent( point.copy(), ++length )
        }
    }

    return points
}

fun Point.copy() = Point( this.x, this.y )