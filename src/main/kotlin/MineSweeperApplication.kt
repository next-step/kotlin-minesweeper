import view.Request
import view.Response

fun main() {
    val info = GameSettingInfo(
        Request.requestInputHeight(),
        Request.requestInputWidth(),
        Request.requestInputMineCount(),
    )

    val mineSweeperGameBoard = MineSweeperGameBoard(MineSweeperBoardGenerator(info))

//    Response.startView(mineSweeperGameBoard.board)
}
