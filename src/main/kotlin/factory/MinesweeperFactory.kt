package factory

import enums.MineType
import service.MinesweeperService
import service.impl.BasicMinesweeperService


class MinesweeperFactory {

    private val basicMinesweeperService = BasicMinesweeperService()

    fun getType(type: MineType?): MinesweeperService {
        val minesweeperService: MinesweeperService = when (type) {
            MineType.BASIC -> basicMinesweeperService
            else -> throw IllegalArgumentException()
        }
        return minesweeperService
    }
}