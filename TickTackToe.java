import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean player1Turn = true;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }



      
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        //current player's turn
        if (player1Turn) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        // Switch the turn
        player1Turn = !player1Turn;
        if (checkForWin()) {
            if (!player1Turn) {
                JOptionPane.showMessageDialog(this, "Player 1 (X) wins!");
            } else {
                JOptionPane.showMessageDialog(this, "Player 2 (O) wins!");
            }
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    //check if the board is full 
    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForWin() {
        //  Winning combinations
        int[][] winCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            if (buttons[combination[0]].getText().equals(buttons[combination[1]].getText()) &&
                buttons[combination[1]].getText().equals(buttons[combination[2]].getText()) &&
                !buttons[combination[0]].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    // Reset the board
    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
