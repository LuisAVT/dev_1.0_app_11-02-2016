package com.company.ruta_a_tiempo_app;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    FloatingActionButton fabreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        spinner = (Spinner) findViewById(R.id.spinnerrep);
        fabreport = (FloatingActionButton) findViewById(R.id.fabreport);
        fabreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDialogAlert myDialogAlert = new MyDialogAlert();
                myDialogAlert.show(getFragmentManager(), "Dialogo Atencion");

            }
        });

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Report_Type, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        //setTitle("Reportar");
    }

    public void onStart() {
        super.onStart();

        final EditText txtDate = (EditText) findViewById(R.id.editrepdate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });


        EditText txtTime=(EditText)findViewById(R.id.editreptime);
        txtTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    TimeDialog dialog = TimeDialog.newInstance(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "TimeDialog");

                }
            }

        });
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this,"Seleccionaste"+" "+myText.getText(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class MyDialogAlert extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Al enviar el reporte aseguras que la información es verídica, el uso indebido de esta opción tendrá consecuencias negativas para el usuario");
            builder.setTitle("Atención");
            builder.setIcon(R.drawable.ic_report_black_24dp);
            builder.setCancelable(true);
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Reporte enviado",Toast.LENGTH_LONG).show();


                }
            });
            return builder.create();
        }
    }
}
