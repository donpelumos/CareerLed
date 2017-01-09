package com.jempton.careerled;

/**
 * Created by BALE on 28/09/2015.
 */
public class MessageList {
    String personName;
    String dateTime;
    int personImage;
    public MessageList(String personName, int personImage, String dateTime){
        this.personImage = personImage;
        this.personName = personName;
        this.dateTime = dateTime;
    }
}
