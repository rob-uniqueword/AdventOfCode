package utilities

private const val BLACK = 0
private const val WHITE = 1
private const val TRANS = 2

class SpaceImage( width:Int, height:Int, digits:List<Int> )
{
    private val layers = digits.chunked( width * height ).map { c -> SpaceImageLayer( width, c ) }

    fun checksum() : Int
    {
        val minLayer = layers.minBy { l -> l.count( 0 ) }!!
        return minLayer.count( 1 ) * minLayer.count( 2 )
    }

    fun print()
    {
        val combinedLayer = layers.reduce { acc, spaceImageLayer -> acc.writeOver( spaceImageLayer ) }
        combinedLayer.print()
    }
}

class SpaceImageLayer( private val width:Int, private val digits:List<Int> )
{
    private val rows = digits.chunked( width ).map { c -> SpaceImageRow( c ) }

    fun count( digit:Int ) = rows.sumBy { r -> r.count( digit ) }

    fun writeOver( otherLayer:SpaceImageLayer ) : SpaceImageLayer
    {
        val combinedDigits = mutableListOf<Int>()
        for ( i:Int in digits.indices )
        {
            combinedDigits.add( if ( digits[ i ] == TRANS ) otherLayer.digits[ i ] else digits[ i ] )
        }
        return SpaceImageLayer( width, combinedDigits )
    }

    fun print() = rows.forEach { r -> r.print() }
}

class SpaceImageRow( private val digits:List<Int> )
{
    fun count( digit:Int ) = digits.count { d -> d == digit }
    fun print() = println(digits.joinToString( "" ) { d -> if ( d == WHITE ) "#" else " " })
}