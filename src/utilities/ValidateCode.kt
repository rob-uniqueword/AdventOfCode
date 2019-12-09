package utilities

fun isValidCode( code:String ) : Boolean
{
    val digits = code.toCharArray()

    if ( digits.size != 6 )
        return false

    var containsDouble = false
    var matchCount = 0

    for ( i:Int in 0..4 )
    {
        if ( code[ i ] > code[ i + 1 ] )
            return false

        if ( code[ i ] == code[ i + 1 ] ) {
            matchCount++
        }
        else
        {
            if ( matchCount == 1 )
                containsDouble = true

            matchCount = 0
        }
    }

    return containsDouble || matchCount == 1
}