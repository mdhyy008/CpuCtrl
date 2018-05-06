package com.dabai.KernelWork;
import android.support.v7.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.support.design.widget.*;
import android.view.*;

public class cpuinfo extends AppCompatActivity
{

	TextView te1,te2;
	SeekBar se1,se2;

	cpuUtil cu;
	Context mContext;

	int pro1,pro2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_info_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		te1 = (TextView)findViewById(R.id.cpu_text_max);
		te2 = (TextView)findViewById(R.id.cpu_text_min);

		se1 = (SeekBar)findViewById(R.id.cpu_seek_max);
		se2 = (SeekBar)findViewById(R.id.cpu_seek_min);

		cu = new cpuUtil();

		init();


		se1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					switch (progress)
					{
						case 0:
							pro1 = 652;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;
						case 1:
							pro1 = 1036;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;	
						case 2:
							pro1 = 1401;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;	
						case 3:
							pro1 = 1689;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;
						case 4:
							pro1 = 1804;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;
						case 5:
							pro1 = 1958;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;
						case 6:
							pro1 = 2016;
							te1.setText("最大频率: " + pro1 + "MHz");
							break;
					}
				}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{
				}
				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{
					String cmd[]={"chmod 755 /sys/bus/cpu/devices/cpu0/cpufreq/scaling_max_freq",
						"echo " + pro1 + "000 > /sys/bus/cpu/devices/cpu0/cpufreq/scaling_max_freq"
						,"chmod 444 /sys/bus/cpu/devices/cpu0/cpufreq/scaling_max_freq"};
					new shell().execCommand(cmd,true);	
					snack("最大频率设置为:" + pro1 + "MHz");
				}
			});
		se2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					switch (progress)
					{
						case 0:
							pro2 = 652;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;
						case 1:
							pro2 = 1036;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;	
						case 2:
							pro2 = 1401;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;	
						case 3:
							pro2 = 1689;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;
						case 4:
							pro2 = 1804;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;
						case 5:
							pro2 = 1958;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;
						case 6:
							pro2 = 2016;
							te2.setText("最小频率: " + pro2 + "MHz");
							break;
					}
				}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{
				}
				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{
					
					String cmd[]={"chmod 755 /sys/bus/cpu/devices/cpu0/cpufreq/scaling_min_freq",
						"echo " +pro2+ "000 > /sys/bus/cpu/devices/cpu0/cpufreq/scaling_min_freq"
						,"chmod 444 /sys/bus/cpu/devices/cpu0/cpufreq/scaling_min_freq"};
					new shell().execCommand(cmd,true);
					snack("最小频率设置为:" + pro2 + "MHz");
				}
			});

	}





	public void init()
	{
		
		//重点！！！拿到最大最小频率才行
		setMaxCpu(cu.getCpuFrequence());
		setMinCpu(cu.getMinCpuFreq());
		//频率进度条逻辑
		int maxCpu = Integer.parseInt(cu.getCpuFrequence()) / 1000;
		int minCpu = Integer.parseInt(cu.getMinCpuFreq()) / 1000;

		if (maxCpu > 2000)
		{
			se1.setProgress(6);
		}
		else if (maxCpu > 1900)
		{
			se1.setProgress(5);
		}
		else if (maxCpu > 1800)
		{
			se1.setProgress(4);
		}
		else if (maxCpu > 1600)
		{
			se1.setProgress(3);
		}
		else if (maxCpu > 1400)
		{
			se1.setProgress(2);
		}
		else if (maxCpu > 1000)
		{
			se1.setProgress(1);
		}
		else if (maxCpu > 650)
		{
			se1.setProgress(0);
		}



		if (minCpu > 2000)
		{
			se2.setProgress(6);
		}
		else if (minCpu > 1900)
		{
			se2.setProgress(5);
		}
		else if (minCpu > 1800)
		{
			se2.setProgress(4);
		}
		else if (minCpu > 1600)
		{
			se2.setProgress(3);
		}
		else if (minCpu > 1400)
		{
			se2.setProgress(2);
		}
		else if (minCpu > 1000)
		{
			se2.setProgress(1);
		}
		else if (minCpu > 650)
		{
			se2.setProgress(0);
		}


	}

	public void setMaxCpu(String a)
	{
		te1.setText("最大频率: " + Integer.parseInt(a) / 1000 + "MHz");
	}
	public void setMinCpu(String a)
	{
		te2.setText("最小频率: " + Integer.parseInt(a) / 1000 + "MHz");
	}

    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) {  
            case android.R.id.home:  
                finish();  
                break;  
        }  
        return true;  
    }
	
	public void toast(String i)
	{
		Toast.makeText(getApplicationContext(), i, 1).show();
	}
	public void snack(String a)
	{
		Snackbar.make(getWindow().getDecorView(), a,Snackbar.LENGTH_LONG).show();
	}
}
