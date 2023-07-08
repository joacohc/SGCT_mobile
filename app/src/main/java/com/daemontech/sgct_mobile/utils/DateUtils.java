package com.daemontech.sgct_mobile.utils;

import android.app.DatePickerDialog;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtils {

    private static final String DEFAULT_FORMAT = "dd-MM-yyyy";

    public static void openDatePickerDialog(EditText editText) {
        openDatePickerDialog(editText, DEFAULT_FORMAT);
    }

    public static void openDatePickerDialog(EditText editText, String dateFormat) {
        DatePickerDialog.OnDateSetListener listener = getDatePickerDialogListener(editText, dateFormat);
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear el diálogo de selección de fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(editText.getContext(),
                listener, year, month, day);

        // Mostrar el diálogo
        datePickerDialog.show();
    }

    public static DatePickerDialog.OnDateSetListener getDatePickerDialogListener(EditText editText, String dateFormat) {
        return (view, yearSelected, monthSelected, daySelected) -> {
            // Actualizar el EditText con la fecha seleccionada
            LocalDate selectedDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                selectedDate = LocalDate.of(yearSelected, monthSelected + 1, daySelected);
            }
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern(dateFormat);
            }
            String formattedDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDate = selectedDate.format(formatter);
            }
            editText.setText(formattedDate);
        };
    }

    public static LocalDate parseLocalDate(String dateString, String dateFormat) {
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern(dateFormat);
        }

        LocalDate dateDev = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateDev = LocalDate.parse(dateString, formatter);
        }
        return dateDev;
    }

    public static LocalDate parseDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

}