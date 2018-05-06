package com.dabai.KernelWork;

import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.widget.*;
import java.io.*;
import java.util.regex.*;
import org.apache.commons.codec.binary.*;

import java.lang.Process;
import android.view.*;
import android.content.*;
import org.apache.http.util.*;

public class MainActivity extends AppCompatActivity {
TextView te0,te1,te2;
EditText ed1,ed2,ed3,ed4,ed5,ed6;
Context mContext;
CurCpu cur;
cpuUtil cu;

String c0,c1,c2,c3,c4,c5,c6,c7;

String ipu1,ipu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
	
		te0 = (TextView)findViewById(R.id.main_activityTextView0);
		te1 = (TextView)findViewById(R.id.main_activityTextView);
		te2 = (TextView)findViewById(R.id.main_activityTextView1);
		
		ed1 = (EditText)findViewById(R.id.main_activityEditText1);
		ed2 = (EditText)findViewById(R.id.main_activityEditText2);
		ed3 = (EditText)findViewById(R.id.main_activityEditText3);
		ed4 = (EditText)findViewById(R.id.main_activityEditText4);
		ed5 = (EditText)findViewById(R.id.main_activityEditText5);
		ed6 = (EditText)findViewById(R.id.main_activityEditText6);

		//重点！！！拿到最大和最小频率 part1 就完工了
		te1.setText(
			"CPU Name: "+cu.getCpuName()
			+"\n总核心数: "+getNumCores()+" Cores"
			+"\n最大频率: "+Integer.parseInt(cu.getCpuFrequence())/1000+"MHz"
			+"\n最小频率: "+Integer.parseInt(cu.getMinCpuFreq())/1000+"MHz"
		);
		
		
		mContext = getApplicationContext();
		cur = new CurCpu();
		cu = new cpuUtil();

		handler.post(task);
		readCpuInfo();

    }
	
	
	
	
	
	public void readCpuInfo(){
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							ed1.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"));
							ed2.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"));
							ed3.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"));
							ed4.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"));
							ed5.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"));
							ed6.setText(new shell().su("cat /sys/devices/system/cpu/cpufreq/interactive/target_loads"));			
						}
					});
			}
		}.start();	
	}
	
	
	
	//保存配置
	public void saveCpuInfo(View v){
		
		String cmd[]={
			"mount -o rw,remount /system"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/target_loads"
			,"echo '"+ed1.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"echo '"+ed2.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"echo '"+ed3.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"echo '"+ed4.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"echo '"+ed5.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"echo '"+ed6.getText().toString()+"' > /sys/devices/system/cpu/cpufreq/interactive/target_loads"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/target_loads"
		};
		new shell().execCommand(cmd,true);
		snack("配置已保存");
		readCpuInfo();
		
	}
	
	public void cpuInfo_625(View v){
		String cmd[]={
			"mount -o rw,remount /system"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"chmod 755 /sys/devices/system/cpu/cpufreq/interactive/target_loads"
			,"echo '18000 1680000:98000 1780000:138000' > /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"echo '38000' > /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"echo '94' > /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"echo '1380000' > /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"echo '18000' > /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"echo '64 980000:68 1380000:86 1680000:98' > /sys/devices/system/cpu/cpufreq/interactive/target_loads"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/above_hispeed_delay"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/boostpulse_duration"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/go_hispeed_load"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/hispeed_freq"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/min_sample_time"
			,"chmod 644 /sys/devices/system/cpu/cpufreq/interactive/target_loads"
		};
		new shell().execCommand(cmd,true);
		snack("尝试调教625");
		readCpuInfo();
	}
	
	
	

	public void cpuinfo(View v){
		Intent i = new Intent(MainActivity.this,cpuinfo.class);
		startActivity(i);
	}
	
	public void cpuctrl(View v){
		Intent a = new Intent(MainActivity.this,cpuCtrl.class);
		startActivity(a);
	}
	
	//菜单 
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.lookI:
				lookI();
				break;
        }
        return super.onOptionsItemSelected(item);
    }
	
	
	public String readSDFile(String fileName) throws IOException {    

        File file = new File(fileName);    
        FileInputStream fis = new FileInputStream(file);    
        int length = fis.available();   
		byte [] buffer = new byte[length];   
		fis.read(buffer); 
		String res = EncodingUtils.getString(buffer, "UTF-8");
		fis.close();       
		return res;    
	} 
	
	
	
	public void lookI(){
		String a = new shell().su("cat /sys/bus/cpu/devices/cpu0/cpufreq/scaling_governor");
		new AlertDialog.Builder(this)
			.setTitle("当前调整器")
			.setMessage(a)
			.setPositiveButton("知道了",null) 
			.show();
		}
	
	private String getTimes() {    
		long ut = SystemClock.elapsedRealtime() / 1000;    
		if (ut == 0) {    
			ut = 1;    
		}    
		int m = (int) ((ut / 60) % 60);    
		int h = (int) ((ut / 3600));    
		return h + "小时 "+m+"分钟";    
	}   
	
	
	
	
	
	//定时器循环获取cpu信息
	private Handler handler = new Handler();   
    private Runnable task = new Runnable() {  
        public void run() {   

			handler.postDelayed(this,1000);//设置循环时间，此处是5秒
			//取得当前时间
		
			if(Exists("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")){
				c0 = Integer.parseInt(cur.getCurCpu0())/1000+"MHz";
			}else{c0="离线";}
			
			if(Exists("/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq")){
				c1 = Integer.parseInt(cur.getCurCpu1())/1000+"MHz";
			}else{c1="离线";}
			
			if(Exists("/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq")){
				c2= Integer.parseInt(cur.getCurCpu2())/1000+"MHz";
			}else{c2="离线";}
			
			if(Exists("/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq")){
				c3= Integer.parseInt(cur.getCurCpu3())/1000+"MHz";
			}else{c3="离线";}
			
			
			if(Exists("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq")){
				c4= Integer.parseInt(cur.getCurCpu4())/1000+"MHz";
			}else{c4="离线";}

			if(Exists("/sys/devices/system/cpu/cpu5/cpufreq/scaling_cur_freq")){
				c5= Integer.parseInt(cur.getCurCpu5())/1000+"MHz";
			}else{c5="离线";}

			if(Exists("/sys/devices/system/cpu/cpu6/cpufreq/scaling_cur_freq")){
				c6= Integer.parseInt(cur.getCurCpu6())/1000+"MHz";
			}else{c6="离线";}

			if(Exists("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq")){
				c7= Integer.parseInt(cur.getCurCpu7())/1000+"MHz";
			}else{c7="离线";}
			
			
			
			te2.setText(
				"CPU 1: "+c0
				+"\nCPU 2: "+c1
				+"\nCPU 3: "+c2
				+"\nCPU 4: "+c3
				+"\nCPU 5: "+c4
				+"\nCPU 6: "+c5
				+"\nCPU 7: "+c6
				+"\nCPU 8: "+c7
			);
        }   
    };
	
	


//文件是否存在
	public boolean Exists(String fi){
		try{
			File f=new File(fi);
			if(!f.exists()){
				return false;
			}

		}catch (Exception e) {

			return false;
		}
		return true;
	}
	
	
	//总核心数
	private int getNumCores()
    {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter
        {
            @Override
            public boolean accept(File pathname)
            {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName()))
                {
                    return true;
                }
                return false;
            }
        }

        try
        {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e)
        {
            // Default to return 1 core
            return 1;
        }
    }
	
	
	public void toast(String i){
		Toast.makeText(getApplicationContext(),i,1).show();
	}
	public void snack(String a){
		Snackbar.make(getWindow().getDecorView(),a,Snackbar.LENGTH_LONG).show();
	}
	
}
