import map.Board
import map.Position
import view.InputViewInterface
import view.OutputViewInterface
import java.util.LinkedList
import java.util.Queue

class MinesWeeperGame(
    private val board: Board,
    private val inputView: InputViewInterface,
    private val outputView: OutputViewInterface,
) {

    fun startGame() {
        while (true) {
            // input
            val positionStr: String = inputView.inputSelect()

            aroundOpenWithNotMine(positionStr.toPosition())

            // 주변 지뢰 탐색.

            // output
            outputView.drawBoard(board)
        }
    }

    private fun aroundOpenWithNotMine(position: Position) {
        // 첫 오픈 셀.
        val queue: Queue<Position> = LinkedList()
        queue.add(position)

        while (queue.isNotEmpty()) {
            val newPosition = queue.poll()

            board.open(newPosition)
            queue.addAll(board.getAroundPosition(newPosition))
            queue.distinct()
        }
    }

    private fun String.toPosition(): Position {
        val positionList = splitWithComma(this)
        return Position(positionList[1], positionList[0])
    }

    private fun splitWithComma(input: String): List<Int> {
        return input.split(',')
            .map { it.trim().toInt() }
    }
}
