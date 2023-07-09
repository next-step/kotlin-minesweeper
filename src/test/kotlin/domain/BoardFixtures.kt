package domain

/**
 * 다음 구성으로된 board를 생성한다.
 * 0 1 2 * 2 1 1 * 1 0
 * 0 1 * 3 * 1 1 1 1 0
 * 0 1 1 2 1 1 0 0 0 0
 * 1 1 0 0 0 0 0 0 0 0
 * * 1 0 0 0 1 1 1 0 0
 * 1 2 1 1 0 2 * 2 0 0
 * 0 1 * 1 0 3 * 3 1 1
 * 0 1 1 1 0 2 * 2 1 *
 * 0 0 0 0 0 1 1 1 1 1
 * 0 0 0 0 0 0 0 0 0 0
 */
fun createTestBoard(): MineSweeperBoard {
    return TestMineSweeperBoardGenerator().generate()
}
