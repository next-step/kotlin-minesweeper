class MineSweeperBoardGenerator(private val gameSettingInfo: GameSettingInfo) : BoardGenerator {

    override fun create(): List<Squares> {
        val shuffledSquares = createSquares()

        val board: MutableList<Squares> = mutableListOf()

        repeat(gameSettingInfo.height) {
            val squaresLine = shuffledSquares.subList(FIRST_INDEX, gameSettingInfo.width)
            board.add(Squares(squaresLine.toList()))
            shuffledSquares.removeAll(squaresLine)
        }

        return board.toList()
    }

    private fun createSquares(): MutableList<Square> {
        val squares: MutableList<Square> = mutableListOf()
        repeat(gameSettingInfo.mineCount) {
            squares.add(Mine())
        }

        val nonMineCount = gameSettingInfo.height * gameSettingInfo.width - gameSettingInfo.mineCount
        repeat(nonMineCount) {
            squares.add(NonMine())
        }

        return squares.shuffled().toMutableList()
    }

    companion object {
        private const val FIRST_INDEX = 0
    }
}
