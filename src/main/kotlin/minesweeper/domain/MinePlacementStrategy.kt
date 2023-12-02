package minesweeper.domain

fun interface MinePlacementStrategy {
    fun placeMine(rows: List<Row>, mineCount: Int): List<Row>
}
