import input.readInputFile
import input.toIntcode
import utilities.HullPaintingRobot

fun day11() {
    val input = readInputFile( "day11_input.txt").toIntcode()

    val robot = HullPaintingRobot( input )
    println( robot.run() )
    println( robot.paintingAsString() )
}