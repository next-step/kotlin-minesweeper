package minesweeper.domain.flag

class BlockFlag(aroundMineCount: Int) : Flag(blockState = BlockState.valueOf(aroundMineCount = aroundMineCount))
