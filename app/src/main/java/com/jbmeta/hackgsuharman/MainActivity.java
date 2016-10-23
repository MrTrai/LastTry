package com.jbmeta.hackgsuharman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;

import java.util.Random;

import java.util.ArrayList;
import java.lang.String;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Random randomPattern;
    PulseColor bgColor;
    PulseHandlerInterface pulseHandler;

    protected static final int RESULT_SPEECH = 1;
    private String s;
    int randomNumber = 0, r, g, b;
    private ImageButton btnSpeak;
    private TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pulseHandler = new ImplementPulseHandler();

        pulseHandler.registerPulseNotifiedListener(new PulseNotifiedListener() {
            @Override
            public void onConnectMasterDevice() {

            }

            @Override
            public void onDisconnectMasterDevice() {

            }

            @Override
            public void onRetBrightness(int i) {

            }

            @Override
            public void onLEDPatternChanged(PulseThemePattern pulseThemePattern) {

            }

            @Override
            public void onSoundEvent(int i) {

            }

            @Override
            public void onRetCaptureColor(PulseColor pulseColor) {

            }

            @Override
            public void onRetCaptureColor(byte b, byte b1, byte b2) {

            }

            @Override
            public void onRetSetDeviceInfo(boolean b) {

            }

            @Override
            public void onRetGetLEDPattern(PulseThemePattern pulseThemePattern) {

            }

            @Override
            public void onRetRequestDeviceInfo(DeviceModel[] deviceModels) {

            }

            @Override
            public void onRetSetLEDPattern(boolean b) {

            }
        });

        pulseHandler.SetBrightness(10);
        pulseHandler.ConnectMasterDevice(this);

        //button Google
        txtText = (TextView) findViewById(R.id.txtText);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    //Google button and switch
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));

                    s = text.get(0);
                    s = s.toLowerCase();

                    switch (s) {
                        case "excited": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Fire);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "FIRE", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "angry": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Canvas);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "CANVAS", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "relax": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Firefly);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "FIREFLY", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "crazy": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Firework);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "FIREWORK", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "sleepy": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Hourglass);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "HOURGLASS", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "thoughtful": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Rain);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "RAIN", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "jazzy": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Ripple);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "RIPPLE", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "sparkle": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Star);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "STAR", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "dance": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Traffic);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "TRAFFIC", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "do something": {
                            pulseHandler.SetLEDPattern(PulseThemePattern.PulseTheme_Wave);
                            Toast.makeText(getApplicationContext(), "LED pattern shuffled successfully to " + "WAVE", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    break;
                }

            }
        }
    }

    public void connectSpeaker(View v) {
        pulseHandler.ConnectMasterDevice(this);
        Toast.makeText(getApplicationContext(), "Speaker Connected Successfully", Toast.LENGTH_LONG).show();
    }

    public void setDeviceName(View v) {
        pulseHandler.SetDeviceName("Jay's JBL", 0);
        Toast.makeText(getApplicationContext(), "Speaker Name Changed Successfully", Toast.LENGTH_LONG).show();
    }


    public void shuffleColor(View v) {
        randomPattern = new Random();
        randomNumber = randomPattern.nextInt(3);

        switch (randomNumber) {
            case 0: {

                bgColor = new PulseColor(getByte(255), getByte(0), getByte(0));
                pulseHandler.SetBackgroundColor(bgColor, true);
                break;
            }
            case 1: {
                bgColor = new PulseColor(getByte(97), getByte(255), getByte(0));
                pulseHandler.SetBackgroundColor(bgColor, true);
                break;
            }
            case 2: {
                bgColor = new PulseColor(getByte(0), getByte(0), getByte(255));
                PulseColor foreColor = new PulseColor(getByte(255), getByte(0), getByte(0));
                pulseHandler.SetCharacterPattern('/', foreColor, bgColor, true);
                break;
            }
        }

        bgColor = null;
    }


    public void setAllRandom(View v) {

        PulseColor[] randomBitmap = new PulseColor[99];
        PulseColor randomColor, white, red;
        int randomR, randomG, randomB;

        randomPattern = new Random();
        randomBitmap[0] = new PulseColor(getByte(0), getByte(0), getByte(0));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 11; j++) {
                if (i % 9 <= 2) {
                    white = new PulseColor(getByte(255), getByte(165), getByte(0));
                    randomBitmap[i * 11 + j] = white;
                } else if (i % 9 <= 5) {
                    red = new PulseColor(getByte(255), getByte(255), getByte(255));
                    randomBitmap[i * 11 + j] = red;

                } else {
                    red = new PulseColor(getByte(0), getByte(255), getByte(0));
                    randomBitmap[i * 11 + j] = red;
                }
            }
        }

        pulseHandler.SetColorImage(randomBitmap);
    }

    private PulseColor[] makeVerticalLine(PulseColor _color, int _startrow, int _startcolumn, int _height, byte[] _bgColor) {

        PulseColor[] verticalLineBitmap = new PulseColor[99];

        if (_bgColor == null) {
            _bgColor[0] = getByte(0);
            _bgColor[1] = getByte(0);
            _bgColor[2] = getByte(0);
        }

        for (int i = 0; i < 99; i++) {
            verticalLineBitmap[i] = new PulseColor(_bgColor[0], _bgColor[1], _bgColor[2]);
        }

        return verticalLineBitmap;

    }

    private PulseColor[] makeHorizontalLine(PulseColor _color, int _startrow, int _startcolumn, int _width, byte[] _bgColor) {

        PulseColor[] horizontalLineBitmap = new PulseColor[99];

        if (_bgColor == null) {
            _bgColor[0] = getByte(0);
            _bgColor[1] = getByte(0);
            _bgColor[2] = getByte(0);
        }

        for (int i = 0; i < 99; i++) {
            horizontalLineBitmap[i] = new PulseColor(_bgColor[0], _bgColor[1], _bgColor[2]);
        }

        return horizontalLineBitmap;
    }

    private PulseColor[] makePoint(PulseColor _color, int _row, int _column, byte[] _bgColor) {

        PulseColor[] pointBitmap = new PulseColor[99];

        if (_bgColor == null) {
            _bgColor[0] = getByte(0);
            _bgColor[1] = getByte(0);
            _bgColor[2] = getByte(0);
        }

        for (int i = 0; i < 99; i++) {
            pointBitmap[i] = new PulseColor(_bgColor[0], _bgColor[1], _bgColor[2]);
        }

        pointBitmap[11 * _row + _column] = _color;

        return pointBitmap;
    }



    private byte getByte(int intval) {
        return (byte) intval;
    }


}