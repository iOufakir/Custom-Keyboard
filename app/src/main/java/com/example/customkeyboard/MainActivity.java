package com.example.customkeyboard;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v13.view.inputmethod.InputContentInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import java.io.InputStream;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyEditText myEditText = findViewById(R.id.editText);
        MyCustomKeyboard keyboard =  findViewById(R.id.keyboard);
        keyboard.setEditText(myEditText);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = myEditText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

    }


}
