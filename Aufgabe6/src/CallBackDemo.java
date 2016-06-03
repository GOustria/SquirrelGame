
public class CallBackDemo {
	
	public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("foo");
            }
        };
 
        doSomething(r);
 
        doSomething(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("trallala");
                }
            }
        });
    }
 
    public static void doSomething(Runnable r) {
        System.out.println("before Execution");
        r.run();
        System.out.println("after Execution");
    }

}
