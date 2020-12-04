package com.example.mp3player.util;

public class ChuyenDoiTime {
    public  static  String miliSecondToTimer(long miliseconds){
        String finalTimerString = "";
        String secondString= "";

        //chuyen doi
        int hours=(int) (miliseconds/(1000*60*60));
        int minutes=(int) (miliseconds%(1000*60*60))/(1000*60);
        int seconds=(int) ((miliseconds%(1000*60*60))%(1000*60)/1000);
        if(hours>0){
            finalTimerString=hours+":";
        }
        if(seconds<10){
            secondString="0"+seconds;
        }else {
            secondString=""+seconds;
        }
        finalTimerString=finalTimerString+minutes+":"+secondString;
        return finalTimerString;
    }

    //chuyen doi phan thoi gian ve dang phan tram

    public static int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage=(double) 0;
        long currentSeconds=(int) (currentDuration/1000);
        long totalSeconds=(int) (totalDuration/1000);

        //doi ra phan tram
        percentage=(((double)currentSeconds)/totalSeconds)*100;
        //return phan tram o dang int
        return percentage.intValue();
    }

    //Chuyen doi tu progress ve hien thi thoi gian

    public static int ProgressToTimer(int progress, int totalDuration){
        int currentDuration=0;
        totalDuration=(int) (totalDuration/1000);
        currentDuration=(int) (((double)progress/100)*totalDuration);
        return currentDuration*1000;
    }
}

