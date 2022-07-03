package minesweeper.domain.cell

data class Cell(
    // TODO: 2022/07/03 x,y 좌표 값을 이용한 동작이 등장하면 한번 더 포장할까..?
    val x: CellValue,
    val y: CellValue,
    val dot: Dot,
) {
}
