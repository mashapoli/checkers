package lab3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void emulateGame() {
        Board b = new Board();
        b.move(2,6, 3,7);
        b = b.swap();
        b.move(2,2,3,3);
        b = b.swap();
        b.move(2,4,3,5);
        b = b.swap();
        b.capture(3,3,5,1);
        b = b.swap();
        b.capture(1,7,3,5);
        b = b.swap();
        b.move(2,6,3,7);
        b = b.swap();
        b.move(0,6,1,7);
        b = b.swap();
        b.move(2,4,3,5);
        b = b.swap();
        b.move(1,5,2,6);
        b = b.swap();
        b.move(1,5,2,4);
        b = b.swap();
        b.move(0,4,1,5);
        b = b.swap();
        b.move(2,4,3,3);
        b = b.swap();
        b.capture(3,5,5,3);
        b.capture(5,3,3,1);
        b = b.swap();
        b.move(1,3,2,2);
        b = b.swap();
        b.move(3,1,4,2);
        b = b.swap();
        b.move(2,2,3,3);
        b = b.swap();
        b.move(4,2,5,3);
        b = b.swap();
        b.capture(3,3,1,5);
        b = b.swap();
        b.move(2,2,3,3);
        b = b.swap();
        b.move(1,1,2,2);
        b = b.swap();
        b.move(3,7,4,6);
        b = b.swap();
        b.capture(2,0,4,2);
        b = b.swap();
        b.capture(2,6,4,4);
        b.capture(4,4,6,6);
        b = b.swap();
        b.capture(0,0,2,2);
        b = b.swap();
        b.move(3,3,4,4);
        b = b.swap();
        b.capture(2,2,4,4);
        b = b.swap();
        b.move(1,3,2,4);
        b = b.swap();
        b.move(1,5,2,6);
        b = b.swap();
        b.capture(2,4,4,2);
        b = b.swap();
        b.capture(2,6,4,4);
        b = b.swap();
        b.move(1,7,2,6);
        b = b.swap();
        b.move(3,7,4,6);
        b = b.swap();
        b.capture(2,0,4,2);
        b.capture(4,2,2,4);
        b = b.swap();
        b.move(0,2,1,3);
        b = b.swap();
        b.move(2,6,3,5);
        b = b.swap();
        b.move(1,3,2,4);
        b = b.swap();
        b.move(3,5,4,6);
        b = b.swap();
        b.move(0,4,1,3);
        b = b.swap();
        b.move(4,6,5,7);
        b = b.swap();
        b.move(0,6,1,5);
        b = b.swap();
        b.move(5,7,6,6);
        b = b.swap();
        b.move(2,4,3,5);
        b = b.swap();
        b.move(6,6,7,7);
        b = b.swap();
        b.move(1,3,2,2);
        b = b.swap();
        b.capture(7,7,3,3);
        b.capture(3,3,5,1);
        b.capture(5,1,7,3);
        b = b.swap();
        b.move(1,7,2,6);
        b = b.swap();
        b.capture(7,3,4,0);

        int pieceCount = 0;
        for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {
                if(b.get(rowIdx, colIdx) != Board.EMPTY_SQUARE) {
                   pieceCount++;
                }
            }
        }

        assertThat(pieceCount, is(6));
        assertThat(b.get(4, 0), is(Board.WHITE_KING_SQUARE));
        assertThat(b.get(2, 4), is(Board.WHITE_MEN_SQUARE));
        assertThat(b.get(1, 5), is(Board.WHITE_MEN_SQUARE));
        assertThat(b.get(1, 1), is(Board.WHITE_MEN_SQUARE));
        assertThat(b.get(0, 2), is(Board.WHITE_MEN_SQUARE));
        assertThat(b.get(0, 0), is(Board.WHITE_MEN_SQUARE));

        b.printBoard();
    }
}



