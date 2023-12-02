package minesweeper

interface GameBoard {

     fun render(mines: Mines): RenderedGameBoard

     companion object {
         const val MINE = '*'
     }
}
