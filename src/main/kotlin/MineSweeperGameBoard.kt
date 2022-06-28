import domain.Point

class MineSweeperGameBoard(boardGenerator: BoardGenerator) {

    val board: Map<Point, Square> = boardGenerator.create()

}
