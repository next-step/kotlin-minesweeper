package view

import domain.OpenedCell

object OutputView {
    fun notifyGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printOpenedCells(openedCells: List<OpenedCell>) {
        buildTotalRowSymbols(openedCells).forEach(::println)
    }

    /**
     * 전체 행을 문자열로 만들어 리스트로 반환.
     */
    private fun buildTotalRowSymbols(openedCells: List<OpenedCell>): List<String> {
        val height = openedCells.maxOf { it.position.row }

        return (1..height).map { rowIndex ->
            buildRowSymbol(openedCells, rowIndex)
        }
    }

    /**
     * 한 행을 구성하는 문자열 생성.
     */
    private fun buildRowSymbol(
        openedCells: List<OpenedCell>,
        rowIndex: Int,
    ): String {
        val width = openedCells.maxOf { it.position.column }

        return (1..width).joinToString(" ") { colIndex ->
            openedCells.filter { it.position.row == rowIndex && it.position.column == colIndex }
                .joinToString(" ") { it.symbol }
        }
    }
}
