package input

class ResourceFile( fileName:String )
{
    val fileText = ResourceFile::class.java.getResource( fileName ).readText()
}

fun readInputFileAsIntegerList( inputFileName:String ) : List<Int> = ResourceFile( inputFileName ).fileText.lines().map { s -> s.toInt() }