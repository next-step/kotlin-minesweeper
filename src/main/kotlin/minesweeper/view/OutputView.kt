package minesweeper.view

class OutputView {
    fun printGameStart() {
        println("지뢰찾기 게임을 시작합니다.")
    }

    fun printMineField(viewData: List<List<Char>>) {
        for (row in viewData) {
            println(row.joinToString(" "))
        }
    }

    fun printGameOver() {
        println("Lose Game.")
    }

    fun printGameCleared() {
        println("Win Game.")
    }
}
