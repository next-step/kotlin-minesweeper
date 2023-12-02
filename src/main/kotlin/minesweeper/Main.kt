package minesweeper

fun main() {
    val mineSweeper = MineSweeper(InputView.getMineMap())
    ResultView.start()

    do {
        val clicked = InputView.getClickedPoint()
        val result = mineSweeper.click(clicked)
        when (result) {
            MineSweeper.ClickResult.CONTINUE -> {
                ResultView.showMap(mineSweeper)
            }

            MineSweeper.ClickResult.ALREADY_CLICKED -> {
                ResultView.showAlreadyClickedMessage()
            }

            MineSweeper.ClickResult.GAME_OVER -> {
                ResultView.showGameOver()
                break
            }

            MineSweeper.ClickResult.ERROR -> {
                ResultView.showError()
                break
            }
        }
    } while (!mineSweeper.isDone)

    if (mineSweeper.isDone) ResultView.showFinished()
}
