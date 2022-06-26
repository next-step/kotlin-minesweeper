import view.Console
import view.GameInputView
import view.GameView

fun main() {
    val game = GameInputView(Console).initGame()

    GameView(Console).viewBoard(game.board)
}
