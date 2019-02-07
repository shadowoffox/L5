public class Thredin {


private static final int SIZE=10000000;
private static final int H=SIZE/2;
private static float[] arr = new float[SIZE];


public static void getSlowArr(){
for (int i=0;i<SIZE;i++){
    arr[i]=1;
}
long a = System.currentTimeMillis();
for (int i=0; i<SIZE;i++){
    arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}
    System.out.println(System.currentTimeMillis()-a);
}




public static void  getFastArr() throws InterruptedException {

    float arr1[] = new float[H];
    float arr2[] = new float[H];
    for (int i=0;i<SIZE;i++){
        arr[i]=1;
    }
    long a = System.currentTimeMillis();
    System.arraycopy(arr, 0, arr1, 0, H);
    System.arraycopy(arr, H, arr2, 0, H);
    System.out.println("Время разбива массива на 2" + (System.currentTimeMillis()-a));

   Thread arg =new Thread(new Runnable() {
        @Override
        public void run() {
            long a = System.currentTimeMillis();
            for (int i=0; i<H;i++){
                arr1[i]=(float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Просчет 1 подмассива " +(System.currentTimeMillis()-a));
        }
    });

   Thread arg2 = new Thread(new Runnable() {
        @Override
        public void run() {
            long a = System.currentTimeMillis();
            for (int i=0; i<H;i++){
                arr2[i]=(float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Просчет 2 подмассива " +(System.currentTimeMillis()-a));
        }
    });

    arg.start();
    arg.join();
    arg2.start();
    arg2.join();


        a = System.currentTimeMillis();

        System.arraycopy(arr1, 0, arr, 0, H);

        System.arraycopy(arr2, 0, arr, H, H);
        System.out.println("склейка " + (System.currentTimeMillis() - a));

    }


    public static void main(String[] args) throws InterruptedException {

        getSlowArr();
        System.out.println();
        getFastArr();

    }
}
