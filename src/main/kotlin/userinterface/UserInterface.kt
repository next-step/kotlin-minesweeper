package userinterface

import dto.MineSweeperInitDto

interface UserInterface {
    fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto
    fun inputCheckCoordinate(): Pair<Int, Int>
}
