package dam.pspro;

import javax.sql.rowset.Joinable;

import com.sun.org.apache.bcel.internal.generic.NEW;

class GlobalVar {
	public static int c1 = 0;
	public static int c2 = 0;
	
	private static final Object mutex1 = new Object();
	private static final Object mutex2 = new Object();
	
	
	public static void inc1() {
		synchronized (mutex1) {
			c1++;
		}
	}
	
	public static void inc2() {
		synchronized (mutex2) {
			c2++;
		}
	}
}

class DosMutex extends Thread {
	
	@Override
	public void run() {
		GlobalVar.inc1();
		GlobalVar.inc2();
	}
}

public class HilosExclusiónMutua {

	public static void main(String[] args) throws InterruptedException {
		int n = Integer.parseInt(args[0]);
		DosMutex hilos[];
		System.out.println("Creando " + n + " hilos");
		
		hilos = new DosMutex[n];
		
		for (int i = 0; i < n; i++) {
			hilos[i] = new DosMutex();
			hilos[i].start();
		}
		
		for (int i = 0; i < n; i++) {
			hilos[i].join();
		}
		
		System.out.println("C1 = " + GlobalVar.c1);
		System.out.println("\r");
		System.out.println("C2 = " + GlobalVar.c2);
		
	}

}
