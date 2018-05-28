package lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

    Board curBoard = new Board();

    boolean isWhite = true;
    Integer fromRowIdx, fromColIdx;
    String msg = "";

    public GameFrame() {
        JPanel boardPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {

                // рисуем поле
                for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
                    for (int colIdx = 0; colIdx < 8; colIdx++) {
                        int x = colIdx * 64;
                        int y = rowIdx * 64;

                        if (rowIdx % 2 == colIdx % 2) {
                            g.setColor(Color.LIGHT_GRAY);
                        } else {
                            g.setColor(Color.GRAY);
                        }

                        g.fillRect(x, y, 64, 64);
                    }
                }
                if(!isWhite) {
                    curBoard = curBoard.swap();
                }
                // рисуем фигуры
                for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
                    for (int colIdx = 0; colIdx < 8; colIdx++) {

                        int x = colIdx * 64;
                        int y = rowIdx * 64;

                        int square = curBoard.get(7 - rowIdx, colIdx);
                        if (square != Board.EMPTY_SQUARE) {
                            if (square > 0) {
                                g.setColor(Color.WHITE);
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            g.fillOval(x, y, 64, 64);

                            if (Math.abs(square) == 2) {
                                g.setColor(Color.BLUE);
                                g.drawOval(x+8, y+8, 48, 48);
                            }
                        }
                    }
                }
                if(!isWhite) {
                    curBoard = curBoard.swap();
                }
                // рисуем чей ход
                if(!isWhite) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(0, 512, 512, 64);
                g.setColor(Color.RED);
                g.drawString(msg, 256, 530);
            }
        };
        add(boardPanel);
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIdx = 7 - (e.getY() / 64);
                int colIdx = e.getX() / 64;
                action(rowIdx, colIdx);
                repaint();
            }
        });
    }

    private boolean isFromIdxsSet() {
        return fromRowIdx != null && fromColIdx != null;
    }

    private void unsetFromIdxs() {
        fromRowIdx = null;
        fromColIdx = null;
    }

    private void setFromIdxs(int fromRowIdx, int fromColIdx) {
        this.fromRowIdx = fromRowIdx;
        this.fromColIdx = fromColIdx;
    }

    private void action(int rowIdx, int colIdx) {

        msg = "";

        if(!isWhite) {
            rowIdx = 7 - rowIdx;
            colIdx = 7 - colIdx;
        }

        if(curBoard.isCapture() && !isFromIdxsSet()) {
            if(curBoard.isMenCapture(rowIdx, colIdx)
                || curBoard.isKingCapture(rowIdx, colIdx)) {
                setFromIdxs(rowIdx, colIdx);
            } else {
                msg = "Ситуация \"бить обязан\"";
            }
        } else if (curBoard.isPiece(rowIdx, colIdx) && !isFromIdxsSet()) {
            setFromIdxs(rowIdx, colIdx);
        } else if (curBoard.isCapture() && isFromIdxsSet()) {
            if(curBoard.capture(fromRowIdx, fromColIdx, rowIdx, colIdx)) {
                if (curBoard.isMenCapture(rowIdx, colIdx) || curBoard.isKingCapture(rowIdx, colIdx)) {
                    setFromIdxs(rowIdx, colIdx);
                } else {
                    unsetFromIdxs();
                    isWhite = !isWhite;
                    curBoard = curBoard.swap();
                }
                return;
            }
            msg = "Некорректный ход";
            unsetFromIdxs();
            return;
        } else if(isFromIdxsSet()) {
            if(curBoard.move(fromRowIdx, fromColIdx, rowIdx, colIdx)) {
                unsetFromIdxs();
                isWhite = !isWhite;
                curBoard = curBoard.swap();
                return;
            }
            msg = "Некорректный ход";
            unsetFromIdxs();
            return;
        }else {
            msg = "Некорректный ход";
            unsetFromIdxs();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameFrame gameFrame = new GameFrame();
                Dimension size = new Dimension(520, 570);
                gameFrame.setMinimumSize(size);
                gameFrame.setMaximumSize(size);
                gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.pack();
                gameFrame.setVisible(true);
            }
        });
    }
}
