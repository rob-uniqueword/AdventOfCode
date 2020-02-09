package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AsteroidMapTest {

    @Test
    fun getAsteroidWithHighestViewCountTest() {
        doHighestViewCountTest(
            (".#..#\n" +
                    ".....\n" +
                    "#####\n" +
                    "....#\n" +
                    "...##").toCharacterGrid(),
            Asteroid(3, 4), 8
        )

        doHighestViewCountTest(
            ("......#.#.\n" +
                    "#..#.#....\n" +
                    "..#######.\n" +
                    ".#.#.###..\n" +
                    ".#..#.....\n" +
                    "..#....#.#\n" +
                    "#..#....#.\n" +
                    ".##.#..###\n" +
                    "##...#..#.\n" +
                    ".#....####").toCharacterGrid(),
            Asteroid(5, 8), 33
        )

        doHighestViewCountTest(
            ("#.#...#.#.\n" +
                    ".###....#.\n" +
                    ".#....#...\n" +
                    "##.#.#.#.#\n" +
                    "....#.#.#.\n" +
                    ".##..###.#\n" +
                    "..#...##..\n" +
                    "..##....##\n" +
                    "......#...\n" +
                    ".####.###.").toCharacterGrid(),
            Asteroid(1, 2), 35
        )

        doHighestViewCountTest(
            (".#..#..###\n" +
                    "####.###.#\n" +
                    "....###.#.\n" +
                    "..###.##.#\n" +
                    "##.##.#.#.\n" +
                    "....###..#\n" +
                    "..#.#..#.#\n" +
                    "#..#.#.###\n" +
                    ".##...##.#\n" +
                    ".....#.#..").toCharacterGrid(),
            Asteroid(6, 3), 41
        )

        doHighestViewCountTest(
            (".#..##.###...#######\n" +
                    "##.############..##.\n" +
                    ".#.######.########.#\n" +
                    ".###.#######.####.#.\n" +
                    "#####.##.#.##.###.##\n" +
                    "..#####..#.#########\n" +
                    "####################\n" +
                    "#.####....###.#.#.##\n" +
                    "##.#################\n" +
                    "#####.##.###..####..\n" +
                    "..######..##.#######\n" +
                    "####.##.####...##..#\n" +
                    ".#####..#.######.###\n" +
                    "##...#.##########...\n" +
                    "#.##########.#######\n" +
                    ".####.#.###.###.#.##\n" +
                    "....##.##.###..#####\n" +
                    ".#.#.###########.###\n" +
                    "#.#.#.#####.####.###\n" +
                    "###.##.####.##.#..##").toCharacterGrid(),
            Asteroid(11, 13), 210
        )
    }

    @Test
    fun getDestroyedOrderTest() {
        val map = AsteroidMap(
            (".#..##.###...#######\n" +
                    "##.############..##.\n" +
                    ".#.######.########.#\n" +
                    ".###.#######.####.#.\n" +
                    "#####.##.#.##.###.##\n" +
                    "..#####..#.#########\n" +
                    "####################\n" +
                    "#.####....###.#.#.##\n" +
                    "##.#################\n" +
                    "#####.##.###..####..\n" +
                    "..######..##.#######\n" +
                    "####.##.####...##..#\n" +
                    ".#####..#.######.###\n" +
                    "##...#.####X#####...\n" +
                    "#.##########.#######\n" +
                    ".####.#.###.###.#.##\n" +
                    "....##.##.###..#####\n" +
                    ".#.#.###########.###\n" +
                    "#.#.#.#####.####.###\n" +
                    "###.##.####.##.#..##").toCharacterGrid()
        )

        val destroyedOrder = map.getDestroyedOrder(Asteroid(11, 13))

        assertEquals(Asteroid(11, 12), destroyedOrder[0])
        assertEquals(Asteroid(12, 1), destroyedOrder[1])
        assertEquals(Asteroid(12, 2), destroyedOrder[2])
        assertEquals(Asteroid(12, 8), destroyedOrder[9])
        assertEquals(Asteroid(16, 0), destroyedOrder[19])
        assertEquals(Asteroid(16, 9), destroyedOrder[49])
        assertEquals(Asteroid(10, 16), destroyedOrder[99])
        assertEquals(Asteroid(9, 6), destroyedOrder[198])
        assertEquals(Asteroid(8, 2), destroyedOrder[199])
        assertEquals(Asteroid(10, 9), destroyedOrder[200])
        assertEquals(Asteroid(11, 1), destroyedOrder[298])
    }

    private fun doHighestViewCountTest(map: List<List<Char>>, expectedAsteroid: Asteroid, expectedCount: Int) {
        val result = AsteroidMap(map).getAsteroidWithHighestViewCount()
        assertEquals(expectedAsteroid, result?.first)
        assertEquals(expectedCount, result?.second)
    }

    private fun String.toCharacterGrid() = this.lines().map { l -> l.toList() }
}