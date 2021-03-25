import java.util.*;
import java.io.*;
public class Radix_LSD {
    static String ifile = "f.txt";
    static String ofile = "g.txt";
    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br;

        FileWriter w;
        File file;

        int n = 1000;
        int size = 0;
        
        String infile = reader.readLine();
        String outfile = reader.readLine();
        String st;

        try {
            file = new File(infile); 
            br = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            file = new File(ifile);
            br = new BufferedReader(new FileReader(file));
        }
        try {
            w = new FileWriter(outfile);
        }
        catch (Exception e) {
            w = new FileWriter(ofile);
        }
        String[][] S = new String[n][21];
        while ((st = br.readLine()) != null) {
            for (int i = 0; i < 21; i++) {
                if (i > st.length() - 1)
                    S[size][i] = " ";
                else {
                    S[size][i] = String.valueOf(st.charAt(i));
                }
            }
            size++;
        }
        br.close();
        n = size;
        
        int[] P = new int[n];
        for (int i = 0; i < n; i++) { P[i] = i; }
        P = radix(S, P, n);
        // write to output file
        
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 21; j++)
			    w.write(S[P[i]][j]);
            w.write("\n");
        }
        w.close();
    }
    static int[] radix(String S[][], int P[],int n){
        int[][] bucket = new int[91][n];
        int[] count = new int[91];
        for (int i = 20; i >= 0; i--){
            for (int y = 0; y < 91; y++){count[y] = 0; bucket[y] = new int[n];}
            for (int j = 0; j < P.length; j++){
                int value = (int)S[P[j]][i].charAt(0);
                bucket[value][count[value]] = P[j];
                count[value]++;
            }
            int k = 0;
            while (k<n){
                for (int j = 0; j < 91; j++){
                    int temp = count[j];
                    for (int l = 0; l < temp; l++){
                        P[k] = bucket[j][l];
                        k++;
                    }
                }
            }
        }
        return P;
    }
    static void print2dArray(String arr[][], int n)
	{
		for (int i = 0; i < n; i++){
            for (int j = 0; j < 21; j++)
			    System.out.print(arr[i][j]);
            System.out.print("\n");
        }  
    }
    static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.println(arr[i] + " ");
	}
    
}
