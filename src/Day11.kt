import input.readInputFileAsIntcode
import utilities.HullPaintingRobot

fun day11() {
    val input = readInputFileAsIntcode( "day11_input.txt")

    val robot = HullPaintingRobot( input )
    println( robot.run() )
    println( robot.paintingAsString() )
}