package minsweeper.domain

class Board(
    private val params: BoardParam,
    boardLinesGenerator: BoardLinesGenerator = BoardLinesGenerator(),
) {
    val boardLines: BoardLines = boardLinesGenerator.generate(params)

}
