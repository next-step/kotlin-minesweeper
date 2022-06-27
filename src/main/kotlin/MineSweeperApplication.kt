import view.Response

fun main() {

    val info = GameSettingInfo(10, 10, 10)

    val mineSweeperGameBoard = MineSweeperGameBoard(MineSweeperBoardGenerator(info))

    Response.startView(mineSweeperGameBoard.board)
}
