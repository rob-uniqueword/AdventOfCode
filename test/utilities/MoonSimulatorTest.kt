package utilities

import input.toPoints
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MoonSimulatorTest {

    @Test
    fun processTest() {
        var simulator = MoonSimulator( ( "<x=-1, y=0, z=2>\n" +
                                         "<x=2, y=-10, z=-7>\n" +
                                         "<x=4, y=-8, z=8>\n" +
                                         "<x=3, y=5, z=-1>" ).toPoints() )
        
        simulator.process( 10 )
        assertEquals( 179, simulator.totalPotentialEnergy() )

        simulator = MoonSimulator( ( "<x=-8, y=-10, z=0>\n" +
                                     "<x=5, y=5, z=10>\n" +
                                     "<x=2, y=-7, z=3>\n" +
                                     "<x=9, y=-8, z=-3>" ).toPoints() )

        simulator.process( 100 )
        assertEquals( 1940, simulator.totalPotentialEnergy() )
    }
}