/*


 */


class SudokuBoard(values: Array[Array[Char]]) {
  require(values forall (_.length == 9), "sudoku board must be 9x9")

  private val one_through_nine = Set('1', '2', '3', '4', '5', '6', '7', '8', '9')
  private val missing = '0'
  // indexes
  private val start = 0;
  private val end = 9;

  def partialVerify: Boolean = false

  def checkCorrect: Boolean = {
    for {
      i <- start until end
    } if (!checkRow(i) || !checkColumn(i) || !checkSquare(i)) return false
    true
  }

  def checkComplete: Boolean = {
    // check for NULLs
    for {
      i <- start until end
      j <- start until end
    } if (values(i)(j) == missing) return false
    true
  }

  def checkRow(index: Integer): Boolean = {
    values(index).toSet == one_through_nine;
  }

  def checkColumn(index: Integer): Boolean = {
    // would it be easier to write a transpose method?
    var columnValues = Set(' ') - ' '; // how to initialize empty set??
    for {
      i <- start until end
    } columnValues += values(i)(index)
    columnValues == one_through_nine
  }

  def checkSquare(index: Integer): Boolean = {
    /* each square is 3x3
    square indexes are
    0  1  2
    3  4  5
    6  7  8
    */

    val firstRow = 3 * (index / 3) // integer division
    val firstCol = 3 * (index % 3)

    val squareValues = (
      values(firstRow + 0).slice(firstCol, firstCol+3).toSet |
      values(firstRow + 1).slice(firstCol, firstCol+3).toSet |
      values(firstRow + 2).slice(firstCol, firstCol+3).toSet
      )
    squareValues == one_through_nine
  }


  override def toString: String = {
    "-----------" + "\n" + ""
  }

}
