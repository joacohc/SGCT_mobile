package com.daemontech.sgct_mobile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daemontech.sgct_mobile.api.Api;
import com.daemontech.sgct_mobile.api.RequestHandler;
import com.daemontech.sgct_mobile.modelos.EnfTerVar;
import com.daemontech.sgct_mobile.modelos.Ternera;
import com.daemontech.sgct_mobile.utils.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnfTerVarActivity extends AppCompatActivity {
    private static final int CODE_GET_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enf_ter_var);
        readEnfPadecidas();
        // Add back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnfTerVarActivity.this, MenuActivity.class));
            }
        });
    }

    private void readEnfPadecidas() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_ENF_PADECIDAS, null, CODE_GET_REQUEST, this);
        request.execute();
    }

    public void populateCards(List<EnfTerVar> enfTerVarList) {
        LinearLayout containerLayout = findViewById(R.id.containerLayout);
        containerLayout.removeAllViews();

        for (EnfTerVar enfTerVar : enfTerVarList) {
            View cardView = getLayoutInflater().inflate(R.layout.card_enf_padecidas, containerLayout, false);

            TextView textViewCaravana = cardView.findViewById(R.id.caravanaTextView);
            textViewCaravana.setText(String.valueOf("Caravana: " + enfTerVar.getCaravana_ternera()));

            TextView textViewEnfermedad = cardView.findViewById(R.id.enfermedadTextView);
            textViewEnfermedad.setText("Enfermedad: " + enfTerVar.getEnfermedad());

            TextView textViewVariante = cardView.findViewById(R.id.varianteTextView);
            textViewVariante.setText("Variante: " + enfTerVar.getVariante());

            TextView textViewPesoSeveridad = cardView.findViewById(R.id.severidadTextView);
            textViewPesoSeveridad.setText("Severidad: " + enfTerVar.getSeveridad());

            TextView textFechaInicio = cardView.findViewById(R.id.fechaInicioTextView);
            textFechaInicio.setText("Fecha de Inicio: " + enfTerVar.getFechaInicio());

            TextView textFechaFin = cardView.findViewById(R.id.fechaFinTextView);
            textFechaFin.setText("Fecha de Fin: " + enfTerVar.getFechaFin());

            TextView textViewMotivoBaja = cardView.findViewById(R.id.motivoBajaTextView);
            textViewMotivoBaja.setText("Motivo de Baja: " + enfTerVar.getMotivoFin());

            containerLayout.addView(cardView);
        }
    }

    private static class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        private Context context;
        private String url;
        private HashMap<String, String> params;
        private int requestCode;

        private List<EnfTerVar> enfTerVarList;

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

                if (jsonObject.has("enfPadecidas")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("enfPadecidas");
                    enfTerVarList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject enfTerVarObject = jsonArray.getJSONObject(i);

                        JSONObject terneraVT = enfTerVarObject.getJSONObject("terneraVT");
                        int caravana_ternera = terneraVT.getInt("caravana_ternera");

                        JSONObject enfermedadVT = enfTerVarObject.getJSONObject("enfermedadVT");
                        String enfermedad = enfermedadVT.getString("enfermedad");

                        JSONObject varianteVT = enfTerVarObject.getJSONObject("varianteVT");
                        String variante_enfermedad = varianteVT.getString("variante_enfermedad");

                        String severidad_enfermedad = enfTerVarObject.getString("severidad_enfermedad");

                        JSONObject enfermedad_r_ternera_r_variante_pk = enfTerVarObject.getJSONObject("enfermedad_r_ternera_r_variante_pk");
                        String fec_inic_enf = enfermedad_r_ternera_r_variante_pk.getString("fec_inic_enf");

                        String fc_fin_enfe = enfTerVarObject.getString("fc_fin_enfe");

                        String motivo_baja_enfermedad = enfTerVarObject.getString("motivo_baja_enfermedad");

                        EnfTerVar enfTerVar= new EnfTerVar(caravana_ternera, enfermedad, variante_enfermedad, severidad_enfermedad, fec_inic_enf, fc_fin_enfe, motivo_baja_enfermedad);
                        enfTerVarList.add(enfTerVar);
                    }

                    ((EnfTerVarActivity) context).populateCards(enfTerVarList);
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