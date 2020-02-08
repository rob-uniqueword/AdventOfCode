package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AsteroidMapTest {

    @Test
    fun getAsteroidWithHighestViewCountTest()
    {
        doTest((".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##").toCharacterGrid(),
            Asteroid(3, 4), 8)

        doTest(("......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####").toCharacterGrid(),
            Asteroid(5,8), 33)

        doTest(("#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###.").toCharacterGrid(),
            Asteroid(1,2), 35
        )

        doTest((".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..").toCharacterGrid(),
            Asteroid(6,3), 41
        )

        doTest((".#..##.###...#######\n" +
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
            Asteroid(11,13), 210)
    }

    private fun doTest( map:List<List<Char>>, expectedAsteroid:Asteroid, expectedCount:Int )
    {
        val result = AsteroidMap( map ).getAsteroidWithHighestViewCount()
        assertEquals( expectedAsteroid, result?.first )
        assertEquals( expectedCount, result?.second )
    }

    private fun String.toCharacterGrid() = this.lines().map { l -> l.toList() }
}