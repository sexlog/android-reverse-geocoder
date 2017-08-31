package br.com.esapiens.geocoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import java.util.Locale;

import br.com.esapiens.geocoder_library.LocaleRetriever;
import br.com.esapiens.geocoder_library.PromiseLocaleRetriever;
import br.com.esapiens.geocoder_library.SimpleLocation;
import br.com.esapiens.geocoder_library.parsers.CityNameParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.textView);

        SimpleLocation simpleLocation = new SimpleLocation(65.54027, 116.433105);

        LocaleRetriever localeRetriever = new LocaleRetriever(this, Locale.getDefault(), new CityNameParser(false));
        PromiseLocaleRetriever promiseLocaleRetriever = new PromiseLocaleRetriever(localeRetriever);

        promiseLocaleRetriever.findWithGoogleApi(simpleLocation)
                .done(new DoneCallback<String>() {
                    @Override
                    public void onDone(String result) {
                        textView.setText(result);
                    }
                })
                .fail(new FailCallback<Throwable>() {
                    @Override
                    public void onFail(Throwable result) {
                        Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
