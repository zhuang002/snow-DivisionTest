/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package divisiontest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class DivisionTest {

    /**
     * @param args the command line arguments
     */
    static long M,N;
    static long[] primes={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
    static ArrayList<Long> primesList=new ArrayList();
    static ArrayList<ArrayList<Long>> primeSets=new ArrayList();
    static HashMap<String,ArrayList<Long>> cache=new HashMap();
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        N=sc.nextLong();
        M=sc.nextLong();
        Long result=0L;
        
        initPrimeSets();
        boolean add=true;
        for (ArrayList<Long> primeSet:primeSets) {
            if (add) result+=getSetCount(primeSet);
            else result-=getSetCount(primeSet);
            add=!add;
        }
        System.out.println(result);
    }

    private static void initPrimeSets() {
        for (Long num:primes) {
            if (num<=N) primesList.add(num);
            else break;
        }
        
        for (long i=1;i<=primesList.size();i++) {
            ArrayList<Long> al=getProductSet(primesList,primesList.size(),i);
            if (!al.isEmpty())
                primeSets.add(al);
        }
    }
    
    private static long getSetCount(ArrayList<Long> set) {
        long result=0;
        for (Long ele : set) {
            result+=M/ele;
        }
        return result;
    }


    private static ArrayList<Long> getProductSet(ArrayList<Long> list, int size, Long num) {
        ArrayList<Long> ret=new ArrayList();
        
        String key=size+"-"+num;
        if (cache.containsKey(key)) return cache.get(key);
        if (num==1) {
            for (int i=0;i<size;i++)
                ret.add(list.get(i));
            cache.put(key,ret);
            return ret;
        }
        
        if (size==0) {
            return ret;
        }
        
        if (size==num) {
            long a=1;
            for (int i=0;i<size;i++) {
                a*=list.get(i);
                if (a>M) break;
            }
            if (a<=M)
                ret.add(a);
            cache.put(key,ret);
            return ret;
        }
        
        ret.addAll(getProductSet(list,size-1,num));
        ArrayList<Long> al=getProductSet(list,size-1,num-1);
        long last=list.get(size-1);
        for (long ele:al) {
            long a=ele*last;
            if (a<=M)
                ret.add(a);
        }
        cache.put(key,ret);
        return ret;
    }
}
