import domain.Point

interface BoardGenerator {

    fun create(): Map<Point, Square>
}
