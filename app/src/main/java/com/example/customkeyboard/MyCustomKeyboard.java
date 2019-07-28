package com.example.customkeyboard;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.util.StringTokenizer;

public class MyCustomKeyboard extends LinearLayout implements View.OnClickListener {

    private static final String TAG = MyCustomKeyboard.class.getName();
    // keyboard keys (buttons)
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton10;
    private Button mButtonEnter;

    private EditText editText;

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    private SparseArray<Drawable> keyValues = new SparseArray<>();

    // Our communication link to the EditText
    private InputConnection inputConnection;


    // constructors
    public MyCustomKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyCustomKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        mButton1 =  findViewById(R.id.button_1);
        mButton2 =  findViewById(R.id.button_2);
        mButton3 =  findViewById(R.id.button_3);
        mButton4 =  findViewById(R.id.button_4);
        mButton5 =  findViewById(R.id.button_5);
        mButton6 =  findViewById(R.id.button_6);
        mButton7 =  findViewById(R.id.button_7);
        mButton8 =  findViewById(R.id.button_8);
        mButton9 =  findViewById(R.id.button_9);
        mButton10 =  findViewById(R.id.button_10);
        mButtonEnter =  findViewById(R.id.button_enter);

        // draw images in the keyboard
        drawImages();

        // set button click listeners
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton10.setOnClickListener(this);
        mButtonEnter.setOnClickListener(this);
    }

    // draw Image in the keyboard
    public void drawImages(){
        mButton1.setCompoundDrawables(getDrawableImage("1.png"), null, null, null );
        mButton2.setCompoundDrawables(getDrawableImage("2.png"), null, null, null );
        mButton3.setCompoundDrawables(getDrawableImage("3.png"), null, null, null );
        mButton4.setCompoundDrawables(getDrawableImage("1.png"), null, null, null );
        mButton5.setCompoundDrawables(getDrawableImage("2.png"), null, null, null );
        mButton6.setCompoundDrawables(getDrawableImage("3.png"), null, null, null );
        mButton7.setCompoundDrawables(getDrawableImage("1.png"), null, null, null );
        mButton8.setCompoundDrawables(getDrawableImage("2.png"), null, null, null );
        mButton9.setCompoundDrawables(getDrawableImage("3.png"), null, null, null );
        mButton10.setCompoundDrawables(getDrawableImage("3.png"), null, null, null );


        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, getDrawableImage("1.png"));
        keyValues.put(R.id.button_2, getDrawableImage("2.png"));
        keyValues.put(R.id.button_3, getDrawableImage("3.png"));
        keyValues.put(R.id.button_4, getDrawableImage("1.png"));
        keyValues.put(R.id.button_5, getDrawableImage("1.png"));
        keyValues.put(R.id.button_6, getDrawableImage("2.png"));
        keyValues.put(R.id.button_7, getDrawableImage("3.png"));
        keyValues.put(R.id.button_8, getDrawableImage("3.png"));
        keyValues.put(R.id.button_9, getDrawableImage("3.png"));
        keyValues.put(R.id.button_10, getDrawableImage("3.png"));

        keyValues.put(R.id.button_enter, null);
    }


    // get drawable image
    private Drawable getDrawableImage(String path) {
        AssetManager mngr = getContext().getAssets();
        InputStream in = null;
        try {
            in = mngr.open("emoticons/" + path);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        Bitmap bitmap = BitmapFactory.decodeStream(in, null, null);
        Drawable drawableIcon = new BitmapDrawable(bitmap);
        drawableIcon.setBounds( 0, 0, 60, 60 );
        return drawableIcon;
    }

    // Add image into EditText
    public void addImageInEditText(final Drawable d){
        final String index="1";

        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                return d;
            }
        };


        Spanned cs = Html.fromHtml("<img src ='"+ index +"'/>", imageGetter, null);
        editText.getText().insert(editText.getSelectionStart(), cs);
    }


    @Override
    public void onClick(View v) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        addImageInEditText(keyValues.get(v.getId(), null));



    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }
}