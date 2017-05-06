/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.Arrays;

/**
 *
 * @author Aluno
 */
public class Array {
    private int size;
    private int array[];

    public Array(int size, int array[]) {
        this.size = size;
        this.array = array;
    }
    
    public int at(int index){
        int a = this.array[index];
        return a;
    }
    public int size(){
        this.size = array.length;
        return this.size;
    }
    public void show(){
        System.out.println(this.toString());
        
    }
    public void set(int index, int value){
        this.array[index] = value;
    
    }
    public Array sort(){  //bubble sort
        boolean troca = true;
        int j = 0;
        int tmp;
        Array a = new Array(this.size(), new int[this.size()]);
        for(int i=0; i<a.size();i++)
            a.set(i, this.array[i]);
        while (troca) {
            troca = false;
            j++;
            for (int i = 0; i < a.array.length - j; i++) {
                if (a.array[i] > a.array[i + 1]) {
                    tmp = a.array[i];
                    a.array[i] = a.array[i + 1];
                    a.array[i + 1] = tmp;
                    troca = true;
                }
            }
        }
        return a;
    }
    public Array add(int value){
        int s = this.size();
        Array a = new Array((s+1), new int[s+1]);
        for(int i=0;i<s;i++){
            a.set(i,this.at(i));
        }
        a.set(s,value);
//        this.array= a.array;
        return a;
    }
    public Array add(Array array){
        int s1 = this.size();
        int s2 = array.size();
        Array a = new Array((s1+s2), new int[s1+s2]);
        for(int i=0;i<s1&i<s2;i++){
            a.set(i,this.at(i));
            a.set(s1+i,array.at(i));
        }for(int i=s2;i<s1;i++){
            a.set(i,this.at(i));
        }for(int i=s1;i<s2;i++){
            a.set(i+i,array.at(i));
        }
//        this.array= a.array;
        return a;
    }
    public String toString(){
        return Arrays.toString(array);
    }
    
}
