
public class WAIT {
	public static void sleep() {
		long t=System.currentTimeMillis();
		
		while(true) {
			long t1=System.currentTimeMillis();
			if((t1-t)/1000.0000>=0.8)
				break;
		}
	}
}
