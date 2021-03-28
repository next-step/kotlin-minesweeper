package minesweeper.domain

internal data class BoardSpec(val width: NaturalNumber, val height: NaturalNumber, val mineCount: NaturalNumber) {

    init {
        require(mineCount <= width * height) {
            "invalid mine count"
        }
    }
}
