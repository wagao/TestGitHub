package javaapplication1;

public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		getName(): It is used for Obtaining a thread�s name
//		getPriority(): Obtain a thread�s priority
//		isAlive(): Determine if a thread is still running
//		join(): Wait for a thread to terminate
//		run(): Entry point for the thread
//		sleep(): suspend a thread for a period of time
//		start(): start a thread by calling its run() method

	   
    RunnableDemo R1 = new RunnableDemo( "runnable-1");
    R1.start();
    
    RunnableDemo R2 = new RunnableDemo( "runnable-2");
    R2.start();
    

    threadDemo T3 = new threadDemo( "Thread-~~~~");
    T3.start();
    
    threadDemo T4 = new threadDemo( "-------");
    T4.start();
	}

}
