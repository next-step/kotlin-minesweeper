package minesweeper

import minesweeper.entity.MapInformation
import minesweeper.entity.MineMap
import minesweeper.ui.Input
import minesweeper.ui.Result

class MineSweeper {
  fun play() {
    val mapInfo = MapInformation(Input.getHeight(), Input.getWidth(), Input.getNumberOfMine())
    Result().informPlay(MineMap().getChunkedMap(mapInfo))
  }
}