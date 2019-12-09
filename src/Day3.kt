import input.readInputFileAsWirePaths
import utilities.calculateWirePoints

fun day3() {
    val paths = readInputFileAsWirePaths( "day3_input.txt" )

    val line1 = calculateWirePoints( paths[0] )
    val line2 = calculateWirePoints( paths[1] )

    val intersections = line1.keys.intersect(line2.keys)

    println( intersections.map { i -> line1.getValue( i ) + line2.getValue( i ) }.min() )
}