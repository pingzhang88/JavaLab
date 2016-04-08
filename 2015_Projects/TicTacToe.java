import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A TicTacToe Board built with JFrame.
 *
 * @author Ping Zhang Dec. 2015
 */
public class TicTacToe extends JFrame {

    private final String NO_WINNER = "No Winner";

    private String currentPlayer = "X";
    private String winner = NO_WINNER;
    private boolean isOver = false;
    
    private final int WINDOW_WIDTH = 320;
    private final int WINDOW_HEIGHT = 260;

    private final BoardCell[][] board = new BoardCell[3][3];
    private final JPanel pane;
    
    private final JPanel pane2;
    private final JLabel statusLabel;
    private final JButton resetButton;

    public TicTacToe() {

        this.setTitle("TicTacToe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pane = new JPanel();
        pane.setLayout(new GridLayout(3, 3));
        pane.setBorder(BorderFactory.createLineBorder(Color.red, 3));

        for (int m = 0; m < board.length; ++m) {
            for (int n = 0; n < board[m].length; ++n) {
                board[m][n] = new BoardCell();
            }
        }

        for (BoardCell[] rowItem : board) {
            for (BoardCell columnItem : rowItem) {
                pane.add(columnItem, 0, 0);
            }
        }

        statusLabel = new JLabel(currentPlayer + "'s Turn.", SwingConstants.CENTER);

        resetButton = new JButton("ReSet");

        resetButton.addActionListener((ActionEvent e) -> {
            reSet();
        });
        
        pane2 = new JPanel();
        pane2.setLayout(new GridLayout(1, 2));
        pane2.setBackground(Color.lightGray);
        pane2.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        
        pane2.add(statusLabel);
        pane2.add(resetButton);

        this.setLayout(new BorderLayout());
        this.add(pane, BorderLayout.CENTER);
        this.add(pane2, BorderLayout.SOUTH);
        this.pack(); 
        this.setVisible(true);
    }

    private boolean checkIsDraw() {
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (board[row][col].occupant.equals("Empty")) {
                    return false;
                }
            }

        }
        isOver = true;
        return true;
    }

    private boolean setWinner() {
        if (isOver) {
            winner = currentPlayer;
            return true;
        }
        return false;
    }

    private boolean checkResult() {

        isOver = true;
        for (int row = 0; row < board.length; ++row) {
            if (!currentPlayer.equals(board[row][row].occupant)) {
                isOver = false;
                break;
            }
        }

        if (setWinner()) {
            return true;
        }

        isOver = true;
        for (int row = 0; row < board.length; ++row) {
            if (!currentPlayer.equals(board[row][board.length - 1 - row].occupant)) {
                isOver = false;
                break;
            }

        }
        if (setWinner()) {
            return true;
        }

        for (int row = 0; row < board.length; ++row) {
            isOver = true;
            for (int col = 0; col < board[row].length; ++col) {
                if (!currentPlayer.equals(board[row][col].occupant)) {
                    isOver = false;
                    break;
                }
            }
            if (setWinner()) {
                return true;
            }

        }

        for (int col = 0; col < board[0].length; ++col) {
            isOver = true;
            for (int row = 0; row < board.length; ++row) {
                if (!currentPlayer.equals(board[row][col].occupant)) {
                    isOver = false;
                    break;
                }
            }
            if (setWinner()) {
                return true;
            }
        }

        return false;
    }

    private class BoardCell extends JPanel implements MouseListener {

        private String occupant = "Empty";

        private final int CELL_WIDTH = ((WINDOW_WIDTH) - 20) / 3;
        private final int CELL_HEIGHT = (WINDOW_HEIGHT - 50) / 3;

        public BoardCell() {
            this.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
            this.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
            addMouseListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int w = this.getWidth();
            int h = this.getHeight();
            if (occupant.equals("O")) {
                g.drawOval(10, 10, w - 20, h - 20);
            } else if (occupant.equals("X")) {

                g.drawLine(10, 10, CELL_WIDTH - 10, CELL_HEIGHT - 10);
                g.drawLine(CELL_WIDTH - 10, 10, 10, CELL_HEIGHT - 10);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!isOver) {
                if (!occupant.equals("Empty")) {
                    JOptionPane.showMessageDialog(null, "The Cell has been taken.");
                    return;
                }
                occupant = currentPlayer;
                this.repaint();
                if (!checkResult()) {
                    if (checkIsDraw()) {
                        JOptionPane.showMessageDialog(null, "The game is over with a Draw");
                        statusLabel.setText("The game is over with a Draw");
                    } else {
                        currentPlayer = (currentPlayer.equals("O")) ? "X" : "O";
                        statusLabel.setText(currentPlayer + "'s Turn.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, winner + " won, Game is Over.");
                    statusLabel.setText(winner + " won, Game is Over.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Game Over.");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public final void reSet() {
        currentPlayer = "X";
        winner = NO_WINNER;
        isOver = false;

        statusLabel.setText(currentPlayer + "'s Turn.");

        for (int m = 0; m < board.length; ++m) {
            for (int n = 0; n < board[m].length; ++n) {
                if (board[m][n] != null) {
                    board[m][n].occupant = "Empty";
                    board[m][n].repaint();
                }
            }
        }

    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
