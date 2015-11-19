package com.msonasath.todobasic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditItem;
    Button bttnSave;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        position = getIntent().getStringExtra("pos");
        String item = getIntent().getStringExtra("item");
        etEditItem.append(item);
    }

    public void onSubmit(View view) {
        Intent resutIntent = new Intent();
        resutIntent.putExtra("item", etEditItem.getText().toString());
        resutIntent.putExtra("pos", position);
        setResult(RESULT_OK, resutIntent); // set result code and bundle data for response
        finish();
    }
}
