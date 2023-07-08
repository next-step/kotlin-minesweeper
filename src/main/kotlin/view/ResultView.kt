package view

object ResultView {

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMinesweeperMap(mineMap: List<MutableList<Char>>) {
        mineMap.forEach { row ->
            row.forEach { columns ->
                print("$columns ")
            }
            println()
        }
    }
}