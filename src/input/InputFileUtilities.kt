package input

import java.math.BigInteger

class ResourceFile( fileName:String )
{
    val fileText = ResourceFile::class.java.getResource( fileName ).readText()
}

fun readInputFileAsString( inputFileName: String ) : String
        = ResourceFile( inputFileName ).fileText

fun readInputFileAsIntegerList( inputFileName:String ) : List<Int>
        = readInputFileAsString( inputFileName ).lines().map { s -> s.toInt() }

fun readInputFileAsSimpleIntcode( inputFileName:String ) : List<Int>
        = readInputFileAsString( inputFileName ).split(",").map { s -> s.toInt() }

fun readInputFileAsIntcode( inputFileName:String ) : List<BigInteger>
        = readInputFileAsString( inputFileName ).split(",").map { s -> s.toBigInteger() }

fun readInputFileAsWirePaths( inputFileName:String ) : List<List<Pair<Char,Int>>>
        = readInputFileAsString( inputFileName ).lines().map { l -> l.split( "," )
                                                        .map { s -> Pair( s[0], s.substring( 1 ).toInt() ) } }

fun readInputFileAsIntegerRange( inputFileName: String ) : IntRange
{
    val pair = readInputFileAsString( inputFileName ).split( "-" )
    return pair[0].toInt()..pair[1].toInt()
}

fun readInputFileAsOrbitMap( inputFileName: String ) : List<Pair<String,String>>
        = readInputFileAsString( inputFileName ).lines().map { l -> Pair( l.substringBefore( ")" ), l.substringAfter( ")" ) ) }

fun readInputFileAsSpaceImage( inputFileName: String ) : List<Int>
        = readInputFileAsString( inputFileName ).toCharArray().map { c -> c.toString().toInt() }

fun readInputFileAsCharacterGrid( inputFileName: String ) : List<List<Char>>
        = readInputFileAsString( inputFileName ).lines().map { l -> l.toList() }