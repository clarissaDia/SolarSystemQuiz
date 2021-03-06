package com.example.android.solarsystemquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * global variable that keeps track of the score
     * right answers
     * RadioGroups for the resetScore method
     */

    int score = 0;
    EditText nameField;
    RadioButton mercuryAnswer;
    RadioButton eightMinutesAnswer;
    RadioButton jSunAnswer;
    RadioButton jupiterAnswer;
    RadioButton milkyWayAnswer;
    RadioButton noMoonsAnswer;
    RadioButton giantStormAnswer;
    CheckBox ringsSurrounded;
    CheckBox tiltedOrbit;
    CheckBox oneYear;
    CheckBox alphaCentauri;
    CheckBox hydrogenHelium;
    CheckBox redDwarf;
    EditText plutoAnswer;

    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioGroup radioGroup4;
    RadioGroup radioGroup5;
    RadioGroup radioGroup6;
    RadioGroup radioGroup7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name_field);
        mercuryAnswer = findViewById(R.id.mercury_button);
        eightMinutesAnswer = findViewById(R.id.eight_minutes_button);
        jSunAnswer = findViewById(R.id.j_sun_button);
        jupiterAnswer = findViewById(R.id.jupiter_button);
        milkyWayAnswer = findViewById(R.id.milky_way_button);
        noMoonsAnswer = findViewById(R.id.no_moons_button);
        giantStormAnswer = findViewById(R.id.giant_storm_button);
        ringsSurrounded = findViewById(R.id.rings_surrounded_cb);
        tiltedOrbit = findViewById(R.id.tilted_orbit_cb);
        oneYear = findViewById(R.id.one_year_cb);
        alphaCentauri = findViewById(R.id.alpha_centauri_cb);
        hydrogenHelium = findViewById(R.id.hydrogen_helium_cb);
        redDwarf = findViewById(R.id.red_dwarf_cb);
        plutoAnswer = findViewById(R.id.pluto_answer);

        radioGroup1 = findViewById(R.id.first_radio_group);
        radioGroup2 = findViewById(R.id.second_radio_group);
        radioGroup3 = findViewById(R.id.third_radio_group);
        radioGroup4 = findViewById(R.id.fourth_radio_group);
        radioGroup5 = findViewById(R.id.fifth_radio_group);
        radioGroup6 = findViewById(R.id.sixth_radio_group);
        radioGroup7 = findViewById(R.id.seventh_radio_group);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            float x = ev.getRawX() + view.getLeft();
            float y = ev.getRawY() + view.getTop();
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
            nameField.clearFocus();
            plutoAnswer.clearFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    /*** calculates the score based on the right answers**/

    public int calculateScore(int score) {
        boolean firstQuestion =
                mercuryAnswer.isChecked();
        if (firstQuestion) score += 1;

        boolean secondQuestion =
                eightMinutesAnswer.isChecked();
        if (secondQuestion) {
            score += 1;
        }
        boolean thirdQuestion =
                jSunAnswer.isChecked();
        if (thirdQuestion) {
            score += 1;
        }
        boolean fourthQuestion =
                jupiterAnswer.isChecked();
        if (fourthQuestion) {
            score += 1;
        }
        boolean fifthQuestion =
                milkyWayAnswer.isChecked();
        if (fifthQuestion) {
            score += 1;
        }
        boolean sixthQuestion =
                noMoonsAnswer.isChecked();
        if (sixthQuestion) {
            score += 1;
        }
        boolean seventhQuestion =
                giantStormAnswer.isChecked();
        if (seventhQuestion) {
            score += 1;
        }
        boolean eightQuestion1 =
                tiltedOrbit.isChecked();
        boolean eightQuestion2 =
                ringsSurrounded.isChecked();
        boolean eightQuestion3 =
                oneYear.isChecked();
        if (eightQuestion1 && eightQuestion2 && !eightQuestion3) {
            score += 1;
        }
        boolean ninthQuestion1 =
                alphaCentauri.isChecked();
        boolean ninthQuestion2 =
                hydrogenHelium.isChecked();
        boolean ninthQuestion3 =
                redDwarf.isChecked();
        if (ninthQuestion1 && !ninthQuestion2 && ninthQuestion3) {
            score += 1;
        }
        String tenthQuestion = plutoAnswer.getText().toString();
        tenthQuestion = tenthQuestion.trim();
        if (tenthQuestion.contains("Pluto") || tenthQuestion.contains("Plutone")) {
            score += 1;
        }
        return (score);
    }

    /*** This method  triggers when the reset button is pressed**/

    public void resetAnswers(View view) {
        nameField.setText("");
        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
        radioGroup3.clearCheck();
        radioGroup4.clearCheck();
        radioGroup5.clearCheck();
        radioGroup6.clearCheck();
        radioGroup7.clearCheck();
        ringsSurrounded.setChecked(false);
        tiltedOrbit.setChecked(false);
        oneYear.setChecked(false);
        alphaCentauri.setChecked(false);
        hydrogenHelium.setChecked(false);
        redDwarf.setChecked(false);
        plutoAnswer.setText("");
    }

    /*** it triggers when the Submit Answers button is pressed and it shows a Toast message with a score summary, based on the right answers given**/

    public void submitAnswer(View view) {
        String name = nameField.getText().toString();
        int finalScore = calculateScore(score);
        String finalScoreMessage = name + "\b" + getString(R.string.you) + "\b" + finalScore + "\b" + getString(R.string.questions);

        if (finalScore >= 8) {
            Toast.makeText(this, finalScoreMessage + "\n" +
                    getString(R.string.toast1), Toast.LENGTH_LONG).show();
        } else if (finalScore <= 7 && finalScore > 5) {
            Toast.makeText(this, finalScoreMessage + "\n" + getString(R.string.toast2),
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, finalScoreMessage + "\n" + getString(R.string.toast3) + "\n" + getString(R.string.again),
                    Toast.LENGTH_LONG).show();
        }

        /*** This intent opens a second activity in which the user will share the score.**/

        Intent shareIntent = new Intent(this, Main2Activity.class);
        String message = getString(R.string.scored) + "\b" + finalScore + "\b" + getString(R.string.share_mess);
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putExtra("message", message);
        shareIntent.setType("message");
        startActivity(shareIntent);
    }
}
