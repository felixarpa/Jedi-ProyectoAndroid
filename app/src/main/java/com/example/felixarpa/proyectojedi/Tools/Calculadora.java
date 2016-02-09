package com.example.felixarpa.proyectojedi.Tools;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felixarpa.proyectojedi.MainNavigationDrawer;
import com.example.felixarpa.proyectojedi.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculadora extends MainNavigationDrawer {

    Button b0, bDe, bANS, bEq, b1, b2, b3, bSUM, b4, b5, b6, bRES;
    Button b7, b8, b9, bMUL, bC, bAP, bCP, bDIV, bAC;
    TextView operation, result;
    List<String> Lst;
    String textPantalla = "";
    String operInterna = "(_";
    String answer = "E";
    int parsAbiertos, index;
    boolean esperandoNumero = true;
    boolean esNegativo = false;
    boolean justClickIgual = true;
    boolean esDecimal = false;
    boolean ansEnPantalla = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_layout);

        setViewsCalc();
        parsAbiertos = 0;

        b0  .setOnClickListener(listener);
        bDe .setOnClickListener(listener);
        bANS.setOnClickListener(listener);
        bEq .setOnClickListener(listener);
        b1  .setOnClickListener(listener);
        b2  .setOnClickListener(listener);
        b3  .setOnClickListener(listener);
        bSUM.setOnClickListener(listener);
        b4  .setOnClickListener(listener);
        b5  .setOnClickListener(listener);
        b6  .setOnClickListener(listener);
        bRES.setOnClickListener(listener);
        b7  .setOnClickListener(listener);
        b8  .setOnClickListener(listener);
        b9  .setOnClickListener(listener);
        bMUL.setOnClickListener(listener);
        bC  .setOnClickListener(listener);
        bAP .setOnClickListener(listener);
        bCP .setOnClickListener(listener);
        bDIV.setOnClickListener(listener);
        bAC .setOnClickListener(listener);
    }

    private boolean esNumero(char a) {
        return a >= '0' && a <= '9';
    }

    @Override
    protected void onRestoreInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        textPantalla = outstate.getString("txt");
        operInterna  = outstate.getString("op");
        answer = outstate.getString("ans");
        parsAbiertos = outstate.getInt("pars");
        esDecimal = outstate.getBoolean("dec");
        esperandoNumero = outstate.getBoolean("num");
        justClickIgual = outstate.getBoolean("eq");
        esNegativo = outstate.getBoolean("neg");
        operation.setText(outstate.getString("PANTALLA"));
        result.setText(outstate.getString("RESULTAT"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString("txt", textPantalla);
        outstate.putString("op", operInterna);
        outstate.putString("ans", answer);
        outstate.putInt("pars", parsAbiertos);
        outstate.putBoolean("dec",esDecimal);
        outstate.putBoolean("num",esperandoNumero);
        outstate.putBoolean("eq",justClickIgual);
        outstate.putBoolean("new", esNegativo);
        outstate.putString("PANTALLA", operation.getText().toString());
        outstate.putString("RESULTAT", result.getText().toString());
    }

    private void setViewsCalc() {
        setTitle("Calculator");
        b0   = (Button) findViewById(R.id.zero);
        bDe  = (Button) findViewById(R.id.decimal);
        bANS = (Button) findViewById(R.id.answer);
        bEq  = (Button) findViewById(R.id.igual);
        b1   = (Button) findViewById(R.id.uno);
        b2   = (Button) findViewById(R.id.dos);
        b3   = (Button) findViewById(R.id.tres);
        bSUM = (Button) findViewById(R.id.sum);
        b4   = (Button) findViewById(R.id.cuatro);
        b5   = (Button) findViewById(R.id.cinco);
        b6   = (Button) findViewById(R.id.seis);
        bRES = (Button) findViewById(R.id.resta);
        b7   = (Button) findViewById(R.id.siete);
        b8   = (Button) findViewById(R.id.ocho);
        b9   = (Button) findViewById(R.id.nueve);
        bMUL = (Button) findViewById(R.id.mult);
        bC   = (Button) findViewById(R.id.clear);
        bAP  = (Button) findViewById(R.id.aPar);
        bCP  = (Button) findViewById(R.id.cPar);
        bDIV = (Button) findViewById(R.id.div);
        bAC  = (Button) findViewById(R.id.allClear);

        operation = (TextView) findViewById(R.id.oper);
        result = (TextView) findViewById(R.id.res);
    }

    private void numero(String n) {
        if (!ansEnPantalla) {
            if (operInterna.charAt(operInterna.length() - 1) == '0' && operInterna.charAt(operInterna.length() - 1) == '_') {
                operInterna = operInterna.substring(0, operInterna.length() - 1);
                textPantalla = textPantalla.substring(0, textPantalla.length() - 1);
            }
            operInterna += n;
            textPantalla += n;
            esperandoNumero = false;
            justClickIgual = false;
        }
    }

    private static String doubleToString(double d) {
        if(d == (long) d) return String.format("%d",(long)d);
        else return String.valueOf(d);
    }

    private void ponerParentesis(int it) {
        int af,bef;
        af = bef = it;
        ++af; --bef;
        if (Lst.get(af).equals("(") || Lst.get(af).equals(")")) { // es parentesis
            int find = 1;
            while (find > 0 && af < Lst.size()) {
                ++af;
                if (Lst.get(af).equals("(") || Lst.get(af).equals(")")) {
                    if (Lst.get(af).equals("(")) ++find;
                    else --find;
                }
            }
        }
        else ++af;
        Lst.add(af, ")");
        if (Lst.get(bef).equals("(") || Lst.get(bef).equals(")")) {
            int find = 1;
            while (find > 0 && bef >= 0) {
                --bef;
                if (Lst.get(bef).equals("(") || Lst.get(bef).equals(")")) {
                    if (Lst.get(bef).equals(")")) ++find;
                    else --find;
                }
            }
        }
        Lst.add(bef, "(");
    }

    void prioridadOperaciones() {
        String s;
        for (int it = 0; it < Lst.size(); ++it) {
            s = Lst.get(it);
            Log.v("ASDF",s+" "+it+" "+Lst.size()+" "+(it < Lst.size()));
            // es operacio de div/mult
            if (s.equals("m") || s.equals("d")) {
                Log.v("PRIORIDAD","M / D");
                ponerParentesis(it);
                ++it;
            }
        }

        for (int it = 0; it < Lst.size(); ++it) {
            s = Lst.get(it);
            // es opeacio de resta/suma
            if (s.equals("s") || s.equals("r")) {
                Log.v("PRIORIDAD","S / R");
                ponerParentesis(it);
                ++it;
            }
        }
    }

    private String operar(String a, String o, String b) {
        Double c = Double.parseDouble(a);
        Double d = Double.parseDouble(b);
        switch (o.charAt(0)) {
            case 's':
                Log.v("Suma","Suma: "+ c +" + "+ d +" = "+ (c+d) +" --> "+doubleToString(c+d));
                return doubleToString(c+d);
            case 'r':
                return doubleToString(c-d);
            case 'm':
                return doubleToString(c*d);
            case 'd':
                if (d == 0.0) return "E";
                else return doubleToString(c/d);

            default:
                return "E";
        }
    }

    private String operarExpresionRecursiva() {
        if (index < Lst.size()) {
            if (Lst.get(index).equals("(")) {

                ++index;
                String primer = operarExpresionRecursiva();
                String operac = Lst.get(++index);
                if (operac.equals(")")) return primer;
                ++index;
                String segon  = operarExpresionRecursiva();
                Log.v("Recursiva",primer+" "+operac+" "+segon);
                if (primer.equals("E") || segon.equals("E")) return "E";
                primer = operar(primer, operac, segon);

                while (!Lst.get(++index).equals(")")) {
                    operac = Lst.get(index++);
                    segon  = operarExpresionRecursiva();
                    Log.v("Recursiva","Bucle: "+primer+" "+operac+" "+segon);
                    if (primer.equals("E") || segon.equals("E")) return "E";
                    primer = operar(primer, operac, segon);
                }
                return primer;
            }
            else return Lst.get(index);
        }
        return "E";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calc_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calcCall:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:605111655")));
                return true;
            case R.id.slothFly:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://youtu.be/ba7rRfKIHxU?t=15s"));
                startActivity(intent);
                return true;
            case R.id.changeNotif:
                SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = getSharedPreferences("SPSP", 0).edit();
                if (sharedPreferences.getBoolean("usaToast", true)) {
                    editor.putBoolean("usaToast", false);
                    Toast.makeText(getApplicationContext(), "State Notification", Toast.LENGTH_LONG).show();
                }
                else {
                    editor.putBoolean("usaToast", true);
                    Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_LONG).show();
                }
                editor.apply();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void operarExpresio() {
        prioridadOperaciones();
        for (int i = 0; i < Lst.size(); ++i) Log.v("LIST2",Lst.get(i));
        index = 0;
        answer = operarExpresionRecursiva();
        Log.v("Resultat Final", answer);
        if (answer.equals("E")) {
            SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
            if (sharedPreferences.getBoolean("usaToast",false)) {
                Toast.makeText(getApplicationContext(), "Math Error", Toast.LENGTH_LONG).show();
            }
            else {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setSmallIcon(R.drawable.sloth_navv);
                builder.setContentTitle("Math Error");
                builder.setContentText("Watch out bro!");
                notificationManager.notify(1,builder.build());
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.zero:
                    numero("0");
                    break;

                case R.id.uno:
                    numero("1");
                    break;

                case R.id.dos:
                    numero("2");
                    break;

                case R.id.tres:
                    numero("3");
                    break;

                case R.id.cuatro:
                    numero("4");
                    break;

                case R.id.cinco:
                    numero("5");
                    break;

                case R.id.seis:
                    numero("6");
                    break;

                case R.id.siete:
                    numero("7");
                    break;

                case R.id.ocho:
                    numero("8");
                    break;

                case R.id.nueve:
                    numero("9");
                    break;

                case R.id.decimal:
                    if (!esDecimal && !ansEnPantalla) {
                        if (esperandoNumero) {
                            operInterna += "0";
                            textPantalla += "0";
                        }
                        operInterna += ".";
                        textPantalla += ".";
                        esperandoNumero = false;
                        justClickIgual = false;
                        esDecimal = true;
                    }
                    break;

                case R.id.igual:
                    if (!esperandoNumero) {
                        if (esNegativo) textPantalla += ")";
                        operInterna += "_)";
                        while (parsAbiertos > 0) {
                            textPantalla += ")";
                            operInterna += "_)";
                            --parsAbiertos;
                        }
                        Lst = new ArrayList<>(Arrays.asList(operInterna.split("_")));

                        Log.v("LIST", operInterna);
                        for (int i = 0; i < Lst.size(); ++i) Log.v("LIST",Lst.get(i));

                        operarExpresio();
                        justClickIgual = true;
                        operInterna = "(_";
                    }
                    break;

                case R.id.sum:
                    if (!esperandoNumero) {
                        operInterna += "_s_";
                        if (esNegativo) textPantalla += ")";
                        textPantalla += "+";
                        esNegativo = false;
                    }
                    if (justClickIgual && !answer.equals("E")) {
                        textPantalla = answer + "+";
                        operInterna = answer + "_s_";
                        justClickIgual = false;
                    }
                    esperandoNumero = true;
                    esDecimal = false;
                    ansEnPantalla = false;
                    break;

                case R.id.resta:
                    if (!esperandoNumero) {
                        operInterna += "_r_";
                        if (esNegativo) textPantalla += ")";
                        textPantalla += "-";
                        esperandoNumero = true;
                        esNegativo = false;
                        esDecimal = false;
                        ansEnPantalla = false;
                    }
                    else if (!esNegativo) {
                        operInterna += "-";
                        textPantalla += "(-";
                        esNegativo = true;
                    }
                    if (justClickIgual && !answer.equals("E")) {
                        textPantalla = answer + "-";
                        operInterna = answer + "_r_";
                        justClickIgual = false;
                        esperandoNumero = true;
                        esDecimal = false;
                        ansEnPantalla = false;
                    }
                    break;

                case R.id.mult:
                    if (!esperandoNumero) {
                        operInterna += "_m_";
                        if (esNegativo) textPantalla += ")";
                        textPantalla += "×";
                        esNegativo = false;
                    }
                    if (justClickIgual && !answer.equals("E")) {
                        textPantalla = answer + "×";
                        operInterna = answer + "_m_";
                        justClickIgual = false;
                    }
                    esperandoNumero = true;
                    esDecimal = false;
                    ansEnPantalla = false;
                    break;

                case R.id.div:
                    if (!esperandoNumero) {
                        operInterna += "_d_";
                        if (esNegativo) textPantalla += ")";
                        textPantalla += "÷";
                        esNegativo = false;
                    }
                    if (justClickIgual && !answer.equals("E")) {
                        textPantalla = answer + "÷";
                        operInterna = answer + "_d_";
                        justClickIgual = false;
                    }
                    esperandoNumero = true;
                    esDecimal = false;
                    ansEnPantalla = false;
                    break;

                case R.id.aPar:
                    ++parsAbiertos;
                    if (!esperandoNumero) {
                        operInterna += "_m_";
                        textPantalla += "×";
                        esperandoNumero = true;
                    }
                    operInterna += "(_";
                    textPantalla += "(";
                    justClickIgual = false;
                    break;

                case R.id.cPar:
                    if (!esperandoNumero && parsAbiertos > 0) {
                        operInterna += "_)";
                        textPantalla += ")";
                        --parsAbiertos;
                    }
                    break;

                case R.id.answer:
                    if (!answer.equals("E") && !ansEnPantalla) {
                        textPantalla += answer;
                        operInterna += answer;
                        esperandoNumero = false;
                        ansEnPantalla = true;
                    }
                    break;

                case R.id.clear:
                    if (!esperandoNumero && !justClickIgual && textPantalla.length() > 0) {
                        if (textPantalla.charAt(textPantalla.length() - 1) == '.') esDecimal = false;
                        textPantalla = textPantalla.substring(0, textPantalla.length() - 1);
                        if (operInterna.charAt(operInterna.length() - 2) == '(') { // Borras abrir parentesis
                            operInterna = operInterna.substring(0, operInterna.length() - 2);
                            --parsAbiertos;
                        }
                        else if (operInterna.charAt(operInterna.length() - 1) == '_')
                            operInterna = operInterna.substring(0, operInterna.length() - 3); // Borras operacion
                        else if (operInterna.charAt(operInterna.length() - 1) == ')') {
                            operInterna = operInterna.substring(0, operInterna.length() - 2); // Borras cerrar parentesis
                            ++parsAbiertos;
                        }
                        else if (esNumero(operInterna.charAt(operInterna.length() - 1))) {
                            operInterna = operInterna.substring(0, operInterna.length() - 1); // Borras numeros
                        }
                        esperandoNumero = !esNumero(operInterna.charAt(operInterna.length() - 1)) ||
                                operInterna.charAt(operInterna.length() - 1) != '.';
                    }
                    else if (esperandoNumero && textPantalla.length() > 0) {
                        if (operInterna.charAt(operInterna.length() - 2) == '(') { // Borras abrir parentesis
                            operInterna = operInterna.substring(0, operInterna.length() - 2);
                            --parsAbiertos;
                            textPantalla = textPantalla.substring(0, textPantalla.length() - 1);
                        }
                        else if (operInterna.charAt(operInterna.length() - 1) == '_') {
                            operInterna = operInterna.substring(0, operInterna.length() - 3); // Borras operacion
                            esperandoNumero = false;
                            textPantalla = textPantalla.substring(0, textPantalla.length() - 1);
                        }
                    }
                    break;

                case R.id.allClear:
                    textPantalla = "";
                    operInterna  = "(_";
                    result.setText("");
                    justClickIgual = false;
                    esperandoNumero = true;
                    esNegativo = false;
                    esDecimal = false;
                    ansEnPantalla = false;
                    break;

                default:
                    Log.v("Calculadora","Boton no implementado");
            }

            if (esperandoNumero) ansEnPantalla = false;
            if (!justClickIgual) operation.setText(textPantalla);
            if (!answer.equals("E") && justClickIgual) {
                result.setText("= "+answer);
                textPantalla = "";
            }
            else result.setText("");

        }
    };
}
