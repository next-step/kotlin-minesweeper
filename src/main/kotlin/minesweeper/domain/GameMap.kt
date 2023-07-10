package minesweeper.domain

class GameMap(rectangle: Pair<Number, Number>, val count: Number) {
    private val height: Number
    private val width: Number

    val mineColumns : MineColumn

    init {
        height = rectangle.first
        width = rectangle.second
        mineColumns = MineColumn(height, width)

    }
    fun open(position: MinePosition): Boolean {
        require(position.x.value < height.value && position.y.value < width.value) {
            "입력받은 위치는 높이와 넓이보다 작거나 같아야한다"
        }
        return !mineColumns.get(position).isMine
    }

    fun generateMine(positionGenerator: MinePositionGenerator) {
        var makeCount = 0
        do {
            val isSuccess = mineColumns.setMine(positionGenerator.generatePosition())
            if(isSuccess) makeCount = makeCount.inc()
        } while(makeCount != count.value)
    }
}