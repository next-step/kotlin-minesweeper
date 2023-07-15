package minesweeper.domain

class MinesWeeperGame(rectangle: Pair<NumberValue, NumberValue>, val count: NumberValue) {
    private val height: NumberValue
    private val width: NumberValue

    val minesMap: MinesMap

    init {
        height = rectangle.first
        width = rectangle.second
        minesMap = MinesMap(height, width)
    }

    private fun openMap(position: MinePosition): GameStatus {
        require(position.x.value < height.value && position.y.value < width.value) {
            "입력받은 위치는 높이와 넓이보다 작거나 같아야한다"
        }
        val tile = minesMap.get(position)
        if (tile.isMine) {
            return GameStatus.LOSE
        }

        val isFinish = minesMap.isFinishGame()

        if (isFinish) {
            return GameStatus.WIN
        }

        minesMap.openNearTiles(position)
        return GameStatus.CONTINUE
    }

    fun generateMine(positionGenerator: MinePositionGenerator) {
        var makeCount = 0
        do {
            val isSuccess = minesMap.setMine(positionGenerator.generatePosition())
            if (isSuccess) makeCount = makeCount.inc()
        } while (makeCount != count.value)
    }

    fun openTile(gameStateNotify: GameStateNotify) {
        val position = gameStateNotify.getOpenPosition()
        when (val gameResult = openMap(position)) {
            GameStatus.WIN, GameStatus.LOSE -> gameStateNotify.showGameState(gameResult)
            GameStatus.CONTINUE -> {
                gameStateNotify.showMineMapInProgress(minesMap)
                if (gameStateNotify.isContinueGame()) openTile(gameStateNotify)
            }
        }
    }
}
