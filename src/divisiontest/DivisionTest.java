/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package divisiontest;

import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class DivisionTest {

    /**
     * @param args the command line arguments
     */
    static int M,N;
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();
        int result=0;
        for (int i=2;i<=M;i++) {
            if (isDevisible(i,N)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean isDevisible(int num, int N) {
        for (int i=2;i<=N;i++) {
            if (num%i==0) return true;
        }
        return false;
    }
    
    
}
