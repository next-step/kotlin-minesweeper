package minesweeper.domain

class MinesWeeperGame(rectangle: Pair<Number, Number>, val count: Number) {
    private val height: Number
    private val width: Number

    val minesMap : MinesMap

    init {
        height = rectangle.first
        width = rectangle.second
        minesMap = MinesMap(height, width)

    }
    fun open(position: MinePosition): GameStatus {
        require(position.x.value < height.value && position.y.value < width.value) {
            "입력받은 위치는 높이와 넓이보다 작거나 같아야한다"
        }
        val isSuccess = minesMap.open(position)
        if(!isSuccess) {
            return GameStatus.LOSE
        }

        val isFinish = minesMap.isFinishGame()

        if(isFinish) {
            return GameStatus.WIN
        }

        return GameStatus.CONTINUE
    }

    fun generateMine(positionGenerator: MinePositionGenerator) {
        var makeCount = 0
        do {
            val isSuccess = minesMap.setMine(positionGenerator.generatePosition())
            if(isSuccess) makeCount = makeCount.inc()
        } while(makeCount != count.value)
    }
}