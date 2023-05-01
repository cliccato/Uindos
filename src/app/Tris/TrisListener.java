package app.Tris;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TrisListener implements ActionListener {

    private TrisFrame trisFrame;

    public TrisListener(TrisFrame trisFrame){
        this.trisFrame = trisFrame;
    }

    private boolean isAlreadyClick(JButton btn){
        return !btn.getText().equals("");
    }

    private boolean won(String turno){
        JButton[][] btnTris = trisFrame.getBtnTris();
        for (int i = 0; i < 3; i++) {
            if (btnTris[i][0].getText().equals(turno) && btnTris[i][1].getText().equals(turno) && btnTris[i][2].getText().equals(turno)) return true;
            if (btnTris[0][i].getText().equals(turno) && btnTris[1][i].getText().equals(turno) && btnTris[2][i].getText().equals(turno)) return true;
        }

        if (btnTris[0][0].getText().equals(turno) && btnTris[1][1].getText().equals(turno) && btnTris[2][2].getText().equals(turno)) return true;
        if (btnTris[2][0].getText().equals(turno) && btnTris[1][1].getText().equals(turno) && btnTris[0][2].getText().equals(turno)) return true;
        return false;
    }

    private void updateScore(){
        if (trisFrame.isVittoriaO()) {
            trisFrame.setPunteggioO(trisFrame.getPunteggioO()+1);
            trisFrame.getLblPunteggioO().setText("Punteggio O: " + trisFrame.getPunteggioO());
        } else {
            trisFrame.setPunteggioX(trisFrame.getPunteggioX()+1);
            trisFrame.getLblPunteggioX().setText("Punteggio X: " + trisFrame.getPunteggioX());
        }
    }

    private void clearAll(){
        JButton[][] btnTris = trisFrame.getBtnTris();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btnTris[i][j].setText("");
            }
        }
        trisFrame.setMosse(0);
        trisFrame.setTurno(trisFrame.getLettereTris()[new Random().nextInt(0,2)]);
        trisFrame.getLblTurnoGiocatore().setText("Turno del giocatore: " + trisFrame.getTurno());
        trisFrame.setVittoriaX(false);
        trisFrame.setVittoriaO(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton) e.getSource();
        String turno = trisFrame.getTurno();
        if (!isAlreadyClick(btn)){
            btn.setText(turno);
            if (turno.equals("X"))
                trisFrame.setVittoriaX(won(turno));
            else
                trisFrame.setVittoriaO(won(turno));

            if (trisFrame.isVittoriaO() || trisFrame.isVittoriaX()){
                JOptionPane.showMessageDialog(null, "Vincitore: " + turno, "Tris", JOptionPane.INFORMATION_MESSAGE);
                updateScore();
                clearAll();
            } else {
                if (turno.equals("O"))
                    trisFrame.setTurno("X");
                else
                    trisFrame.setTurno("O");

                trisFrame.getLblTurnoGiocatore().setText("Turno del giocatore: " + trisFrame.getTurno());
                trisFrame.setMosse(trisFrame.getMosse()+1);

                if (trisFrame.getMosse() == trisFrame.NUM_BOTTONI_TRIS && !trisFrame.isVittoriaX() && !trisFrame.isVittoriaO()) {
                    JOptionPane.showMessageDialog(null, "Pareggio!", "Tris", JOptionPane.INFORMATION_MESSAGE);
                    clearAll();
                }
            }
        }
    }
}