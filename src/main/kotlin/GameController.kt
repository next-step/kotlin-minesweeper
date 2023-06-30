class GameController {
    fun execute() {
        val map = input()
        output(map)
    }

    private fun input(): GameMap {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()

        val map = GameMap.createEmptyMap(width, height)
        map.generateMines(mineCount)
        return map
    }

    private fun output(map: GameMap) {
        OutputView.printGameStart(map)
    }
}
