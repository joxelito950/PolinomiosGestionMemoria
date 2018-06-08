/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomios;
import javax.swing.*;
/**
 *
 * @author sala311
 */
public class Lista {
    private Nodo cab;

    public Lista() {
        cab=null;
    }
    
    public Nodo getCab(){
        return cab;
    }
    
    public void mostrar()
    {
        if(cab==null)
            JOptionPane.showMessageDialog(null, "El polinomio esta vacio", "Falta Informacion", JOptionPane.INFORMATION_MESSAGE);
        String imprimir="";
        Nodo aux=cab;
        while(aux!=null)
        {
                if(aux.getExp()>0 && aux!=cab)
                    imprimir+= " + "+aux.getCoef()+"x^"+(Integer.toString(aux.getExp()));
                else
                    imprimir+= aux.getCoef()+"x^"+(Integer.toString(aux.getExp()));
        }
        JOptionPane.showMessageDialog(null, imprimir,"Polinomio",JOptionPane.PLAIN_MESSAGE);
    }
    
    public int getTamaño()
    {
        int n=0;
        Nodo aux=cab;
        while(aux!=null){
            n++;
            aux=aux.getLiga();
        }
        return n;
    }
    
    public float evaluar(float x)
    {
        float res=0;
        Nodo aux=cab;
        while(aux!=null){
            res=res+aux.getCoef()+((float)Math.pow(x, aux.getExp()));
            aux=aux.getLiga();
        }
        return res;
    }
    
    
    public void almacenarTerm(float coef,int exp)
    {
        Nodo x,ant = null,p=cab;
        while(p!=null && p.getExp()>exp){
            ant=p;
            p=p.getLiga();
        }
        if(p!=null && p.getExp()==exp)
            JOptionPane.showMessageDialog(null, "Ya existe un termino con ese exponente", "No se puede insertar", JOptionPane.INFORMATION_MESSAGE);
        else{
               x=new Nodo(coef,exp);
               x.setLiga(p);
               if(p==cab)
                   cab=x;
               else
                   ant.setLiga(x);
        }
    }
    
    public void insertarTerm(float coef,int exp)
    {
        Nodo x,ant=null,p=cab;
        while(p!=null && p.getExp()>exp){
            ant=p;
            p=p.getLiga();
        }
        if(p!=null && p.getExp()==exp){
            if(p.getCoef()+coef!=0)
                p.setCoef(p.getCoef()+coef);
            if(p==cab)
                cab=cab.getLiga();
            else
                ant.setLiga(p.getLiga());
        }
        else
        {
            x=new Nodo(coef,exp);
            x.setLiga(p);
            if(p==cab)
                cab=x;
            else
                ant.setLiga(x);
        }
    }
    
    public void eliminarTerm()
    {
        
    }
    
    public Lista sumar(Lista b)
    {
        Nodo p=cab,q=b.getCab();
        Lista r=new Lista();
        while(p!=null && q!= null){
            if(p.getExp()==q.getExp()){
                r.insertarTerm(p.getCoef()+q.getCoef(),p.getExp());
                p=p.getLiga();
                q=q.getLiga();
            }
            else{
                if(p.getExp()>q.getExp()){
                    r.insertarTerm(p.getCoef(), p.getExp());
                    p=p.getLiga();
                }
                else{
                    r.insertarTerm(q.getCoef(), q.getExp());
                    q=q.getLiga();
                }
            }
        }
        while(p!=null){
            r.insertarTerm(p.getCoef(), p.getExp());
            p=p.getLiga();
        }
        while(q!=null){
            r.insertarTerm(q.getCoef(), q.getExp());
            q=q.getLiga();
        }
        return r;
    }
    
    public Lista multiplicar(Lista b)
    {
        Nodo p=cab, q=b.getCab();
        if(p==null || q==null)
            JOptionPane.showMessageDialog(null, "Uno de los Polinomios esta vacio", "Falta Informacion", JOptionPane.INFORMATION_MESSAGE);
        else{
            Lista r=new Lista();
            while(q!=null){
                while(p!=null){
                    r.insertarTerm(p.getCoef()*q.getCoef(), p.getExp()+q.getExp());
                    p=p.getLiga();
                }
                q=q.getLiga();
            }
            return r;
        }
        return null;
    }

    public Lista dividir(Lista b)
    {
        
        return null;
    }
    
    public void comparar(Lista b)
    {
        
    }
    
    public Lista hacerCopia()
    {
        Nodo aux=cab;
        Lista copia=new Lista();
        while(aux!=null)
        {
            copia.almacenarTerm(aux.getCoef(), aux.getExp());
            aux=aux.getLiga();
        }
        return copia;
    }
    
}
