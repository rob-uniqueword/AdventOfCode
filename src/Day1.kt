import input.readInputFile
import input.toIntegerList
import utilities.calculateFuel

fun day1() {
    val masses = readInputFile( "day1_input.txt" ).toIntegerList()
    println( masses )
    println( masses.sumBy { m -> calculateFuel(m) } )
}