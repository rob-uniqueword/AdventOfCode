import input.readInputFile
import input.toOrbitMap
import utilities.OrbitMap

fun day6()
{
    val input = readInputFile( "day6_input.txt" ).toOrbitMap()

    val orbitMap = OrbitMap( input )

    println( orbitMap.getOrbitCount() )
    println( orbitMap.getDistanceBetweenOrbitees( "YOU", "SAN" ) )
}