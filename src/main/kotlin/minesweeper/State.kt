package minesweeper

sealed interface State {
    fun toggle(position: Position): State

    fun displayBoard(): BoardDrawing

    fun isWin(): Boolean
}

class Playing(private val board: Board) : State {
    override fun toggle(position: Position): State {
        return when (board.open(position)) {
            OpenState.CONTINUE -> this
            OpenState.LOSE -> {
                GameOver(false)
            }

            OpenState.ALL_DONE -> {
                GameOver(true)
            }
        }
    }

    override fun displayBoard(): BoardDrawing {
        return board.draw()
    }

    override fun isWin(): Boolean {
        throw IllegalStateException("게임이 끝나지 않았습니다.")
    }
}

class GameOver(private val isWin: Boolean) : State {
    override fun toggle(position: Position): State {
        return this
    }

    override fun displayBoard(): BoardDrawing {
        throw IllegalStateException("게임이 끝났습니다.")
    }

    override fun isWin(): Boolean {
        return isWin
    }
}
