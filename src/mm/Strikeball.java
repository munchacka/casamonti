/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.util.Arrays;



/**
 *
 * @author casam
 */
public class Strikeball {

    private int[] numero;
    private int strike;
    private int ball;


    public Strikeball() {
        
        strike = 0;
        ball = 0;
        numero = new int[3];
        for (int i = 0; i < 3; i++) {
            numero[i] = (int)(Math.random()*10-1);

        }
    }
    
    /**
     *
     * @param sequenza in stringa divisa da spazi
     * @return array di int
     */
    public int[] convertToArray(String seq) {
        String[] seqSplit = seq.split("\\s+");
        return Arrays.stream(seqSplit).mapToInt(Integer::parseInt).toArray();
    }

    public boolean checkBall(int[] numeroPlayer) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numeroPlayer[i] == numero[j]) {
                    ball++;
                    System.out.println("checkBall");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkStrike(int[] numeroPlayer) {
        for (int i = 0; i < 4; i++) {
            if (numeroPlayer[i] == numero[i]) {
                strike++;
                System.out.println("checkStrike");
                return true;
            }
        }
        return false;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int[] getNumero() {
        return numero;
    }

    public void setNumero(int[] numero) {
        this.numero = numero;
    }

    public static boolean compareArrays(int[] array1, int[] array2) {
        if (array1 != null && array2 != null) {
            if (array1.length != array2.length)
                return false;
            else
                for (int i = 0; i < array2.length; i++) {
                    if (array2[i] != array1[i]) {
                        return false;
                    }
                }
        } return true;
    }

}

