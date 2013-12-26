package edu.project.academico;

import java.util.ArrayList;
import com.project.academico.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
 
public class Historial extends Activity {
    private ListView lv;
    private EditText et;
    private String listview_array[] = { "Dr. Carlos Monsalve", "Msc. Juan Mite", "Ing. Danny Ponce", "Ing. César Madrid", "Ing. Mariuxi Salazar", "Ing. Tania Sanchez", "Ing. Gustavo Londa", "Ing. Chimecho", "Ing. Mario Izquierdo", "Lcdo. Daniel Muñoz" };
    private ArrayList<String> array_sort = new ArrayList<String>();
    int textlength = 0;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
 
        lv = (ListView) findViewById(R.id.ListView01);
        et = (EditText) findViewById(R.id.EditText01);
         
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listview_array));
        
 
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // Abstract Method of TextWatcher Interface.
            }
 
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // Abstract Method of TextWatcher Interface.
            }
 
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = et.getText().length();
                array_sort.clear();
                 
                for (int i = 0; i < listview_array.length; i++) {
                    if (textlength <= listview_array[i].length()) {
                        if (et.getText().toString().equalsIgnoreCase((String) listview_array[i].subSequence(0, textlength))) {
                            array_sort.add(listview_array[i]);
                        }
                    }
                }
                 
                lv.setAdapter(new ArrayAdapter<String>(Historial.this, android.R.layout.simple_list_item_1, array_sort));
            }
        });
    }
}