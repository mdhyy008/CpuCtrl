package com.dabai.KernelWork;
import java.io.*;

public class cpuUtil
{
	
	public static String getCpuName() {  
		try {  
			FileReader fr = new FileReader("/proc/cpuinfo");  
			BufferedReader br = new BufferedReader(fr);  
			String text = br.readLine();  
			String[] array = text.split(":\\s+", 2);  
			for (int i = 0; i < array.length; i++) {  
			}  
			return array[1];  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return null;  
	}  
	
	
	//最小频率
	public static String getMinCpuFreq() {  
		String result = "";  
		ProcessBuilder cmd;  
		try {  
			String[] args = { "/system/bin/cat",  
				"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" };  
			cmd = new ProcessBuilder(args);  
			Process process = cmd.start();  
			InputStream in = process.getInputStream();  
			byte[] re = new byte[24];  
			while (in.read(re) != -1) {  
				result = result + new String(re);  
			}  
			in.close();  
		} catch (IOException ex) {  
			ex.printStackTrace();  
			result = "N/A";  
		}  
		return result.trim();  
    }  


	//最大频率
	public static String getCpuFrequence() {  
        ProcessBuilder cmd;  
        try {  
            String[] args = { "/system/bin/cat",  
				"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };  
            cmd = new ProcessBuilder(args);  

            Process process = cmd.start();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
														   process.getInputStream()));  
            String line = reader.readLine();  
            return line;
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
        return null;  
    }  
}
