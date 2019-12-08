import input.readInputFileAsIntegerList
import utilities.calculateFuel

fun day1() {
    val masses = readInputFileAsIntegerList( "day1_input.txt" )
    println( masses )
    println( masses.sumBy { m -> calculateFuel(m) } )
}