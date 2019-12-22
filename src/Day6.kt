import input.readInputFileAsOrbitMap
import utilities.OrbitMap

fun day6()
{
    val input = readInputFileAsOrbitMap( "day6_input.txt" )

    val orbitMap = OrbitMap( input )

    println( orbitMap.getOrbitCount() )
    println( orbitMap.getDistanceBetweenOrbitees( "YOU", "SAN" ) )
}