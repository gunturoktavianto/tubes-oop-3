package Game;


public class Sun {
    private static int sun = 25;

    public static void generateSun() {
        while (true) {
            try {
                // Menghasilkan interval acak antara 5 hingga 10 detik (5000 hingga 10000 milidetik)
                int delay = 5000 + (int) (Math.random() * 5000);
                // System.out.println(delay);
                // Tidur selama interval acak
                Thread.sleep(delay);

                // Menambah nilai sun sebesar 25
                sun += 25;
                System.out.println("Sun: " + sun);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSun()
    {
        return sun;
    }

    public static void setSun(int s)
    {
        sun = s;
    }
}
