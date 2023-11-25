package domain

class Row(private val columns: List<Coordinate>) {
    init {
        require(columns.size >= MIN_SIZE) { "행에는 최소 $MIN_SIZE 이상의 좌표가 포함되어야 합니다. " }
    }

    constructor(vararg coordinates: Coordinate) : this(coordinates.toList())

    companion object {
        private const val MIN_SIZE = 1
    }
}
