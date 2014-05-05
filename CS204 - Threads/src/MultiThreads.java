public class MultiThreads extends Thread {
   public final static int NUM_THREADS = 6;

   public void run() {
      System.out.println(getName());
   }



   public static void main(String args[]) {
      Thread newThread = null;
      String name = "Thread #";
      for(int i=0; i< NUM_THREADS; i++) {
         newThread = new MultiThreads();
         newThread.setName(name+i);
         newThread.start();
      }
   }
}
