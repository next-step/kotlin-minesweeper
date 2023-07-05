package mine.sweeper.application

import mine.sweeper.application.value.GameStatus
import mine.sweeper.application.value.MineCount
import mine.sweeper.domain.Vulture
import mine.sweeper.view.dto.Fields
import mine.sweeper.view.dto.Position

class MineSweeperGame(
    private val fieldsManager: FieldsManager,
    private val vulture: Vulture,
) {
    private var totalMine: Int = 0
    fun setMine(mineCount: MineCount) {
        this.totalMine = mineCount.value
        val minePositions = vulture.findMinesPosition(mineCount)
        fieldsManager.create(minePositions)
    }

    fun open(position: Position): GameStatus {
        val field = fieldsManager.findOrNull(position) ?: return GameStatus.RE_TRY
        val status = fieldsManager.open(field)
        return determineGameStatus(status, position)
    }

    fun getResult(): Fields {
        return fieldsManager.toField()
    }

    private fun determineGameStatus(status: GameStatus, position: Position): GameStatus {
        if (status === GameStatus.ON_PROGRESS) fieldsManager.gridOpen(position)

        return when {
            status === GameStatus.GAME_OVER -> GameStatus.GAME_OVER
            isGameComplete() -> GameStatus.COMPLETE
            status === GameStatus.ON_PROGRESS -> GameStatus.ON_PROGRESS
            else -> throw IllegalStateException(status.name + " 은 지원되지 않는 상태입니다.")
        }
    }

    private fun isGameComplete(): Boolean {
        return totalMine == getResult().remainingFieldCount()
    }
}
