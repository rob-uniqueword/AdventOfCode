import input.readInputFile
import input.toIntegerRange
import utilities.isValidCode

fun day4()
{
    val range = readInputFile( "day4_input.txt" ).toIntegerRange()
    print( range.filter { c -> isValidCode( c.toString() ) }.count() )
}