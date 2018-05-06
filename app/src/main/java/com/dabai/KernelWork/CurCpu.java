package com.dabai.KernelWork;
import java.io.*;

public class CurCpu
{

	
	//实时频率
	public static String getCurCpu0() {  
	
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
		
	}  
	
	

	//实时频率
	public static String getCurCpu1() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  
	
	

	//实时频率
	public static String getCurCpu2() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  
	
	
	
	

	//实时频率
	public static String getCurCpu3() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  



	//实时频率
	public static String getCurCpu4() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  



	//实时频率
	public static String getCurCpu5() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu5/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  
	
	
	
	

	//实时频率
	public static String getCurCpu6() {  
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu6/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return result;  
	}  



	//实时频率
	public static String getCurCpu7() {  
		
			
		String result = "N/A";  
		try {  
			FileReader fr = new FileReader(  
				"/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			result = text.trim();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		
		return result;  
		
		
	}  

	
}
