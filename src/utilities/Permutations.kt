package utilities

fun <T> List<T>.permutations() : List<List<T>>
{
    if ( this.size == 1 ) return listOf( this )

    val permutations = mutableListOf<List<T>>()
    this.forEach { i -> this.minusElement( i ).permutations().forEach { p -> permutations.add( listOf( i ).plus( p ) ) } }

    return permutations
}