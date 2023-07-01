class GameController {
    fun execute() {
        val map = input()
        output(map)
    }

    private fun input(): GameMap {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()
        return MapGenerator.generate(width, height, mineCount)
    }

    private fun output(map: GameMap) {
        OutputView.printGameStart(map)
    }
}
