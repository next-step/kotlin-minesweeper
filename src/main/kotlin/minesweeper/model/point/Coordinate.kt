package minesweeper.model.point

data class Coordinate(
    private val vertical: Vertical,
    private val horizontal: Horizontal,
) {
    // constructor(vertical: Int, horizontal: Int) : this(Vertical(vertical), Horizontal(horizontal))
    /*
    이슈 해결 필요
    Platform declaration clash: The following declarations have the same JVM signature (<init>(II)V):
    constructor Coordinate(vertical: Int, horizontal: Int) defined in minesweeper.model.point.Coordinate
    constructor Coordinate(vertical: Vertical, horizontal: Horizontal) defined in minesweeper.model.point.Coordinate
     */
    companion object {
        fun of(vertical: Int, horizontal: Int): Coordinate {
            return Coordinate(Vertical(vertical), Horizontal(horizontal))
        }
    }
}
