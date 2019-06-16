package com.ramotion.cardslider.examples.simple.detection;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.ramotion.cardslider.examples.simple.DescriptionFragment;
import com.ramotion.cardslider.examples.simple.detection.tflite.Classifier;

import java.util.List;
import java.util.Locale;


/**
 * Created by Zoltan Szabo on 4/25/18.
 */

public abstract class TextToSpeechActivity extends CameraActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    private String lastRecognizedClass = "";
    private String SpeakString = "";

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("erreur", "Text to speech error: This Language is not supported");
            }
        } else {
            Log.e("erreur", "Text to speech: Initilization Failed!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToSpeech = new TextToSpeech(this, this);
    }

    protected void speak(List<Classifier.Recognition> results) {
        if (!(results.isEmpty() || lastRecognizedClass.equals(results.get(0).getTitle()))) {
            switch (results.get(0).getTitle()){
                case "avenue" :SpeakString="l'horloge de l'avenue Habib Bourguiba"; lastRecognizedClass="avenue"; break;
                case "diwan" : SpeakString="porte diwan Sfax";lastRecognizedClass="diwan"; break;
                case "bourgiba" :SpeakString="Statue Habib Bourguiba";lastRecognizedClass="bourgiba"; break;
            }

//            lastRecognizedClass = results.get(0).getTitle();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(SpeakString, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                textToSpeech.speak(SpeakString, TextToSpeech.QUEUE_FLUSH, null);
            }

            DescriptionFragment dialog = new DescriptionFragment(results.get(0).getTitle());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, "");
        }
    }

}
