package model

data class Position (
    private val widthWithMax: IndexWithMax,
    private val heightWithMax: IndexWithMax
) {
    constructor(width: Int, height: Int, max: Int) : this(width, height, max, max)

    constructor(widthHeightPair: Pair<Int, Int>, maxWidth: Int, maxHeight: Int) : this(widthHeightPair.first, widthHeightPair.second, maxWidth, maxHeight)

    constructor(width: Int, height: Int, maxWidth: Int, maxHeight: Int) : this(IndexWithMax(width, maxWidth), IndexWithMax(height, maxHeight))

    fun indexPair(): Pair<Int, Int> {
        return Pair(widthWithMax.index(), heightWithMax.index())
    }

    fun aroundPositions(): List<Position> {
        return widthWithMax.aroundIndexesWithMax()
            .flatMap { widthWithMax ->
                heightWithMax.aroundIndexesWithMax().map { heightWithMax ->
                    Position(widthWithMax, heightWithMax)
                }
            }
            .filter {
                it != this
            }
    }
}