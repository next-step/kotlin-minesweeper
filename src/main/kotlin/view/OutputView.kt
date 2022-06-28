package view

import constants.Messages
import constants.Messages.MINE
import constants.Messages.NOT_MINE
import domain.Board
import domain.GameSettingInfo

/**
 * 출력을 위한 object.
 * Created by Jaesungchi on 2022.06.28..
 */
object OutputView {
    fun printBoard(board: Board, settingInfo: GameSettingInfo) {
        println(Messages.START_MINE_SWEEPER)
        board.grounds.forEach { (position, ground) ->
            print(if (ground.isMine) MINE else NOT_MINE)
            if (position.x == settingInfo.width - 1) println()
        }
    }
}
