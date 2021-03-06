
import org.scalatest.FunSuite



class SudokuBoardTest extends FunSuite {

  test("check 4x4") {
    val vals = List(
      "1234".toList,
      "4123".toList,
      "3412".toList,
      "2341".toList
    )
    val board = new SudokuBoard(vals)
    assert(board.isComplete)
  }

  test("solve 4x4") {
    // from http://sudopedia.enjoysudoku.com/Shi_Doku.html
    val vals = List(
      "0000".toList,
      "0001".toList,
      "0102".toList,
      "3000".toList
    )
    val board = new SudokuBoard(vals)
    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
  }

  /*
  test("solve 16 x 16") {
    // https://puzzlemadness.co.uk/16by16giantsudoku/2019/4/2
      List(
        List(5,0,0,2,0,10,3,0,0,16,0,7,0,0,0,8),
        List(0,14,0,8,2,0,0,0,3,6,0,0),
        List(0,0,0,12,0,0,7,0,0,0,0,12),
        List(0,0,1,0,4,6,0,0,0,6,0,15),
        List(0,0,0,0,0,0,0,12,0,3,0,0),
        List(2,0,0,10,0,11,0,0,12,0,0,0),
        List(0,0,13,6,0,0,13,0,1,0,0,0),
        List(0,0,0,0,14,9,0,16,0,9,0,0),
        List(0,0,0,0,16,5,6,0,12,8,0,0),
        List(8,12,0,0,0,7,13,0,2,0,9,0),
        List(0,1,3,0,0,11,16,9,5,0,0,1),
        List(0,4,0,0,0,0,0,0,3,0,0,0),
        List(6,0,0,0,0,0,0,0,0,0,14,15)
        ,0,0,0,7,0,12,8,15,0,0,3,10,11,0,4,0,0,0,8,12,14,0,0,0,0,0,11,0,0,0,0,0,0,7,0,0,0,0,16,0,4,15,0,5,13,0,0,0,0,0,9,4,10,13,0,0,0,0,0,0,7,16,0,0,0,10,2,6,0,0,0,5,0,0,0,0,0,0,0,0,0,13,14,0,0,1,0,0,0,7,8,11,0,10,9,2],
    [0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15],

      "0001".toList,
      "0102".toList,
      "3000".toList
    )
    val board = new SudokuBoard(vals)
    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
  }
  */

  test("set is unique") {
    val vals = List(
      "473598126".toList,
      "561432879".toList,
      "892167354".toList,
      "647385291".toList,
      "935216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974512".toList
    )
    val board1 = new SudokuBoard(vals)
    val board2 = new SudokuBoard(vals)
    val set = Set(board1, board2)
    assert(set.size == 1)
  }

  test("complete board") {
    // based on https://kjell.haxx.se/sudoku/
    val board = new SudokuBoard(List(
      "473598126".toList,
      "561432879".toList,
      "892167354".toList,
      "647385291".toList,
      "935216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974512".toList
    ))
    assert(board.isComplete)
    assert(board.isCorrect)
  }

  test("incomplete board") {
    val board = new SudokuBoard(List(
      "473598126".toList,
      "501432079".toList,
      "892167354".toList,
      "647385291".toList,
      "930216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974510".toList
    ))
    assert(!board.isComplete)
  }

  test("bad board") {
    val board = new SudokuBoard(List(
      "173598126".toList,
      "561432879".toList,
      "892167354".toList,
      "647385291".toList,
      "935216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974512".toList
    ))

    assert(!board.isCorrect)
  }

  test("test set value") {
    val board = new SudokuBoard(List(
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
    ))

    val newBoard = board.setValue(0, 0, '3')
    assert(newBoard.getValue(0, 0) == '3')
    assert(board.getValue(0, 0) == '0')
  }

  test("generate candidates") {
    val board = new SudokuBoard(List(
      "473598126".toList,
      "561432879".toList,
      "892167354".toList,
      "647385291".toList,
      "935216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974512".toList
    ))
    assert(board.isCorrect)
    val newBoard = board.setValue(8, 8, '0')
    assert(board.isCorrect)
    assert(!newBoard.isComplete)
    assert(!newBoard.isCorrect)
    val candidates = newBoard.generateCandidates()
    assert(candidates.size == 1)
  }

  test("test index") {
    val board = new SudokuBoard(List(
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
      "000000000".toList,
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

  test("trivial solution") {
    val board = new SudokuBoard(List(
      "473598126".toList,
      "561432879".toList,
      "892167354".toList,
      "647385291".toList,
      "935216748".toList,
      "218749635".toList,
      "154623987".toList,
      "729851463".toList,
      "386974512".toList
    ))
    assert(board.isCorrect)
    val newBoard = board.setValue(8, 8, '0')
    val solution = new SudokuSolver().solve(newBoard)
    assert(solution.isCorrect)
  }

  test("easy solution") {
    val board = new SudokuBoard(List(
      "020043001".toList,
      "600700020".toList,
      "309000745".toList,
      "904087210".toList,
      "000500078".toList,
      "003026094".toList,
      "790431600".toList,
      "210800030".toList,
      "036005009".toList
    ))

    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
    assert(solution.isCorrect)
  }

  // GC overhead limit exceeded
  test("extreme") {
    val board = new SudokuBoard(List(
      "070036000".toList,
      "000000510".toList,
      "000009000".toList,
      "000800000".toList,
      "904000000".toList,
      "300500020".toList,
      "000000006".toList,
      "000000903".toList,
      "028000000".toList
    ))
    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
    assert(solution.isCorrect)
  }

  test("hard") {
    val board = new SudokuBoard(List(
      "700608400".toList,
      "000430200".toList,
      "001000850".toList,
      "450000038".toList,
      "000005000".toList,
      "000093005".toList,
      "600000080".toList,
      "003700091".toList,
      "095000000".toList
    ))
    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
    assert(solution.isCorrect)
  }

  test("another hard one") {
    val board = new SudokuBoard(List(
      "800000000".toList,
      "003600000".toList,
      "070090200".toList,
      "050007000".toList,
      "000045700".toList,
      "000100030".toList,
      "001000068".toList,
      "008500010".toList,
      "090000400".toList
    ))
    val solution = new SudokuSolver().solve(board)
    assert(solution.isComplete)
    assert(solution.isCorrect)
  }
}
