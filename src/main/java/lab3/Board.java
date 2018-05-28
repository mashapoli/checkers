package lab3;

public final class Board {

    public static final int EMPTY_SQUARE = 0;
    public static final int WHITE_MEN_SQUARE = 1;
    public static final int BLACK_MEN_SQUARE = -1;
    public static final int WHITE_KING_SQUARE = 2;
    public static final int BLACK_KING_SQUARE = -2;

    // 0,0 - левый нижний; 0,7 - правый нижний; 7,0 - левый верхний
    // белые внизу
    int[][] squares;

    public Board() {
        this(new int[8][8]);
        init();
    }

    public Board(int[][] squares) {
        this.squares = squares;
    }

    void init() {

        for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {

                if (rowIdx == 0 || rowIdx == 2) {
                    if (colIdx % 2 == 0) {
                        squares[rowIdx][colIdx] = WHITE_MEN_SQUARE;
                    }
                }
                if (rowIdx == 1 && colIdx % 2 == 1) {
                    squares[rowIdx][colIdx] = WHITE_MEN_SQUARE;
                }
                if (rowIdx == 5 || rowIdx == 7) {
                    if (colIdx % 2 == 1) {
                        squares[rowIdx][colIdx] = BLACK_MEN_SQUARE;
                    }
                }
                if (rowIdx == 6 && colIdx % 2 == 0) {
                    squares[rowIdx][colIdx] = BLACK_MEN_SQUARE;
                }
            }
        }
    }

    public Board swap() {

        int newSquares[][] = new int[8][8];

        for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {
                newSquares[7 - rowIdx][7 - colIdx] = squares[rowIdx][colIdx] * -1;
            }
        }
        System.out.println("Board swap");
        return new Board(newSquares);
    }


    public int get(int rowIdx, int colIdx) {
        return squares[rowIdx][colIdx];
    }

    public void set(int rowIdx, int colIdx, int value) {
        squares[rowIdx][colIdx] = value;
    }

    public boolean isBoard(int rowIdx, int colIdx) {
        return !(rowIdx < 0 || rowIdx > 7 || colIdx < 0 || colIdx > 7);
    }

    // только для белых
    public boolean isPiece(int rowIdx, int colIdx) {
        return isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) > 0;
    }

    public boolean isEmpty(int rowIdx, int colIdx) {
        return isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) == 0;
    }

    public boolean isBlack(int rowIdx, int colIdx) {
        return isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) < 0;
    }

    public boolean isWhite(int rowIdx, int colIdx) {
        return isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) > 0;
    }

    public boolean isMenCapture(int rowIdx, int colIdx) {
        if (isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) == Board.WHITE_MEN_SQUARE) {
            if (isBoard(rowIdx + 1, colIdx + 1) && get(rowIdx + 1, colIdx + 1) < 0
                    && isBoard(rowIdx + 2, colIdx + 2) && get(rowIdx + 2, colIdx + 2) == 0
                    || isBoard(rowIdx + 1, colIdx - 1) && get(rowIdx + 1, colIdx - 1) < 0
                    && isBoard(rowIdx + 2, colIdx - 2) && get(rowIdx + 2, colIdx - 2) == 0
                    || isBoard(rowIdx - 1, colIdx - 1) && get(rowIdx - 1, colIdx - 1) < 0
                    && isBoard(rowIdx - 2, colIdx - 2) && get(rowIdx - 2, colIdx - 2) == 0
                    || isBoard(rowIdx - 1, colIdx + 1) && get(rowIdx - 1, colIdx + 1) < 0
                    && isBoard(rowIdx - 2, colIdx + 2) && get(rowIdx - 2, colIdx + 2) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isKingCapture(int rowIdx, int colIdx) {
        if (isBoard(rowIdx, colIdx) && get(rowIdx, colIdx) == WHITE_KING_SQUARE) {
            for (int blkIdx = 1; blkIdx < 8; blkIdx++) {
                for (int stpIdx = blkIdx + 1; stpIdx < 8; stpIdx++) {


                    if (isBoard(rowIdx + blkIdx, colIdx + blkIdx) && isBlack(rowIdx + blkIdx, colIdx + blkIdx)
                            && isBoard(rowIdx + stpIdx, colIdx + stpIdx) && isEmpty(rowIdx + stpIdx, colIdx + stpIdx)) {

                        // провереям, что нет белых на диагонали
                        for (int i = 1; i < stpIdx; i++) {
                            if (isBoard(rowIdx + i, colIdx + i) && isWhite(rowIdx + i, colIdx + i)) {
                                return false;
                            }
                        }
                        return true;

                    }
                    if (isBoard(rowIdx + blkIdx, colIdx - blkIdx) && isBlack(rowIdx + blkIdx, colIdx - blkIdx)
                            && isBoard(rowIdx + stpIdx, colIdx - stpIdx) && isEmpty(rowIdx + stpIdx, colIdx - stpIdx)) {

                        // провереям, что нет белых на диагонали
                        for (int i = 1; i < stpIdx; i++) {
                            if (isBoard(rowIdx + i, colIdx - i) && isWhite(rowIdx + i, colIdx - i)) {
                                return false;
                            }
                        }
                        return true;
                    }

                    if(isBoard(rowIdx - blkIdx, colIdx - blkIdx) && isBlack(rowIdx - blkIdx, colIdx - blkIdx)
                            && isBoard(rowIdx - stpIdx, colIdx - stpIdx) && isEmpty(rowIdx - stpIdx, colIdx - stpIdx)) {

                        // провереям, что нет белых на диагонали
                        for (int i = 1; i < stpIdx; i++) {
                            if (isBoard(rowIdx - i, colIdx - i) && isWhite(rowIdx - i, colIdx - i)) {
                                return false;
                            }
                        }
                        return true;
                    }
                    if(isBoard(rowIdx - blkIdx, colIdx + blkIdx) && isBlack(rowIdx - blkIdx, colIdx + blkIdx)
                            && isBoard(rowIdx - stpIdx, colIdx + stpIdx) && isEmpty(rowIdx - stpIdx, colIdx + stpIdx)) {

                        // провереям, что нет белых на диагонали
                        for (int i = 1; i < stpIdx; i++) {
                            if (isBoard(rowIdx - i, colIdx + i) && isWhite(rowIdx - i, colIdx + i)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCapture() {
        for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {
                if (isMenCapture(rowIdx, colIdx) || isKingCapture(rowIdx, colIdx)) {
                    return true;
                }
            }
        }
        return false;
    }

    // только для белых
    public boolean move(int fromRowIdx, int fromColIdx, int toRowIdx, int toColIdx) {

        if (isBoard(fromRowIdx, fromColIdx) && get(fromRowIdx, fromColIdx) == WHITE_MEN_SQUARE
                && isBoard(toRowIdx, toColIdx) && get(toRowIdx, toColIdx) == EMPTY_SQUARE
                && Math.abs(fromRowIdx - toRowIdx) == 1 && Math.abs(fromColIdx - toColIdx) == 1
                && toRowIdx > fromRowIdx) {

            set(fromRowIdx, fromColIdx, EMPTY_SQUARE);
            if (toRowIdx < 7) {
                set(toRowIdx, toColIdx, WHITE_MEN_SQUARE);
            } else {
                set(toRowIdx, toColIdx, WHITE_KING_SQUARE);
            }
            System.out.println("move (" + fromRowIdx + "," + fromColIdx + ") to (" + toRowIdx + "," + toColIdx + ")");
            return true;
        }
        if (isBoard(fromRowIdx, fromColIdx) && get(fromRowIdx, fromColIdx) == WHITE_KING_SQUARE
                && isBoard(toRowIdx, toColIdx) && get(toRowIdx, toColIdx) == EMPTY_SQUARE) {

            int rowDiff = toRowIdx - fromRowIdx;
            int colDiff = toColIdx - fromColIdx;


            if (Math.abs(rowDiff) == Math.abs(colDiff)) {

                int stpRowStartIdx = rowDiff > 0 ? 1 : -1;
                int stpRowInc = rowDiff > 0 ? 1 : -1;

                int stpColStartIdx = colDiff > 0 ? 1 : -1;
                int stpColInc = colDiff > 0 ? 1 : -1;

                for (int stpRowIdx = fromRowIdx + stpRowStartIdx; stpRowIdx != fromRowIdx + rowDiff; stpRowIdx += stpRowInc) {
                    for (int stpColIdx = fromColIdx + stpColStartIdx; stpColIdx != fromColIdx + colDiff; stpColIdx += stpColInc) {
                        if (!(isBoard(stpRowIdx, stpColIdx) && get(stpRowIdx, stpColIdx) == EMPTY_SQUARE)) {
                            return false;
                        }
                    }
                }

                set(fromRowIdx, fromColIdx, EMPTY_SQUARE);
                set(toRowIdx, toColIdx, WHITE_KING_SQUARE);
                System.out.println("move (" + fromRowIdx + "," + fromColIdx + ") to (" + toRowIdx + "," + toColIdx + ")");
                return true;
            }
        }
        return false;
    }

    // только для белых
    public boolean capture(int fromRowIdx, int fromColIdx, int toRowIdx, int toColIdx) {

        if (isMenCapture(fromRowIdx, fromColIdx)
                && isBlack((fromRowIdx + toRowIdx) / 2, (fromColIdx + toColIdx) / 2)
                && isEmpty(toRowIdx, toColIdx)) {

            set(fromRowIdx, fromColIdx, EMPTY_SQUARE);
            set((fromRowIdx + toRowIdx) / 2, (fromColIdx + toColIdx) / 2, EMPTY_SQUARE);
            if (toRowIdx < 7) {
                set(toRowIdx, toColIdx, WHITE_MEN_SQUARE);
            } else {
                set(toRowIdx, toColIdx, WHITE_KING_SQUARE);
            }
            System.out.println("capture (" + fromRowIdx + "," + fromColIdx + ") to (" + toRowIdx + "," + toColIdx + ")");
            return true;
        }

        if (isKingCapture(fromRowIdx, fromColIdx)
                && isEmpty(toRowIdx, toColIdx)) {

            int rowDiff = toRowIdx - fromRowIdx;
            int colDiff = toColIdx - fromColIdx;


            if (Math.abs(rowDiff) == Math.abs(colDiff)) {

                int stpRowStartIdx = rowDiff > 0 ? 1 : -1;
                int stpRowInc = rowDiff > 0 ? 1 : -1;

                int stpColStartIdx = colDiff > 0 ? 1 : -1;
                int stpColInc = colDiff > 0 ? 1 : -1;

                int blackCount = 0;
                Integer blackRowIdx = null, blackColIdx = null;
                for (int stpRowIdx = fromRowIdx + stpRowStartIdx; stpRowIdx != fromRowIdx + rowDiff; stpRowIdx += stpRowInc) {
                    for (int stpColIdx = fromColIdx + stpColStartIdx; stpColIdx != fromColIdx + colDiff; stpColIdx += stpColInc) {
                        if (isBlack(stpRowIdx, stpColIdx)) {
                            blackRowIdx = stpRowIdx;
                            blackColIdx = stpColIdx;
                            blackCount++;
                        }
                        if(isWhite(stpRowIdx, stpColIdx)) {
                            return false;
                        }
                    }
                }

                if (blackCount != 1) {
                    return false;
                }

                set(blackRowIdx, blackColIdx, EMPTY_SQUARE);
                set(fromRowIdx, fromColIdx, EMPTY_SQUARE);
                set(toRowIdx, toColIdx, WHITE_KING_SQUARE);
                System.out.println("capture (" + fromRowIdx + "," + fromColIdx + ") to (" + toRowIdx + "," + toColIdx + ")");
                return true;
            }
        }
        return false;
    }

    public void printBoard() {

        for (int rowIdx = 7; rowIdx >= 0; rowIdx--) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {
                String s;
                switch (squares[rowIdx][colIdx]) {
                    case Board.EMPTY_SQUARE:
                        s = " ";
                        break;
                    case Board.BLACK_MEN_SQUARE:
                        s = "B";
                        break;
                    case Board.BLACK_KING_SQUARE:
                        s = "BK";
                        break;
                    case Board.WHITE_MEN_SQUARE:
                        s = "W";
                        break;
                    case Board.WHITE_KING_SQUARE:
                        s = "WK";
                        break;
                    default:
                        throw new IllegalStateException();
                }
                System.out.print(s);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
