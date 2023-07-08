package com.daemontech.sgct_mobile;

import static android.view.View.GONE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daemontech.sgct_mobile.api.Api;
import com.daemontech.sgct_mobile.api.RequestHandler;
import com.daemontech.sgct_mobile.modelos.Ternera;
import com.daemontech.sgct_mobile.utils.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerneraActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
    //defining views
    EditText terneraIdEt, caravanaEt, caravanaPadre, caravanaMadre, pesoNacimientoEt, fechaNacEt, fechaAltaEt;
    Spinner razasSpinner, partoSpinner;
    ListView listViewTerneras;
    Button botonEnviar;
    List<Ternera> terneraList;
    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ternera);


        terneraIdEt = (EditText) findViewById(R.id.terneraIdEt);
        caravanaEt = (EditText) findViewById(R.id.caravanaEt);
        caravanaPadre = (EditText) findViewById(R.id.caravanaPadre);
        caravanaMadre = (EditText) findViewById(R.id.caravanaMadre);
        pesoNacimientoEt = (EditText) findViewById(R.id.pesoNacimientoEt);
        razasSpinner = (Spinner) findViewById(R.id.razasSpinner);
        partoSpinner = (Spinner) findViewById(R.id.partoSpinner);
        razasSpinner.setSelection(0);
        fechaNacEt = findViewById(R.id.fechaNacEt);
        fechaAltaEt = findViewById(R.id.fechaAltaEt);
        fechaNacEt.setOnClickListener(v -> {
            // Abrir el diálogo de selección de fecha
            DateUtils.openDatePickerDialog(fechaNacEt, "dd/MM/yyyy");
        });
        fechaAltaEt.setOnClickListener(v -> {
            // Abrir el diálogo de selección de fecha
            DateUtils.openDatePickerDialog(fechaAltaEt, "dd/MM/yyyy");
        });

        botonEnviar = (Button) findViewById(R.id.botonEnviar);
        terneraList = new ArrayList<>();

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TerneraActivity.this, ListarTernerasActivity.class));
            }
        });


        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is updating
                if (isUpdating) {
                    //calling the method update ternera
                    //method is commented becuase it is not yet created
                   updateTernera();
                } else {
                    //if it is not updating
                    //that means it is creating
                    //so calling the method create ternera
                    try {
                        createTernera();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });

        //calling the method read heroes to read existing heros from the database
        //method is commented because it is not yet created
        readTerneras();
    }
    private void createTernera() throws JSONException {
        int caravana_ternera = Integer.parseInt(caravanaEt.getText().toString().trim());
        int id_padr_tern = Integer.parseInt(caravanaPadre.getText().toString().trim());
        int id_madr_tern = Integer.parseInt(caravanaMadre.getText().toString().trim());
        LocalDate fec_alt_tern = DateUtils.parseLocalDate(fechaAltaEt.getText().toString(), "dd/MM/yyyy");
        LocalDate fec_nac_tern = DateUtils.parseLocalDate(fechaNacEt.getText().toString(), "dd/MM/yyyy");
        int peso_nac = Integer.parseInt(pesoNacimientoEt.getText().toString().trim());
        String raza_ternera = razasSpinner.getSelectedItem().toString();
        String tipo_parto_ternera = partoSpinner.getSelectedItem().toString();

        //validating the inputs
        if (caravana_ternera == 0) {
            caravanaEt.setError("Ingrese una caravana valida");
            caravanaEt.requestFocus();
            return;
        }
        if (id_padr_tern == 0) {
            caravanaPadre.setError("Ingrese una caravana valida.");
            caravanaPadre.requestFocus();
            return;
        }
        if (id_madr_tern == 0) {
            caravanaMadre.setError("Ingrese una caravana valida.");
            caravanaMadre.requestFocus();
            return;
        }
        if (fec_nac_tern.equals(null)) {
            fechaNacEt.setError("Ingrese una fecha de nacimiento valida.");
            fechaNacEt.requestFocus();
            return;
        }
        if (fec_alt_tern.equals(null)) {
            fechaAltaEt.setError("Ingrese una fecha de Alta valida.");
            fechaAltaEt.requestFocus();
            return;
        }


        //if validation passes

        JSONObject params = new JSONObject();
        try {
            params.put("caravana_ternera", caravana_ternera);
            params.put("id_padr_tern", id_padr_tern);
            params.put("id_madr_tern", id_madr_tern);
            params.put("fec_alt_tern", fec_alt_tern);
            params.put("fec_nac_tern", fec_nac_tern);
            params.put("raza_ternera", raza_ternera.toUpperCase());
            params.put("tipo_parto_ternera", tipo_parto_ternera.toUpperCase());
            params.put("peso_nac", peso_nac);
            params.put("eliminado", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Calling the create terneras API
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREAR_TERNERA, params, CODE_POST_REQUEST) {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject object = new JSONObject(s);
                    if (!object.getBoolean("error")) {
                        // Show success toast
                        Toast.makeText(TerneraActivity.this, "Ternera dada de alta con éxito", Toast.LENGTH_SHORT).show();
                        // Refresh the ternera list
                        readTerneras();
                        startActivity(new Intent(TerneraActivity.this, ListarTernerasActivity.class));
                    } else {
                        // Show error toast or handle error case
                        Toast.makeText(TerneraActivity.this, "Error al dar de alta la ternera", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        request.execute();
    }
    private void readTerneras() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_LISTAR_TERNERAS, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void updateTernera() {
        Long id_ternera =Long.parseLong(terneraIdEt.getText().toString().trim());
        int caravana_ternera = Integer.parseInt(caravanaEt.getText().toString().trim());
        int id_padr_tern = Integer.parseInt(caravanaPadre.getText().toString().trim());
        int id_madr_tern = Integer.parseInt(caravanaMadre.getText().toString().trim());
        LocalDate fec_alt_tern = DateUtils.parseLocalDate(fechaAltaEt.getText().toString(), "dd/MM/yyyy");
        LocalDate fec_nac_tern = DateUtils.parseLocalDate(fechaNacEt.getText().toString(), "dd/MM/yyyy");
        int peso_nac = Integer.parseInt(pesoNacimientoEt.getText().toString().trim());
        String raza_ternera = razasSpinner.getSelectedItem().toString();
        String tipo_parto_ternera = partoSpinner.getSelectedItem().toString();

        //validating the inputs
        if (caravana_ternera == 0) {
            caravanaEt.setError("Ingrese una caravana valida");
            caravanaEt.requestFocus();
            return;
        }
        if (id_padr_tern == 0) {
            caravanaPadre.setError("Ingrese una caravana valida.");
            caravanaPadre.requestFocus();
            return;
        }
        if (id_madr_tern == 0) {
            caravanaMadre.setError("Ingrese una caravana valida.");
            caravanaMadre.requestFocus();
            return;
        }
        if (fec_nac_tern.equals(null)) {
            fechaNacEt.setError("Ingrese una fecha de nacimiento valida.");
            fechaNacEt.requestFocus();
            return;
        }
        if (fec_alt_tern.equals(null)) {
            fechaAltaEt.setError("Ingrese una fecha de Alta valida.");
            fechaAltaEt.requestFocus();
            return;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("caravana_ternera", caravana_ternera);
            params.put("id_padr_tern", id_padr_tern);
            params.put("id_madr_tern", id_madr_tern);
            params.put("fec_alt_tern", fec_alt_tern);
            params.put("fec_nac_tern", fec_nac_tern);
            params.put("raza_ternera", raza_ternera.toUpperCase());
            params.put("tipo_parto_ternera", tipo_parto_ternera.toUpperCase());
            params.put("peso_nac", peso_nac);
            params.put("eliminado", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_ACTUALIZAR_TERNERA, params, CODE_POST_REQUEST);
        request.execute();

        botonEnviar.setText("Agregar");

        terneraIdEt.setText("");
        caravanaEt.setText("");
        caravanaPadre.setText("");
        caravanaMadre.setText("");
        pesoNacimientoEt.setText("");
        razasSpinner.setSelection(0);
        partoSpinner.setSelection(0);
        fechaNacEt.setText("");
        fechaAltaEt.setText("");
        fechaNacEt.setText("");
        fechaAltaEt.setText("");

        isUpdating = false;
    }
    private void refreshTerneraList(JSONArray terneras) throws JSONException {
        //clearing previous heroes
        terneraList.clear();

        //traversing through all the items in the json array
        //the json we got from the response
        for (int i = 0; i < terneras.length(); i++) {
            //getting each ternera object
            JSONObject obj = terneras.getJSONObject(i);

            //adding the hero to the list
            terneraList.add(new Ternera(
                    obj.getLong("id_ternera"),
                    obj.getInt("caravana_ternera"),
                    obj.getInt("id_padr_tern"),
                    obj.getInt("id_madr_tern"),
                    obj.getString("fec_alt_tern"),
                    obj.getString("fec_nac_tern"),
                    obj.getDouble("peso_nac"),
                    obj.getDouble("peso_act"),
                    obj.getString("tipo_parto_ternera"),
                    obj.getString("motivo_baja_ternera")
            ));
        }

        //creating the adapter and setting it to the listview
        TerneraAdapter adapter = new TerneraAdapter(terneraList);
        listViewTerneras.setAdapter(adapter);
    }
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        JSONObject params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, JSONObject params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //this method will give the response from the request


        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    class TerneraAdapter extends ArrayAdapter<Ternera> {
        //our hero list
        List<Ternera> terneraList;
        //constructor to get the list
        public TerneraAdapter(List<Ternera> terneraList) {
            super(TerneraActivity.this, R.layout.layout_listado_ternera, terneraList);
            this.terneraList = terneraList;
        }
        //method returning list item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_listado_ternera, null, true);

            //getting the textview for displaying name
            TextView textViewCaravana = listViewItem.findViewById(R.id.textViewCaravana);

            //the update and delete textview
            TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

            final Ternera ter = terneraList.get(position);
           // textViewCaravana.setText(ter.getCaravana_ternera().toString());

            //attaching click listener to update
            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //so when it is updating we will
                    //make the isUpdating as true
                    isUpdating = true;

                    //we will set the selected ternera to the UI elements
                    terneraIdEt.setText(String.valueOf(ter.getId_ternera()));
                    caravanaEt.setText(ter.getCaravana_ternera());
                    caravanaPadre.setText(ter.getId_padr_tern());
                    caravanaMadre.setText(ter.getId_madr_tern());
                    pesoNacimientoEt.setText(ter.getPeso_nac().toString());
                    razasSpinner.setSelection(((ArrayAdapter<String>) razasSpinner.getAdapter()).getPosition(ter.getRaza_ternera()));
                    partoSpinner.setSelection(((ArrayAdapter<String>) partoSpinner.getAdapter()).getPosition(ter.getTipo_parto_ternera()));
                    fechaNacEt.setText(ter.getFec_nac_tern().toString().trim());
                    fechaAltaEt.setText(ter.getFec_alt_tern().toString().trim());
                    botonEnviar.setText("Update");
                }
            });

            //when the user selected delete
            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // we will display a confirmation dialog before deleting
                    AlertDialog.Builder builder = new AlertDialog.Builder(TerneraActivity.this);

                    builder.setTitle("Delete " + ter.getCaravana_ternera())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //if the choice is yes we will delete the hero
                                    //method is commented because it is not yet created
                                    //deleteTernera(ternera.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });

            return listViewItem;
        }
    }
}


