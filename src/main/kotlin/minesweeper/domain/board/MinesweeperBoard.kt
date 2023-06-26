package minesweeper.domain.board

import minesweeper.domain.Block
import minesweeper.domain.Coordinate
import minesweeper.domain.flag.BlockState
import minesweeper.domain.flag.MatchState

class MinesweeperBoard(private val board: Map<Coordinate, Block>) {

    fun sortedBlocks(): List<Block> = board.values.sortedBy { it.coordinate }

    fun start(
        openCoordinateEvent: () -> Coordinate,
        currentBoardEvent: (MinesweeperBoard) -> Unit,
    ): MatchState = if (isWinGame()) {
        MatchState.WIN
    } else {
        progressGame(
            openCoordinate = openCoordinateEvent(),
            currentBoardEvent = currentBoardEvent,
            openCoordinateEvent = openCoordinateEvent,
        )
    }

    private fun progressGame(
        openCoordinate: Coordinate,
        openCoordinateEvent: () -> Coordinate,
        currentBoardEvent: (MinesweeperBoard) -> Unit,
    ): MatchState = when (openBlock(coordinate = openCoordinate)) {
        BlockState.MINE -> MatchState.LOSE
        BlockState.ZERO -> {
            openAroundBlock(coordinates = openCoordinate.getFourDirections())
            runGameEvent(currentBoardEvent = currentBoardEvent, openCoordinateEvent = openCoordinateEvent)
        }

        BlockState.ALREADY_OPEN -> start(
            openCoordinateEvent = openCoordinateEvent,
            currentBoardEvent = currentBoardEvent,
        )

        else -> runGameEvent(currentBoardEvent = currentBoardEvent, openCoordinateEvent = openCoordinateEvent)
    }

    private fun isWinGame() = board.values
        .filterNot { it.hasMine }
        .none { it.isHidden() }

    private fun openBlock(coordinate: Coordinate): BlockState {
        val board = board[coordinate]
            ?: throw IllegalStateException(
                "보드 범위에 존재하는 블록을 열어야 합니다. 입력 좌표 : ${coordinate.x}, ${coordinate.y}"
            )

        return board.open()
    }

    private fun openAroundBlock(coordinates: List<Coordinate>) {
        coordinates.mapNotNull { board[it] }
            .filter { it.open() == BlockState.ZERO }
            .takeIf { it.isNotEmpty() }
            ?.let { block ->
                openAroundBlock(
                    coordinates = block.flatMap { it.coordinate.getFourDirections() },
                )
            }
    }

    private fun runGameEvent(
        openCoordinateEvent: () -> Coordinate,
        currentBoardEvent: (MinesweeperBoard) -> Unit,
    ): MatchState {
        currentBoardEvent(this)
        return start(openCoordinateEvent = openCoordinateEvent, currentBoardEvent = currentBoardEvent)
    }
}
