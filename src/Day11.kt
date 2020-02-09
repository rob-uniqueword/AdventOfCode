import input.readInputFileAsIntcode
import utilities.HullPaintingRobot

fun day11() {
    val input = readInputFileAsIntcode( "day11_input.txt")
    print( HullPaintingRobot(input).run() )
}