package utilities

import input.toOrbitMap
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OrbitMapTest {

    @Test
    fun getOrbitCountTest() {
        val orbitMap = OrbitMap( "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L".toOrbitMap() )
        assertEquals( 42, orbitMap.getOrbitCount() )
    }

    @Test
    fun getDistanceBetweenTest() {
        val orbitMap = OrbitMap( "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L\nK)YOU\nI)SAN".toOrbitMap() )
        assertEquals( 4, orbitMap.getDistanceBetweenOrbitees( "YOU", "SAN" ) )
    }
}