package org.prateek.SOLIDPrinciples.DIP.BadCode;

class SMSService {
    public void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}