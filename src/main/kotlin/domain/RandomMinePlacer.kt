package domain

class RandomMinePlacer : MinePlacer {
    override fun placeMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        val nonMined = cells.nonMinedCells()
        require(nonMined.size >= mineCount) { "지뢰개수는 지뢰가 설치되지 않은 셀의 개수보다 적거나 같아야 합니다. [mineCount: $mineCount]" }
        val selectedCells = nonMined.shuffled().take(mineCount)
        return selectedCells.fold(cells) { acc, cell ->
            acc.updateCell(cell.addMine())
        }
    }
}
