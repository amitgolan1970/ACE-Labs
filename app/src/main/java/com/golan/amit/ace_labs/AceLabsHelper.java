package com.golan.amit.ace_labs;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AceLabsHelper {

    private static final String ACE_STR = "ACE";
    private static final String LABS_STR = "LABS";
    private static final int MAGIC_NUMBER = 10;

    private int[] rnd_ace;
    private int[] rnd_labs;

    private int ace_counter;
    private int labs_counter;

    private int ace_rnd_index;
    private int labs_rnd_index;

    /**
     * Constructor
     */

    public AceLabsHelper() {
        rnd_ace = new int[ACE_STR.length()];
        rnd_labs = new int[LABS_STR.length()];
        resetAce_counter();
        resetAce_counter();
//        generateAce_rnd_index();
//        generateLabs_rnd_index();
    }

    public void generate_random_ace() {
        increaseAce_counter();
        if (getAce_counter() < getAce_rnd_index()) {
            List<Integer> computerNumbersList = new ArrayList<>();
            for (int i = 0; i < ACE_STR.length(); i++) {
                computerNumbersList.add(i);
            }
            Collections.shuffle(computerNumbersList);
            for (int i = 0; i < ACE_STR.length(); i++) {
                rnd_ace[i] = computerNumbersList.get(i);
            }
        } else {
//            Log.d(MainActivity.DEBUGTAG, "setting the sequantial ordered strings for ace");
            resetAce_counter();
            for (int i = 0; i < ACE_STR.length(); i++) {
                rnd_ace[i] = i;
            }
        }
    }

    public void generate_random_labs() {
        increaseLabs_counter();
        if (getLabs_counter() < getLabs_rnd_index()) {
            List<Integer> computerNumbersList = new ArrayList<>();
            for (int i = 0; i < LABS_STR.length(); i++) {
                computerNumbersList.add(i);
            }
            Collections.shuffle(computerNumbersList);
            for (int i = 0; i < LABS_STR.length(); i++) {
                rnd_labs[i] = computerNumbersList.get(i);
            }
        } else {
            Log.d(MainActivity.DEBUGTAG, "setting the sequantial ordered strings for labs");
            resetLabs_counter();
            for (int i = 0; i < LABS_STR.length(); i++) {
                rnd_labs[i] = i;
            }
        }
    }


    public String getAceCharByIndex(int ind) {
        if(ind >= rnd_ace.length) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ACE_STR.charAt(rnd_ace[ind]));
        return sb.toString();
    }

    public String getLabsCharByIndex(int ind) {
        if(ind >= rnd_labs.length) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LABS_STR.charAt(rnd_labs[ind]));
        return sb.toString();
    }

    public String ace_rnd_representation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rnd_ace.length; i++) {
            sb.append(ACE_STR.charAt(rnd_ace[i]));
            if(i < (rnd_ace.length - 1))
                sb.append(" ");
        }
        return sb.toString();
    }

    public String labs_rnd_representation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rnd_labs.length; i++) {
            sb.append(LABS_STR.charAt(rnd_labs[i]));
            if(i < (rnd_labs.length - 1))
                sb.append(" ");
        }
        return sb.toString();
    }

    public boolean isAceSet() {
        boolean aceIsSet = true;
        for(int i = 0; i < rnd_ace.length; i++) {
            if(i != rnd_ace[i]) {
                aceIsSet = false;
                break;
            }
        }
        return aceIsSet;
    }

    public boolean isLabsSet() {
        boolean labsIsSet = true;
        for(int i = 0; i < rnd_labs.length; i++) {
            if(i != rnd_labs[i]) {
                labsIsSet = false;
                break;
            }
        }
        return labsIsSet;
    }

    /**
     * Getters and Setters
     * @return
     */


    public int[] getRnd_ace() {
        return rnd_ace;
    }

    public void setRnd_ace(int[] rnd_ace) {
        this.rnd_ace = rnd_ace;
    }

    public int[] getRnd_labs() {
        return rnd_labs;
    }

    public void setRnd_labs(int[] rnd_labs) {
        this.rnd_labs = rnd_labs;
    }

    public int getAce_counter() {
        return ace_counter;
    }

    public void setAce_counter(int ace_counter) {
        this.ace_counter = ace_counter;
    }

    public void resetAce_counter() {
        this.ace_counter = 0;
    }

    public void increaseAce_counter() {
        this.ace_counter++;
    }

    public int getLabs_counter() {
        return labs_counter;
    }

    public void setLabs_counter(int labs_counter) {
        this.labs_counter = labs_counter;
    }

    public void resetLabs_counter() {
        this.labs_counter=0;
    }

    public void increaseLabs_counter() {
        this.labs_counter++;
    }

    public int getAce_rnd_index() {
        return ace_rnd_index;
    }

    public void setAce_rnd_index(int ace_rnd_index) {
        this.ace_rnd_index = ace_rnd_index;
    }

    public int getLabs_rnd_index() {
        return labs_rnd_index;
    }

    public void setLabs_rnd_index(int labs_rnd_index) {
        this.labs_rnd_index = labs_rnd_index;
    }
    
    public void generateAce_rnd_index() {
        this.ace_rnd_index = (int)(Math.random() * MAGIC_NUMBER + MAGIC_NUMBER);
    }

    public void generateLabs_rnd_index() {
        this.labs_rnd_index = (int)(Math.random() * MAGIC_NUMBER + MAGIC_NUMBER);
    }
}
