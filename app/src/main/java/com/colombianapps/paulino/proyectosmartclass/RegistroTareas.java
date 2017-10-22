package com.colombianapps.paulino.proyectosmartclass;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistroTareas extends AppCompatActivity {



    //normal


    Spinner combomaterias;
    ArrayList<String> listamaterias;
    ArrayList<Asignatuas> materiaslist;
    //aca lo que no es de spiner, registrar tareascomo tal!!

    EditText fechaentregar;



    ConexionSQLiteHelper conn;
    //alarma

    private EditText t4, t3,t5,t6,t7;
    private ConexionSQLiteHelper admin;
    private SQLiteDatabase bd;
    private ContentValues registro;
    private EditText tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    // date
    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    // hora
    private int minute;
    private int hour;
    private TimePicker timePicker;
    private TextView textViewTime;
    private Button button;
    private static final int TIME_DIALOG_ID = 998;
    Calendar calendario = Calendar.getInstance();
    int hora, min,dia,mes,ano,hora11;
    String cadenaF, cadenaH,fecha_sistema,hora_sistema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //spiner, botones base datos tareas y demas

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tareas);

        //diferente a spinner

        fechaentregar = (EditText) findViewById(R.id.fechaentrega);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_asignaturas",null,1);

        combomaterias = (Spinner) findViewById(R.id.comboasignaturasentareas);



        consultarlistamaterias();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,listamaterias);
        combomaterias.setAdapter(adaptador);

        combomaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0) {
                    t3.setText(materiaslist.get(position-1).getNickname());


                }else {

                    t3.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //alarma y base datos alatma con date y time picker

        admin = new ConexionSQLiteHelper(this, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema=mes+"-"+dia+"-"+ano+" ";
        hora_sistema = hora+":"+min;
        setCurrentDateOnView();
        addListenerOnButton();
        // hora
        setCurrentTimeOnView();
        //cambiar variables!!!!!!!!!?????'
        t3 = (EditText) findViewById(R.id.mostrarnickname);
        t5= (EditText) findViewById(R.id.descripcion_tarea);
        t6= (EditText) findViewById(R.id.fecharealizacion);
        t7= (EditText) findViewById(R.id.horarealizacion);
        servicio();
    }
    public void servicio() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
        int intervalMillis = 1 * 3 * 1000; //3 segundos
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }




    public void onClickalarma(View view) {

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, vars.bd, null, vars.version);
        SQLiteDatabase bd = admin.getReadableDatabase();
        bd = admin.getWritableDatabase();
        registro = new ContentValues();
        registro.put("encabezado", t3.getText().toString());
        registro.put("mensaje", t5.getText().toString());//nombre del campo
        registro.put("fecha", t6.getText().toString());
        registro.put("hora", t7.getText().toString());
        bd.insert("alarma", null, registro);//nombre de la tabla
        bd.close();
        Toast.makeText(this, "alarma establecida", Toast.LENGTH_LONG).show();
    }

    public void setCurrentTimeOnView() {

        textViewTime = (EditText) findViewById(R.id.horarealizacion);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);



    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btn_fecharealizacion);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
        // hora

        button = (Button) findViewById(R.id.btn_horarealizacion);
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }
    // hora

    public void setCurrentDateOnView() {

        tvDisplayDate = (EditText) findViewById(R.id.fecharealizacion);
        dpResult = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }





    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case TIME_DIALOG_ID:



                return new TimePickerDialog(this, timePickerListener, hour, minute,false);

        }
        return null;
    }
    // hora
    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

            hour = selectedHour;

            minute = selectedMinute;
            textViewTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);

        }



    };


    private static String padding_str(int c) {

        if (c >= 10)
            return String.valueOf(c);

        else

            return "0" + String.valueOf(c);
    }

    // hora

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);


        }
    };

    //aquiiii

    private void consultarlistamaterias() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Asignatuas materia= null;
        materiaslist=new ArrayList<Asignatuas>();

        Cursor cursor =db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ASIGNATURA,null);

        while (cursor.moveToNext())  {

            materia =new Asignatuas();
            materia.setNombremateria(cursor.getString(0));
            materia.setNickname(cursor.getString(1));
            materia.setMaestro(cursor.getString(2));



            materiaslist.add(materia);
        }

        obtenerlista();
    }

    private void obtenerlista() {
        listamaterias=new ArrayList<String>();
        listamaterias.add("Seleccione");

        for (int i=0; i<materiaslist.size();i++)  {

            listamaterias.add(materiaslist.get(i).getNombremateria()+" - "+materiaslist.get(i).getNickname());

        }

    }

    private void registrartareas() {


        int idcombo= (int) combomaterias.getSelectedItemId();


        if (idcombo!=0) {
            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_tareas",null,1);
            SQLiteDatabase db=conn.getWritableDatabase();
            ContentValues values= new ContentValues();

            values.put(Utilidades.CAMPO_NICKNAMENEW,t3.getText().toString());
            values.put(Utilidades.CAMPO_DESCRIPTNEW,t5.getText().toString());
            values.put(Utilidades.CAMPO_FECHAENTREGANEW,fechaentregar.getText().toString());
            values.put(Utilidades.CAMPO_FECHAREALIARNEW, t6.getText().toString());
            values.put(Utilidades.CAMPO_HORAREALIZARNEW, t7.getText().toString());


            //intent mandar al main ina vez presionado guaradar tarea

            Long idresultanter=db.insert(Utilidades.TABLA_TAREANEW,Utilidades.CAMPO_DESCRIPTNEW,values);

            t5.setText("");
            limpiar();
            Toast.makeText(getApplicationContext(),"Tarea Asignada",Toast.LENGTH_LONG).show();
            db.close();

            Intent intento = new Intent(RegistroTareas.this,MainActivity.class);
            startActivity(intento);

        }else{

            Toast.makeText(getApplicationContext(),"Debe seleccionar una asignatura",Toast.LENGTH_LONG).show();

        }

    }

    private void limpiar() {
        t3.setText("");
        fechaentregar.setText("");
        t6.setText("");
        t7.setText("");

    }

    public void onClicktarea(View view) {

        registrartareas();


    }
}

