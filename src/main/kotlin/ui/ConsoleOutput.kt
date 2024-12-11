package ui

import Row

object ConsoleOutput {
    fun announceGameStarted() = println("지뢰찾기 게임 시작")

    fun displayCurrentGameBoard(rows: List<Row>) {
        for (row in rows) {
            println(row.display())
        }
    }
}
