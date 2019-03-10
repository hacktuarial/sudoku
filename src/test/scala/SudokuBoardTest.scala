
import org.scalatest.FunSuite



class SudokuBoardTest extends FunSuite {

  test("complete board") {
    // based on https://kjell.haxx.se/sudoku/
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "561432879".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "935216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974512".toCharArray
    ))
    assert(board.isComplete)
    assert(board.isCorrect)
  }

  test("incomplete board") {
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "501432079".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "930216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974510".toCharArray
    ))
    assert(!board.isComplete)
  }

  test("bad board") {
    val board = new SudokuBoard(Array(
      "173598126".toCharArray,
      "561432879".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "935216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974512".toCharArray
    ))

    assert(!board.isCorrect)
  }

  test("test set value") {
    val board = new SudokuBoard(Array(
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
    ))

    board.setValue(0, 0, '3')
    assert(board.getValue(0, 0) == '3')
  }

  test("generate candidates") {
    val board = new SudokuBoard(Array(
      "473598126".toCharArray,
      "561432879".toCharArray,
      "892167354".toCharArray,
      "647385291".toCharArray,
      "935216748".toCharArray,
      "218749635".toCharArray,
      "154623987".toCharArray,
      "729851463".toCharArray,
      "386974512".toCharArray
    ))
    assert(board.isCorrect)
    board.setValue(8, 8, '0')
    assert(!board.isComplete)
    assert(!board.isCorrect)
    val candidates = board.generateCandidates()
    assert(candidates.size == 1)
  }

  test("test index") {
    val board = new SudokuBoard(Array(
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
      "000000000".toCharArray,
    ))
    assert(board.getIndex(0, 0) == 0)
    assert(board.getIndex(0, 1) == 0)
    assert(board.getIndex(0, 2) == 0)
    assert(board.getIndex(0, 3) == 1)
    assert(board.getIndex(0, 4) == 1)
    assert(board.getIndex(0, 5) == 1)
    assert(board.getIndex(0, 6) == 2)
    assert(board.getIndex(0, 7) == 2)
    assert(board.getIndex(0, 8) == 2)
    assert(board.getIndex(1, 0) == 0)
    assert(board.getIndex(1, 1) == 0)
    assert(board.getIndex(1, 2) == 0)
    assert(board.getIndex(1, 3) == 1)
    assert(board.getIndex(1, 4) == 1)
    assert(board.getIndex(1, 5) == 1)
    assert(board.getIndex(1, 6) == 2)
    assert(board.getIndex(1, 7) == 2)
    assert(board.getIndex(1, 8) == 2)
    assert(board.getIndex(2, 0) == 0)
    assert(board.getIndex(2, 1) == 0)
    assert(board.getIndex(2, 2) == 0)
    assert(board.getIndex(2, 3) == 1)
    assert(board.getIndex(2, 4) == 1)
    assert(board.getIndex(2, 5) == 1)
    assert(board.getIndex(2, 6) == 2)
    assert(board.getIndex(2, 7) == 2)
    assert(board.getIndex(2, 8) == 2)

    assert(board.getIndex(3, 0) == 3)
    assert(board.getIndex(3, 1) == 3)
    assert(board.getIndex(3, 2) == 3)
    assert(board.getIndex(3, 3) == 4)
    assert(board.getIndex(3, 4) == 4)
    assert(board.getIndex(3, 5) == 4)
    assert(board.getIndex(3, 6) == 5)
    assert(board.getIndex(3, 7) == 5)
    assert(board.getIndex(3, 8) == 5)
    assert(board.getIndex(4, 0) == 3)
    assert(board.getIndex(4, 1) == 3)
    assert(board.getIndex(4, 2) == 3)
    assert(board.getIndex(4, 3) == 4)
    assert(board.getIndex(4, 4) == 4)
    assert(board.getIndex(4, 5) == 4)
    assert(board.getIndex(4, 6) == 5)
    assert(board.getIndex(4, 7) == 5)
    assert(board.getIndex(4, 8) == 5)
    assert(board.getIndex(5, 0) == 3)
    assert(board.getIndex(5, 1) == 3)
    assert(board.getIndex(5, 2) == 3)
    assert(board.getIndex(5, 3) == 4)
    assert(board.getIndex(5, 4) == 4)
    assert(board.getIndex(5, 5) == 4)
    assert(board.getIndex(5, 6) == 5)
    assert(board.getIndex(5, 7) == 5)
    assert(board.getIndex(5, 8) == 5)

    assert(board.getIndex(6, 0) == 6)
    assert(board.getIndex(6, 1) == 6)
    assert(board.getIndex(6, 2) == 6)
    assert(board.getIndex(6, 3) == 7)
    assert(board.getIndex(6, 4) == 7)
    assert(board.getIndex(6, 5) == 7)
    assert(board.getIndex(6, 6) == 8)
    assert(board.getIndex(6, 7) == 8)
    assert(board.getIndex(6, 8) == 8)
    assert(board.getIndex(7, 0) == 6)
    assert(board.getIndex(7, 1) == 6)
    assert(board.getIndex(7, 2) == 6)
    assert(board.getIndex(7, 3) == 7)
    assert(board.getIndex(7, 4) == 7)
    assert(board.getIndex(7, 5) == 7)
    assert(board.getIndex(7, 6) == 8)
    assert(board.getIndex(7, 7) == 8)
    assert(board.getIndex(7, 8) == 8)
    assert(board.getIndex(8, 0) == 6)
    assert(board.getIndex(8, 1) == 6)
    assert(board.getIndex(8, 2) == 6)
    assert(board.getIndex(8, 3) == 7)
    assert(board.getIndex(8, 4) == 7)
    assert(board.getIndex(8, 5) == 7)
    assert(board.getIndex(8, 6) == 8)
    assert(board.getIndex(8, 7) == 8)
    assert(board.getIndex(8, 8) == 8)
  }

}
