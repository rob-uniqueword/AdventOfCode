package input

class ResourceFile( fileName:String )
{
    val fileText = ResourceFile::class.java.getResource( fileName ).readText()
}

fun readInputFileAsIntegerList( inputFileName:String ) : List<Int>
        = ResourceFile( inputFileName ).fileText.lines().map { s -> s.toInt() }

fun readInputFileAsIntcode( inputFileName:String ) : List<Int>
        = ResourceFile( inputFileName ).fileText.split(",").map { s -> s.toInt() }

fun readInputFileAsWirePaths( inputFileName:String ) : List<List<Pair<Char,Int>>>
        = ResourceFile( inputFileName ).fileText.lines().map { l -> l.split( "," )
                                                        .map { s -> Pair( s[0], s.substring( 1 ).toInt() ) } }