package view

import domain.Board
import domain.GameSettingInfo

/**
 * 출력을 위한 object.
 * Created by Jaesungchi on 2022.06.28..
 */
object OutputView {
    private const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"
    private const val MINE = "M "
    private const val NOT_MINE = "O "

    fun printBoard(board: Board, settingInfo: GameSettingInfo) {
        println(START_MINE_SWEEPER)
        board.grounds.forEach { (position, ground) ->
            print(if (ground.isMine) MINE else NOT_MINE)
            if (position.x == settingInfo.width - 1) println()
        }
    }
}
