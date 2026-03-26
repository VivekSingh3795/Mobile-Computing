package com.example.messagealertapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Grab the data bundle from the incoming intent
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // "pdus" is the standard protocol format for SMS messages
            Object[] pdus = (Object[]) bundle.get("pdus");

            if (pdus != null) {
                for (Object pdu : pdus) {
                    // Convert the raw data into a readable message
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String messageBody = smsMessage.getMessageBody();

                    // Create the Alert!
                    Toast.makeText(context, "ALERT! Message from " + sender + ": " + messageBody, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}