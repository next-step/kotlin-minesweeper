package minsweeper.domain

class Board(params: BoardParam) {

    val boardLines: BoardLines = BoardLinesGenerator.generate(params.height, params.width, params.mineCount)

}
