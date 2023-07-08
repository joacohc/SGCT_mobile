package com.daemontech.sgct_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daemontech.sgct_mobile.MenuActivity;
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

public class ListarTernerasActivity extends AppCompatActivity {
    private static final int CODE_GET_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ternera);
        readTerneras();

        // Add back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarTernerasActivity.this, MenuActivity.class));
            }
        });
        ImageButton floatingButton = findViewById(R.id.floatingButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarTernerasActivity.this, TerneraActivity.class));
            }
        });
    }


    private void readTerneras() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_LISTAR_TERNERAS, null, CODE_GET_REQUEST, this);
        request.execute();
    }

    public void populateCards(List<Ternera> terneraList) {
        LinearLayout containerLayout = findViewById(R.id.containerLayout);
        containerLayout.removeAllViews();

        for (Ternera ternera : terneraList) {
            View cardView = getLayoutInflater().inflate(R.layout.card_ternera, containerLayout, false);

            TextView textViewCaravana = cardView.findViewById(R.id.caravanaTextView);
            textViewCaravana.setText(String.valueOf(ternera.getCaravana_ternera()));

            TextView textViewFechaNacimiento = cardView.findViewById(R.id.fechaNacimientoTextView);
            textViewFechaNacimiento.setText(ternera.getFec_nac_tern().toString());

            TextView textViewFechaAlta = cardView.findViewById(R.id.fechaAltaTextView);
            textViewFechaAlta.setText(ternera.getFec_alt_tern().toString());

            TextView textViewPesoNacimiento = cardView.findViewById(R.id.pesoNacimientoTextView);
            textViewPesoNacimiento.setText(String.valueOf(ternera.getPeso_nac()));

            TextView textViewPesoActual = cardView.findViewById(R.id.pesoActualTextView);
            textViewPesoActual.setText(String.valueOf(ternera.getPeso_act()));

            TextView textViewRaza = cardView.findViewById(R.id.razaTextView);
            textViewRaza.setText(ternera.getRaza_ternera());

            TextView textViewTipoParto = cardView.findViewById(R.id.tipoPartoTextView);
            textViewTipoParto.setText(ternera.getTipo_parto_ternera());

            containerLayout.addView(cardView);
        }
    }

    private static class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        private Context context;
        private String url;
        private HashMap<String, String> params;
        private int requestCode;
        private List<Ternera> terneraList;

        public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, Context context) {
            this.context = context;
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            try {
                JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.has("terneras")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("terneras");
                    terneraList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject terneraObject = jsonArray.getJSONObject(i);

                        Long id = terneraObject.getLong("id_ternera");
                        Integer caravana = terneraObject.getInt("caravana_ternera");
                        String fechaNacimientoStr = terneraObject.getString("fec_nac_tern");
                        String fechaAltaStr = terneraObject.getString("fec_alt_tern");

                        LocalDate fechaNacimiento = DateUtils.parseDateFromString(fechaNacimientoStr);
                        LocalDate fechaAlta = DateUtils.parseDateFromString(fechaAltaStr);
                        Double pesoNac = terneraObject.getDouble("peso_nac");
                        Double pesoActual = pesoNac;
                        if(terneraObject.getString("peso_act").equals("")) {
                            pesoActual = terneraObject.getDouble("peso_act");
                        }


                        String raza = terneraObject.getString("raza_ternera");
                        String tipoParto = terneraObject.getString("tipo_parto_ternera");

                        Ternera ternera = new Ternera(id, caravana, fechaNacimiento, fechaAlta,pesoNac, pesoActual, raza, tipoParto);
                        terneraList.add(ternera);
                    }

                    ((ListarTernerasActivity) context).populateCards(terneraList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            if (requestCode == CODE_GET_REQUEST) {
                return requestHandler.sendGetRequest(url);
            }
            return null;
        }
    }
}
