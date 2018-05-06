package com.dabai.KernelWork;
import android.support.v7.app.*;
import android.os.*;
import android.content.*;
import android.preference.*;
import android.view.*;
import android.support.design.widget.*;
import java.io.*;
import org.apache.http.util.*;
import android.widget.*;
import java.util.regex.*;

public class cpuCtrl extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
{

	Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8;

	Context mContext;

	int corenum;
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_ctrl_activity);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mContext = getApplicationContext();
		corenum=getNumCores();
		
		sw1 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch0);
		sw2 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch1);
		sw3 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch2);
		sw4 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch3);
		sw5 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch4);
		sw6 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch5);
		sw7 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch6);
		sw8 = (Switch)findViewById(R.id.cpu_ctrl_activitySwitch7);
		
		
		sw1.setOnCheckedChangeListener(this);
		sw2.setOnCheckedChangeListener(this);
		sw3.setOnCheckedChangeListener(this);
		sw4.setOnCheckedChangeListener(this);
		sw5.setOnCheckedChangeListener(this);
		sw6.setOnCheckedChangeListener(this);
		sw7.setOnCheckedChangeListener(this);
		sw8.setOnCheckedChangeListener(this);
		

		handler.postDelayed(task,500);
		
    }

	
	
	//菜单 
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cpuctrl, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	
	
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
	 switch (item.getItemId()) {  
            case android.R.id.home:  
                finish();  
                break;
			
			case R.id.cpu_1:
				cpuMode(0);
				break;
				
			case R.id.cpu_3:
				cpuMode(2);
				break;
				
			case R.id.cpu_5:
				cpuMode(4);
				break;
				
			case R.id.cpu_8:
				cpuMode(7);
				break;		
        }  
        return true;  
    }
	
	public void stopSys(View v){
		String g[]={
			"chmod 444 /sys/devices/system/cpu/cpu0/online"
			,"chmod 444 /sys/devices/system/cpu/cpu1/online"
			,"chmod 444 /sys/devices/system/cpu/cpu2/online"
			,"chmod 444 /sys/devices/system/cpu/cpu3/online"
			,"chmod 444 /sys/devices/system/cpu/cpu4/online"
			,"chmod 444 /sys/devices/system/cpu/cpu5/online"
			,"chmod 444 /sys/devices/system/cpu/cpu6/online"
			,"chmod 444 /sys/devices/system/cpu/cpu7/online"	
			};
		new shell().execCommand(g,true);	
		snack("已经尝试阻止了");
	}
	
	
	
	
	
	public void cpuMode(final int i){
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作
				for(int a=corenum;a>=0;a--){
					if(i<a){
						offLine(a);
					}
					else{
						onLine(a);
					}	
				}
				
				try
				{
					Thread.sleep(1700);
				}
				catch (InterruptedException e)
				{}
				snack("已保留" + (i+1) + "个核心 - 操作完毕");
	
			}
		}.start();
	}
	
	
	//总核心数
	public int getNumCores()
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
	
	
	
	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		switch (p1.getId()){  
            case R.id.cpu_ctrl_activitySwitch0:    
                if(!p2){snack("你不能让这个核心离线"); } 
                break; 
			case R.id.cpu_ctrl_activitySwitch1:    
				if(p2){onLine(1);}else{offLine(1);}
                break; 
			case R.id.cpu_ctrl_activitySwitch2:    
				if(p2){onLine(2);}else{offLine(2);}
                break; 	
			case R.id.cpu_ctrl_activitySwitch3:    
				if(p2){onLine(3);}else{offLine(3);}
                break; 
			case R.id.cpu_ctrl_activitySwitch4:    
				if(p2){onLine(4);}else{offLine(4);}
                break; 	
			case R.id.cpu_ctrl_activitySwitch5:    
				if(p2){onLine(5);}else{offLine(5);}
                break; 
			case R.id.cpu_ctrl_activitySwitch6:    
				if(p2){onLine(6);}else{offLine(6);}
                break; 	
			case R.id.cpu_ctrl_activitySwitch7:    
				if(p2){onLine(7);}else{offLine(7);}
                break; 		
        }  
	}


	
	//定时器
	private Handler handler = new Handler();   
    private Runnable task = new Runnable() {  
        public void run() {   
			handler.postDelayed(this,2000);//设置循环时间，此处是5秒
		
			lookCheck();
			
		}};
	
	
	
	//获取cpu运行状态
	public void lookCheck()
	{

		if (lookCore(0)){sw1.setChecked(true);}
		else{sw1.setChecked(false);}
		
		if (lookCore(1)){sw2.setChecked(true);}
		else{sw2.setChecked(false);}
		
		if (lookCore(2)){sw3.setChecked(true);}
		else{sw3.setChecked(false);}
		
		if (lookCore(3)){sw4.setChecked(true);}
		else{sw4.setChecked(false);}
		
		if (lookCore(4)){sw5.setChecked(true);}
		else{sw5.setChecked(false);}

		if (lookCore(5)){sw6.setChecked(true);}
		else{sw6.setChecked(false);}

		if (lookCore(6)){sw7.setChecked(true);}
		else{sw7.setChecked(false);}

		if (lookCore(7)){sw8.setChecked(true);}
		else{sw8.setChecked(false);}
		
	}


	//查看核心运行状态
	public boolean lookCore(int i)
	{
		if (Exists("/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_cur_freq"))
		{
			return true;
		}
		else
		{
			return false;	
		}
	}


	//开启核心
	public void onLine(final int i)
	{
		new Thread() {
			@Override
			public void run() {
				super.run();
				String c[]={
					"chmod 755 /sys/devices/system/cpu/cpu"+i+"/online"
					,"echo \"1\" > /sys/devices/system/cpu/cpu"+i+"/online"
					,"chmod 444 /sys/devices/system/cpu/cpu"+i+"/online"};
				new shell().execCommand(c,true);
			}
		}.start();	
	}
	
	//关闭核心
	public void offLine(final int i)
	{
		new Thread() {
			@Override
			public void run() {
				super.run();
				String c[]={
					"chmod 755 /sys/devices/system/cpu/cpu"+i+"/online"
					,"echo \"0\" > /sys/devices/system/cpu/cpu"+i+"/online"
					,"chmod 444 /sys/devices/system/cpu/cpu"+i+"/online"};
				new shell().execCommand(c,true);
			}
		}.start();	
	}

	public String readSDFile(String fileName) throws IOException
	{    

        File file = new File(fileName);    
        FileInputStream fis = new FileInputStream(file);    
        int length = fis.available();   
		byte [] buffer = new byte[length];   
		fis.read(buffer); 
		String res = EncodingUtils.getString(buffer, "UTF-8");
		fis.close();       
		return res;    
	} 

	//删除高通625温控
	public void del625WK(View v)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(cpuCtrl.this);
		//    指定下拉列表的显示数据
		final String[] cities = {"删除温控", "恢复温控"};
		//    设置列表选择项
		builder.setItems(cities, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{

					switch (cities[which])
					{
						case "删除温控":
							Snackbar.make(getWindow().getDecorView(), "确认删除嘛", Snackbar.LENGTH_LONG)
								.setAction("删除", new View.OnClickListener() {
									@Override
									public void onClick(View v)
									{
										String del[]={"mount -o rw,remount /system"
											,"mkdir /sdcard/CpuCtrl"
											,"cp -f /system/etc/thermal-engine-cn.conf /sdcard/CpuCtrl/thermal-engine-cn.conf"
											,"cp -f /system/etc/thermal-engine.conf /sdcard/CpuCtrl/thermal-engine.conf"
											,"cp -f /vendor/bin/thermal-engine /sdcard/CpuCtrl/thermal-engine"
											,"rm -f /system/etc/thermal-engine-cn.conf"
											,"rm -f /system/etc/thermal-engine.conf"
											,"rm -f /vendor/bin/thermal-engine"
											,"mount -o ro,remount /system"};
										new shell().execCommand(del, true);
									}
								})
								.show();	
							break;
						case "恢复温控":
							if (Exists("/sdcard/CpuCtrl/thermal-engine-cn.conf"))
							{
								Snackbar.make(getWindow().getDecorView(), "确认恢复嘛", Snackbar.LENGTH_LONG)
									.setAction("恢复", new View.OnClickListener() {
										@Override
										public void onClick(View v)
										{
											String del[]={"mount -o rw,remount /system"
												,"cp -f /sdcard/CpuCtrl/thermal-engine-cn.conf /system/etc/thermal-engine-cn.conf"
												,"cp -f /sdcard/CpuCtrl/thermal-engine.conf /system/etc/thermal-engine.conf"
												,"cp -f /sdcard/CpuCtrl/thermal-engine /vendor/bin/thermal-engine"
												,"mount -o ro,remount /system"};
											new shell().execCommand(del, true);
										}
									})
									.show();			
							}
							else
							{
								Snackbar.make(getWindow().getDecorView(), "没有找到备份", Snackbar.LENGTH_SHORT).show();
							}
							break;
					}  
				}
			});
		builder.show();		
	}

	public void toast(String i)
	{
		Toast.makeText(getApplicationContext(), i, 1).show();
	}
	public void snack(String a)
	{
		Snackbar.make(getWindow().getDecorView(), a, Snackbar.LENGTH_LONG).show();
	}
//文件是否存在
	public boolean Exists(String fi)
	{
		try
		{
			File f=new File(fi);
			if (!f.exists())
			{
				return false;
			}

		}
		catch (Exception e)
		{

			return false;
		}
		return true;
	}
}
	

