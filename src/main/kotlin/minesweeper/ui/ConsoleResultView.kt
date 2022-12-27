package minesweeper.ui

class ConsoleResultView : ResultView {
    override fun drawBlocks(blocks: List<List<String>>) {
        println("지뢰찾기 게임 시작")
        blocks.forEach { rowBlocks ->
            println(rowBlocks.joinToString { it }.replace(",", ""))
        }
    }
}
