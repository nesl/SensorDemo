package edu.ucla.nesl.testsensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener, SensorEventListener {
	private SensorManager sensorManager;
	private Sensor mAcc;
	private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcc = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			sensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if (v.getId() == R.id.button2) {
			sensorManager.unregisterListener(this);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Log.i("Activity", "Acc data x=" + event.values[0] + " y=" + event.values[1] + " z=" + event.values[2]);
	}
    
}
