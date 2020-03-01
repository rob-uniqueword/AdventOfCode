package utilities

import input.Point3D
import kotlin.math.absoluteValue

class MoonSimulator( initialPositions:List<Point3D> )
{
    private val moons = run {
        val moons = initialPositions.map { Moon( it ) }
        moons.associateWith { m -> moons.filter { it != m } }
    }

    fun process( steps:Int )
    {
        for ( i:Int in 1..steps )
        {
            for ( entry in moons.entries )
            {
                entry.key.updateVelocity( entry.value )
            }

            for ( moon in moons.keys )
            {
                moon.updatePosition()
            }
        }
    }

    fun totalPotentialEnergy() = moons.keys.sumBy { it.totalEnergy() }
}

class Moon( private val position: Point3D )
{
    private val velocity = Point3D( 0, 0, 0 )

    fun updateVelocity( otherMoons:List<Moon> )
    {
        for ( otherMoon in otherMoons ) {
            if ( otherMoon.position.x < position.x )
                velocity.x -= 1
            else if ( otherMoon.position.x > position.x )
                velocity.x += 1

            if ( otherMoon.position.y < position.y )
                velocity.y -= 1
            else if ( otherMoon.position.y > position.y )
                velocity.y += 1

            if ( otherMoon.position.z < position.z )
                velocity.z -= 1
            else if ( otherMoon.position.z > position.z )
                velocity.z += 1
        }
    }

    fun updatePosition()
    {
        position.x += velocity.x
        position.y += velocity.y
        position.z += velocity.z
    }

    private fun potentialEnergy() = position.x.absoluteValue + position.y.absoluteValue + position.z.absoluteValue
    private fun kineticEnergy() = velocity.x.absoluteValue + velocity.y.absoluteValue + velocity.z.absoluteValue
    fun totalEnergy() = potentialEnergy() * kineticEnergy()

    override fun toString(): String {
        return "Position: (${position.x},${position.y},${position.z}) Velocity: (${velocity.x}, ${velocity.y}, ${velocity.z})"
    }
}