package controller

import domain.BoardMaker
import domain.GameSettingInfo
import view.InputView
import view.OutputView

/**
 * 지뢰찾기를 진행하는 컨트롤러
 * Created by Jaesungchi on 2022.06.28..
 */
object MineController {
    fun startMineSweeper() {
        val settingInfo = GameSettingInfo(
            InputView.getHeight(),
            InputView.getWidth(),
            InputView.getMineCount()
        )
        val board = BoardMaker(settingInfo).makeBoard()
        OutputView.printBoard(board, settingInfo)
    }
}
