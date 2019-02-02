package io.github.codekaust.cityprototype;

//left up right bottom

import java.util.Random;

public class Intersection {

    private int c1, c2, c3, c4;
//    public int dr[] = new int[4];
//    private final int RED_LIGHT = R.drawable.circle_red;
//    private final int GREEN_LIGHT = R.drawable.circle_green;

    Intersection() {
        Random rn = new Random();
        this.c1 = rn.nextInt(25 - 2) + 2;
        this.c2 = rn.nextInt(25 - 2) + 2;
        this.c3 = rn.nextInt(25 - 2) + 2;
        this.c4 = rn.nextInt(25 - 2) + 2;

//        for (int i=0; i<4; i++){
//            dr[i]= RED_LIGHT;
//        }
//        dr[direcGreen()] = GREEN_LIGHT;

    }

    public String getC1() {
        return String.valueOf(c1);
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return String.valueOf(c2);
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return String.valueOf(c3);
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return String.valueOf(c4);
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }

//    private int direcGreen(){
//        int max=c1, index=0;
//        if(c2>max){
//            index=1;
//            max = c2;
//        }if(c3>max){
//            index=2;
//            max = c3;
//        }if(c4>max){
//            index=3;
//            max = c4;
//        }
//        return index;
//    }
}
