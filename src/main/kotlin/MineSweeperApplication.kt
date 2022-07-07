import domain.Point
import view.Request
import view.Response

fun main() {
    val info = GameSettingInfo(
        Request.requestInputHeight(),
        Request.requestInputWidth(),
        Request.requestInputMineCount(),
    )

    val mineSweeperGameBoard = MineSweeperGameBoard(MineSweeperBoardGenerator(info))
    val mineSetting = AroundMineSetting(mineSweeperGameBoard.board)

    var board = mineSetting.board
    while (true) {
        val aroundMineChecker = AroundMineChecker(board, bindPoint())
        if (aroundMineChecker.isFinish()) {
            Response.startView(board, info)
            Response.looseGame()
            break
        }
        board = aroundMineChecker.getBoard()
        Response.startView(board, info)
    }
}

private fun bindPoint(): Point {
    val points = Request.requestPoint()
        .split(",")
        .map { it.trim().toInt() }

    return Point(points[0], points[1])
}
