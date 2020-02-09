package utilities

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Math.floorMod
import java.math.BigInteger
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class HullPaintingRobot( program:List<BigInteger> )
{
    private var currentSquare = Point(0,0)
    private var currentDirection = CompassDirection.NORTH
    private val canvas = mutableMapOf( Pair(currentSquare, Colour.BLACK) )

    private val paintedSquares = mutableSetOf<Point>()

    private var inputQueue = LinkedBlockingQueue<BigInteger>()
    private var outputQueue = LinkedBlockingQueue<BigInteger>()

    private val computer = IntcodeComputer( program,
        { inputQueue.take(); },
        { out:BigInteger -> outputQueue.add( out ) } )

    private fun writeInput() {
        inputQueue.add( canvas[currentSquare]!!.ordinal.toBigInteger() )
    }

    private fun processOutput() {
        while ( true ) {
            canvas[currentSquare] = Colour.values()[outputQueue.take().toInt()]
            paintedSquares.add( currentSquare )

            val directionInput = outputQueue.take().toInt()
            currentDirection = when (directionInput) {
                0 -> CompassDirection.values()[floorMod(currentDirection.ordinal - 1, 4)]
                1 -> CompassDirection.values()[floorMod(currentDirection.ordinal + 1, 4)]
                else -> throw InputMismatchException("Computer output unexpected turn instruction $directionInput")
            }

            currentSquare = currentSquare.move(currentDirection)
            canvas.putIfAbsent(currentSquare, Colour.BLACK)

            writeInput()
        }
    }

    fun run() : Int
    {
        writeInput()

        GlobalScope.launch { processOutput() }
        computer.start()

        return paintedSquares.size
    }
}

private enum class Colour {
    BLACK, WHITE
}

private enum class CompassDirection(val x:Int, val y:Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0,-1),
    WEST(-1,0)
}

private class Point(val x:Int, val y:Int)
{
    fun move( compassDirection:CompassDirection ) : Point = Point( x + compassDirection.x, y + compassDirection.y )

    override fun equals(other: Any?): Boolean {
        if ( this === other ) return true
        if ( javaClass != other?.javaClass ) return false

        other as Point
        return other.x == x && other.y == y
    }

    override fun hashCode(): Int = 31 * x + y

    override fun toString(): String = "($x,$y)"
}