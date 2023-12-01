package minesweeper

interface GameBoard {

     fun render(mines: Mines): Array<Array<String>>

     companion object {
         const val MINE = "*"
     }
}
