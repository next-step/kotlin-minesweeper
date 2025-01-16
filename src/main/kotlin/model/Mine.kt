package model

object Mine {
    fun generateMines(boardHeight: BoardHeight, boardWidth: BoardWidth, mineSize: MineSize): List<Int> {
        return (0 until (boardHeight.height * boardWidth.width)).shuffled().subList(0, mineSize.value)
    }
}
