import map.Board
import map.Position
import view.InputViewInterface
import view.OutputViewInterface

class MinesWeeperGame(private val board: Board, private val inputView: InputViewInterface, private val outputView: OutputViewInterface) {

    fun startGame() {
        while (true) {
            // input
            val positionStr: String = inputView.inputSelect()

            // 유효성 검사.
            val positionList = positionStr.split(',')
                .map { it.trim() }
                .filter { it.isNotBlank() }
            val position = Position(positionList[0].toInt(), positionList[1].toInt())

            board.open(position)

            // output
            outputView.drawBoard(board)
        }
    }

}
