package view

class StdoutOutputView : OutputView {
    override fun drawMineMap(mineMap: List<List<String>>) {
        println("지뢰찾기 게임 시작")
        mineMap.forEach {
            println(it)
        }
    }
}
