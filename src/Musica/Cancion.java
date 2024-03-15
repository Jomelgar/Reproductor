/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Musica;

import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static reproductor.Reproductor.thread;

/**
 *
 * @author jomel
 */
public class Cancion implements Serializable{
    public int size;
    Music musica;
    String autor;
    String cancion;
    ImageIcon imagen;
    long duracion;
    String tipo;
    Cancion siguiente;
    JPanel form;
            
    public Cancion(int file, int image, String autor, String cancion, String tipo){
        size = 1;
        siguiente = null;
        this.tipo = tipo;
        this.autor = autor;
        this.cancion = cancion;
        this.duracion = duracion;
        form = new Panel(this);
        musica = new Music(new File("src/Musica/"+ Integer.toString(file)+ ".wav"));
        imagen = new ImageIcon(getClass().getResource("/Musica/"+ Integer.toString(image)+".jpg"));
        
        switch (file){
            case 0:
                duracion = 67*1000;
                break;
            case 1:
                duracion = 153 * 1000;
                break;
            default:
                duracion = 225 * 1000;
                break;
        }
    }
    
    public void add(Cancion dato){
        Cancion tmp = this;
        while(tmp.siguiente != null){
            tmp = tmp.siguiente;
        }
        tmp.siguiente = dato;
        size++;
    }
    
    void run(){
        Cancion tmp = this;
            thread = tmp.musica;
            thread.start();       
    }
    
    void pause() throws InterruptedException{
        thread.wait();
    }
    
    void contin(){
        thread.notifyAll();
    }
    
    void close(){
        thread.stop();
    }
    
    public void printList(JPanel panel){
        Cancion tmp = this;
        for(int i =0; i < size; i++){
            tmp.form.setBounds(0,i*100,420,100);
            panel.add(tmp.form);
            panel.revalidate();
            tmp = tmp.siguiente;
        }
    }
}
