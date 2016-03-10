package com.company.ruta_a_tiempo_app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static EditText txt_time;
    public static TimeDialog newInstance(View view){
        txt_time=(EditText)view;
        return(new TimeDialog());
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default time in the dialog
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        //Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, false);

    }


    public void onTimeSet(TimePicker picker,int hour, int minute){
        txt_time.setText(String.format("%02d:%02d", hour, minute));
    }
}
