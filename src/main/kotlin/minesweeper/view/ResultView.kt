package minesweeper.view

object ResultView {

    fun start(map: Array<Array<String>>) {
        println("지뢰 찾기 게임 시작")
        map.forEach { row ->
            row.forEach { cols ->
                print("$cols ")
            }
            println()
        }
    }
}
