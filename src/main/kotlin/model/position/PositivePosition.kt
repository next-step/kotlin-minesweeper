package model.position

import model.IndexWithMax

data class PositivePosition (
    private val widthWithMax: IndexWithMax,
    private val heightWithMax: IndexWithMax
) : Position {
    override fun indexPair(): Pair<Int, Int> {
        return Pair(widthWithMax.index(), heightWithMax.index())
    }

    override fun aroundPositions(): List<Position> {
        return widthWithMax.aroundIndexesWithMax()
            .flatMap { widthWithMax ->
                heightWithMax.aroundIndexesWithMax().map { heightWithMax ->
                    PositivePosition(widthWithMax, heightWithMax)
                }
            }
            .filter {
                it != this
            }
    }
}