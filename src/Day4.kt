import input.readInputFileAsIntegerRange
import utilities.isValidCode

fun day4()
{
    val range = readInputFileAsIntegerRange( "day4_input.txt" )
    print( range.filter { c -> isValidCode( c.toString() ) }.count() )
}