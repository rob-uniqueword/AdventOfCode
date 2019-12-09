import input.readInputFileAsWirePaths
import utilities.calculateWirePoints
import kotlin.math.absoluteValue

fun day3() {
    val paths = readInputFileAsWirePaths( "day3_input.txt" )

    val points1 = calculateWirePoints( paths[0] )
    val points2 = calculateWirePoints( paths[1] )

    val intersections = points1.intersect(points2)

    println( intersections.minBy { i -> i.x.absoluteValue + i.y.absoluteValue } )
}